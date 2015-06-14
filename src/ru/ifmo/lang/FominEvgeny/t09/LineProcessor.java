package ru.ifmo.lang.FominEvgeny.t09;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wolfram on 18.04.15.
 */
public class LineProcessor {

    public static void main(final String[] args) {

        Path pathOutPut = Paths.get(args[0]);
        Path pathInPut = Paths.get(args[1]);


        try {

            for (int i = 2; i < args.length; i++) {

                List<String> strings = Files.readAllLines(pathOutPut);
                Stream<String> stream = strings.stream();

                if (args[i].equals("sort")) {

                    Files.write(pathInPut, strings.stream().sorted().collect(Collectors.toList()));

                }

                if (args[i].equals("skip")){

                    Files.write(pathInPut, strings.stream().skip(Long.parseLong(args[i + 1])).collect(Collectors.toList()));

                }

                if (args[i].equals("limit")) {

                    Files.write(pathInPut, strings.stream().limit(Integer.parseInt(args[i + 1])).collect(Collectors.toList()));

                }

                if (args[i].equals("filter")) {

                    for (int j = 0; j < strings.size(); j++) {

                        final int finalI = i;

                        Files.write(pathInPut, strings.stream().filter((s) -> s.matches(args[finalI + 1])).collect(Collectors.toList()));

                    }

                }

                if (args[i].equals("shuffle")) {

                    Collections.shuffle(stream.collect(Collectors.toList()));

                }

                if (args[i].equals("distinct")) {

                    Files.write(pathInPut, strings.stream().distinct().collect(Collectors.toList()));


                }

                pathOutPut = Paths.get(args[1]);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}