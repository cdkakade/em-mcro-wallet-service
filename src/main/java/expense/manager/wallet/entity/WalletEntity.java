package expense.manager.wallet.entity;

import expense.manager.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "wallet")
public class WalletEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3334574155955632843L;

    @Column(nullable = false)
    private String name;

    private BigDecimal balance;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "user_id")
    private String userId;

}
