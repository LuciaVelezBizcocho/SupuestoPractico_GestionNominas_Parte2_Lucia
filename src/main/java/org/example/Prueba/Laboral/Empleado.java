package org.example.Prueba.Laboral;

import org.example.Prueba.Laboral.DatosNoCorrectosException;

public class Empleado extends Persona {
    private int categoria;
    public int anyos;

    public Empleado(String nombre, String dni, char sexo, int anyos, int categoria) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (anyos > 0) {
            this.anyos = anyos;
            if (categoria > 0 && categoria <= 10) {
                this.categoria = categoria;
            } else {
                throw new DatosNoCorrectosException("La categoria no es un numero entre 1 y 10");
            }
        } else {
            throw new DatosNoCorrectosException("Los anyos trabajados no puede ser menor que 0");
        }
    }

    public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
    }

    public int getCategoria() {
        return this.categoria;
    }

    public int getAnyos() {
        return this.anyos;
    }

    public void setAnyos(int anyos) throws DatosNoCorrectosException {
        if (anyos > 0) {
            this.anyos = anyos;
        } else {
            throw new DatosNoCorrectosException("El sexo no es correcto");
        }
    }

    public void setCategoria(int categoria) {
        if (categoria > 0 && categoria <= 10) {
            this.categoria = categoria;
        } else {
            throw new IllegalArgumentException("El categoria debe estar entre 1 e 10");
        }
    }

    public void incrAnyo() {
        ++this.anyos;
    }

    public void Imprime() {
        super.Imprime();
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Anyos Trabajados: " + this.anyos);
    }
}
