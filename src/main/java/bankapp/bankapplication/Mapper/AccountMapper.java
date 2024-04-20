package bankapp.bankapplication.Mapper;

import bankapp.bankapplication.DTO.AccountDto;
import bankapp.bankapplication.Entity.Account;

public class AccountMapper {
     public static Account AccountMapper(AccountDto accountDto){
         Account account = new Account(
                 accountDto.getId(),
                 accountDto.getAccount(),
                 accountDto.getBalance()
         );
         return  account;
     }
    public static AccountDto ToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccount(),
                account.getBalance()
        );
        return accountDto;
    }
}
