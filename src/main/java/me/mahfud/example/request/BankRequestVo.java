package me.mahfud.example.request;

public class BankRequestVo {

    private String name;
    private String bank;
    private String number;
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
