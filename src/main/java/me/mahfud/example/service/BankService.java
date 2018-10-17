package me.mahfud.example.service;

import me.mahfud.example.exception.BusNotFoundException;
import me.mahfud.example.model.Bank;
import me.mahfud.example.model.BankUser;
import me.mahfud.example.repositories.BankRepository;
import me.mahfud.example.repositories.BankUserRepository;
import me.mahfud.example.request.BankRequestVo;
import me.mahfud.example.response.mapper.BankUserToUserMapper;
import me.mahfud.example.response.vo.BankItem;
import me.mahfud.example.response.vo.DetailBankVo;
import me.mahfud.example.response.vo.UserDetail;
import me.mahfud.example.validator.BankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankValidator bankValidator;

    @Autowired
    BankUserToUserMapper mapper;

    @Autowired
    Validator validator;

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

    public List<BankItem> searchBankByName(String query) {
        List<Bank> banks = bankRepository.findByNameContaining(query);
        
        List<BankItem> bankItems = new ArrayList<>();
        banks.forEach(bank -> {
            bankItems.add(new BankItem(bank.getId(), bank.getName()));
        });

        return bankItems;
    }
    
    public DetailBankVo bankItemDetail(Long id) {
        if (bankRepository.findById(id).isPresent()) {
            Bank bank = bankRepository.findById(id).get();

            return mapper.map(bank, DetailBankVo.class);
        } else {
            throw new BusNotFoundException("The resource is not found", 1L);
        }
    }

    public DetailBankVo createBank(@Valid BankRequestVo bankRequestVo) {
        Bank newBank = new Bank();

//        if (validator.validate(bankRequestVo).size() > 0) {
//            throw new ConstraintViolationException(validator.validate(bankRequestVo));
//        }

        bankValidator.validate(bankRequestVo);

        newBank.setName(bankRequestVo.getName());
        newBank.setBank(bankRequestVo.getBank());
        newBank.setLogo(bankRequestVo.getLogo());
        newBank.setNumber(bankRequestVo.getNumber());
        newBank.setBankUserList(new ArrayList());

        Bank createdBank = bankRepository.save(newBank);
        return mapper.map(createdBank, DetailBankVo.class);
    }

    public DetailBankVo updateBank(BankRequestVo bankRequestVo, Long id) {
        if (!bankRepository.findById(id).isPresent()) {
            throw new BusNotFoundException("The resource is not found", id);
        }
        Bank updatedBank = bankRepository.findById(id).get();

        updatedBank.setName(bankRequestVo.getName());
        updatedBank.setBank(bankRequestVo.getBank());
        updatedBank.setLogo(bankRequestVo.getLogo());
        updatedBank.setNumber(bankRequestVo.getNumber());

        Bank savedBank = bankRepository.save(updatedBank);

        return mapper.map(savedBank, DetailBankVo.class);
    }

    public DetailBankVo deleteBank(Long id) {
        if (!bankRepository.findById(id).isPresent()) {
            throw new BusNotFoundException("The resource is not found", id);
        }

        Bank deletedBank = bankRepository.findById(id).get();

        bankRepository.delete(deletedBank);

        return mapper.map(deletedBank, DetailBankVo.class);
    }
}
