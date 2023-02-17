package com.example.usertest.service;

import com.example.usertest.dto.AccountDto;
import com.example.usertest.entity.Account;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.AccountMapper;
import com.example.usertest.repo.AccountRepository;
import com.example.usertest.repo.UserRepository;
import com.example.usertest.request.AccountRequest;
import com.example.usertest.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto, UserEntity user) {
        Account account = accountMapper.toAccountEntity(accountDto);
        account.setAmount(accountDto.getAmount());
        account.setUser(user);
        Account saved = accountRepository.save(account);
        AccountDto accDto = accountMapper.toAccountDto(saved);
        user.setAccount(saved);
        userRepository.save(user);
        return accDto;

    }

    @Override
    public AccountDto getAccountById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        Account acc = account.get();
        AccountDto accountDto = accountMapper.toAccountDto(acc);

        return accountDto;
    }

    @Override
    public AccountDto updateAccount(int id, AccountDto accountDto) {
        Account account = accountRepository.findById(id).get();
        account.setAmount(accountDto.getAmount());
        Account saved = accountRepository.save(account);
        AccountDto savedAcc = accountMapper.toAccountDto(saved);

        return savedAcc;
    }

    @Override
    public void deleteAccount(int id) {

        Optional<Account> account = accountRepository.findById(id);
        Account acc = account.get();
        accountRepository.delete(acc);

    }

    @Override
    public AccountDto getAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        UserEntity user = userRepository.findById(userPrincipal.getId()).get();
        Account account = user.getAccount();
//        Optional<Account> account = accountRepository.findById(id);
//        Account acc = account.get();
        AccountDto accountDto = accountMapper.toAccountDto(account);

        return accountDto;
    }

    @Override
    public AccountDto addAmount(int id, AccountRequest accountRequest) {
        UserEntity user=userRepository.findById(id).get();
        Account account = user.getAccount();
       // if (accountRequest.getAmount() < 0) throw new CheckAmount("check again your amount");
        account.setAmount(accountRequest.getAmount() + account.getAmount());
        Account save=accountRepository.save(account);
        AccountDto accountDto=accountMapper.toAccountDto(save);

        return accountDto;
    }


}
