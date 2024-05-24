package expense.manager.wallet.service;

import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;

import java.util.List;

public interface WalletService {

	List<WalletResponse> findAll();

	WalletResponse findById(String id);

	WalletResponse save(WalletRequest wallet);

}
