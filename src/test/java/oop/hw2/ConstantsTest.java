package oop.hw2;

import java.util.Arrays;
import java.util.List;

public class ConstantsTest {

    public static final String pathToImg = "src\\test\\resources\\test\\troll.png";
    public static final String pathToImgParallel = "src\\test\\resources\\test\\sudoku.png";
    public static final String pathToResult = "src\\test\\resources\\out\\";
    public static final String pathToActual = "src\\test\\resources\\test\\";

    public static final int blockSize = 1;
    public static final boolean horizontalMirror = true;
    public static final int degree = 90;
    public static final boolean verticalMirror = true;

    public static final List<Integer> linesHorizontalActual = Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331);
    public static final List<Integer> linesVerticalActual  = Arrays.asList(42, 83, 124, 125, 166, 207, 248, 249, 290, 331);

    public static final List<Pair<Integer, Integer>> partsHorizontalActual = Arrays.asList(new Pair<Integer, Integer>(0, 41),
            new Pair<Integer, Integer>(43, 82),
            new Pair<Integer, Integer>(84, 123),
            new Pair<Integer, Integer>(126, 165),
            new Pair<Integer, Integer>(167, 206),
            new Pair<Integer, Integer>(208, 247),
            new Pair<Integer, Integer>(250, 289),
            new Pair<Integer, Integer>(291, 330),
            new Pair<Integer, Integer>(332, 373));
    public static final List<Pair<Integer, Integer>> partsVerticalActual = Arrays.asList(new Pair<Integer, Integer>(0, 41),
            new Pair<Integer, Integer>(43, 82),
            new Pair<Integer, Integer>(84, 123),
            new Pair<Integer, Integer>(126, 165),
            new Pair<Integer, Integer>(167, 206),
            new Pair<Integer, Integer>(208, 247),
            new Pair<Integer, Integer>(250, 289),
            new Pair<Integer, Integer>(291, 330),
            new Pair<Integer, Integer>(332, 373));
}
