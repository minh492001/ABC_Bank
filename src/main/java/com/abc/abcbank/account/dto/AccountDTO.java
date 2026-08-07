package com.abc.abcbank.account.dto;

import com.abc.abcbank.auth_users.dto.UserDTO;
import com.abc.abcbank.enums.AccountStatus;
import com.abc.abcbank.enums.AccountType;
import com.abc.abcbank.enums.Currency;
import com.abc.abcbank.transaction.dto.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;

    @JsonBackReference // this will not be added to the AccountDTO
    private UserDTO user;

    private Currency currency;
    private AccountStatus status;

    @JsonManagedReference // helps avoid infinite recursion by ignoring the AccountDTO while calling TransactionDTO
    private List<TransactionDTO> transactions;

    private LocalDateTime closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
