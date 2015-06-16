package ru.ifmo.lang.FominEvgeny.t07;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WhiteboardClient {
    public static void main(String[] args) {
        switch (args[0]) {
            case "post": {
                postMessage(args[1]);
                break;
            }
            case "get": {
                System.out.println(getMessage());
                break;
            }
            default: {
                System.out.println("Check correctness.");
            }
        }
    }

    private static void postMessage(String message) {
        try {
            URL url = new URL("http://localhost:8080/post?message=" + URLEncoder.encode(message, "UTF-8"));
            URLConnection connection = url.openConnection();
            InputStream istream = connection.getInputStream();
            istream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMessage() {
        try {
            URL url = new URL("http://localhost:8080/get");
            URLConnection connection = url.openConnection();
            InputStream istream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
            String message = reader.readLine();
            reader.close();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}