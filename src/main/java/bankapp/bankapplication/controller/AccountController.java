package bankapp.bankapplication.controller;

import bankapp.bankapplication.DTO.AccountDto;
import bankapp.bankapplication.service.AccountSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountSrvice accountSrvice;
    @Autowired
    public AccountController(AccountSrvice accountSrvice) {
        this.accountSrvice = accountSrvice;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDto> create(@RequestBody  AccountDto accountDto){
       AccountDto account = accountSrvice.create(accountDto);
        return  new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto accountDto1 = accountSrvice.getAccount(id);
        return ResponseEntity.ok(accountDto1);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountSrvice.deposit(id, amount);
        return  ResponseEntity.ok(accountDto);

    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto acc = accountSrvice.withdraw(id, amount);
        return  ResponseEntity.ok(acc);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAll(){

       List<AccountDto> acc = accountSrvice.getAll();
        return ResponseEntity.ok(acc);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id){
         accountSrvice.delete(id);
     return    ResponseEntity.ok("Successully deleted");
    }
}
