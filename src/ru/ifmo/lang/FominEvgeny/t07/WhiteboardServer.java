package ru.ifmo.lang.FominEvgeny.t07;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;


public class WhiteboardServer {

    private static String message = "Start_message";

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/post", new PostHandler());
            server.createContext("/get", new GetHandler());
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class GetHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, 0);
            OutputStream ostream = exchange.getResponseBody();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ostream));
            writer.write(message);
            writer.close();
            exchange.close();
        }
    }

    private static class PostHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            URI uri = exchange.getRequestURI();
            OutputStream ostream = exchange.getResponseBody();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ostream));
            String query = uri.getQuery();
            message = query.substring(8);
            exchange.sendResponseHeaders(200, 0);
            writer.write("Message changed");
            writer.close();
            exchange.close();
        }
    }
}