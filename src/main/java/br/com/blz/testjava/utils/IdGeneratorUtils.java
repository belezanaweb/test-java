package br.com.blz.testjava.utils;

import java.util.UUID;

public class IdGeneratorUtils {


    public static Integer generateId() {

        UUID pk = UUID.randomUUID();
        StringBuilder generics = new StringBuilder();
        generics.append(pk.toString().hashCode() / 2);

        return Integer.parseInt(generics.toString().replaceAll("-", ""));
    }

}
