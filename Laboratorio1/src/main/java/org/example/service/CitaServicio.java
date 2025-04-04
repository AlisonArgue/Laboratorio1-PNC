package org.example.service;

import org.example.entity.Cita;
import org.example.entity.Doctor;
import org.example.entity.Persona;
import java.util.*;

public class CitaServicio {

    private final List<Cita> citas = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final Collection<Persona> pacientes;
    private final Collection<Doctor> doctores;

    public CitaServicio(Collection<Persona> pacientes, Collection<Doctor> doctores) {
        this.pacientes = pacientes;
        this.doctores = doctores;
    }

    public void agendarCita() {
        if (pacientes.isEmpty()) {
            System.out.println("Debe haber al menos un paciente ingresado.");
            return;
        } else if (doctores.isEmpty()) {
            System.out.println("Debe haber al menos un doctor ingresado.");
        }


    }

}
