package me.mahfud.example;

import com.google.common.base.Preconditions;
import me.mahfud.example.model.Bank;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class BankController {

    @Autowired
    BankRepository bankRepository;


    @GetMapping("/bank")
    public List<Bank> index() {
        return bankRepository.findAll();
    }

    @GetMapping(value = "/app")
    public HashMap<String, Object> getBank(@RequestParam(name = "min", defaultValue = "10") String minimum) {
        List<Bank> banks = new ArrayList<>();

        HashMap<String, Object> groups = new HashMap<>();
        banks.add(new Bank(1, "Bank Permata", "012010212"));
        banks.add(new Bank(2, "Bank BPD DIY", "0121212"));
        banks.add(new Bank(3, "Bank Mega", "208431924731"));
        groups.put("banks", banks);
        groups.put("min", Integer.parseInt(minimum));
        groups.put("error", false);

        return groups;
    }

    @PostMapping(value = "/app")
    public Bank postBank(@RequestParam(name = "min", defaultValue = "10") String minimum,
                           @RequestBody Bank bank) {
        Preconditions.checkNotNull(bank);
        return bankRepository.save(bank);
    }

    @PutMapping(value = "/bank/{id}/update")
    public Bank putBank(@PathVariable(name = "id") String bankId, @RequestBody Bank bank) {
        Integer id = Integer.parseInt(bankId);

        Preconditions.checkNotNull(bank);

        Bank oldBank = bankRepository.getOne(id);
        oldBank.setBank(bank.getBank());

        System.out.println("Update the bank with " + oldBank.toString());

        return bankRepository.save(oldBank);
    }

    @DeleteMapping(value = "/bank/{id}/delete")
    public HashMap<String, Boolean> deleteBank(@PathVariable(name = "id") String bankId) {
        Optional<Bank> bankQuery = bankRepository.findById(Integer.parseInt(bankId));

        if (bankQuery.isPresent()) {
            Bank bank = bankQuery.get();
            bankRepository.delete(bank);
        }

        HashMap<String, Boolean> response = new HashMap<>();
        response.put("success", bankQuery.isPresent());

        return response;
    }
}
