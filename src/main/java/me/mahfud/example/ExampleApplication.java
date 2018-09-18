package me.mahfud.example;
import me.mahfud.example.model.App;
import me.mahfud.example.model.Bank;
//import me.mahfud.example.repositories.AppRepository;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ExampleApplication {

	@Autowired
	BankRepository bankRepository;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ExampleApplication.class, args);
		Person person = context.getBean(Person.class);
		System.out.println("Act as a good person ");
		System.out.println(person.toString());
	}

	@Bean
	public Person createPerson() {

		List<Bank> banks = bankRepository.findAll();
		for (Bank bank: banks) {
			System.out.println("Link " + bank.toString());
		}

		return new Person("Ali Mahfud", 24);
	}

}
