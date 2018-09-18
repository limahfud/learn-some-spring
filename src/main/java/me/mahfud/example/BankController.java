package me.mahfud.example;

import me.mahfud.example.model.Bank;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/app")
    public HashMap<String, Object> getBank(@RequestParam(name = "min", defaultValue = "10") String minimum) {
        List<Bank> banks = new ArrayList<>();

        HashMap<String, Object> groups = new HashMap<>();
        banks.add(new Bank(1, "Bank Permata"));
        banks.add(new Bank(2, "Bank BPD DIY"));
        banks.add(new Bank(3, "Bank Mega"));
        groups.put("banks", banks);
        groups.put("min", Integer.parseInt(minimum));
        groups.put("error", false);

        return groups;
    }

    @PostMapping(value = "/app")
    public String postBank(@RequestParam(name = "min", defaultValue = "10") String minimum,
                           @RequestBody Bank string) {

        System.out.println("Parameter : " + string.toString());
        return "success";
    }
}
