package me.mahfud.example;
import me.mahfud.example.model.Bank;
//import me.mahfud.example.repositories.AppRepository;
import me.mahfud.example.model.Person;
import me.mahfud.example.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

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

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("/WEB-INF/classes/application");
//		return messageSource;

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("application");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("application");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

}
