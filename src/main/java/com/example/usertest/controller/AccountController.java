package com.example.usertest.controller;

import com.example.usertest.dto.AccountDto;
import com.example.usertest.entity.Account;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.exception.CheckAmount;
import com.example.usertest.mapper.AccountMapper;
import com.example.usertest.reponse.AccountResponse;
import com.example.usertest.request.AccountRequest;
import com.example.usertest.security.UserPrincipal;
import com.example.usertest.service.AccountService;
import com.example.usertest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AccountController {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;


    @PostMapping(path = "/account")
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        UserEntity user = userService.getUserById(userPrincipal.getId());
        AccountDto accountDto = accountMapper.toAccountDto(accountRequest);
        AccountDto saved = accountService.createAccount(accountDto,user);
        AccountResponse response = accountMapper.toAccountResponse(saved);

        return response;
    }
    @GetMapping(path = "account/{id}")
    public AccountResponse getAccount(@PathVariable("id") int id){
        AccountDto accountDto = accountService.getAccountById(id);
        AccountResponse response = accountMapper.toAccountResponse(accountDto);

        return response;
    }
    @GetMapping(path = "/account")
    public AccountResponse getCurrentAccount(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        UserEntity user = userService.getUserById(userPrincipal.getId());
        Account account = user.getAccount();
        AccountDto accountDto = accountMapper.toAccountDto(account);
        AccountResponse response = accountMapper.toAccountResponse(accountDto);
        return response;
    }
    @PutMapping(path = "account/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AccountResponse UpdateAccount(@PathVariable("id") int id,@RequestBody AccountRequest accountRequest){
        AccountDto accountDto = accountMapper.toAccountDto(accountRequest);
        AccountDto savedAcc = accountService.updateAccount(id,accountDto);
        AccountResponse response = accountMapper.toAccountResponse(savedAcc);
        System.out.println(response);

        return response;
    }
    @DeleteMapping(path = "account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/{id}/amount")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AccountResponse addAmount(@PathVariable int id,@RequestBody AccountRequest accountRequest){
        if (accountRequest.getAmount() < 0) throw new CheckAmount("check again your amount");
        AccountDto accountDto=accountService.addAmount(id,accountRequest);
        AccountResponse response = accountMapper.toAccountResponse(accountDto);

        return response;
    }
}
