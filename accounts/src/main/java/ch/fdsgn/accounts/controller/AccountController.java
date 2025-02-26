package ch.fdsgn.accounts.controller;

import ch.fdsgn.accounts.dto.AccountDto;
import ch.fdsgn.accounts.dto.CustomerDto;
import ch.fdsgn.accounts.dto.MainInfoDto;
import ch.fdsgn.accounts.dto.ResponseDto;
import ch.fdsgn.accounts.entity.Account;
import ch.fdsgn.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD APIs for account", description = "Provide manage accounts")
@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private AccountService accountService;

    @Autowired
    private MainInfoDto mainInfoDto;

    @Operation(summary = "Create a new account for customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
    })
    @PostMapping("/create")
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto dto) {

        accountService.createAccForCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/fetch")
    public ResponseEntity<AccountDto> fetch(@NotEmpty @RequestParam String mobileNumber) {

        Account account = accountService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(AccountDto.fromEntity(account));
    }

    @PatchMapping("/update")
    public ResponseEntity<AccountDto> update(@Valid @RequestBody AccountDto dto) {

        AccountDto account = accountService.updateAccForCustomer(dto);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Boolean> remove(@NotEmpty @RequestParam String mobileNumber) {

        accountService.removeAccountByMobileNumber(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<MainInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mainInfoDto);
    }
}
