import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.Assert.*;

class MainTest {
    private Main maincl;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before CalculatorTest.class");
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("After CalculatorTest.class");
    }
    @Before
    public void initTest() {
        maincl = new Main();
    }
    @After
    public void afterTest() {
        maincl = null;
    }
    // normal work
    @Test
    public void test1() throws Exception {
        Fraction a = Main.calculate("1/2+4/5");
        assertEquals(a.numerator,13);
        assertEquals(a.denominator,10);
    }
    @Test
    public void test2() throws Exception {
        Fraction a = Main.calculate("1/2-4/5");
        assertEquals(a.numerator,-3);
        assertEquals(a.denominator,10);
    }
    @Test
    public void test3() throws Exception {
        Fraction a = Main.calculate("1/2*4/5");
        assertEquals(a.numerator,2);
        assertEquals(a.denominator,5);
    }
    @Test
    public void test4() throws Exception {
        Fraction a = Main.calculate("1/2:4/5");
        assertEquals(a.numerator,5);
        assertEquals(a.denominator,8);
    }
    // several functions
    @Test
    public void test5() throws Exception {
        Fraction a = Main.calculate("1/2*4/5:7/6");
        assertEquals(a.numerator,12);
        assertEquals(a.denominator,35);
    }
    @Test
    public void test6() throws Exception {
        Fraction a = Main.calculate("1/2*4/5+7/6");
        assertEquals(a.numerator,47);
        assertEquals(a.denominator,30);
    }
    @Test
    public void test7() throws Exception {
        Fraction a = Main.calculate("1/2-4/5:7/6");
        assertEquals(a.numerator,13);
        assertEquals(a.denominator,-70);
    }
    // errors
    @Test
    public void test8() throws Exception {
        assertEquals(Objects.equals(Main.calculate("1/2i-7"), null), true);
    }
    @Test
    public void test9() throws Exception {
        assertEquals(Objects.equals(Main.calculate("1/2-7"), null), true);
    }
    @Test
    public void test10() throws Exception {
        assertEquals(Objects.equals(Main.calculate("1/0+5/6"), null), true);
    }

}