package com.aim.balanced.transaction;

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
@RequestMapping(path = "api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(path = "")
    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Transaction getTransaction(@PathVariable(value = "id") Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.get();
    }

    @PostMapping(path = "/")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        
        // Add logic to make sure there is either a fromAccount or toAccount
        return transactionRepository.save(transaction);
    }

    @PutMapping(path = "/{id}")
    public Transaction updateTransaction(@PathVariable(value = "id") Integer id, @RequestBody Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        Transaction transaction = optionalTransaction.get();

        BeanUtils.copyProperties(transactionDetails, transaction);
        transaction.setId(id);

        transactionRepository.save(transaction);
        return transaction;
    }

    @DeleteMapping(path = "/{id}")
    public String deleteTransaction(@PathVariable(value = "id") Integer id) {
        transactionRepository.deleteById(id);
        return "Deleted";
    }
}
