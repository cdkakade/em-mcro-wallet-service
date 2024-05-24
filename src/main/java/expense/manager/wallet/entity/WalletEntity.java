package expense.manager.wallet.entity;

import expense.manager.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "wallet")
public class WalletEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3334574155955632843L;

    @Column(nullable = false)
    private String name;

    private BigDecimal balance;
}
