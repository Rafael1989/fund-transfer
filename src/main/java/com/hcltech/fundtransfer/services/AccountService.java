package com.hcltech.fundtransfer.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hcltech.fundtransfer.dtos.AccountResumeDto;
import com.hcltech.fundtransfer.dtos.TransferDto;
import com.hcltech.fundtransfer.entities.Account;
import com.hcltech.fundtransfer.entities.Customer;
import com.hcltech.fundtransfer.entities.Transaction;
import com.hcltech.fundtransfer.entities.TransactionCategory;
import com.hcltech.fundtransfer.entities.TransactionType;
import com.hcltech.fundtransfer.exceptions.TransferException;
import com.hcltech.fundtransfer.repositories.AccountRepository;
import com.hcltech.fundtransfer.repositories.TransactionRepository;

import jakarta.validation.Valid;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

	public List<AccountResumeDto> findAllAccountsResumeByCustomer(Customer currentCustomer) {
		List<Account> accounts = accountRepository.findByCustomer(currentCustomer);
		List<AccountResumeDto> accountResumeDtos = new ArrayList<>();
		for(Account account : accounts) {
			AccountResumeDto accountResumeDto = AccountResumeDto.builder()
				.iban(account.getIban())
				.accountType(account.getAccountType())
				.creationDate(account.getCreatedAt())
				.balance(account.getBalance()).build();
			accountResumeDtos.add(accountResumeDto);
		}
		return accountResumeDtos;
	}

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	public Account findByIbanAndCustomer(String iban, Customer currentCustomer) {
		return accountRepository.findByIbanAndCustomer(iban, currentCustomer);
	}

	public TransferDto transfer(TransferDto transferDto) {
		Account originAccount = checkOriginAccount(transferDto);
		Account destinationAccount = checkDestinationAccount(transferDto);
		BigDecimal totalBalance = originAccount.getBalance().add(originAccount.getLimitAccount());
		if(totalBalance.compareTo(transferDto.getAmount()) >= 0) {
			setBalance(transferDto, originAccount, destinationAccount);
			accountRepository.save(originAccount);
			accountRepository.save(destinationAccount);
			String description = transferDto.getOriginAccount() + " transfer to " + transferDto.getDestinationAccount() + " comments: " + transferDto.getComments();
			String descriptionDestinationAccount = transferDto.getOriginAccount() + " transfer to " + transferDto.getDestinationAccount() + " comments: " + transferDto.getComments();
			Transaction originAccountTransaction = buildTransaction(transferDto, originAccount, description, TransactionCategory.TRANSFER, TransactionType.DEBIT);
			Transaction destinationAccountTransaction = buildTransaction(transferDto, destinationAccount, descriptionDestinationAccount, TransactionCategory.TRANSFER, TransactionType.CREDIT);
			transactionRepository.save(originAccountTransaction);
			transactionRepository.save(destinationAccountTransaction);
		}else {
			throw new TransferException("You don't have enough money to make this transfer. You just have: " + totalBalance);
		}
        return transferDto;
	}

	private Transaction buildTransaction(TransferDto transferDto, Account account, String description, TransactionCategory transactionCategory, TransactionType transactionType) {
		Transaction originAccountTransaction = Transaction.builder()
			.description(description)
			.category(transactionCategory)
			.type(transactionType)
			.amount(transferDto.getAmount())
			.valuationDate(transferDto.getToday())
			.account(account).build();
		return originAccountTransaction;
	}

	private void setBalance(TransferDto transferDto, Account originAccount, Account destinationAccount) {
		if(originAccount.getBalance().compareTo(transferDto.getAmount()) >= 0) {
			originAccount.setBalance(originAccount.getBalance().subtract(transferDto.getAmount()));
			destinationAccount.setBalance(destinationAccount.getBalance().add(transferDto.getAmount()));
		}else {
			originAccount.setBalance(originAccount.getBalance().subtract(transferDto.getAmount()));
			destinationAccount.setBalance(destinationAccount.getBalance().add(transferDto.getAmount()));
		}
	}

	private Account checkDestinationAccount(TransferDto transferDto) {
		Account destinationAccount = accountRepository.findByIban(transferDto.getDestinationAccount());
		if(destinationAccount == null) {
        	throw new TransferException("Destination account not found.");
        }
		return destinationAccount;
	}

	private Account checkOriginAccount(TransferDto transferDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer currentCustomer = (Customer) authentication.getPrincipal();
        Account originAccount = findByIbanAndCustomer(transferDto.getOriginAccount(), currentCustomer);
        if(originAccount == null) {
        	throw new TransferException("Origin account not found for this customer.");
        }
		return originAccount;
	}

	public Optional<Account> findById(@Valid Account account) {
		return accountRepository.findById(account.getIban());
	}

}
