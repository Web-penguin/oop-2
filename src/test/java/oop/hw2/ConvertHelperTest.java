package oop.hw2;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

import static oop.hw2.ConstantsTest.pathToImg;
import static org.junit.Assert.*;

public class ConvertHelperTest {

    ConvertHelper convertHelper;

    @Before
    public void setUp() throws Exception {
        convertHelper = new ConvertHelper(ImageIO.read(new File(pathToImg)));
    }

    @Test
    public void getColor() throws Exception {
        Color actualColor = new Color(183, 183, 183);
        assertEquals(convertHelper.getColor(), actualColor);
    }

    @Test
    public void getGrayIndex() throws Exception {
        assertEquals(convertHelper.getGrayIndex(), 183);
    }

}