package ru.caramba.balancechange.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.caramba.balancechange.model.Wallet;

import java.util.UUID;

public class BalanceControllerTestData {
    static final String REST_URL_GET_EXISTED_WALLET = BalanceController.BALANCE_CONTROLLER_URL + "/wallets/5f3e3087-66a3-4c41-98a3-defb68afb617";
    static final String REST_URL_GET_NOT_EXISTED_WALLET = BalanceController.BALANCE_CONTROLLER_URL + "/wallets/5f3e3087-66a3-4c41-98a3-defb68afb619";
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static UUID walletId = UUID.fromString("5f3e3087-66a3-4c41-98a3-defb68afb617");

    public static String getWalletJson() throws JsonProcessingException {
        Wallet testWallet = new Wallet(walletId, 8_504_521);
        return objectMapper.writeValueAsString(testWallet);
    }

}
