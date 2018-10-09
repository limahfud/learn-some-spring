package me.mahfud.example.validator;

import me.mahfud.example.request.BankRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

@Component
public class BankValidator {

    @Autowired
    Validator validator;

    public boolean validate(BankRequestVo bankRequestVo) {
        if (validator.validate(bankRequestVo).size() > 0) {
            throw new ConstraintViolationException(validator.validate(bankRequestVo));
        }

        return true;
    }
}
