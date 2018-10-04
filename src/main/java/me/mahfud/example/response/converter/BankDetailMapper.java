package me.mahfud.example.response.converter;

import me.mahfud.example.model.Bank;
import me.mahfud.example.model.BankUser;
import me.mahfud.example.response.vo.BankDetail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BankDetailMapper {

    public static BankDetail map(Bank bank) {

        List<BankDetail.User> userList = new ArrayList<>();

        bank.getBankUserList().forEach( b ->
                userList.add(mapUser(b))
            );

        return new BankDetail(bank.getId(), bank.getName(), bank.getNumber(), userList);
    }

    public static BankDetail.User mapUser(BankUser bankUser) {
        return new BankDetail.User(bankUser.getId(), bankUser.getName());
    }
}
