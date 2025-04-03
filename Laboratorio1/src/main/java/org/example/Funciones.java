package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Funciones {

    public void calculateAge(Persona persona) {

        LocalDate cumpleaños = persona.getCumpleaños();

        if (cumpleaños != null) {

            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(cumpleaños, ahora);
            System.out.printf("Tu edad es: %s años, %s meses y %s días\n",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
        } else {
            System.err.println("Cumpleaños inválido");
        }
    }
}
