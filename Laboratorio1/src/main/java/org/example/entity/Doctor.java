package org.example.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.example.utils.CodigoGenerator;

public class Doctor extends Persona {

    //No poner dd/MM/yyyy porque sino al mes 2 debe de ser 02, si solo es d/m/yyyy se pueden ambos ;)
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy");

    private LocalDate fechaReclutamiento;
    private String especialidad;
    private String codigo;

    public Doctor(String nombre, String apellido, String DUI, String cumpleaños,
                  String fechaReclutamiento, String especialidad) {
        super(nombre, apellido, DUI, cumpleaños);
        this.especialidad = especialidad;
        this.codigo = CodigoGenerator.generarCodigoDoctor();

        try {
            this.fechaReclutamiento = LocalDate.parse(fechaReclutamiento, fmt);
        } catch (DateTimeParseException e) {
            System.err.println("Formato inválido, colocar día/mes/año");
            this.fechaReclutamiento = null;
        }
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getReclutamiento() {
        return fechaReclutamiento;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
