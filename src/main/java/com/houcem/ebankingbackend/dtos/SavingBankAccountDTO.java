package com.houcem.ebankingbackend.dtos;
import lombok.Data;
import com.houcem.ebankingbackend.enums.AccountStatus;
import java.util.Date;
@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
