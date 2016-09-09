package ru.sbt.net;

import java.io.IOException;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {
    public static void listen(String host, int port, Object impl) throws IOException {
        String methodName = null;
        Object[] args = null;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try (Socket client = serverSocket.accept()) {

//                прочитать два массива байт, десериализовать их
//                вызвать метод у калькулятора через рефлекшен
//                полученное значение серилизовать и отправить клиенту.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }
}
