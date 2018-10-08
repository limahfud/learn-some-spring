package me.mahfud.example.repositories;

import me.mahfud.example.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    public List<Bank> findByNameContaining(String search);

    public List<Bank> findByName(String name);

    public List<Bank> findFirstByBankContaining(String search);
}
