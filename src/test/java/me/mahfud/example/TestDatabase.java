package me.mahfud.example;

import me.mahfud.example.model.BankUser;
import me.mahfud.example.model.Person;
import me.mahfud.example.repositories.BankRepository;
import me.mahfud.example.response.vo.BankDetail;
import me.mahfud.example.service.BankService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDatabase {

    @Autowired
    ApplicationContext context;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

    @Test
    public void whenAddTwoPlusTwo_getFour() {
        Assertions.assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    public void whenGetPerson_isNamedAliMahfud() {
        Person person = context.getBean(Person.class);

        Assertions.assertThat(person.getName()).isEqualTo("Ali Mahfud");
    }

    @Test
    public void whenGetBanks_returnTwoBanks() {
        Assertions.assertThat(bankRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void whenSearchMandiri_returnOne() {
        Assertions.assertThat(bankRepository.findByName("PT. BORLINDO MANDIRI JAYA").size())
                .isGreaterThan(0);
    }

    @Test
    public void whenGetUserFromService_returnBankUser() {
        Assertions.assertThat(bankService.getBankUser(1L) != null);
    }

    @Test
    public void whenGetUserFromService_returnUserWithNameBrandon() {
        Assertions.assertThat(bankService.getBankUser(1L).getTitle().equals("Brandon Stark"));
    }
}
