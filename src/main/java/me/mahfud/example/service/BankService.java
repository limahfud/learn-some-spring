package me.mahfud.example.service;

import me.mahfud.example.exception.BusNotFoundException;
import me.mahfud.example.model.Bank;
import me.mahfud.example.model.BankUser;
import me.mahfud.example.repositories.BankRepository;
import me.mahfud.example.repositories.BankUserRepository;
import me.mahfud.example.response.converter.BankDetailMapper;
import me.mahfud.example.response.mapper.BankUserToUserMapper;
import me.mahfud.example.response.vo.*;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankUserToUserMapper mapper;

    public UserDetail getBankUser(Long id) {
        BankUser bankUser = bankUserRepository.findById(id).get();

        return mapper.map(bankUser, UserDetail.class);
    }

    public List<BankItem> bankItemList() {
        List<Bank> banks = bankRepository.findAll();

        List<BankItem> items = new ArrayList<>();
        banks.forEach(bank -> {
            items.add(new BankItem(bank.getId(), bank.getName()));
        });

        return items;
    }

    public DetailBankVo bankItemDetail(Long id) {
        if (bankRepository.findById(id).isPresent()) {
            Bank bank = bankRepository.findById(id).get();

            return mapper.map(bank, DetailBankVo.class);
        } else {
            throw new BusNotFoundException("the bus is exception", 1L);
        }
    }
}
