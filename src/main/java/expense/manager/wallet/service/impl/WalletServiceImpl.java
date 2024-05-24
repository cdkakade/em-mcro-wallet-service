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
	public WalletResponse findById(String id) {
		WalletEntity walletEntity = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ModelMapper().map(walletEntity, WalletResponse.class);
	}

	@Override
	public WalletResponse save(WalletRequest wallet) {
		ResponseEntity<CurrencyResponse> currencyResponse = currencyClient.findById(wallet.getCurrencyId());
		if (currencyResponse.getStatusCode().is2xxSuccessful() && Objects.nonNull(currencyResponse.getBody())) {
			log.info("Currency id {} is valid",wallet.getCurrencyId());
			WalletEntity entityToSave = new ModelMapper().map(wallet, WalletEntity.class);
			entityToSave.setCurrencyId(currencyResponse.getBody().getId());
			entityToSave = repository.save(entityToSave);
			return new ModelMapper().map(entityToSave, WalletResponse.class);
		}else
			log.info("Currency id {} is invalid, throwing BAD request",wallet.getCurrencyId());
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Currency Id");
	}

}
