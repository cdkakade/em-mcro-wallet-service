package expense.manager.wallet.service.impl;

import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import expense.manager.wallet.entity.WalletEntity;
import expense.manager.wallet.repository.WalletRepository;
import expense.manager.wallet.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository repository;

    @Override
    public List<WalletResponse> findAll() {
        return null;
    }

    @Override
    public WalletResponse findById(String id) {
        return null;
    }

    @Override
    public WalletResponse save(WalletRequest wallet) {
        WalletEntity entityToSave = new ModelMapper().map(wallet, WalletEntity.class);
        entityToSave = repository.save(entityToSave);
        return new ModelMapper().map(entityToSave, WalletResponse.class);
    }
}
