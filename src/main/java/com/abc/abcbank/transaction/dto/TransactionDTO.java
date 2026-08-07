package com.abc.abcbank.transaction.dto;

import com.abc.abcbank.account.dto.AccountDTO;
import com.abc.abcbank.enums.TransactionStatus;
import com.abc.abcbank.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private String description;
    private TransactionStatus status;

    @JsonBackReference
    private AccountDTO account;

    private String sourceAccount;
    private String destinationAccount;
}
