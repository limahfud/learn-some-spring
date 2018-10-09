package me.mahfud.example.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BankRequestVo {

    @NotNull
    @Size(min = 5, max = 100, message = "Account name should be more than 5 characters and less than 50 characters")
    private String name = "";

    @NotNull
    @Size(min = 5, max = 50, message = "Bank name should be 5 characters and less than 50 characters.")
    private String bank = "";

    @NotNull
    @Size(min = 10, message = "Bank number minimum 10 characters")
    @Digits(integer = 100, fraction = 0)
    private String number = "";

    private String logo;

    public  BankRequestVo() { }

    public BankRequestVo(String name, String bank, String number, String logo) {
        this.name = name;
        this.bank = bank;
        this.number = number;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
