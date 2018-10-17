package me.mahfud.example.controller;

import me.mahfud.example.api.model.ResponseError;
import me.mahfud.example.exception.BusNotFoundException;
import me.mahfud.example.model.Bank;
import me.mahfud.example.model.BankUser;
import me.mahfud.example.repositories.BankRepository;
import me.mahfud.example.repositories.BankUserRepository;
import me.mahfud.example.response.vo.UserDetail;
import me.mahfud.example.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Locale;

@Controller
public class BankUserController {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    BankService bankService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/put/users/{id}")
    public UserDetail putAllTheUser(@PathVariable("id") long id) {
        return bankService.getBankUser(id);
    }

    @GetMapping("/put/users/error/{id}")
    public String getTryingError(@PathVariable("id") Integer id, @RequestHeader("Accept-Language") String lang) {

        if (id > 10)
        throw new BusNotFoundException("Bus is not found in " + messageSource.getMessage("apps.name", null, new Locale(lang)) , 10L);

        return "Success";
    }

    @GetMapping("/put/bank/users")
    @Transactional
    public String putAllTheBankUser() {
        Bank bank = bankRepository.findById(1L).get();

        BankUser bankUser1 = new BankUser("Tyrion Lannister");
        BankUser bankUser2 = new BankUser("Jamie Lannister");
        BankUser bankUser3 = new BankUser("Cersei Lannister");

        entityManager.persist(bankUser1);
        entityManager.persist(bankUser2);
        entityManager.persist(bankUser3);

        bank.getBankUserList().add(bankUser1);
        bank.getBankUserList().add(bankUser2);
        bank.getBankUserList().add(bankUser3);

        bankRepository.save(bank);

        return "Success";
    }

    @GetMapping("/error")
    public ResponseEntity<ResponseError> getApiError() {
        ResponseError response = new ResponseError(HttpStatus.NOT_FOUND,  "The page you are looking for is not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/error")
    public ResponseEntity<ResponseError> postApiError() {
        ResponseError response = new ResponseError(HttpStatus.NOT_FOUND,  "The page you are looking for is not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
