package com.example.demo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDto {

    private String usernameFrom;
    private String usernameTo;
    private Long amount;

}
