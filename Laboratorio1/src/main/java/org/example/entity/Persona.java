package org.example.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Persona {

    private String nombre;
    private String apellido;
    private String DUI;
    private LocalDate cumpleaños;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Persona() {}

    public Persona(String nombre, String apellido, String DUI, String cumpleaños) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DUI = DUI;

        try {
            this.cumpleaños = LocalDate.parse(cumpleaños, fmt);
        } catch (DateTimeParseException e) {
            System.err.println("Formato inválido, colocar día/mes/año");
            this.cumpleaños = null;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDUI() {
        return DUI;
    }

    public LocalDate getCumpleaños() {
        return cumpleaños;
    }

}