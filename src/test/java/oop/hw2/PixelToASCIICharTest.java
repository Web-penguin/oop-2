package oop.hw2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PixelToASCIICharTest {
    @Test
    public void toChar() throws Exception {
        char ch = '@';
        for (int i = 0; i < 250; ++i) {
            if (i == 50) ch = '#';
            if (i == 70) ch = '8';
            if (i == 100) ch = '&';
            if (i == 130) ch = 'o';
            if (i == 160) ch = ':';
            if (i == 180) ch = '*';
            if (i == 200) ch = '.';
            if (i == 230) ch = ' ';
            assertEquals(PixelToASCIIChar.toChar(i), ch);
        }
    }
}