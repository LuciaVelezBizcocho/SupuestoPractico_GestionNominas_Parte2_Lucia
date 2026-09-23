package org.example.Prueba.Laboral;

import org.example.Prueba.Laboral.DatosNoCorrectosException;

public class Persona {
    public String nombre;
    public String dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        if (nombre.length() <= 2 && nombre.length() >= 20 && !(nombre instanceof String)) {
            throw new DatosNoCorrectosException("El nombre debe tener entre 2 y 20 caracteres y ser un String");
        } else {
            this.nombre = nombre;
            if (dni.length() != 9 && !(dni instanceof String)) {
                throw new DatosNoCorrectosException("El dni debe tener entre 9 caracteres y ser un String");
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
