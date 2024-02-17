package ru.caramba.balancechange.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.caramba.balancechange.DTO.BalanceChanger;
import ru.caramba.balancechange.exceptions.InsufficientBalanceException;
import ru.caramba.balancechange.exceptions.WalletNotFoundException;
import ru.caramba.balancechange.model.Wallet;
import ru.caramba.balancechange.repository.WalletRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    public synchronized void deposit(BalanceChanger changer) {
        Wallet wallet = getWallet(changer.getWalletId());
        long newBalance = wallet.getBalance() + changer.getAmount();
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    @Transactional
    public synchronized void withdraw(BalanceChanger changer) {
        Wallet wallet = getWallet(changer.getWalletId());
        if (wallet.getBalance() < changer.getAmount()) {
            throw new InsufficientBalanceException();
        }
        long newBalance = wallet.getBalance() - changer.getAmount();
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    public Wallet getWallet(UUID walletId) {
        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
        if (optionalWallet.isEmpty()) throw new WalletNotFoundException();
        return optionalWallet.get();
    }
}
