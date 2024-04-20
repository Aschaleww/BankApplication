package bankapp.bankapplication.service;

import bankapp.bankapplication.DTO.AccountDto;
import bankapp.bankapplication.Entity.Account;
import bankapp.bankapplication.Exception.ResourceNotFoundException;
import bankapp.bankapplication.Mapper.AccountMapper;
import bankapp.bankapplication.Repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountSrvice{
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
       Account account = AccountMapper.AccountMapper(accountDto);
      Account account1 = accountRepository.save(account);
         return  AccountMapper.ToAccountDto(account1);

    }
    public AccountDto getAccount(Long id){
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with  ID" + id));
        AccountDto account1 =  AccountMapper.ToAccountDto(account);
  return  account1;


    }

    @Override
    public AccountDto deposit(Long id, double amount) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Account not found Id " +id));

      double total = account.getBalance() + amount;
        account.setBalance(total);
        Account acc= accountRepository.save(account);
        return AccountMapper.ToAccountDto(acc);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()->new  ResourceNotFoundException("No account with this id"));
        if(account.getBalance() <= amount){
            throw new ResourceNotFoundException(("Not enough balance"));
        }
        else {
            double total = account.getBalance() - amount;
            account.setBalance(total);

            Account accc= accountRepository.save(account);
            return  AccountMapper.ToAccountDto(accc);
        }


    }

    @Override
    public List<AccountDto> getAll() {
       List<Account> acc = accountRepository.findAll();
       if(acc.isEmpty()){
           throw new ResourceNotFoundException("NO resource is found");
       }
       return acc.stream().map((account) -> AccountMapper.ToAccountDto(account))
               .collect(Collectors.toList());





    }

    @Override
    public void delete(Long id) {
        Account acc = accountRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account with this id not exist"));

        accountRepository.deleteById(id);
    }


}
