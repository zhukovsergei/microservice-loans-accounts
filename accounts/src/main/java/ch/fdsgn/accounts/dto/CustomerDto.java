package ch.fdsgn.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerDto {

    @NotEmpty
    @Length(min=3)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String mobileNumber;

//    private AccountDto account;
}
