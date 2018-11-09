package me.mahfud.example;


import junit.framework.TestCase;
import me.mahfud.example.request.BankRequestVo;
import me.mahfud.example.response.vo.BankItem;
import me.mahfud.example.response.vo.DetailBankVo;
import me.mahfud.example.service.BankService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTest extends TestCase {

    private static int count = 0;

    private static BankRequestVo bankRequestVo = new BankRequestVo();
    private static DetailBankVo bankVo = null;

    @Autowired
    BankService bankService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
/Users/alimahfud/Desktop/example/src/main/java/me/mahfud/example/controller/BusController.java

    @Test
    public void test01_whenYouFindAllUser_makeSureItReturnsList() {

        Assertions.assertThat(bankService.bankItemList()).isInstanceOf(List.class);

        Assertions.assertThat(bankService.bankItemList()).isNotNull();

        Assertions.assertThat(bankService.bankItemList().isEmpty()).isFalse();

        count = bankService.bankItemList().size();
    }

    @Test
    public void test02_whenYouCreateBank_ItWillMakeSizeOfAllIncremented() {

        bankRequestVo.setName("Paijo");
        bankRequestVo.setNumber("12324124123123");
        bankRequestVo.setLogo("main.html");
        bankRequestVo.setBank("Bank MadaApaya");

        bankVo = bankService.createBank(bankRequestVo);

        Assertions.assertThat(10).isEqualTo(count);
    }

    @Test
    public void test03_afterBankCreated_theLastItemSavedIsEqualWithSavedItem() {

        List<BankItem> items = bankService.bankItemList();
        BankItem item = items.get(items.size() - 1);

        Assertions.assertThat(item.getName()).isEqualTo(bankRequestVo.getName());
    }

    @Test
    public void test04_afterBankCreated_theObjectSavedIsSimilarWithRequested() {

        DetailBankVo bankVoTested = bankService.bankItemDetail(bankVo.getId());

        Assertions.assertThat(bankVoTested.getId()).isEqualTo(bankVo.getId());
        Assertions.assertThat(bankVoTested.getBank()).isEqualTo(bankVo.getBank());
        Assertions.assertThat(bankVoTested.getName()).isEqualTo(bankVo.getName());
        Assertions.assertThat(bankVoTested.getLogo()).isEqualTo(bankVo.getLogo());
        Assertions.assertThat(bankVoTested.getNumber()).isEqualTo(bankVo.getNumber());
    }

    @Test
    public void test05_afterBankEdited_theBankValueIsChanged() {
        BankRequestVo bankRequestEditVo = new BankRequestVo();
        bankRequestEditVo.setBank("Bank Paraguay");
        bankRequestEditVo.setName("Joko Anwar");
        bankRequestEditVo.setLogo("another_logo.png");
        bankRequestEditVo.setNumber("214087102847");

        // Doing edit
        bankService.updateBank(bankRequestEditVo, bankVo.getId());

        // Getting the item with id
        DetailBankVo detailFromDatabase = bankService.bankItemDetail(bankVo.getId());
        Assertions.assertThat(detailFromDatabase.getName()).isEqualTo(bankRequestEditVo.getName());
        Assertions.assertThat(detailFromDatabase.getNumber()).isEqualTo(bankRequestEditVo.getNumber());
        Assertions.assertThat(detailFromDatabase.getLogo()).isEqualTo(bankRequestEditVo.getLogo());

        bankService.updateBank(bankRequestVo, bankVo.getId());
        detailFromDatabase = bankService.bankItemDetail(bankVo.getId());
        Assertions.assertThat(detailFromDatabase.getName()).isEqualTo(bankRequestVo.getName());
        Assertions.assertThat(detailFromDatabase.getNumber()).isEqualTo(bankRequestVo.getNumber());
        Assertions.assertThat(detailFromDatabase.getLogo()).isEqualTo(bankRequestVo.getLogo());
    }

    @Test
    public void test99_testShouldReturningHelloWorld() {
        String url = "http://localhost:" + port + "/hi";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer dunuopd12j32d2");
        this.testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        Assertions.assertThat(this.testRestTemplate.getForObject(url, String.class)).contains("Hello World");
    }


    @Test
    public void test100_removeAllBankWithPaijoName() {
        List<BankItem> banks = bankService.searchBankByName(bankRequestVo.getName());

        for (BankItem bankItem : banks) {
            bankService.deleteBank(bankItem.getId());
        }

        Assertions.assertThat(bankService.bankItemList().size()).isEqualTo(count);
    }

    @Test
    public void test99_addNewAsTest() {
        Assertions.assertThat(2 + 2).as("Two plus two").isEqualTo(4);


    }
}
