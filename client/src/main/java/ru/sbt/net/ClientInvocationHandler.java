package ru.sbt.net;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try (Socket socket = new Socket(host, port)) {
            Serializable.serialize(method, socket);
            Serializable.serialize(args, socket);

            result = Serializable.deserialize(socket);
        }
        return result;
    }
}
