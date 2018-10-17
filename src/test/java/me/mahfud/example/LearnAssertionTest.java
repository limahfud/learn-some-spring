package me.mahfud.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Index;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Bidi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LearnAssertionTest extends Assertions {

    private Bird pigeon = new Bird("Pigeon", "White");
    private Bird eagle = new Bird("Eagle", "Brown");
    private Bird hawk = new Bird("Hawk", "Black");
    private Bird penguin = new Bird("Penguin", "white");
    private Bird seagull = new Bird("Seagull", "blue");


    @Test
    public void testWhenAddNewNumber() {
        List<String> list = new ArrayList<>();

        assertThat(list).isEmpty();
        list.add("Jaran");
        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list).hasSameSizeAs(list);

        list.add("Wedus");
        assertThat(list).hasSize(2);
        assertThat(list).hasSameSizeAs(Arrays.asList("Kuda", "Kambing"));
        assertThat(list).is(new Condition<String>() {
            @Override
            public boolean matches(String value) {
                return value.equals("Wedus");
            }
        }, Index.atIndex(1));

        assertThat(list).contains("Wedus", Index.atIndex(1));
        assertThat(list).contains("Jaran", Index.atIndex(0));
    }

    @Test
    public void testAssertList() {
        List<String> birds = new ArrayList<>(Arrays.asList("Eagle", "Hawk", "Penguin", "Pigeon", "Seagull"));

        assertThat(birds).contains("Pigeon");
        assertThat(birds).contains("Hawk");
        assertThat(birds).doesNotContain("Cheetah");
        assertThat(birds).doesNotContain("The Owl");

        birds.add("The Owl");
        assertThat(birds).contains("The Owl");

        assertThat(birds).isSorted();
    }

    @Test
    public void testAssertListClass() {
        List<Bird> birds = getListBird();

        Bird bird = new Bird("Josh", "Red");
        Bird anotherBird = new Bird("Josh", "Red");

        assertThat(bird).isEqualTo(anotherBird);

        anotherBird.setName("Mian");
        assertThat(bird).isNotEqualTo(anotherBird);

        assertThat(bird).isNotEqualTo(anotherBird);

        assertThat(birds)
                .filteredOn("color", not("Green"))
                .hasSize(3);

    }

    @Test
    public void testUsingContainOnly() {
        List<Bird> birds = getListBird();

        Bird phoenix = new Bird("Phoenix", "Black");
        Bird kop = new Bird("Kop", "Black");
        Bird toucan = new Bird("Toucan", "Black");

        birds.addAll(Arrays.asList(phoenix, kop));
        assertThat(birds).filteredOn("color", "Black")
                .containsOnly(phoenix, kop);

        birds.add(toucan);

        assertThat(birds).filteredOn("color", "Black")
                .containsOnly(toucan, phoenix, kop);
    }

    @Test
    public void flattenTheBirds() {
        pigeon.setAnotherBird(Arrays.asList(hawk, seagull));
        penguin.setAnotherBird(Arrays.asList(eagle, hawk, seagull));

        List<Bird> birds = Arrays.asList(penguin, pigeon);

        assertThat(birds).flatExtracting("anotherBird")
                .containsOnly(hawk, seagull, eagle);
    }

    @Test
    public void testSoftAssert() {
        List<Bird> birds = Arrays.asList(pigeon, penguin, seagull, hawk, eagle);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(birds).hasSize(4);
        softAssertions.assertThat(birds).first().isEqualTo(penguin);
        softAssertions.assertThat(birds).last().isEqualTo(hawk);

        softAssertions.assertAll();

    }

    @Test
    public void assertThatThrownBy() {
        List<Bird> birds = Arrays.asList(pigeon, penguin, seagull, hawk, eagle);

        assertThatThrownBy(() -> {
            Bird bird = birds.get(10);
        }).isInstanceOf(IndexOutOfBoundsException.class).hasMessageContaining("10");
    }

    @Test
    public void assertThatString() {
        assertThat("A quick brown fox jump to the box")
                .contains("quick")
                .contains("box")
                .contains("brown")
                .containsIgnoringCase("QuICK");

        assertThat("A quick brown fox jump to the box")
                .contains(Arrays.asList("quick", "brown", "fox"));

        assertThat("123087128038293").containsOnlyDigits();

        assertThat("many many many").containsOnlyOnce("many");
        assertThat("many happy returns")
                .containsOnlyOnce("many")
                .containsOnlyOnce("happy")
                .containsOnlyOnce("return")
                .doesNotContain("sherlock");

    }

    @Test
    public void assertThatStringFail() {
        String foxString = "A quick brown fox fox jump over the lazy dog 10";

        assertThat(foxString).contains("dumb");
        assertThat(foxString).containsOnlyOnce("fox");
        assertThat(foxString).contains(Arrays.asList("quick", "brown", "dox"));
        assertThat(foxString).containsOnlyDigits();
        assertThat(foxString).containsIgnoringCase("brOwnw");
        assertThat(foxString).containsPattern(Pattern.compile("[a-z]"));
        assertThat(foxString).isGreaterThan(foxString + "b");
        assertThat(foxString).isEmpty();
    }

    @Test
    public void assertThatStringFailFixed() {
        String foxString = "A quick brown fox fox jump over the lazy dog 10";
        String numericFoxString = "1409133190212824349364180853213941211";

        assertThat(foxString).doesNotContain("dumb");
        assertThat(foxString).containsOnlyOnce("fox fox");
        assertThat(foxString).contains(Arrays.asList("quick", "brown", "fox"));
        assertThat(numericFoxString).containsOnlyDigits();
        assertThat(foxString).containsIgnoringCase("brOwn");
        assertThat(foxString).containsPattern(Pattern.compile("[a-z]"));
        assertThat(foxString).isLessThan(foxString + "b");
        assertThat(foxString).isNotEmpty();
    }

    private List<Bird> getListBird() {
        List<Bird> birds = new ArrayList<>();
        List<String> birdsName = new ArrayList<>(Arrays.asList("Eagle", "Hawk", "Penguin", "Pigeon", "Seagull"));

        final int[] i = {0};
        birdsName.forEach((name) -> {
            if (i[0]++ % 2 == 0) {
                birds.add(new Bird(name, "Yellow"));
            } else {
                birds.add(new Bird(name, "Green"));
            }
        });

        return birds;
    }





    @Data
    @AllArgsConstructor
    class Bird {
        String name;
        String color;

        Bird(String name, String color) {
            this.name = name;
            this.color = color;
        }

        List<Bird> anotherBird;
    }
}
