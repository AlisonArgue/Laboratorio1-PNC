package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Persona p = new Persona("idk", "hola", "4582464-9", "02/04/2000");

        Funciones funciones = new Funciones();

        funciones.calculateAge(p);
    }
}