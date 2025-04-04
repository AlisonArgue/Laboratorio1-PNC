package org.example.service;

import org.example.entity.Doctor;
import java.util.*;
import org.example.utils.CodigoGenerator;
import org.example.entity.Especialidades;

public class DoctorServicio {

    private Collection<Doctor> doctores = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void insertarDoctor() {
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Apellido: ");
        String apellido = sc.nextLine();

        System.out.println("DUI: ");
        String DUI = sc.nextLine();

        System.out.println("Cumpleaños (día/mes/año): ");
        String cumpleaños = sc.nextLine();

        System.out.println("Reclutamiento (día/mes/año): ");
        String reclutamiento = sc.nextLine();

        System.out.println("\nLista de especialidades:");
        for (int i = 1; i <= Especialidades.especialidades.size(); i++) {
            System.out.println(i + ". " + Especialidades.especialidades.get(i));
        }
        System.out.println("\nEspecialidad: ");
        int opc = sc.nextInt();
        sc.nextLine();

        String especialidad = Especialidades.especialidades.get(opc);
        if (especialidad == null) {
            System.out.println("Opción inválida.");
            return;
        }

        String codigo = CodigoGenerator.generarCodigoDoctor();
        System.out.println("Codigo: " + codigo);

        doctores.add(new Doctor(nombre, apellido, DUI, cumpleaños, reclutamiento, especialidad));
        System.out.println("Doctor agregado exitosamente.\n");

    }

    public void mostrarDoctores() {
        if (doctores.isEmpty()) {
            System.out.println("\nNo hay doctores registrados.\n");
            return;
        }

        System.out.println("\nListado de doctores:");
        doctores.forEach(d -> System.out.println("Nombre: " + d.getNombre() + " " + d.getApellido() +
                ", DUI: " + d.getDUI() + ", Cumpleaños: " + d.getCumpleaños() + ", Reclutamiento: " + d.getReclutamiento()
                + ", Especialidad: " + d.getEspecialidad() + ", Código: " + d.getCodigo()));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public Collection<Doctor> getDoctores() {
        return doctores;
    }

}
