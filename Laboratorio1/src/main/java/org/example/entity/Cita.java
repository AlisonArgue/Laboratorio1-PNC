package org.example.entity;

public class Cita {
    private Persona paciente;
    private Doctor doctor;
    private String fechaHora;

    public Cita(Persona paciente, Doctor doctor, String fechaHora) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fechaHora = fechaHora;
    }

    public Persona getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getEspecialidad() {
        return doctor.getEspecialidad();
    }

    public void setPaciente(Persona paciente) {
        this.paciente = paciente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

}
