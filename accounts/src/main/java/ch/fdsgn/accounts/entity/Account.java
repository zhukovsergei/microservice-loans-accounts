package ch.fdsgn.accounts.entity;

import ch.fdsgn.accounts.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "accounts")
public class Account extends BaseEntity {

    public static final String SAVINGS = "savings";
    public static final String ADDRESS = "some address";


    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;

    public static Account create(Long customerId, Long accountNumber, String accountType, String branchAddress) {
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBranchAddress(branchAddress);
//        account.setCreatedAt(LocalDateTime.now());
//        account.setCreatedBy("admin");
        return account;
    }

    public void editByDto(AccountDto dto) {

        this.accountNumber = dto.getAccountNumber();
        this.accountType = dto.getAccountType();
        this.branchAddress = dto.getBranchAddress();
    }
}
