package ru.caramba.balancechange.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.caramba.balancechange.dto.BalanceChanger;
import ru.caramba.balancechange.dto.OperationType;
import ru.caramba.balancechange.services.WalletService;
import ru.caramba.balancechange.exceptions.IncomeJsonValidationException;
import ru.caramba.balancechange.model.Wallet;

import java.util.UUID;

@RestController()
@RequestMapping(value = BalanceController.BALANCE_CONTROLLER_URL)
@AllArgsConstructor
public class BalanceController {

    public static final String BALANCE_CONTROLLER_URL = "/api/v1";
    private final WalletService walletService;

    @PostMapping("/wallet")
    public void changeBalance(@RequestBody @Validated BalanceChanger changer, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new IncomeJsonValidationException();
        }
        if (changer.getOperationType() == OperationType.DEPOSIT) {
            walletService.deposit(changer);
        } else if (changer.getOperationType() == OperationType.WITHDRAW) {
            walletService.withdraw(changer);
        }
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public Wallet getBalance(@PathVariable(name = "WALLET_UUID") UUID walletId) {
        return walletService.getWallet(walletId);
    }
}
