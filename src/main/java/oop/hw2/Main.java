package oop.hw2;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {

    // Argumants usage:
    // -i <path to image> -o <path to output folder> -b <block size (decrease size)>
    // -h (horizontal flip) -v (vertical flip) -r <90|180|270>

    @Parameter(names={"--unput", "-i"}, required = true)
    private String inputPath = null;
    @Parameter(names={"--output", "-o"}, required = true)
    private String outputPath = null;
    @Parameter(names={"--block", "-b"}, required = true)
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

        Converter converter = new Converter(
                main.inputPath, main.outputPath, main.blockSize,
                main.horizontalMirror, main.verticalMirror, main.degree);
        converter.run();
    }
}
