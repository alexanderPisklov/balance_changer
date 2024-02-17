package ru.caramba.balancechange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.caramba.balancechange.model.Wallet;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
