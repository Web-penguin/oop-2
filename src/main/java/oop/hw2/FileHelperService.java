package oop.hw2;

import java.io.PrintWriter;

public class FileHelperService {

    private static final String FILE_OPEN_ERROR = "An error occurred while opening an output file";
    private static final String FILE_CLOSE_ERROR = "An error occurred while closing an output file";
    private static final String FILE_WRITE_ERROR = "An error occurred while writing an output file";

    public PrintWriter createPW(String outputPath) {
        PrintWriter prntWrtr = null;
        try {
            prntWrtr = new PrintWriter(outputPath);
        } catch (Exception e) {
            System.out.println(FILE_OPEN_ERROR);
            e.printStackTrace();
        }
        return prntWrtr;
    }

    public void closePW(PrintWriter prntWrtr) {
        try {
            prntWrtr.close();
        } catch (Exception e) {
            System.out.println(FILE_CLOSE_ERROR);
            e.printStackTrace();
        }
    }

    public void writeToFile(PrintWriter prntWrtr ,String stringToWrite) {
        try {
            prntWrtr.println(stringToWrite);
            prntWrtr.flush();
        } catch (Exception e) {
            System.out.println(FILE_WRITE_ERROR);
            e.printStackTrace();
        }
    }
}
