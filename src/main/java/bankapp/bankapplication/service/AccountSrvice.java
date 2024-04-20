package bankapp.bankapplication.service;

import bankapp.bankapplication.DTO.AccountDto;

import java.util.List;

public interface AccountSrvice {
    AccountDto create(AccountDto accountDto);
    AccountDto getAccount(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAll();
    void delete(Long id);

}
