package com.rakharafifa.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import com.rakharafifa.miniproject.model.dto.WalletDto;
import com.rakharafifa.miniproject.model.entity.User;
import com.rakharafifa.miniproject.model.entity.Wallet;
import com.rakharafifa.miniproject.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> getAllWallet() {
        List<Wallet> wallets = new ArrayList<>();
        walletRepository.findAll().forEach(wallets::add);
        return wallets;
    }

    @Override
    public List<WalletDto> getAllWalletDto() {
        List<Wallet> wallets = walletRepository.findAll();
        List<WalletDto> walletDtos = new ArrayList<>();
        
        wallets.forEach(isi ->{
            WalletDto dto = new WalletDto();
            dto.setWallet_id(isi.getWallet_id());
            dto.setName(isi.getName());
            dto.setAmount(isi.getAmount());

            walletDtos.add(dto);
        });
        return walletDtos;
    }

    @Override
    public Wallet getWalletById(Long wallet_id) {
        return walletRepository.findById(wallet_id).get();
    }

    @Override
    public void createWalletDto(WalletDto walletDtos) {
        Wallet wallet = new Wallet();
        User user = new User();

        user.setUser_id(walletDtos.getUser_id());
        wallet.setWallet_id(walletDtos.getWallet_id());
        wallet.setName(walletDtos.getName());
        wallet.setAmount(walletDtos.getAmount());
        wallet.setTop_up(walletDtos.getTop_up());

        walletRepository.save(wallet);
    }

    @Override
    public void updateWallet(Long wallet_id, Wallet wallet) {
        Wallet wallet2 = walletRepository.findById(wallet_id).get();
        System.out.println(wallet2.toString());

        wallet2.setName(wallet.getName());
        wallet2.setAmount(wallet.getAmount());
        wallet2.setTop_up(wallet.getTop_up());
        walletRepository.save(wallet2);
    }

    @Override
    public void deleteWallet(Long wallet_id) {
        walletRepository.deleteById(wallet_id);
    }
}
