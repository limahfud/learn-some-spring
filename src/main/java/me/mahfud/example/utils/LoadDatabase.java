package me.mahfud.example.utils;

import lombok.extern.slf4j.Slf4j;
import me.mahfud.example.model.BusType;
import me.mahfud.example.repositories.BusTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initRepository(BusTypeRepository busTypeRepository) {
        return args -> {
            busTypeRepository.save(new BusType("Bus A", "A901212", "Panasonic"));
            busTypeRepository.save(new BusType("Bus B", "B12323123", "Samsung"));

        };
    }
}
