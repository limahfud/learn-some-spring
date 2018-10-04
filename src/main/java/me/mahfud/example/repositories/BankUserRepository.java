package me.mahfud.example.repositories;

import me.mahfud.example.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
}
