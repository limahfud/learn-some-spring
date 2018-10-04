package me.mahfud.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 100)
    private String bank;

    @Size(min = 3, max = 100)
    @Column(name = "atas_nama")
    private String name;

    @Column(name = "no_rekening")
    private String number;

    @Column(name = "logo")
    private String logo;

    @OneToMany
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private List<BankUser> bankUserList;

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
    public long getId() {
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

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bank='" + bank + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public List<BankUser> getBankUserList() {
        return bankUserList;
    }

    public void setBankUserList(List bankUserList) {
        this.bankUserList = bankUserList;
    }
}
