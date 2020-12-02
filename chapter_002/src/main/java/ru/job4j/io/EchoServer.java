package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean close = true;
            while (close) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("msg=Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                            break;

                        } else if (str.contains("msg=Exit")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Closed.".getBytes());
                            close = false;
                            break;
                        } else if (!str.contains("Hello") && !str.contains("msg=Exit")){
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("What.\r\n\r\n".getBytes());
                            break;
                        }
                    }
                }
            }
        }
    }
}
