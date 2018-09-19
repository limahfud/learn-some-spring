package me.mahfud.example.repositories;

import me.mahfud.example.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
