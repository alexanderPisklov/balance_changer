package ru.caramba.balancechange.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BalanceChanger {

    @NotNull
    private UUID walletId;
    @NotNull
    private OperationType operationType;
    @Positive
    private long amount;
}
