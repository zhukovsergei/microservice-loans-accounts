package ch.fdsgn.loans.entity;

import ch.fdsgn.loans.dto.LoanDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "loans")
public class Loan extends BaseEntity {

    public static final String  HOME_LOAN = "Home Loan";
    public static final int  NEW_LOAN_LIMIT = 1_00_000;
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Loan created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    public static Loan create(String mobileNumber, String loanNumber, String loanType,  int totalLoan, int amountPaid, int outstandingAmount) {
        Loan ent = new Loan();
        ent.setMobileNumber(mobileNumber);
        ent.setLoanNumber(loanNumber);
        ent.setLoanType(loanType);
        ent.setTotalLoan(totalLoan);
        ent.setAmountPaid(amountPaid);
        ent.setOutstandingAmount(outstandingAmount);

        return ent;
    }

    public void editByDto(LoanDto dto) {

//        this.accountNumber = dto.getAccountNumber();
//        this.accountType = dto.getAccountType();
//        this.branchAddress = dto.getBranchAddress();
    }
}
