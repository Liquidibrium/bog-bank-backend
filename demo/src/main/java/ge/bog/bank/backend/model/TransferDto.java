package ge.bog.bank.backend.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDto implements Serializable {

    private String usernameFrom;
    private String usernameTo;
    private Long amount;

}
