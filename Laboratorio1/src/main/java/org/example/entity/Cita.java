package org.example.entity;

public class Cita {
    private Persona paciente;
    private Doctor doctor;
    private String fecha;
    private String hora;
    private String estado;

    public Cita(Persona paciente, Doctor doctor, String fecha, String hora) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "pendiente";
    }

    public Persona getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "paciente=" + paciente.getNombre() + " " + paciente.getApellido() +
                ", doctor=" + doctor.getNombre() + " " + doctor.getApellido() +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}