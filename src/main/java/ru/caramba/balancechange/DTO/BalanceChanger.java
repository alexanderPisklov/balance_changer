package ru.caramba.balancechange.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BalanceChanger {
    private UUID walletId;
    private OperationType operationType;
    private long amount;
}
