package ge.bog.bank.backend.entitiy;


import lombok.*;

import javax.persistence.*;
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
public class AccountEntity {

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
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user; // TODO change FK name

    @OneToMany(mappedBy = "toAcc")
    private Set<TransactionEntity> transactionEntityListTo = new HashSet<>();

    @OneToMany(mappedBy = "fromAcc")
    private Set<TransactionEntity> transactionEntityListFrom = new HashSet<>();

    public AccountEntity(String currency, UserEntity user) {
        this.currency = currency;
        this.user = user;
        this.balance = BigDecimal.ZERO;
    }
}
