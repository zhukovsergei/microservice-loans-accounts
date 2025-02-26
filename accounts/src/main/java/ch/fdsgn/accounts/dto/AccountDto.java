package ch.fdsgn.accounts.dto;

import ch.fdsgn.accounts.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Account222", description = "Account DTO")
@Data
public class AccountDto {

    @NotEmpty
    @Schema(description = "Account number", example = "998XXXXXXXXX")
    private Long accountNumber;

    @NotEmpty
    private String accountType;

    @NotEmpty
    private String branchAddress;


    public static AccountDto fromEntity(Account ent) {
        AccountDto dto = new AccountDto();
        dto.accountNumber = ent.getAccountNumber();
        dto.accountType = ent.getAccountType();
        dto.branchAddress = ent.getBranchAddress();
        return dto;
    }
}
