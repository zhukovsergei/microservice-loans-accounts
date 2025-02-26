package ch.fdsgn.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {

    public DomainException(String msg) {
        super(msg);
    }
}
