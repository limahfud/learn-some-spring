package me.mahfud.example.controller;

import com.google.common.base.Preconditions;
import me.mahfud.example.api.model.Response;
import me.mahfud.example.model.Bank;
import me.mahfud.example.repositories.BankRepository;
import me.mahfud.example.request.BankRequestVo;
import me.mahfud.example.response.vo.BankItem;
import me.mahfud.example.response.vo.DetailBankVo;
import me.mahfud.example.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Detail;
import java.util.*;

@RestController
@CrossOrigin
public class BankController {

    @Autowired
    BankService bankService;
    @Autowired
    BankRepository bankRepository;


    @GetMapping("/bank")
    public ResponseEntity<Response>  index() {
        List<BankItem> items = bankService.bankItemList();

        Response response = new Response(HttpStatus.OK, items);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<Response> detail(@PathVariable("id") Long id) {
        DetailBankVo detail = bankService.bankItemDetail(id);

        Response response = new Response(HttpStatus.OK, detail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/bank", method = RequestMethod.POST)
    public ResponseEntity<Response> createBank(@RequestBody BankRequestVo bankRequestVo) {

        DetailBankVo detail = bankService.createBank(bankRequestVo);

        Response response = new Response(HttpStatus.OK, detail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/bank/{id}")
    public ResponseEntity<Response> updateBank(@RequestBody BankRequestVo bankRequestVo,
                                               @PathVariable("id") Long id) {

        DetailBankVo detail = bankService.updateBank(bankRequestVo, id);

        Response response = new Response(HttpStatus.OK, detail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<Response> deleteBank(@PathVariable("id") Long id) {
        DetailBankVo detail = bankService.deleteBank(id);

        Response response = new Response(HttpStatus.OK, detail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
