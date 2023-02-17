package com.example.usertest.service;

import com.example.usertest.dto.AccountDto;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.request.AccountRequest;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto, UserEntity user);

    AccountDto getAccountById(int id);

    AccountDto updateAccount(int id, AccountDto accountDto);

    void deleteAccount(int id);

    AccountDto getAccount();

    AccountDto addAmount(int id, AccountRequest accountRequest);
}
