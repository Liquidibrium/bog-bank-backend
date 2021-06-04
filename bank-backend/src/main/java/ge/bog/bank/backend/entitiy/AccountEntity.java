package ge.bog.bank.backend.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.bog.bank.backend.model.AccountDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "account_seq",
            sequenceName = "account_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "account_seq"
    )
    @Column(name = "account_id",
            updatable = false)
    private Long accountId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    private UserEntity user; // TODO change FK name

    @OneToMany(mappedBy = "toAcc")
    @JsonIgnore
    private Set<TransactionEntity> transactionSetTo = new HashSet<>();

    @OneToMany(mappedBy = "fromAcc")
    @JsonIgnore
    private Set<TransactionEntity> transactionSetFrom = new HashSet<>();

    public AccountEntity(String currency, UserEntity user) {
        this.currency = currency;
        this.user = user;
    }

    public AccountEntity(AccountDto accountDto) {
        this.currency = accountDto.getCurrency();
        this.user = accountDto.getUser();
    }

    public void addTransactionTo(TransactionEntity transactionEntity) {
        transactionSetTo.add(transactionEntity);
    }

    public void addTransactionFrom(TransactionEntity transactionEntity) {
        transactionSetFrom.add(transactionEntity);
    }
}
