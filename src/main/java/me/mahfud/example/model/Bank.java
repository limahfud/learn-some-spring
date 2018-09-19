package me.mahfud.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 100)
    private String bank;

    @Size(min = 3, max = 100)
    @Column(name = "atas_nama")
    private String name;

    @Column(name = "no_rekening")
    private String number;

    @Column(name = "logo")
    private String logo;

    public Bank() {}

    public Bank(String bank) {
        this.bank = bank;
    }

    public Bank(int id, String bank, String name) {
        this.id = id;
        this.bank = bank;
        this.name = name;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
