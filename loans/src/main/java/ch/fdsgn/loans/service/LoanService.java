package ch.fdsgn.loans.service;

import ch.fdsgn.loans.dto.LoanDto;
import ch.fdsgn.loans.entity.Loan;
import ch.fdsgn.loans.exception.NotFoundException;
import ch.fdsgn.loans.repository.LoanRepository;
import ch.fdsgn.loans.exception.DomainException;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanService {

    private LoanRepository loansRepository;

    public void createLoan(String mobileNumber) {

        Optional<Loan> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new DomainException("Loan already registered with given mobileNumber " + mobileNumber);
        }

        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        Loan loan = Loan.create(
                mobileNumber,
                Long.toString(randomLoanNumber),
                Loan.HOME_LOAN,
                Loan.NEW_LOAN_LIMIT,
                0,
                Loan.NEW_LOAN_LIMIT
        );
        loansRepository.save(loan);
    }

    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Loan not found")
        );
        return LoanDto.fromEntity(loan);
    }


    /*public Account fetchAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );

        Account account = LoanRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new NotFoundException("Account not found")
        );

        return account;
    }

    public LoanDto updateAccForCustomer(LoanDto dto) {
        Account account = LoanRepository.findByAccountNumber(dto.getAccountNumber());
        account.editByDto(dto);
        LoanRepository.save(account);
        return LoanDto.fromEntity(account);
    }

    public void removeAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );

        Account account = LoanRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new NotFoundException("Account not found")
        );

        customerRepository.delete(customer);
        LoanRepository.delete(account);
    }*/
}
