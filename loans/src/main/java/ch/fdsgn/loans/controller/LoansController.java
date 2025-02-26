package ch.fdsgn.loans.controller;

import ch.fdsgn.loans.dto.LoanDto;
import ch.fdsgn.loans.dto.MainInfoDto;
import ch.fdsgn.loans.dto.ResponseDto;
import ch.fdsgn.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD APIs for loans", description = "Provide manage loan")
@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private LoanService loanService;

    @Autowired
    private MainInfoDto mainInfoDto;

    @Operation(summary = "Create a new loan")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestParam @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.create("201", HttpStatus.CREATED.toString()));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        LoanDto loansDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

//
//    @PatchMapping("/update")
//    public ResponseEntity<LoanDto> update(@Valid @RequestBody LoanDto dto) {
//
//        LoanDto account = loanService.updateAccForCustomer(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(account);
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<Boolean> remove(@NotEmpty @RequestParam String mobileNumber) {
//
//        loanService.removeAccountByMobileNumber(mobileNumber);
//
//        return ResponseEntity.status(HttpStatus.OK).body(true);
//    }

    @GetMapping("/contact-info")
    public ResponseEntity<MainInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mainInfoDto);
    }
}
