package ge.bog.bank.backend.entitiy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "transaction_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_seq"
    )
    @Column(name = "transaction_id",
            updatable = false, nullable = false)
    private Long transactionId;


    @ManyToOne
    @JoinColumn(name = "account_from",
            foreignKey = @ForeignKey(name = "transaction_acc_from_fk"),
            nullable = false)
    private AccountEntity fromAcc;


    @ManyToOne
    @JoinColumn(name = "account_to",
            foreignKey = @ForeignKey(name = "transaction_acc_to_fk"),
            nullable = false)
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
