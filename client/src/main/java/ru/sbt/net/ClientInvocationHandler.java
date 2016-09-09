package ru.sbt.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;
    Serializable serializable = new Serializable();

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try (Socket Socket = new Socket(host, port)) {
            byte[] serializeMethod = serializable.serialize(method);
            byte[] serializeArgs = serializable.serialize(args);

            DataOutputStream out = new DataOutputStream(Socket.getOutputStream());
            out.write(serializeMethod);
            out.write(serializeArgs);

            DataInputStream in = new DataInputStream(Socket.getInputStream());
            byte[] value = new byte[in.available()];
            int i = 0;
            while (in.available() > 0) {
                value[i] = in.readByte();
                i++;
            }

            result = serializable.deserialize(value);
        }
        return result;
    }
}
