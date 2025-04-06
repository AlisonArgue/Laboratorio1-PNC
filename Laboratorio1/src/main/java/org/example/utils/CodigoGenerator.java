package org.example.utils;

import java.util.*;

public class CodigoGenerator {
    private static final Set<String> codigosGenerados = new HashSet<>();
    private static final Random random = new Random();

    public static String generarCodigoDoctor() {
        String codigo;
        do {
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10);
            char letra1 = generarLetraAleatoria();
            char letra2 = generarLetraAleatoria();

            codigo = String.format("ZNH-%d%c%d-MD-%c%d", num1, letra1, num1, letra2, num2);
        } while (codigosGenerados.contains(codigo));

        codigosGenerados.add(codigo);
        return codigo;
    }

    private static char generarLetraAleatoria() {
        int ascii = (int) Math.floor(Math.random() * (122 - 97 + 1) + 97);
        return Character.toUpperCase((char) ascii);
    }
}
