package oop.hw2;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static oop.hw2.ConstantsTest.pathToImgParallel;
import static org.junit.Assert.*;

public class SplitterTest {

    BufferedImage inputImage;
    Splitter splitter;

    @Before
    public void setUp() throws Exception {
        splitter = new Splitter();
        inputImage = ImageIO.read(new File(pathToImgParallel));
    }

    @Test
    public void horizontalLines() throws Exception {
        List<Integer> actual = Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331);
        assertEquals(splitter.horizontalLines(inputImage, inputImage.getHeight(), inputImage.getWidth()), actual);
    }

    @Test
    public void verticalLines() throws Exception {
        List<Integer> actual = Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331);
        assertEquals(splitter.verticalLines(inputImage, inputImage.getHeight(), inputImage.getWidth()), actual);
    }

    @Test
    public void horizontalImageParts() throws Exception {
        List<Pair<Integer, Integer>> actual = Arrays.asList(
                new Pair<Integer, Integer>(0, 41),
                new Pair<Integer, Integer>(43, 82),
                new Pair<Integer, Integer>(84, 123),
                new Pair<Integer, Integer>(126, 165),
                new Pair<Integer, Integer>(167, 206),
                new Pair<Integer, Integer>(208, 247),
                new Pair<Integer, Integer>(250, 289),
                new Pair<Integer, Integer>(291, 330),
                new Pair<Integer, Integer>(332, 373));
        assertEquals(splitter.horizontalImageParts(inputImage, Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331)), actual);
    }

    @Test
    public void verticalImageParts() throws Exception {
        List<Pair<Integer, Integer>> actual = Arrays.asList(new Pair<Integer, Integer>(0, 41),
                new Pair<Integer, Integer>(43, 82),
                new Pair<Integer, Integer>(84, 123),
                new Pair<Integer, Integer>(126, 165),
                new Pair<Integer, Integer>(167, 206),
                new Pair<Integer, Integer>(208, 247),
                new Pair<Integer, Integer>(250, 289),
                new Pair<Integer, Integer>(291, 330),
                new Pair<Integer, Integer>(332, 373));
        assertEquals(splitter.verticalImageParts(inputImage, Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331)), actual);
    }

    @Test
    public void cutImage() throws Exception {
        List<BufferedImage> parts = splitter.cutImage(inputImage).getFirst();
        for (Integer i = 0; i < parts.size(); ++i){
            assertTrue(
                    bufferedImagesEqual(
                            parts.get(i),
                            ImageIO.read(new File(String.format("src\\test\\resources\\test\\parts\\part%s.png", i)))
                    )
            );
        }
    }

    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}

