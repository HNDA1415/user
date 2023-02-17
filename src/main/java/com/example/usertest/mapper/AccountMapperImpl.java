package com.example.usertest.mapper;

import com.example.usertest.dto.AccountDto;
import com.example.usertest.entity.Account;
import com.example.usertest.reponse.AccountResponse;
import com.example.usertest.request.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper{
    @Override
    public AccountDto toAccountDto(AccountRequest accountRequest) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAmount(accountRequest.getAmount());

        return accountDto;
    }

    @Override
    public Account toAccountEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setAmount(accountDto.getAmount());

        return account;
    }

    @Override
    public AccountDto toAccountDto(Account saved) {
        if (saved == null){
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setId(saved.getId());
        accountDto.setAmount(saved.getAmount());

        return accountDto;
    }

    @Override
    public AccountResponse toAccountResponse(AccountDto saved) {
        AccountResponse response = new AccountResponse();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());

        return response;
    }
}
