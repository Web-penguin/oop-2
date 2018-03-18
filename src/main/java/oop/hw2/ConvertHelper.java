package oop.hw2;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ConvertHelper {

    private BufferedImage img;
    private Color imgColor;
    private int grayIndex;

    public ConvertHelper(BufferedImage img) {
        this.img = img;
        init();
    }
	
	// Почему бы просто не написать это в конструкторе?
    private void init() {

        int height = img.getHeight();
        int width = img.getWidth();

        int reds = 0;
        int greens = 0;
        int blues = 0;
        int alphas = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color clr = new Color(img.getRGB(i, j));
				// Можно не выделять в отдельные переменные, а сразу добавлять в сумму.
                int red = clr.getRed();
                int green = clr.getGreen();
                int blue = clr.getBlue();
                int alpha = clr.getAlpha();

                reds += red;
                greens += green;
                blues += blue;
                alphas += alpha;
            }
        }

        int pixelCount = height * width;
        int red = reds / pixelCount;
        int green = greens / pixelCount;
        int blue = blues / pixelCount;
        int alpha = alphas / pixelCount;
        imgColor = new Color(red, green, blue, alpha);
        grayIndex = (int) (red * 0.3 + blue * 0.59 + green * 0.11);
    }

    public Color getColor() {
        return imgColor;
    }

    public int getGrayIndex() {
        return grayIndex;
    }
}
