package oop.hw2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.PrintWriter;

public class Converter {

    private Transformer transformer;
    private String inputPath;
    private String outputPath;
    private int blockSize;
    private boolean horizontalMirror;
    private int degree;
    private boolean verticalMirror;

    private static final String FILE_OUTPUT_MAIN = "out.txt";
    private static final String READ_IMG_ERROR = "An error occured while reading an input file";
    private static final String INCORRECT_DEGREE_VALUE = "Incorrect degree value";

    public Converter(String inputPath, String outputPath, int blockSize, boolean horizontalMirror, boolean verticalMirror, int degree) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.blockSize = blockSize;
        this.degree = degree;
        this.horizontalMirror = horizontalMirror;
        this.verticalMirror = verticalMirror;
    }

    private BufferedImage readImg(String imgName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgName));
        } catch (Exception e) {
            System.out.println(READ_IMG_ERROR);
            e.printStackTrace();
        }
        return img;
    }

    private StringBuilder convert(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();

        int rows = height / blockSize;
        int columns = width / blockSize;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ConvertHelper hlpr = new ConvertHelper(img.getSubimage(j * blockSize, i * blockSize, blockSize, blockSize));
                char selection;
                if (hlpr.getColor().getAlpha() < 150) {
                    selection = ' ';
                } else {
                    selection = PixelToASCIIChar.toChar(hlpr.getGrayIndex());
                }
                sb.append(selection);
            }
            sb.append("\n");
        }
        return sb;
    }

    private void transform(StringBuilder sb) throws Exception {

        if (degree == 90) {
            transformer.rotate(sb, 90);
        } else if (degree == 180) {
            transformer.rotate180Transform(sb);
        } else if (degree == 270) {
            transformer.rotate(sb, 270);
        } else {
            throw new Exception(INCORRECT_DEGREE_VALUE);
        }

        if (horizontalMirror) {
            transformer.horizontalMirrorTransform(sb);
        }

        if (verticalMirror) {
            transformer.verticalMirrorTransform(sb);
        }
    }

    public void run() throws Exception {
        FileHelperService fhs = new FileHelperService();
        transformer = new Transformer(outputPath);

        BufferedImage img = readImg(inputPath);
        StringBuilder sb = convert(img);

        PrintWriter printWriter = fhs.createPW(outputPath + FILE_OUTPUT_MAIN);
        fhs.writeToFile(printWriter, sb.toString());
        fhs.closePW(printWriter);

        transform(sb);
    }


}
