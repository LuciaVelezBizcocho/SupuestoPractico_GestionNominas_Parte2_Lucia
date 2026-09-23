package org.example.Prueba.Laboral;

import org.example.Prueba.Laboral.Empleado;

public class Nomina {
    private static final int[] sueldoBase = new int[]{50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

    public int sueldo(Empleado empleado) {
        int sueldo = sueldoBase[empleado.getCategoria()] + 5000 * empleado.anyos_trabajados;
        return sueldo;
    }
}
