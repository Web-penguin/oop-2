package oop.hw2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

public class FileHelperService {

    public PrintWriter createPW(String outputPath) {
        PrintWriter prntWrtr = null;
        try {
            prntWrtr = new PrintWriter(outputPath);
        } catch (Exception e) {
            System.out.println(Constants.FILE_OPEN_ERROR);
            e.printStackTrace();
        }
        return prntWrtr;
    }

    public void closePW(PrintWriter prntWrtr) {
        try {
            prntWrtr.close();
        } catch (Exception e) {
            System.out.println(Constants.FILE_CLOSE_ERROR);
            e.printStackTrace();
        }
    }

    public void writeString(PrintWriter prntWrtr , String stringToWrite) {
        try {
            prntWrtr.print(stringToWrite);
            prntWrtr.flush();
        } catch (Exception e) {
            System.out.println(Constants.FILE_WRITE_ERROR);
            e.printStackTrace();
        }
    }

    public void writeToFile(String outputDir, String fileName, String stringToWrite) {
        PrintWriter prntWrtr = createPW(outputDir + fileName + ".txt");
        writeString(prntWrtr, stringToWrite);
        closePW(prntWrtr);
    }

    public BufferedImage readImg(String imgName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgName));
        } catch (Exception e) {
            System.out.println(Constants.READ_IMG_ERROR);
            e.printStackTrace();
        }
        return img;
    }
}
