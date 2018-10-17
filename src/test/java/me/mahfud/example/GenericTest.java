package me.mahfud.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GenericTest extends Assertions {

    @Test
    public void testClassGeneric() {
        Flag<String, Price> flag = new Flag<>();

        Price price = flag.getKey();
        List<String> list = flag.getList();

        assertThat(price).isNull();
        assertThat(list).isNull();

        Price price10 = new Price(10);
        flag.setKey(price10);
        flag.setList(Arrays.asList("Gold", "Silver"));

        price = flag.getKey();
        list = flag.getList();

        assertThat(price).isNotNull();
        assertThat(list).isNotNull();
        assertThat(price).isEqualTo(price10);
        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(2);

    }

    @Test
    public void testMethodGeneric() {
        List<Integer> integerList = Arrays.asList(10, 20, 30);
        List<Float> floatList = Arrays.asList(10.3F, 10.5F, 10.6F);
        List<String> stringList = Arrays.asList("Coconut", "Palm", "Date");
        List<Price> priceList = Arrays.asList(new Price(20), new Price(30));

        assertThat(getFirst(integerList)).isEqualTo(10);
        assertThat(getFirst(floatList)).isEqualTo(10.3F);
        assertThat(getFirst(stringList)).isEqualTo("Coconut");
        assertThat(getFirst(priceList)).isEqualTo(new Price(20));
    }

    @Test
    public void testMethodGenericExtends() {
        Price price1 = new Price(1000);
        Price price2 = new Price(2000);

        Weight weight1 = new Weight(15);
        Weight weight2 = new Weight(30);

        assertThat(doubleItem(price1, Price.class)).isEqualTo(price2);
        assertThat(doubleItem(weight1, Weight.class)).isEqualTo(weight2);
    }

    private <T> T getFirst(List<T> items) {
        return items.get(0);
    }

    private <T extends Countable> T doubleItem(T item, Class<T> clazz) {

        if (clazz.equals(Price.class)) {
            return (T) new Price(2 * item.count());
        } else if (clazz.equals(Weight.class)) {
            return (T) new Weight(2 * item.count());
        }

        return null;
    }


    @Data
    @NoArgsConstructor
    class Flag<T, K> {
        private List<T> list;
        private K key;

        public Flag(List<T> list, K key) {
            this.list = list;
            this.key = key;
        }
    }

    @Data
    @AllArgsConstructor
    class Price implements Countable{
        int value;

        @Override
        public int count() { return value; }
    }

    @Data
    @AllArgsConstructor
    class Weight implements Countable {
        int value;

        @Override
        public int count() { return value; }
    }

    interface Countable {
        int count();
    }
}
