package me.mahfud.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 3, max = 100)
    private String bank;

    public Bank() {}

    public Bank(String bank) {
        this.bank = bank;
    }

    public Bank(int id, String bank) {
        this.id = id;
        this.bank = bank;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("bank_name")
    public String getBank() {
        return bank;
    }

    @JsonProperty("bank_name")
    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                '}';
    }
}
