package org.example.Prueba.Laboral;

import org.example.Prueba.Laboral.DatosNoCorrectosException;

public class Persona {
    public String nombre;
    public String dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        if(nombre == null || nombre.length() < 2 || nombre.length() > 20){
            throw new DatosNoCorrectosException("Nombre incorrecto");
        } else {
            this.nombre = nombre;
            if(dni == null || dni.length()!=9){
                throw new DatosNoCorrectosException("DNI incorrecto");
            } else {
                this.dni = dni;
                if (sexo != 'f' && sexo != 'm') {
                    throw new DatosNoCorrectosException("El sexo no es correcto");
                } else {
                    this.sexo = sexo;
                }
            }
        }
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void Imprime() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("DNI: " + this.dni);
    }
}
