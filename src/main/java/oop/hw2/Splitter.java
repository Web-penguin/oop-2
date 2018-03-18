package oop.hw2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Splitter {

    private Color lineColor = new Color(30, 30, 30);

    private boolean checkColor(Color first, Color second) {
        return first.getRed() <= second.getRed() &&
                first.getBlue() <= second.getBlue() &&
                first.getGreen() <= second.getGreen();
    }
	
	/*
	Эту и следующую функции можно объединить в одну с параметров option
	для того, чтобы убрать дубликацию
	*/
    private List<Integer> horizontalLines(BufferedImage img, int height, int width) {

        List<Integer> horizontal = new ArrayList<Integer>();

        for (int i = 0; i < height; i++) {

            Color startLineColor = new Color(img.getRGB(0, i));
            boolean isHorizontalLine = false;
            if (checkColor(startLineColor, lineColor)) {
                for (int j = 0; j < width; j++) {
                    isHorizontalLine = checkColor(new Color(img.getRGB(j, i)), lineColor);

                }
                if (isHorizontalLine) {
                    horizontal.add(i);
                }
            }
        }
        return horizontal;
    }

    private List<Integer> verticalLines(BufferedImage img, int height, int width) {

        Color lineColor = new Color(30, 30, 30);
        List<Integer> vertical = new ArrayList<Integer>();
        for (int i = 0; i < width; i++) {
            Color startLineColor = new Color(img.getRGB(i, 0));
            boolean isVerticalLine = true;
            if (checkColor(startLineColor, lineColor)) {
                for (int j = 0; j < height; j++) {
                    isVerticalLine = !checkColor(new Color(img.getRGB(i, j)), lineColor);
                }
                if (!isVerticalLine) {
                    vertical.add(i);
                }
            }
        }
        return vertical;
    }
	
	/*
	Аналогично, эту и следующую функции можно объединить в одну с параметров option
	для того, чтобы убрать дубликацию
	*/
    private List<Pair<Integer, Integer>> horizontalImageParts(BufferedImage img, List<Integer> vertical) {
        Integer startXPart = 0;
        Integer endXPart = 0;

        List<Pair<Integer, Integer>> horParts = new ArrayList<Pair<Integer, Integer>>();

        for (int x = 0; x < img.getWidth()+1; x++) {
            if (vertical.contains(x) || x == img.getWidth()) {
                endXPart = x-1;
                if (!vertical.contains(x-1)) {
                    horParts.add(new Pair(startXPart, endXPart));
                }
                if (!vertical.contains(x+1)) {

                    startXPart = x+1;
                } else {
                    continue;
                }
            }
        }
        return horParts;
    }

    private List<Pair<Integer, Integer>> verticalImageParts(BufferedImage img, List<Integer> horizontal) {
        Integer startYPart = 0;
        Integer endYPart = 0;
        List<Pair<Integer, Integer>> vertParts = new ArrayList<Pair<Integer, Integer>>();

        for (int y = 0; y < img.getHeight()+1; y++) {
            if (horizontal.contains(y) || y == img.getHeight()) {
                endYPart = y-1;
                if (!horizontal.contains(y-1)) {
                    vertParts.add(new Pair(startYPart, endYPart));
                }
                if (!horizontal.contains(y+1)) {
                    startYPart = y+1;
                } else {
                    continue;
                }
            }
        }
        return vertParts;
    }

    public Pair<List<BufferedImage>, Pair<Integer, Integer>> cutImage(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        List<Integer> hor = horizontalLines(img, height, width);
        List<Integer> vert = verticalLines(img, height, width);
        List<Pair<Integer, Integer>> hparts = horizontalImageParts(img, vert);
        List<Pair<Integer, Integer>> vparts = verticalImageParts(img, hor);

        List<BufferedImage> imageParts = new ArrayList<BufferedImage>();
        for (Pair<Integer, Integer> vpart : vparts) {
            for (Pair<Integer, Integer> hpart : hparts) {
                int x = hpart.getFirst();
                int y = vpart.getFirst();
                int w = hpart.getSecond() - hpart.getFirst();
                int h = vpart.getSecond() - vpart.getFirst();
                imageParts.add(img.getSubimage(x, y, w, h));
            }
        }

        Pair<Integer, Integer> count = new Pair<Integer, Integer>(hparts.size(), vparts.size());

        Pair<List<BufferedImage>, Pair<Integer, Integer>> res =
                new Pair<List<BufferedImage>, Pair<Integer, Integer>>(imageParts, count);

        return res;
    }
}
