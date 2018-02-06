package oop.hw2;


import java.io.PrintWriter;

public class Transformer {

    private FileHelperService fhs;

    private String outputPath;
    private static final String FILE_OUTPUT_ROTATE_180 = "out180.txt";
    private static final String FILE_OUTPUT_HORIZONTAL_MIRROR = "outHM.txt";
    private static final String FILE_OUTPUT_VERTICAL_MIRROR = "outVM.txt";
    private static final String FILE_OUTPUT_ROTATE = "outRot.txt";

    public Transformer(String outputPath) {
        this.outputPath = outputPath;
    }

    public void rotate180Transform(StringBuilder sb) {
        fhs = new FileHelperService();
        PrintWriter prntWrtr = fhs.createPW(outputPath + FILE_OUTPUT_ROTATE_180);
        fhs.writeToFile(prntWrtr, new StringBuilder(sb).reverse().toString());
        fhs.closePW(prntWrtr);
    }

    public void horizontalMirrorTransform(StringBuilder sb) {
        fhs = new FileHelperService();
        PrintWriter prntWrtr = fhs.createPW(outputPath + FILE_OUTPUT_HORIZONTAL_MIRROR);
        String[] temp = new StringBuilder(sb).toString().split("\n");
        for (String t : temp) {
            fhs.writeToFile(prntWrtr, new StringBuilder(t).reverse().toString());
        }
        fhs.closePW(prntWrtr);
    }

    public void verticalMirrorTransform(StringBuilder sb) {
        fhs = new FileHelperService();
        PrintWriter prntWrtrr = fhs.createPW(outputPath + FILE_OUTPUT_VERTICAL_MIRROR);
        String[] temp = new StringBuilder(sb).toString().split("\n");
        for(int i = temp.length - 1; i > 0; i--) {
            fhs.writeToFile(prntWrtrr, temp[i]);
        }
        fhs.closePW(prntWrtrr);
    }

    public void rotate(StringBuilder sb, int degree) {
        fhs = new FileHelperService();
        String[] temp = new StringBuilder(sb).toString().split("\n");
        String[][] matrix = new String[temp.length][temp[0].length()];
        for (int i = 0; i < temp.length; i++) {
            matrix[i] = temp[i].split("(?!^)");
        }

        int row = matrix.length;
        int col = matrix[0].length;
        String[][] rotated = null;

        for(int i=0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                if (degree == 90) {
                    if (rotated == null) rotated = new String[col][row];
                    rotated[col - j - 1][i] = matrix[i][j];
                }

                if (degree == 270) {
                    if (rotated == null) rotated = new String[col][row];
                    rotated[j][row - i - 1] = matrix[i][j];
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String[] r : rotated) {
            for (int j = 0; j < r.length; ++j)
                stringBuilder.append(r[j]);
            stringBuilder.append("\n");
        }

        PrintWriter printWriter = fhs.createPW(outputPath + FILE_OUTPUT_ROTATE);
        fhs.writeToFile(printWriter, stringBuilder.toString());
        fhs.closePW(printWriter);
    }

}
