package expense.manager.wallet.service.impl;

import expense.manager.common.dto.currency.response.CurrencyResponse;
import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import expense.manager.wallet.client.CurrencyClient;
import expense.manager.wallet.entity.WalletEntity;
import expense.manager.wallet.repository.WalletRepository;
import expense.manager.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository repository;

    @Autowired
    private CurrencyClient currencyClient;

    @Override
    public List<WalletResponse> findAll() {
        List<WalletEntity> walletEntities = repository.findAll();
        return new ModelMapper().map(walletEntities, new TypeToken<List<WalletResponse>>() {
        }.getType());
    }

    @Override
    public WalletResponse findById(String id, Jwt jwt) {
        String userId = jwt.getSubject();
        WalletEntity walletEntity = repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ModelMapper().map(walletEntity, WalletResponse.class);
    }

    @Override
    public WalletResponse addWallet(WalletRequest wallet, Jwt jwt) {
        ResponseEntity<CurrencyResponse> currencyResponse = currencyClient.findById(wallet.getCurrencyId());
        if (currencyResponse.getStatusCode().is2xxSuccessful() && Objects.nonNull(currencyResponse.getBody())) {
            String userId = jwt.getSubject();
            log.info("Currency id {} is valid", wallet.getCurrencyId());
            WalletEntity entityToSave = new ModelMapper().map(wallet, WalletEntity.class);
            entityToSave.setCurrencyId(currencyResponse.getBody().getId());
            entityToSave.setUserId(userId);
            entityToSave.setId(null);
            entityToSave = repository.save(entityToSave);
            return new ModelMapper().map(entityToSave, WalletResponse.class);
        } else
            log.info("Currency id {} is invalid, throwing BAD request", wallet.getCurrencyId());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Currency Id");
    }

}
