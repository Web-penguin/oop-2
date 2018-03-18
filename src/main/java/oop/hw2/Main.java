package oop.hw2;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class Main {

    @Parameter(names={"--unput", "-i"}, required = true)
    private String inputPath = null;
    @Parameter(names={"--output", "-o"}, required = true)
    private String outputPath = null;
    @Parameter(names={"--block", "-b"}, required = true)
	// Неинформативное название переменной, лучше заменить на что - то вроде decreasingSize
    private Integer blockSize = 1;
    @Parameter(names={"--horizontal", "-h"})
    private boolean horizontalMirror = false;
    @Parameter(names={"--vertical", "-v"})
    private boolean verticalMirror = false;
    @Parameter(names={"--rotate", "-r"})
    private int degree = 0;

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

//        Converter converter = new Converter(
//                main.inputPath, main.outputPath, main.blockSize,
//                main.horizontalMirror, main.verticalMirror, main.degree);
//        converter.run();
        ParallelTransform th = new ParallelTransform(main.inputPath, main.outputPath, main.blockSize,
                main.horizontalMirror, main.verticalMirror, main.degree);
        th.run();

    }
}
