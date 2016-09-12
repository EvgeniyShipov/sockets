package ru.sbt.net;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistrator {
    public static void listen(String host, int port, Object impl) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try (Socket client = serverSocket.accept()) {
                Method method = Serializable.deserialize(client);
                Object[] args = Serializable.deserialize(client);
                String methodName = method.getName();

                Method method1 = impl.getClass().getMethod(methodName);
                Object result = method1.invoke(impl, args);

                Serializable.serialize(result, client);
            }
        }
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ServerRegistrator.listen("localhost", 5000, new CalculatorImpl());
    }
}
