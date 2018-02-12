package oop.hw2;

import java.awt.image.BufferedImage;

import java.io.PrintWriter;

public class Converter {

    private Transformer transformer;
    private FileHelperService fhs;
    private String inputPath;
    private String outputPath;
    private int blockSize;
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

        if (degree == 90) {
            StringBuilder stringBuilder = transformer.rotate(sb, 90);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE, stringBuilder.toString());
        } else if (degree == 180) {
            StringBuilder stringBuilder = transformer.rotate180Transform(sb);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE, stringBuilder.toString());
        } else if (degree == 270) {
            StringBuilder stringBuilder = transformer.rotate(sb, 270);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE, stringBuilder.toString());
        }

        if (horizontalMirror) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strings = transformer.horizontalMirrorTransform(sb);
            for (String t : strings) {
                stringBuilder.append(new StringBuilder(t).reverse().toString() + "\n");
            }
                fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_HORIZONTAL_MIRROR, stringBuilder.toString());
        }

        if (verticalMirror) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strings = transformer.verticalMirrorTransform(sb);
            for(int i = strings.length - 1; i > 0; i--) {
                stringBuilder.append(strings[i] + "\n");
            }
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_VERTICAL_MIRROR, stringBuilder.toString());
        }
    }

    public void run() throws Exception {
        fhs = new FileHelperService();
        transformer = new Transformer();

        BufferedImage img = fhs.readImg(inputPath);
        StringBuilder sb = convertToASCII(img);
        fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_MAIN, sb.toString());

        transform(sb);
    }

}
