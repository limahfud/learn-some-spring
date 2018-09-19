package me.mahfud.example;

import me.mahfud.example.repositories.BankRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDatabase {

    @Autowired
    ApplicationContext context;

    @Test
    public void whenAddTwoPlusTwo_getFour() {
        Person person = context.getBean(Person.class);

        Assertions.assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    public void whenGetPerson_isNamedAliMahfud() {
        Person person = context.getBean(Person.class);

        Assertions.assertThat(person.getName()).isEqualTo("Ali Mahfud");
    }
}
