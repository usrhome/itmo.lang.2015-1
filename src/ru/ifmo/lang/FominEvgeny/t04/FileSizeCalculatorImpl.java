package ru.ifmo.lang.FominEvgeny.t04;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSizeCalculatorImpl extends SimpleFileVisitor<Path> implements FileSizeCalculator {
    private long count = 0;

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        FileSizeCalculatorImpl fileSizeCalculator = new FileSizeCalculatorImpl();

        try {
            count += Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return FileVisitResult.CONTINUE;

    }

    public static void main(String[] args) {
        FileSizeCalculatorImpl fileCount = new FileSizeCalculatorImpl();
        System.out.println("Size: " + fileCount.getSize("",""));
    }

    public long getSize(String pathToDir, String fileTemplate) {

        Path pathSource = Paths.get("/home/wolfram/smth");
        try {
            Files.walkFileTree(pathSource, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}