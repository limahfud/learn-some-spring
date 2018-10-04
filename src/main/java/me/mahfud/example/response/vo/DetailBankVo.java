package me.mahfud.example.response.vo;

public class DetailBankVo {

    private Long id;
    private String name;
    private String number;
    private String bank;
    private String logo;

    private DetailBankVo() {

    }

    public DetailBankVo(Long id, String name, String number, String bank, String logo) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.bank = bank;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
