package com.example.usertest.mapper;

import com.example.usertest.dto.AccountDto;
import com.example.usertest.entity.Account;
import com.example.usertest.reponse.AccountResponse;
import com.example.usertest.request.AccountRequest;

public interface AccountMapper {
    AccountDto toAccountDto(AccountRequest accountRequest);

    Account toAccountEntity(AccountDto accountDto);

    AccountDto toAccountDto(Account saved);

    AccountResponse toAccountResponse(AccountDto saved);
}
