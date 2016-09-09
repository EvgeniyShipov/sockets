package ru.sbt.net;

import java.io.*;

public class Serializable {

    public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream stream = new ObjectOutputStream(bytes)) {
            stream.writeObject(o);
        }

        return bytes.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(
                new ByteArrayInputStream(bytes))) {

            return (T) stream.readObject();
        }
    }
}
