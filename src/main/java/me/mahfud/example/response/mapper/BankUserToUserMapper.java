package me.mahfud.example.response.mapper;

import me.mahfud.example.model.Bank;
import me.mahfud.example.model.BankUser;
import me.mahfud.example.response.vo.DetailBankVo;
import me.mahfud.example.response.vo.UserDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class BankUserToUserMapper extends ModelMapper {

    public BankUserToUserMapper() {
        super();

        addMappings(new PropertyMap<BankUser, UserDetail>() {
            @Override
            protected void configure() {
                map().setTitle(source.getName());
                map().setId(source.getId());
            }
        });

        addMappings(new PropertyMap<Bank, DetailBankVo>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setBank(source.getBank());
                map().setName(source.getName());
                map().setNumber(source.getNumber());
                map().setLogo(source.getLogo());
            }
        });
    }

}
