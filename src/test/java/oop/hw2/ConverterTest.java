package oop.hw2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

public class ConverterTest {

    private static BufferedImage img;
    private static String pathToImg;
    private static String pathToResult;
    private static Converter converter;
    private static int blockSize;
    private static boolean horizontalMirror;
    private static int degree;
    private static boolean verticalMirror;

    @BeforeClass
    public static void init() throws IOException {
        pathToImg = ConstantsTest.pathToImg;
        img = ImageIO.read(new File(pathToImg));
        pathToResult = ConstantsTest.pathToResult;
        blockSize = ConstantsTest.blockSize;
        horizontalMirror = ConstantsTest.horizontalMirror;
        degree = ConstantsTest.degree;
        verticalMirror = ConstantsTest.verticalMirror;
        converter = new Converter(pathToImg, pathToResult, blockSize, horizontalMirror, verticalMirror, degree);
    }

    @AfterClass
    public static void tearDown() {
        pathToImg = null;
        img = null;
        pathToResult = null;
        blockSize = 0;
        horizontalMirror = false;
        degree = 0;
        verticalMirror = false;
        converter = null;
    }

    @Test
    public void convertToASCII() throws IOException {
        StringBuilder asciiImage = converter.convertToASCII(img);
        char[] ascii = new char[asciiImage.length()];
        asciiImage.getChars(0, asciiImage.length(), ascii, 0);
        File fileActual = new File(ConstantsTest.pathToActual + "out.txt");
        FileReader readerActual = new FileReader(fileActual);
        char[] data = new char[(int)fileActual.length()];
        readerActual.read(data);
        assertArrayEquals(data, ascii);
    }

    @Test
    public void rotate90() throws Exception {
        StringBuilder ascii = converter.convertToASCII(img);
        converter.transform(ascii);
        File fileTest = new File(ConstantsTest.pathToResult + "outRot.txt");
        File fileActual = new File(ConstantsTest.pathToActual + "outRot.txt");
        assertTrue(FileUtils.contentEquals(fileTest, fileActual));
    }

    @Test
    public void horizontalMirror() throws Exception {
        StringBuilder ascii = converter.convertToASCII(img);
        converter.transform(ascii);
        File fileTest = new File(ConstantsTest.pathToResult + "outHM.txt");
        File fileActual = new File(ConstantsTest.pathToActual + "outHM.txt");
        assertTrue(FileUtils.contentEquals(fileTest, fileActual));
    }

    @Test
    public void verticalMirror() throws Exception {
        StringBuilder ascii = converter.convertToASCII(img);
        converter.transform(ascii);
        File fileTest = new File(ConstantsTest.pathToResult + "outVM.txt");
        File fileActual = new File(ConstantsTest.pathToActual + "outVM.txt");
        assertTrue(FileUtils.contentEquals(fileTest, fileActual));
    }
}