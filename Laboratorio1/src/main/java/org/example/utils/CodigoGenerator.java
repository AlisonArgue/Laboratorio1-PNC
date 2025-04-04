package org.example.utils;

import java.util.*;

public class CodigoGenerator {
    private static final Set<String> codigosGenerados = new HashSet<>();
    private static final Random random = new Random();
    private static final String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    public static String generarCodigoDoctor() {
        String codigo;
        do {
            int x1 = random.nextInt(10);
            char a1 = letras.charAt(random.nextInt(letras.length()));
            int x2 = random.nextInt(10);
            char a2 = letras.charAt(random.nextInt(letras.length()));
            codigo = String.format("ZNH-%d%c%d-MD-%c%d", x1, a1, x1, a2, x2);
        } while (codigosGenerados.contains(codigo));
        codigosGenerados.add(codigo);
        return codigo;
    }
}
