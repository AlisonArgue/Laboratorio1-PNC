// Menu.java
package org.example.utils;

import org.example.service.DoctorServicio;
import org.example.service.PacienteServicio;
import org.example.service.CitaServicio;
import org.example.entity.Cita;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    int num;

    // Crear instancias para ocupar las funciones
    PacienteServicio pacienteServicio = new PacienteServicio();
    DoctorServicio doctorServicio = new DoctorServicio();
    CitaServicio citaServicio = new CitaServicio(pacienteServicio.getPacientes(), doctorServicio.getDoctores());

    // Menu de opciones para el usuario
    public void showMenu() {
        do {
            System.out.println("Mundo Salva Vidas >:D");
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Agregar Paciente");
            System.out.println("3. Agendar Cita");
            System.out.println("4. Buscador de citas");
            System.out.println("5. Mostrar listado completo de pacientes");
            System.out.println("6. Mostrar listado completo de doctores");
            System.out.println("7. Salir");
            System.out.println("Ingrese una opcion: ");
            num = sc.nextInt();

            switch (num) {
                case 1:
                    System.out.println("\nIngresar datos del Doctor");
                    doctorServicio.insertarDoctor();
                    citaServicio.actualizarDoctores(doctorServicio.getDoctores());
                    break;
                case 2:
                    System.out.println("\nIngresar datos del Paciente");
                    pacienteServicio.insertarPaciente();
                    break;
                case 3:
                    System.out.println("\nIngresar datos de la cita");
                    citaServicio.agendarCita();
                    break;
                case 4:
                    citaServicio.buscarCitas();
                    System.out.println("\nBuscar citas");
                    break;
                case 5:
                    pacienteServicio.mostrarPacientes();
                    break;
                case 6:
                    doctorServicio.mostrarDoctores();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (num != 7);
    }
}