package oop.hw2;

public class Transformer {

    public StringBuilder rotate180Transform(StringBuilder sb) {
        return new StringBuilder(sb).reverse();
    }
	
	
	// Не вижу различий в этой и следующей функциях.
    public String[] horizontalMirrorTransform(StringBuilder sb) {
        return new StringBuilder(sb).toString().split("\n");
    }

    public String[] verticalMirrorTransform(StringBuilder sb) {
        return new StringBuilder(sb).toString().split("\n");
    }

    public StringBuilder rotate(StringBuilder sb, int degree) {
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
        return stringBuilder;
    }

}
