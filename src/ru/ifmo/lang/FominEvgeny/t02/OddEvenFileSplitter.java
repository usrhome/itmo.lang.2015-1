package ru.ifmo.lang.FominEvgeny.t02;

import java.io.*;

public class OddEvenFileSplitter implements FileSplitter {


    public static void main(final String[] args) {
        OddEvenFileSplitter oddEvenFileSplitter = new OddEvenFileSplitter();
        SplitConfig config = new SplitConfig() {

            public String getSourceFilePath() {
                File file1 = new File(args[0]);
                return args[0];
            }

            public String getOddLinesFilePath() {
                File file2 = new File(args[1]);
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return args[1];
            }

            public String getEvenLinesFilePath() {
                File file3 = new File(args[2]);
                try {
                    file3.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return args[2];
            }
        };
        oddEvenFileSplitter.splitFile(config);
    }

    public void splitFile(SplitConfig config) {
        try {
            Reader reader1 = new FileReader(config.getSourceFilePath());
            Writer writer2 = new FileWriter(config.getOddLinesFilePath());
            Writer writer3 = new FileWriter(config.getEvenLinesFilePath());
            BufferedReader bufferedReader = new BufferedReader(reader1);
            int i = 0;
            String string = new String();
            string = config.getSourceFilePath();
            while ((string = bufferedReader.readLine()) != null) {
                if ((i % 2) == 0)
                    writer2.write(string + "\n");
                else writer3.write(string + "\n");
                i++;
            }
            reader1.close();
            writer2.close();
            writer3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}