package com.example.demo.entitiy;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "transaction_seq",
            sequenceName = "transaction_seq")
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_seq"
    )
    @Column(name = "id",
            updatable = false)
    private Long id;
    @Column(name = "from_user_id",
            nullable = false)
    private Long fromUserId;
    @Column(name = "to_user_id",
            nullable = false)
    private Long toUserId;
    @Column(name = "amount",
            nullable = false)
    private Long amount;
    @Column(name = "time",
            nullable = false)
    private LocalDateTime time;

    public TransactionEntity(Long fromUserId,
                             Long toUserId,
                             Long amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }
}
