package ru.ifmo.lang.FominEvgeny.t06;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleGrepUtility implements Grep {
    private BufferedReader bufferedReader;
    public String string;

    private SimpleGrepUtility(InputStream stream) {

        bufferedReader = new BufferedReader(new InputStreamReader(stream));

    }

    public static void main(final String[] args) {

        try {

            if (args.length == 2) {

                Grep grep = new SimpleGrepUtility(new BufferedInputStream(new FileInputStream(args[1])));
                System.out.println(grep.findLines(args[0]));

            } else if (args.length > 3) {
                
                System.out.println("Неизвестная команда");

            } else {

                Grep grep = new SimpleGrepUtility(new BufferedInputStream(new FileInputStream(args[2])));

                if (args[0].equals("-o")) {
                    System.out.println(grep.findParts(args[1]));
                } else if (args[0].equals("-v")) {
                    System.out.println(grep.findInvertMatch(args[1]));
                } else {
                    System.out.println("Неизвестная команда");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> findLines(String regex) {
        string = new String();
        List<String> lines = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        try {
            while ((string = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    lines.add(string);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> findParts(String regex) {
        string = new String();
        List<String> lines = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        try {
            while ((string = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(string);
                while (matcher.find()) {
                    lines.add(matcher.group());
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> findInvertMatch(String regex) {
        string = new String();
        List<String> lines = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        try {
            while ((string = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(string);
                if (!matcher.find()) {
                    lines.add(string);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}