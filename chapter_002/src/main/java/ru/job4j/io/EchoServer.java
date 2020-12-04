package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static boolean close = true;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {

            while (close) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String res = "";
                    StringBuilder sb = new StringBuilder();

                    while (!(str = in.readLine()).isEmpty()) {
                       sb.append(str);
                    }

                    final String line = sb.toString();

                    if (line.contains("msg=Hello")) {
                        res = "Hello, dear friend.";
                    } else if (line.contains("msg=Exit")) {
                        res = "Closed.";
                        close = false;
                    } else if (!line.contains("Hello") && !line.contains("msg=Exit")){
                        res = "What.";
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(res.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
        }
    }

