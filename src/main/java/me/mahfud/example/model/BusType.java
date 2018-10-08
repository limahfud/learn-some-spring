package me.mahfud.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tipe_bis")
public class BusType {

    @Column(name = "bis")
    private @Id String bus;

    @Column(name = "kode_bis")
    private String code;

    @Column(name = "merk")
    private String brand;

    public BusType(){ }

    public BusType(String bus, String code, String brand) {
        this.bus = bus;
        this.code = code;
        this.brand = brand;
    }
}
