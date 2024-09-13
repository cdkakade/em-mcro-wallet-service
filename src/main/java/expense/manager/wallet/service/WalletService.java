package expense.manager.wallet.service;

import expense.manager.common.dto.wallet.request.WalletRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface WalletService {

	List<WalletResponse> findAll();

	WalletResponse findById(String id, Jwt jwt);

	WalletResponse addWallet(WalletRequest wallet, Jwt jwt);

}
