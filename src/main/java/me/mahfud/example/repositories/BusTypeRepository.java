package me.mahfud.example.repositories;

import me.mahfud.example.model.BusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusTypeRepository extends JpaRepository<BusType, String> {
}
