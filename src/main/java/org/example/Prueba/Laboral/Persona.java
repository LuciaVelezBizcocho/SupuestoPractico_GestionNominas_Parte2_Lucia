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

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public char getSexo() { return sexo; }

    public void setDni(String dni) throws DatosNoCorrectosException {
        if (dni == null || dni.length() != 9) throw new DatosNoCorrectosException("DNI incorrecto");
        this.dni = dni;
    }

    public void setNombre(String nombre) throws DatosNoCorrectosException {
        if (nombre == null || nombre.length() < 2 || nombre.length() > 20)
            throw new DatosNoCorrectosException("Nombre incorrecto");
        this.nombre = nombre;
    }

    public void setSexo(char sexo) throws DatosNoCorrectosException {
        if (sexo != 'f' && sexo != 'm') throw new DatosNoCorrectosException("El sexo no es correcto");
        this.sexo = sexo;
    }

    public void Imprime() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("DNI: " + this.dni);
    }
}
