package ch.fdsgn.accounts.service;

import ch.fdsgn.accounts.dto.AccountDto;
import ch.fdsgn.accounts.dto.CustomerDetailsDto;
import ch.fdsgn.accounts.dto.LoanDto;
import ch.fdsgn.accounts.entity.Account;
import ch.fdsgn.accounts.entity.Customer;
import ch.fdsgn.accounts.exception.DomainException;
import ch.fdsgn.accounts.repository.AccountRepository;
import ch.fdsgn.accounts.repository.CustomerRepository;
import ch.fdsgn.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersService {

    private AccountRepository accountsRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;

    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new DomainException("Error with customer")
        );
        Account accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new DomainException("Error with account")
        );

        ResponseEntity<LoanDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);

        CustomerDetailsDto customerDetailsDto = CustomerDetailsDto.createForCustomersService(
                customer,
                AccountDto.fromEntity(accounts),
                loansDtoResponseEntity.getBody()

        );
        customerDetailsDto.setAccountsDto(AccountDto.fromEntity(accounts));

//        ResponseEntity<LoanDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
//        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());


        return customerDetailsDto;

    }
}
