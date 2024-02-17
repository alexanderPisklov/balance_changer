package ru.caramba.balancechange.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.caramba.balancechange.DTO.BalanceChanger;
import ru.caramba.balancechange.DTO.OperationType;
import ru.caramba.balancechange.Services.WalletService;
import ru.caramba.balancechange.exceptions.IncorrectOperationException;
import ru.caramba.balancechange.model.Wallet;

import java.util.UUID;

@RestController()
@RequestMapping(value = BalanceController.BAL_CONTROLLER_URL)
@AllArgsConstructor
public class BalanceController {

    public static final String BAL_CONTROLLER_URL = "api/v1";
    private final WalletService walletService;

    @PatchMapping("/wallet")
    public void changeBalance(@RequestBody BalanceChanger changer) {
        if (changer.getOperationType() == OperationType.DEPOSIT) {
            walletService.deposit(changer);
        } else if (changer.getOperationType() == OperationType.WITHDRAW) {
            walletService.withdraw(changer);
        } else {
            throw new IncorrectOperationException();
        }
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public Wallet getBalance(@PathVariable(name = "WALLET_UUID") UUID walletId) {
        return walletService.getWallet(walletId);
    }
}
