package ru.caramba.balancechange.exceptions;

public class IncomeJsonValidationException extends RuntimeException {
    public IncomeJsonValidationException() {
        super("Income JSON not valid");
    }
}
