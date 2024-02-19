package ru.caramba.balancechange.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.caramba.balancechange.exceptions.IncomeJsonValidationException;
import ru.caramba.balancechange.exceptions.InsufficientBalanceException;
import ru.caramba.balancechange.exceptions.WalletNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ControllerError> walletNotFound(Exception ex) {
        ControllerError controllerError = new ControllerError();
        controllerError.setTimestamp(LocalDateTime.now());
        controllerError.setError(ex.getMessage());
        controllerError.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(controllerError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncomeJsonValidationException.class)
    public ResponseEntity<ControllerError> incomeJsonNotValid(Exception ex) {
        ControllerError controllerError = new ControllerError();
        controllerError.setTimestamp(LocalDateTime.now());
        controllerError.setError(ex.getMessage());
        controllerError.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(controllerError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ControllerError> insufficientBalance(Exception ex) {
        ControllerError controllerError = new ControllerError();
        controllerError.setTimestamp(LocalDateTime.now());
        controllerError.setError(ex.getMessage());
        controllerError.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(controllerError, HttpStatus.NOT_ACCEPTABLE);
    }
}
