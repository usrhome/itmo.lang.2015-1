package ru.ifmo.lang.FominEvgeny.t04;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSizeCalculatorImpl extends SimpleFileVisitor<Path> implements FileSizeCalculator {
    private static Long count = new Long(0);

    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes fileAttributes) {
        FileSizeCalculatorImpl fileSizeCalculator = new FileSizeCalculatorImpl();

        try {
            fileSizeCalculator.count += Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        count = new Long(count);

        return FileVisitResult.CONTINUE;

    }

    public static void main(String[] args) {
        FileSizeCalculatorImpl fileCount = new FileSizeCalculatorImpl();
        System.out.println("Size: " + fileCount.getSize(args[0], args[1]));
    }

    public long getSize(String pathToDir, String fileTemplate) {

        Path pathSource = Paths.get(pathToDir + "/" + fileTemplate);
        try {
            Files.walkFileTree(pathSource, new FileSizeCalculatorImpl());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}