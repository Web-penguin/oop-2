package oop.hw2;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


import static org.junit.Assert.*;

public class ParallelTransformTest {
    private static BufferedImage img;
    private static String pathToImg;
    private static String pathToResult;

    private static ParallelTransform parallelTransform;
    private static Splitter splitter;
    private static int blockSize;
    private static boolean horizontalMirror;
    private static int degree;
    private static boolean verticalMirror;

    @BeforeClass
    public static void init() throws IOException {
        pathToImg = ConstantsTest.pathToImgParallel;
        img = ImageIO.read(new File(pathToImg));
        pathToResult = ConstantsTest.pathToResult;
        blockSize = ConstantsTest.blockSize;
        horizontalMirror = ConstantsTest.horizontalMirror;
        degree = ConstantsTest.degree;
        verticalMirror = ConstantsTest.verticalMirror;
        parallelTransform = new ParallelTransform(pathToImg, pathToResult, blockSize, horizontalMirror, verticalMirror, degree);
        splitter = new Splitter();
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
        parallelTransform = null;
    }

    @Test
    public void testFindLines() {
        int height = img.getHeight();
        int width = img.getWidth();
        List<Integer> resultLinesVertical = splitter.verticalLines(img, height, width);
        List<Integer> resultLinesHorizontal = splitter.horizontalLines(img, height, width);
        List<Integer> linesHorizontalActual = ConstantsTest.linesHorizontalActual;
        List<Integer> linesVerticalActual = ConstantsTest.linesVerticalActual;
        assertEquals(linesVerticalActual, resultLinesVertical);
        assertEquals(linesHorizontalActual, resultLinesHorizontal);
    }

    @Test
    public void testImageParts() {
        int height = img.getHeight();
        int width = img.getWidth();
        List<Integer> resultLinesVertical = splitter.verticalLines(img, height, width);
        List<Integer> resultLinesHorizontal = splitter.horizontalLines(img, height, width);
        List<Pair<Integer, Integer>> hparts = splitter.horizontalImageParts(img, resultLinesVertical);
        List<Pair<Integer, Integer>> vparts = splitter.verticalImageParts(img, resultLinesHorizontal);

        List<Pair<Integer, Integer>> hpartsActual = ConstantsTest.partsHorizontalActual;
        List<Pair<Integer, Integer>> vpartsActual = ConstantsTest.partsVerticalActual;
        assertEquals(hparts.size(), hpartsActual.size());
        assertEquals(vparts.size(), vpartsActual.size());
        for (int i = 0; i < hparts.size(); i++) {
            assertEquals(hparts.get(i).getSecond() - hparts.get(i).getFirst(),
                    hpartsActual.get(i).getSecond() - hpartsActual.get(i).getFirst());
        }
      for (int i = 0; i < vparts.size(); i++) {
            assertEquals(vparts.get(i).getSecond() - vparts.get(i).getFirst(),
                    vpartsActual.get(i).getSecond() - vpartsActual.get(i).getFirst());
        }
    }

    @Test
    public void testParallelImage() throws IOException {
        try {
            parallelTransform.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File fileTest = new File(ConstantsTest.pathToResult + "out.txt");
        File fileActual = new File(ConstantsTest.pathToActual + "outParallel.txt");
        assertTrue(FileUtils.contentEquals(fileTest, fileActual));
    }
}