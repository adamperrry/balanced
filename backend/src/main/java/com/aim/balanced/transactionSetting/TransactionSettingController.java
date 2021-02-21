package com.aim.balanced.transactionSetting;

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
@RequestMapping(path = "api/transactionSettings")
public class TransactionSettingController {

    @Autowired
    private TransactionSettingRepository transactionSettingRepository;

    @GetMapping(path = "")
    public Iterable<TransactionSetting> getAllTransactionSettings() {
        return transactionSettingRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public TransactionSetting getTransactionSetting(@PathVariable(value = "id") Integer id) {
        Optional<TransactionSetting> transactionSetting = transactionSettingRepository.findById(id);
        return transactionSetting.get();
    }

    @PostMapping(path = "/")
    public TransactionSetting createTransactionSetting(@RequestBody TransactionSetting transactionSetting) {
        return transactionSettingRepository.save(transactionSetting);
    }

    @PutMapping(path = "/{id}")
    public TransactionSetting updateTransactionSetting(@PathVariable(value = "id") Integer id, @RequestBody TransactionSetting transactionSettingDetails) {
        Optional<TransactionSetting> optionalTransactionSetting = transactionSettingRepository.findById(id);
        TransactionSetting transactionSetting = optionalTransactionSetting.get();

        BeanUtils.copyProperties(transactionSettingDetails, transactionSetting);
        transactionSetting.setId(id);

        return transactionSettingRepository.save(transactionSetting);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteTransactionSetting(@PathVariable(value = "id") Integer id) {
        // should really add logic to the foreign key controllers that deletes any transaction setting that references it
        transactionSettingRepository.deleteById(id);
        return "Deleted";
    }
}
