package expense.manager.wallet.repository;

import expense.manager.wallet.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {
    Optional<WalletEntity> findByIdAndUserId(String id, String userId);
}
