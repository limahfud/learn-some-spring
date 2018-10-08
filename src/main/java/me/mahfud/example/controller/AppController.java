package me.mahfud.example.controller;

import com.google.common.base.Preconditions;
import me.mahfud.example.model.Bank;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    BankRepository bankRepository;


    @GetMapping(value = "/app")
    public ResponseEntity<HashMap<String, Object>> getBank(@RequestParam(name = "min", defaultValue = "10") String minimum) {
        List<Bank> banks = new ArrayList<>();

        HashMap<String, Object> groups = new HashMap<>();
        banks.add(new Bank(1, "Bank Permata", "012010212"));
        banks.add(new Bank(2, "Bank BPD DIY", "0121212"));
        banks.add(new Bank(3, "Bank Mega", "208431924731"));
        groups.put("banks", banks);
        groups.put("min", Integer.parseInt(minimum));
        groups.put("error", false);

        return new ResponseEntity<>(groups, HttpStatus.CREATED);
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

        Bank oldBank = bankRepository.getOne(id.longValue());
        oldBank.setBank(bank.getBank());

        System.out.println("Update the bank with " + oldBank.toString());

        return bankRepository.save(oldBank);
    }

    @DeleteMapping(value = "/bank/{id}/delete")
    public ResponseEntity<HashMap<String, Boolean>> deleteBank(@PathVariable(name = "id") String bankId) {
        Optional<Bank> bankQuery = bankRepository.findById(Long.parseLong(bankId));

        if (bankQuery.isPresent()) {
            Bank bank = bankQuery.get();
            bankRepository.delete(bank);
        }

        HashMap<String, Boolean> response = new HashMap<>();
        response.put("success", bankQuery.isPresent());

        return new ResponseEntity<HashMap<String, Boolean>>(response, HttpStatus.OK);
    }

    private HashMap<String, Object> getResponse(Object object, String dataKey) {

        HashMap<String, Object> response = new HashMap<>();

        HashMap<String, Object> data = new HashMap<>();
        data.put(dataKey, object);
        response.put("status", true);
        response.put("data", data);
        response.put("message", "success");

        return response;
    }

}
