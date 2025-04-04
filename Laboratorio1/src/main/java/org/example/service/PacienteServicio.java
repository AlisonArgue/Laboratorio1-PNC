package org.example.service;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import org.example.entity.Persona;

public class PacienteServicio {
    private  Collection<Persona> pacientes = new ArrayList<>();
    private  Scanner sc = new Scanner(System.in);

    public int calcularEdad(Persona persona) {
        LocalDate cumpleaños = persona.getCumpleaños();
        if (cumpleaños != null) {
            return Period.between(cumpleaños, LocalDate.now()).getYears();
        }
        return -1;
    }

    public void insertarPaciente() {
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Apellido: ");
        String apellido = sc.nextLine();

        System.out.println("Cumpleaños (día/mes/año): ");
        String cumpleaños = sc.nextLine();

        Persona tempPersona = new Persona(nombre, apellido, "", cumpleaños);
        int edad = calcularEdad(tempPersona);

        String DUI;
        if (edad < 18 && edad != -1) {
            DUI = "00000000-0";
            System.out.println("DUI: " + DUI);
        } else if (edad == -1) {
            System.err.println("Fecha de cumpleaños inválida.");
            return;
        } else {
            System.out.println("DUI: ");
            DUI = sc.nextLine();
        }

        pacientes.add(new Persona(nombre, apellido, DUI, cumpleaños));
        System.out.println("\nPaciente agregado exitosamente.\n");
    }

    public void mostrarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("\nNo hay pacientes registrados.\n");
            return;
        }

        System.out.println("\nListado de pacientes:");
        pacientes.forEach(p -> System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido() +
                ", DUI: " + p.getDUI() + ", Cumpleaños: " + p.getCumpleaños()));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public Collection<Persona> getPacientes() {
        return pacientes;
    }
}
