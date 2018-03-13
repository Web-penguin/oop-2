package oop.hw2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {
    Pair<String, Integer> pair;

    @Before
    public void setUp() throws Exception {
        pair = new Pair<String, Integer>("Test", 10);
    }

    @Test
    public void getFirst() throws Exception {
        assertEquals(pair.getFirst(), "Test");
    }

    @Test
    public void getSecond() throws Exception {
        assertEquals((int)pair.getSecond(), 10);
    }

}