package me.mahfud.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.mahfud.example.exception.BusNotFoundException;
import me.mahfud.example.utils.option.Drink;
import me.mahfud.example.utils.option.Serving;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.annotation.Retention;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.MessageFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MockitoTest extends Assertions {

    @Test
    public void test01_testFirstMockito() {
        Fixable fixable = mock(Fixable.class);

        when(fixable.fix()).thenReturn(1);
        Repair repair = new Repair(fixable);

        assertThat(repair.getDoubleFix()).isEqualTo(2);
    }


    @Test
    public void test02_testAnotherMethodMockito() {
        Fixable fixable = mock(Fixable.class);
        when(fixable.fix()).thenReturn(10);
        when(fixable.examine()).thenReturn("OK");


        Repair repair = new Repair(fixable);

        assertThat(repair.getDoubleFix()).isEqualTo(20);
        assertThat(repair.getExaminedResult()).isEqualTo("The result is OK");
    }

    @Test
    public void test03_testMethodReturnMultiple() {
        Fixable fixable = mock(Fixable.class);
        when(fixable.fix()).thenReturn(10).thenReturn(15);
        when(fixable.examine()).thenReturn("OK").thenReturn("FAIL");


        Repair repair = new Repair(fixable);

        assertThat(repair.getDoubleFix()).isEqualTo(20);
        assertThat(repair.getExaminedResult()).isEqualTo("The result is OK");

        assertThat(repair.getDoubleFix()).isEqualTo(30);
        assertThat(repair.getExaminedResult()).isEqualTo("The result is FAIL");
    }

    @Test
    public void test04_testUsingAnyMethod() {
        Fixable fixable = mock(Fixable.class);

        when(fixable.check(anyBoolean())).thenReturn(false);
        when(fixable.check(anyString())).thenThrow(new BusNotFoundException("Should not be like that", 1L));

        Repair repair = new Repair(fixable);

        Exception ex = null;
        try {
            repair.accessBoolean(true);
        } catch (Exception e) {
            ex = e;
        }
        assertThat(ex).isNull();
        assertThatThrownBy(() -> repair.accessString("Not found")).isInstanceOf(BusNotFoundException.class);

    }

    @Test
    public void test05_testUsingEnum() {
        Order order = new Order(Drink.JUICE, Serving.COLD);

        assertThat(order.drink).isEqualTo(Drink.JUICE);
        assertThat(order.serving).isEqualTo(Serving.COLD);
    }

    @Test
    public void test06_messageFormatTesting() {
        int i = 1234;
        float f = 10.34f;
        short s = 100_0_0;
        boolean b = true;
        char c = 'v';
        long l = 4_000_000_000L;
        double d = 4_000_000_000.122222;
        byte by = 123;

        String pattern1 = "{0}";
        String pattern2 = "{0} {1}";
        String pattern3 = "{0} {1} {2}";

        String floatPattern = "{0}";

        assertThat(MessageFormat.format(pattern1, i)).isEqualTo("1,234");
        assertThat(MessageFormat.format(pattern1, f)).isEqualTo("10.34");
        assertThat(MessageFormat.format(pattern1, s)).isEqualTo("10,000");
        assertThat(MessageFormat.format(pattern1, b)).isEqualTo("true");
        assertThat(MessageFormat.format(pattern1, c)).isEqualTo("v");
        assertThat(MessageFormat.format(pattern1, l)).isEqualTo("4,000,000,000");
        assertThat(MessageFormat.format(pattern1, by)).isEqualTo("123");
        assertThat(MessageFormat.format(pattern1,  d)).isEqualTo("4000000000.0000000001");

        assertThat(MessageFormat.format(pattern2, 10)).isEqualTo("10 {1}");
    }

    @Test
    public void test07_floatAndDoubleAddition() {
        Float aFloat = 1.12345667812912f;
        Float bFloat = 1.12345667812913f;

        assertThat(bFloat - aFloat).isNotEqualTo(0.00000000000001f);

        Double jDouble = 1.12345667d;
        Double iDouble = 1.12345666d;

        assertThat(jDouble - iDouble).isEqualTo(0.00000001f);

        Double hDouble = 1.1234566782d;
        Double gDouble = 1.1234566781d;

        assertThat(hDouble - gDouble).isEqualTo(0.0000000001f);

        Double fDouble = 1.123456678129d;
        Double eDouble = 1.123456678128d;

        assertThat(fDouble - eDouble).isEqualTo(0.000000000001f);

        Double dDouble = 1.1234566781291d;
        Double cDouble = 1.1234566781290d;

        assertThat(dDouble - cDouble).isEqualTo(0.0000000000001f);

        Double bDouble = 1.12345667812913d;
        Double aDouble = 1.12345667812912d;

        assertThat(bDouble - aDouble).isNotEqualTo(0.00000000000001f);
    }

    @Test
    public void test08_bigDecimalType() {
        BigDecimal cDecimal = new BigDecimal(1.123412911d);
        BigDecimal dDecimal = new BigDecimal(1.123412912d);

        assertThat(dDecimal.subtract(cDecimal)).isEqualTo(new BigDecimal("0.000000001"));

        BigDecimal aDecimal = new BigDecimal(1.12345667812911d);
        BigDecimal bDecimal = new BigDecimal(1.12345667812912d);

        assertThat(bDecimal.subtract(aDecimal)).isEqualTo(new BigDecimal("0.00000000000001"));
    }


    @Test
    public void test09_formattingBigDecimal() {
        BigDecimal decimal = new BigDecimal("0.1234567233123");

        assertThat(decimal.setScale(1, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.1"));
        assertThat(decimal.setScale(2, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.12"));
        assertThat(decimal.setScale(3, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.123"));
        assertThat(decimal.setScale(4, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.1235"));
        assertThat(decimal.setScale(5, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.12346"));
        assertThat(decimal.setScale(6, RoundingMode.HALF_UP)).isEqualTo(new BigDecimal("0.123457"));

        assertThat(Double.parseDouble(decimal.setScale(2, RoundingMode.HALF_UP).toString())).isEqualTo(0.12);
        assertThat(Double.parseDouble(decimal.setScale(3, RoundingMode.HALF_UP).toString())).isEqualTo(0.123);
        assertThat(Double.parseDouble(decimal.setScale(5, RoundingMode.HALF_UP).toString())).isEqualTo(0.1235);
        assertThat(Double.parseDouble(decimal.setScale(6, RoundingMode.HALF_UP).toString())).isEqualTo(0.12346);
        assertThat(Double.parseDouble(decimal.setScale(7, RoundingMode.HALF_UP).toString())).isEqualTo(0.123457);
    }

    @Test
    public void test10_formattingBigDecimal() {

    }


    interface Fixable {
        int fix();

        String examine();

        boolean check(String s);

        boolean check(boolean b);
    }

    class Repair {

        private Fixable fixable;

        public Repair(Fixable fixable) {
            this.fixable = fixable;
        }

        public int getDoubleFix() {
            return fixable.fix() * 2;
        }

        public String getExaminedResult() {
            return String.format("The result is %s", fixable.examine());
        }

        public void accessString(String string) {
            fixable.check(string);
        }

        public void accessBoolean(Boolean b) {
            fixable.check(b);
        }
    }

    @Data
    @AllArgsConstructor
    class Order {
        private Drink drink;
        private Serving serving;

        public String serve() {
            String drinkStr = "";
            String servingStr = "";
            switch (drink) {
                case TEA:
                    drinkStr = "Tea";
                    break;
                case MILK:
                    drinkStr = "Milk";
                    break;
                case JUICE:
                    drinkStr = "Juice";
                    break;
                case WINE:
                    drinkStr = "Wine";
                    break;
                case COFFEE:
                    drinkStr = "Coffee";
                    break;
                case MINERAL:
                    drinkStr = "Mineral";
                    break;
            }

            if (serving == Serving.COLD) {
                servingStr = "Cold";
            } else if (serving == Serving.HOT) {
                servingStr = "Hot";
            } else {
                servingStr = "Standard";
            }

            return MessageFormat.format("Here''s the {0} served as {1} drink.", drinkStr, servingStr);

        }
    }
}
