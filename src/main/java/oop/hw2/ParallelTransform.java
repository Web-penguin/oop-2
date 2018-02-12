package oop.hw2;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParallelTransform {

    private String inputPath;
    private String outputPath;
    private int blockSize;
    private boolean horizontalMirror;
    private int degree;
    private boolean verticalMirror;

    private FileHelperService fhs;

    public ParallelTransform(String inputPath, String outputPath, int blockSize, boolean horizontalMirror, boolean verticalMirror, int degree) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.blockSize = blockSize;
        this.degree = degree;
        this.horizontalMirror = horizontalMirror;
        this.verticalMirror = verticalMirror;
    }

    private void transform(StringBuilder sb, int part) {
        fhs = new FileHelperService();
        Transformer transformer = new Transformer();

        if (degree == 90) {
            StringBuilder stringBuilder = transformer.rotate(sb, 90);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE + "_" + part, stringBuilder.toString());
        } else if (degree == 180) {
            StringBuilder stringBuilder = transformer.rotate180Transform(sb);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE + "_" + part, stringBuilder.toString());
        } else if (degree == 270) {
            StringBuilder stringBuilder = transformer.rotate(sb, 270);
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_ROTATE + "_" + part, stringBuilder.toString());
        }

        if (horizontalMirror) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] str = transformer.horizontalMirrorTransform(sb);

            for (String t : str) {
                stringBuilder.append(new StringBuilder(t).reverse().toString()+"\n");
            }
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_HORIZONTAL_MIRROR + "_" + part, stringBuilder.toString());
        }

        if (verticalMirror) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] str = transformer.verticalMirrorTransform(sb);

            for(int i = str.length - 1; i > 0; i--) {
                stringBuilder.append(str[i]+"\n");
            }
            fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_VERTICAL_MIRROR + "_" + part, stringBuilder.toString());
        }
    }

    private void concatOutput(String outputPath, String outputFileName, int verticalCount, int horizontalCount) {
        fhs = new FileHelperService();
        PrintWriter printWriter = fhs.createPW(outputPath + outputFileName + ".txt");

        for(int i = 0; i < verticalCount; i++) {
            String result = "";

            List<BufferedReader> subImgReaders = new ArrayList<BufferedReader>();
            for (int j = 0; j < horizontalCount; j++) {
                String path = outputPath + outputFileName + "_" + (verticalCount * i + j) + ".txt";
                try {
                    BufferedReader brCurrent = new BufferedReader(new FileReader(path));
                    subImgReaders.add(brCurrent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                String line;
                if (subImgReaders.get(0) != null) {
                    while ((line = subImgReaders.get(0).readLine()) != null) {
                        result += line;
                        for (int j = 1; j < subImgReaders.size(); j++) {
                            result += subImgReaders.get(j).readLine();
                        }
                        result += "\n";
                    }
                    fhs.writeString(printWriter, result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 0; j < subImgReaders.size(); j++) {
                try {
                    subImgReaders.get(j).close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        fhs.closePW(printWriter);

        File dir = new File(outputPath);
            for (File f : dir.listFiles()) {
            if (f.getName().startsWith(outputFileName + "_")) {
                f.delete();
            }
        }
    }

    public void run() throws Exception {
        fhs = new FileHelperService();
        Splitter splitter = new Splitter();
        final Converter converter = new Converter(inputPath, outputPath, blockSize, horizontalMirror, verticalMirror, degree);

        BufferedImage img = fhs.readImg(inputPath);
        final Pair<List<BufferedImage>, Pair<Integer, Integer>> parts = splitter.cutImage(img);

        for (int i = 0; i < parts.getFirst().size(); i++) {
            final int tmp = i;
            new Thread("part_" + i) {
                public void run() {
                    StringBuilder sb = converter.convertToASCII(parts.getFirst().get(tmp));
                    fhs.writeToFile(outputPath, Constants.FILE_OUTPUT_MAIN + "_" + tmp, sb.toString());
                    transform(sb, tmp);
                }
            }.run();
        }

        int horizontalCount = parts.getSecond().getFirst();
        int verticalCount = parts.getSecond().getSecond();

        concatOutput(outputPath, Constants.FILE_OUTPUT_MAIN, verticalCount, horizontalCount);

        if (horizontalMirror) {
            concatOutput(outputPath, Constants.FILE_OUTPUT_HORIZONTAL_MIRROR, verticalCount, horizontalCount);
        }
        if (verticalMirror) {
            concatOutput(outputPath, Constants.FILE_OUTPUT_VERTICAL_MIRROR, verticalCount, horizontalCount);
        }
        if (degree != 0) {
            concatOutput(outputPath, Constants.FILE_OUTPUT_ROTATE, verticalCount, horizontalCount);
        }
    }
}
