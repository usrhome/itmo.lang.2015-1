package ru.ifmo.lang.FominEvgeny.t02;

import java.io.*;

public class OddEvenFileSplitter implements FileSplitter {


    public static void main(final String[] args) {
        OddEvenFileSplitter oddEvenFileSplitter = new OddEvenFileSplitter();
        SplitConfig config = new SplitConfig() {

            public String getSourceFilePath() {
                File mainFile = new File(args[0]);
                return args[0];
            }

            public String getOddLinesFilePath() {
                File oddFile = new File(args[1]);
                try {
                    oddFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return args[1];
            }

            public String getEvenLinesFilePath() {
                File evenFile = new File(args[2]);
                try {
                    evenFile.createNewFile();
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
            Reader mainReader = new FileReader(config.getSourceFilePath());
            Writer oddWriter = new FileWriter(config.getOddLinesFilePath());
            Writer evenWriter = new FileWriter(config.getEvenLinesFilePath());
            BufferedReader bufferedReader = new BufferedReader(mainReader);
            int i = 0;
            String string = new String();
            string = config.getSourceFilePath();
            while ((string = bufferedReader.readLine()) != null) {
                if ((i % 2) == 0)
                    oddWriter.write(string + "\n");
                else evenWriter.write(string + "\n");
                i++;
            }
            mainReader.close();
            oddWriter.close();
            evenWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}