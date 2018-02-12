package oop.hw2;

import java.awt.image.BufferedImage;

import java.io.PrintWriter;

public class Converter {

    private Transformer transformer;
    private FileHelperService fhs;
    private String inputPath;
    private String outputPath;
    private int blockSize = 1;
    private boolean horizontalMirror;
    private int degree;
    private boolean verticalMirror;

    public Converter(String inputPath, String outputPath, int blockSize, boolean horizontalMirror, boolean verticalMirror, int degree) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.blockSize = blockSize;
        this.degree = degree;
        this.horizontalMirror = horizontalMirror;
        this.verticalMirror = verticalMirror;
    }

    public StringBuilder convertToASCII(BufferedImage img) {
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

    public void transform(StringBuilder sb) throws Exception {
        fhs = new FileHelperService();
        StringBuilder stringBuilder = new StringBuilder();

        if (degree == 90) {
            PrintWriter prntWrtr = fhs.createPW(outputPath + Constants.FILE_OUTPUT_ROTATE + ".txt");
            stringBuilder = transformer.rotate(sb, 90);
            fhs.writeToFile(prntWrtr, stringBuilder.toString());
            fhs.closePW(prntWrtr);
        } else if (degree == 180) {
            PrintWriter prntWrtr = fhs.createPW(outputPath + Constants.FILE_OUTPUT_ROTATE + ".txt");
            stringBuilder = transformer.rotate180Transform(sb);
            fhs.writeToFile(prntWrtr, stringBuilder.toString());
            fhs.closePW(prntWrtr);
        } else if (degree == 270) {
            PrintWriter prntWrtr = fhs.createPW(outputPath + Constants.FILE_OUTPUT_ROTATE + ".txt");
            stringBuilder = transformer.rotate(sb, 270);
            fhs.writeToFile(prntWrtr, stringBuilder.toString());
            fhs.closePW(prntWrtr);
        }

        if (horizontalMirror) {
            PrintWriter prntWrtr = fhs.createPW(outputPath + Constants.FILE_OUTPUT_HORIZONTAL_MIRROR + ".txt");
            String[] strings = transformer.horizontalMirrorTransform(sb);
            for (String t : strings) {
                fhs.writeToFile(prntWrtr, new StringBuilder(t).reverse().toString() + "\n");
            }
            fhs.closePW(prntWrtr);
        }

        if (verticalMirror) {
            PrintWriter prntWrt = fhs.createPW(outputPath + Constants.FILE_OUTPUT_VERTICAL_MIRROR + ".txt");
            String[] strings = transformer.verticalMirrorTransform(sb);
            for(int i = strings.length - 1; i > 0; i--) {
                fhs.writeToFile(prntWrt, strings[i] + "\n");
            }
            fhs.closePW(prntWrt);
        }
    }

    public void run() throws Exception {
        fhs = new FileHelperService();
        transformer = new Transformer();

        BufferedImage img = fhs.readImg(inputPath);
        StringBuilder sb = convertToASCII(img);

        PrintWriter printWriter = fhs.createPW(outputPath + Constants.FILE_OUTPUT_MAIN + ".txt");
        fhs.writeToFile(printWriter, sb.toString());
        fhs.closePW(printWriter);

        transform(sb);
    }

}
