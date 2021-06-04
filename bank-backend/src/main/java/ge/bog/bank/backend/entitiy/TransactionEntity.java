package ge.bog.bank.backend.entitiy;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "transaction_seq",
            sequenceName = "transaction_seq")
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_seq"
    )
    @Column(name = "transaction_id",
            updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from", nullable = false)
    private AccountEntity fromAcc;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to", nullable = false)
    private AccountEntity toAcc;

    @Column(name = "amount",
            nullable = false)
    private BigDecimal amount;

    @Column(name = "time",
            nullable = false)
    private LocalDateTime time;

    public TransactionEntity(AccountEntity fromAcc, AccountEntity toAcc, BigDecimal amount) {
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

}
