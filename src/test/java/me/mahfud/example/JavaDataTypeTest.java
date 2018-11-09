package me.mahfud.example;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaDataTypeTest extends Assertions {

    @Test
    public void test01_integer() {
        int a = 2_000_000_000;
        int b = 2_000_000_000;

        assertThat(a + b).isEqualTo(-294_967_296);
        assertThat(a - b).isEqualTo(0);
    }

    @Test
    public void test02_long() {
        long a = 2_000_000_000;
        long b = 2_000_000_000;

        assertThat(a + b).isEqualTo(4_000_000_000L);

    }
}
