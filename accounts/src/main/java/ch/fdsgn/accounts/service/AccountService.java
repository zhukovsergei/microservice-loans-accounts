package ch.fdsgn.accounts.service;

import ch.fdsgn.accounts.dto.AccountDto;
import ch.fdsgn.accounts.dto.CustomerDto;
import ch.fdsgn.accounts.entity.Account;
import ch.fdsgn.accounts.entity.Customer;
import ch.fdsgn.accounts.exception.NotFoundException;
import ch.fdsgn.accounts.repository.AccountRepository;
import ch.fdsgn.accounts.repository.CustomerRepository;
import ch.fdsgn.accounts.exception.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    public void createAccForCustomer(CustomerDto dto) {

        Optional<Customer> existsCustomer = customerRepository.findByMobileNumber(dto.getMobileNumber());
        if (existsCustomer.isPresent()) {
            throw new DomainException("Customer already exists");
        }

        Customer customer = Customer.create(dto.getEmail(), dto.getName(), dto.getMobileNumber());
        customer = customerRepository.save(customer);

        Account account = Account.create(customer.getCustomerId(), 1000000000L + new Random().nextInt(900000000), Account.SAVINGS, Account.ADDRESS);
        accountRepository.save(account);

    }

    public Account fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new NotFoundException("Account not found")
        );

        return account;
    }

    public AccountDto updateAccForCustomer(AccountDto dto) {
        Account account = accountRepository.findByAccountNumber(dto.getAccountNumber());
        account.editByDto(dto);
        accountRepository.save(account);
        return AccountDto.fromEntity(account);
    }

    public void removeAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new NotFoundException("Account not found")
        );

        customerRepository.delete(customer);
        accountRepository.delete(account);
    }
}
