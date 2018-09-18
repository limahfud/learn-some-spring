package me.mahfud.example;

import me.mahfud.example.model.Bank;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BankController {

    @Autowired
    BankRepository bankRepository;


    @GetMapping("/bank")
    public List<Bank> index() {
        return bankRepository.findAll();
    }

    @GetMapping("/app")
    public HashMap<String, List<Bank>> getBank() {
        List<Bank> banks = new ArrayList<>();

        HashMap<String, List<Bank>> groups = new HashMap<>();
        banks.add(new Bank(1, "Bank Permata"));
        banks.add(new Bank(2, "Bank BPD DIY"));
        banks.add(new Bank(2, "Bank Mega"));
        groups.put("Hahahaa", banks);


        return groups;
    }
}
