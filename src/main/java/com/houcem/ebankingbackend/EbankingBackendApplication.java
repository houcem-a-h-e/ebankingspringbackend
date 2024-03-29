package com.houcem.ebankingbackend;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.houcem.ebankingbackend.entities.AccountOperation;
import com.houcem.ebankingbackend.entities.CurrentAccount;
import com.houcem.ebankingbackend.entities.Customer;
import com.houcem.ebankingbackend.entities.SavingAccount;
import com.houcem.ebankingbackend.enums.AccountStatus;
import com.houcem.ebankingbackend.enums.OperationType;
import com.houcem.ebankingbackend.repositories.AccountOperationRepository;
import com.houcem.ebankingbackend.repositories.BankAccountRepository;
import com.houcem.ebankingbackend.repositories.CustomerRepository;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
            AccountOperationRepository accountOperationRepository) {
return args -> {
Stream.of("Hassan", "Yassine", "Aicha").forEach(name -> {
Customer customer = new Customer();
customer.setName(name);
customer.setEmail(name+"@gmail.com");
System.out.print(customer.toString());
customerRepository.save(customer);
customerRepository.findAll().forEach(cust->{
	CurrentAccount currentAccount=new CurrentAccount();
	currentAccount.setId(UUID.randomUUID().toString());
	currentAccount.setBalance(Math.random()*90000);
	currentAccount.setCreatedAt(new Date());
	currentAccount.setStatus(AccountStatus.CREATED);
	currentAccount.setCustomer(customer);
	currentAccount.setOverDraft(9000);
	bankAccountRepository.save(currentAccount);
	
	SavingAccount savingAccount=new SavingAccount();
	savingAccount.setId(UUID.randomUUID().toString());
	savingAccount.setBalance(Math.random()*90000);
	savingAccount.setCreatedAt(new Date());
	savingAccount.setStatus(AccountStatus.CREATED);
	savingAccount.setCustomer(customer);
	savingAccount.setInterestRate(5.5);
	bankAccountRepository.save(savingAccount);
});
bankAccountRepository.findAll().forEach(acc->{
	for(int i=0;i<10;i++) {
		AccountOperation accountOperation= new AccountOperation();
		accountOperation.setOperationDate(new Date());
		accountOperation.setAmount(Math.random()*12000);
		accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
		accountOperation.setBankAccount(acc);
		accountOperationRepository.save(accountOperation);
		}
});
});
};
}

	
	
}
