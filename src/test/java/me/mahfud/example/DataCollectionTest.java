package me.mahfud.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataCollectionTest extends Assertions {

    @Test
    public void testArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Real Madrid");
        list.add("Barcelona");

        assertThat(list).first().isEqualTo("Real Madrid");
        assertThat(list).last().isEqualTo("Barcelona");

        list.add(1, "Bayern Munich");
        assertThat(list.get(1)).isEqualTo("Bayern Munich");
        assertThat(list.get(2)).isEqualTo("Barcelona");


        list.remove("Barcelona");
        assertThat(list).hasSize(2);
        assertThat(list).last().isEqualTo("Bayern Munich");

        list.add("Manchester United");
        assertThat(list).last().isEqualTo("Manchester United");

        list.set(1, "Paris Saint Germain");
        assertThat(list.get(1)).isEqualTo("Paris Saint Germain");

        assertThat(list.indexOf("Real Madrid")).isEqualTo(0);
        assertThat(list.indexOf("Paris Saint Germain")).isEqualTo(1);
        assertThat(list.indexOf("Manchester United")).isEqualTo(2);
        assertThat(list.indexOf("Manchester City")).isEqualTo(-1);

        assertThat(list.contains("Manchester United")).isTrue();
        assertThat(list.contains("Liverpool")).isFalse();

        list.clear();
        assertThat(list).isEmpty();
    }

    @Test
    public void testArrayListInitialization() {
        // Method 1
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Pear", "Orange", "Mango"));

        assertThat(list).hasSize(3);
        assertThat(list).first().isEqualTo("Pear");
        assertThat(list).last().isEqualTo("Mango");
        assertThat(list.get(1)).isEqualTo("Orange");

        list.clear();


        // Method 2
        list = new ArrayList<String>() {{
            add("Grape");
            add("Banana");
            add("Kiwi");
        }};

        assertThat(list).hasSize(3);
        assertThat(list).first().isEqualTo("Grape");
        assertThat(list).last().isEqualTo("Kiwi");
        assertThat(list.get(1)).isEqualTo("Banana");


        list.clear();


        // Method 3
        list = new ArrayList<>();
        list.add("Watermelon");
        list.add("Melon");
        list.add("Apple");

        assertThat(list).hasSize(3);
        assertThat(list).first().isEqualTo("Watermelon");
        assertThat(list).last().isEqualTo("Apple");
        assertThat(list.get(1)).isEqualTo("Melon");


        // Method 4 : collection n copies
        ArrayList<Integer> integers = new ArrayList<>(Collections.nCopies(10, 12));

        assertThat(integers).hasSize(10);
        assertThat(integers.get((new Random()).nextInt(9))).isEqualTo(12);
        assertThat(integers.get((new Random()).nextInt(9))).isEqualTo(12);
        assertThat(integers.get((new Random()).nextInt(9))).isEqualTo(12);
    }

    @Test
    public void testArrayListLoop() {

        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder resultBuilder = new StringBuilder();
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);

        // For Loop
        for (int i = 0; i < list.size(); i++) {
            resultBuilder.append(list.get(i));
        }
        assertThat(resultBuilder.toString()).isEqualTo("10111213");


        // Advanced For Loop
        resultBuilder = new StringBuilder();
        for (Integer i : list) {
            resultBuilder.append(i).append("-");
        }

        assertThat(resultBuilder.toString()).isEqualTo("10-11-12-13-");


        // While Loop
        int count = 0;
        resultBuilder = new StringBuilder();
        while (list.size() > count) {
            resultBuilder.append(list.get(count)).append(",");
            count++;
        }
        assertThat(resultBuilder.toString()).isEqualTo("10,11,12,13,");

        // Iterator
        resultBuilder = new StringBuilder();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            resultBuilder.append(iterator.next()).append("+");
        }
        assertThat(resultBuilder.toString()).isEqualTo("10+11+12+13+");
    }

    @Test
    public void test05_sortingArrayList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Morocco", "Egypt", "Sudan", "Algeria"));

        StringBuilder builder = new StringBuilder();
        for (String string: list) {
            builder.append(string).append(" ");
        }
        assertThat(builder.toString()).isEqualTo("Morocco Egypt Sudan Algeria ");

        // Sort using Collection sort
        Collections.sort(list);
        builder = new StringBuilder();
        for (String string: list) {
            builder.append(string).append(" ");
        }

        assertThat(builder.toString()).isEqualTo("Algeria Egypt Morocco Sudan ");
        Collections.reverse(list);

        builder = new StringBuilder();
        for (String string: list) {
            builder.append(string).append(" ");
        }

        assertThat(builder.toString()).isEqualTo("Sudan Morocco Egypt Algeria ");
    }


    @Test
    public void test06_sortingArrayListComparator() {
        List<String> list = new ArrayList<>(Arrays.asList("Manchester", "Winchester", "Leicester"));

        Collections.sort(list);

        assertThat(list.toString()).isEqualTo("[Leicester, Manchester, Winchester]");

        List<Price> prices = new ArrayList<>(Arrays.asList(new Price(10), new Price(12), new Price(11)));

        Collections.sort(prices);
        StringBuilder builder = new StringBuilder();
        builder.append(prices.get(0).count());
        builder.append(prices.get(1).count());
        builder.append(prices.get(2).count());
        assertThat(builder.toString()).isEqualTo("101112");
    }


    @Test
    public void test07_linkedListType() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Mercury");
        list.add("Venus");
        list.addAll(Arrays.asList("Earth", "Mars", "Jupiter", "Saturn", "Neptune"));

        assertThat(list).hasSize(7);
        assertThat(list).first().isEqualTo("Mercury");
        assertThat(list).last().isEqualTo("Neptune");

        list.add(6, "Uranus");
        assertThat(list).last().isEqualTo("Neptune");
        assertThat(list.get(6)).isEqualTo("Uranus");

        list.addFirst("Sun");
        assertThat(list).first().isEqualTo("Sun");
        assertThat(list).last().isEqualTo("Neptune");

        list.addLast("Pluto");
        assertThat(list.get(9)).isEqualTo("Pluto");


        assertThat(list.pollFirst()).isEqualTo("Sun");
        assertThat(list.pollLast()).isEqualTo("Pluto");

        assertThat(list).hasSize(8);

        list.clear();
        assertThat(list).isEmpty();
    }

    @Test
    public void test08_testHashMap() {
        Set<String> sets = new HashSet<>();

        sets.add("Cassava");
        sets.add("Rice");
        sets.add("Potato");

        assertThat(sets).hasSize(3);

        sets.add("Wheat");
        assertThat(sets).hasSize(4);

        sets.add("Potato");
        assertThat(sets).hasSize(4);


        assertThat(sets).contains("Potato");
        assertThat(sets).doesNotContain("Flour");

        assertThat(sets.isEmpty()).isFalse();

        sets.clear();
        assertThat(sets.isEmpty()).isTrue();
    }

    @Test
    public void test09_testHashSetCopy() {
        Set<String> sets = new HashSet<>();

        sets.add("Cassava");
        sets.add("Rice");
        sets.add("Potato");

        String[] arrays = new String[sets.size()];
        sets.toArray(arrays);

        assertThat(arrays[0]).isEqualTo("Cassava");
        assertThat(arrays[1]).isEqualTo("Rice");
        assertThat(arrays[2]).isEqualTo("Potato");
    }

    @Test
    public void test10_testTreeSet() {
        TreeSet<String> sets = new TreeSet<>();

        sets.add("Cassava");
        sets.add("Rice");
        sets.add("Potato");

        assertThat(sets.toString()).isEqualTo("[Cassava, Potato, Rice]");

    }

    @Test
    public void test11_testHashTable() {
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "Table");
        hashtable.put(2, "Marker Pen");
        hashtable.put(3, "Board");
        hashtable.put(3, "Cable");
        assertThat(hashtable.toString()).isEqualTo("{3=Cable, 2=Marker Pen, 1=Table}");
    }

    @Data
    @AllArgsConstructor
    class Price implements Comparable<Price> {
        int value;

        public int count() { return value; }

        @Override
        public int compareTo(@NotNull Price p) {
            return count() - p.count();
        }
    }

}