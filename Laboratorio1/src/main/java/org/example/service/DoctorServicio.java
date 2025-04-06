// DoctorServicio.java
package org.example.service;

import org.example.entity.Doctor;
import java.util.*;
import org.example.utils.CodigoGenerator;
import org.example.entity.Especialidades;

public class DoctorServicio {

    private Collection<Doctor> doctores = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public DoctorServicio() {
        inicializarDoctores();
    }

    private void inicializarDoctores() {
        doctores.add(new Doctor("Juan", "Perez", "12345678-9", "1/1/1980", "1/1/2010", "Cardiología", "ZNH-1A1-MD-B2"));
        doctores.add(new Doctor("Ana", "Gomez", "98765432-1", "2/2/1985", "2/2/2015", "Pediatría", "ZNH-2B2-MD-C3"));
        doctores.add(new Doctor("Luis", "Martinez", "11223344-5", "3/3/1990", "3/3/2020", "Dermatología", "ZNH-3C3-MD-D4"));
    }

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

        Doctor nuevoDoctor = new Doctor(nombre, apellido, DUI, cumpleaños, reclutamiento, especialidad, codigo);
        doctores.add(nuevoDoctor);
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