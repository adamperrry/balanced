package com.aim.balanced.account;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = "")
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Account getAccount(@PathVariable(value = "id") Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.get();
    }

    @PostMapping(path = "/")
    public Account createAccount(@RequestBody Account account) {
        accountRepository.save(account);
        return account;
    }

    @PutMapping(path = "/{id}")
    public Account updateAccount(@PathVariable(value = "id") Integer id, @RequestBody Account accountDetails) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Account account = optionalAccount.get();

        BeanUtils.copyProperties(accountDetails, account);
        account.setId(id);

        accountRepository.save(account);
        return account;
    }

    // User will not be allowed to delete an account if it's being referenced
    @DeleteMapping(path = "/{id}")
    public String deleteAccount(@PathVariable(value = "id") Integer id) {
        accountRepository.deleteById(id);
        return "Deleted";
    }
}
