package ru.sbt.net;

import java.io.*;
import java.net.Socket;

public class Serializable {

    public static void serialize(Object o, Socket socket) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
        stream.writeObject(o);
    }

    public static <T> T deserialize(Socket socket) throws IOException {
        ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());
        try {
            return (T) stream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
