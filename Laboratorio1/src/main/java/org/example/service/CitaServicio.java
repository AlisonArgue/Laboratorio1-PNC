// CitaServicio.java
package org.example.service;

import org.example.entity.Cita;
import org.example.entity.Doctor;
import org.example.entity.Persona;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CitaServicio {

    private final List<Cita> citas = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final Collection<Persona> pacientes;
    private Collection<Doctor> doctores;

    public CitaServicio(Collection<Persona> pacientes, Collection<Doctor> doctores) {
        this.pacientes = pacientes;
        this.doctores = doctores;
        inicializarCitas();
    }

    public void actualizarDoctores(Collection<Doctor> doctores) {
        this.doctores = doctores;
    }

    private void inicializarCitas() {
        List<Persona> listaPacientes = new ArrayList<>(pacientes);
        List<Doctor> listaDoctores = new ArrayList<>(doctores);

        if (listaPacientes.size() >= 3 && listaDoctores.size() >= 3) {
            citas.add(new Cita(listaPacientes.get(0), listaDoctores.get(0), "10/10/2023", "8:00 AM"));
            citas.add(new Cita(listaPacientes.get(1), listaDoctores.get(1), "11/10/2023", "4:00 PM"));
            citas.add(new Cita(listaPacientes.get(2), listaDoctores.get(2), "12/10/2023", "8:00 AM"));
        }
    }

    public void agendarCita() {
        if (pacientes.isEmpty()) {
            System.out.println("Debe haber al menos un paciente ingresado.");
            return;
        } else if (doctores.isEmpty()) {
            System.out.println("Debe haber al menos un doctor ingresado.");
            return;
        }

        System.out.println("Seleccione un paciente:");
        List<Persona> listaPacientes = new ArrayList<>(pacientes);
        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println((i + 1) + ". " + listaPacientes.get(i).getNombre() + " " + listaPacientes.get(i).getApellido());
        }
        int pacienteIndex = sc.nextInt() - 1;
        sc.nextLine();
        Persona paciente = listaPacientes.get(pacienteIndex);

        System.out.println("Seleccione un doctor:");
        List<Doctor> listaDoctores = new ArrayList<>(doctores);
        for (int i = 0; i < listaDoctores.size(); i++) {
            System.out.println((i + 1) + ". " + listaDoctores.get(i).getNombre() + " " + listaDoctores.get(i).getApellido() + " - " + listaDoctores.get(i).getEspecialidad());
        }
        int doctorIndex = sc.nextInt() - 1;
        sc.nextLine();
        Doctor doctor = listaDoctores.get(doctorIndex);

        LocalDate fechaCita;
        while (true) {
            System.out.println("Ingrese la fecha de la cita (día/mes/año):");
            String fecha = sc.nextLine();
            try {
                fechaCita = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy"));
                if (fechaCita.isBefore(LocalDate.now())) {
                    System.out.println("La fecha debe ser hoy o una fecha futura. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

        LocalTime horaCita;
        while (true) {
            System.out.println("Ingrese la hora de la cita y tambien el tiempo de cita es de 1 hora (HH:mm, entre 08:00 y 16:00):");
            String hora = sc.nextLine();
            try {
                horaCita = LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
                if (horaCita.isBefore(LocalTime.of(8, 0)) || horaCita.isAfter(LocalTime.of(16, 0))) {
                    System.out.println("La hora debe estar entre las 08:00 y las 16:00. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Intente nuevamente.");
            }
        }

        // Verificar que el doctor no tenga otra cita a la misma hora
        for (Cita cita : citas) {
            if (cita.getDoctor().equals(doctor) && cita.getFecha().equals(fechaCita.toString()) && cita.getHora().equals(horaCita.toString())) {
                System.out.println("El doctor ya tiene una cita a esa hora.");
                return;
            }
        }

        // Verificar que el paciente no tenga otra cita el mismo día
        for (Cita cita : citas) {
            if (cita.getPaciente().equals(paciente) && cita.getFecha().equals(fechaCita.toString())) {
                System.out.println("El paciente ya tiene una cita ese día.");
                return;
            }
        }

        Cita nuevaCita = new Cita(paciente, doctor, fechaCita.toString(), horaCita.toString());
        citas.add(nuevaCita);
        System.out.println("Cita agendada exitosamente.");
    }

    public void buscarCitas() {
        System.out.println("Buscar citas por:");
        System.out.println("1. Paciente");
        System.out.println("2. Doctor");
        System.out.println("3. Fecha");
        System.out.println("4. Código de Doctor");
        System.out.println("5. Mostrar todas las citas");
        System.out.println("6. Marcar llegada del paciente");
        System.out.println("7. Marcar si llevó galletas");
        System.out.println("8. Eliminar cita");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre del paciente:");
                String nombrePaciente = sc.nextLine();
                buscarCitasPorPaciente(nombrePaciente);
                break;
            case 2:
                System.out.println("Ingrese el nombre del doctor:");
                String nombreDoctor = sc.nextLine();
                buscarCitasPorDoctor(nombreDoctor);
                break;
            case 3:
                System.out.println("Ingrese la fecha de la cita (día/mes/año):");
                String fecha = sc.nextLine();
                buscarCitasPorFecha(fecha);
                break;
            case 4:
                buscarCitasPorCodigoDeDoctor();
                break;
            case 5:
                mostrarCitas(citas);
                break;
            case 6:
                marcarLlegada();
                break;
            case 7:
                marcarGalletas();
                break;
            case 8:
                eliminarCita();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
    private void buscarCitasPorPaciente(String nombrePaciente) {
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getPaciente().getNombre().equalsIgnoreCase(nombrePaciente))
                .collect(Collectors.toList());
        mostrarCitas(citasFiltradas);
    }

    private void buscarCitasPorDoctor(String nombreDoctor) {
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getDoctor().getNombre().equalsIgnoreCase(nombreDoctor))
                .collect(Collectors.toList());
        mostrarCitas(citasFiltradas);
    }

    private void buscarCitasPorFecha(String fecha) {
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getFecha().equals(fecha))
                .collect(Collectors.toList());
        mostrarCitas(citasFiltradas);
    }

    private void mostrarCitas(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas.");
        } else {
            int index = 1;
            for (Cita cita : citas) {
                System.out.println(index + ". Paciente: " + cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido() +
                        ", Doctor: " + cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellido() +
                        ", Especialidad: " + cita.getDoctor().getEspecialidad() +
                        ", Fecha: " + cita.getFecha() +
                        ", Hora: " + cita.getHora() +
                        ", Estado: " + cita.getEstado() +
                        ", Galletas: " + cita.getGalletas());
                index++;
            }
        }
    }

    public void buscarCitasPorCodigoDeDoctor() {
        System.out.println("Ingrese el código del doctor:");
        String codigoDoctor = sc.nextLine();
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getDoctor().getCodigo().equalsIgnoreCase(codigoDoctor))
                .collect(Collectors.toList());
        mostrarCitas(citasFiltradas);
    }

    private void marcarLlegada() {
        System.out.println("Ingrese el nombre del paciente:");
        String nombrePaciente = sc.nextLine();
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getPaciente().getNombre().equalsIgnoreCase(nombrePaciente))
                .collect(Collectors.toList());
        if (citasFiltradas.isEmpty()) {
            System.out.println("No se encontraron citas para el paciente.");
            return;
        }
        mostrarCitas(citasFiltradas);
        System.out.println("Seleccione la cita a marcar (número):");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < citasFiltradas.size()) {
            System.out.println("Seleccione el estado de la cita:");
            System.out.println("1. Llegó");
            System.out.println("2. No llegó");
            int estadoOpcion = sc.nextInt();
            sc.nextLine();
            if (estadoOpcion == 1) {
                citasFiltradas.get(index).setEstado("llegó");
            } else if (estadoOpcion == 2) {
                citasFiltradas.get(index).setEstado("no llegó");
            } else {
                System.out.println("Opción inválida.");
                return;
            }
            System.out.println("Estado de la cita actualizado.");
        } else {
            System.out.println("Selección inválida.");
        }
    }


    private void eliminarCita() {
        System.out.println("Ingrese el nombre del paciente:");
        String nombrePaciente = sc.nextLine();
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getPaciente().getNombre().equalsIgnoreCase(nombrePaciente))
                .collect(Collectors.toList());
        if (citasFiltradas.isEmpty()) {
            System.out.println("No se encontraron citas para el paciente.");
            return;
        }
        mostrarCitas(citasFiltradas);
        System.out.println("Seleccione la cita a eliminar (número):");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < citasFiltradas.size()) {
            citas.remove(citasFiltradas.get(index));
            System.out.println("Cita eliminada.");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    private void marcarGalletas() {
        System.out.println("Ingrese el nombre del paciente:");
        String nombrePaciente = sc.nextLine();
        List<Cita> citasFiltradas = citas.stream()
                .filter(cita -> cita.getPaciente().getNombre().equalsIgnoreCase(nombrePaciente))
                .collect(Collectors.toList());
        if (citasFiltradas.isEmpty()) {
            System.out.println("No se encontraron citas para el paciente.");
            return;
        }
        mostrarCitas(citasFiltradas);
        System.out.println("Seleccione la cita a marcar (número):");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < citasFiltradas.size()) {
            System.out.println("Seleccione si llevo galletas o no (1 para sí, 2 para no):");
            int estadoOpcion = sc.nextInt();
            sc.nextLine();
            if (estadoOpcion == 1) {
                citasFiltradas.get(index).setGalletas("Sí llevó galletas");
            } else if (estadoOpcion == 2) {
                citasFiltradas.get(index).setGalletas("No llevó galletas");
            } else {
                System.out.println("Opción inválida.");
                return;
            }
            System.out.println("Cita actualizada correctamente.");
        } else {
            System.out.println("Selección inválida.");
        }
    }
}