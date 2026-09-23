package org.example.Prueba.Laboral;

public class CalculaNomina {
    private static String escribe(Empleado e1, Empleado e2) {
        Nomina n1 = new Nomina();
        Nomina n2 = new Nomina();

        return "Empleado: " + e1 + "Sueldo: " + n1.sueldo(e1) + "Empleado: " + e2 + "Sueldo: " + n2.sueldo(e2);
    }

    public static void main() throws DatosNoCorrectosException {
        Empleado e1 = new Empleado("James Cosling", "32000032G", 'm', 4, 7);
        Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'f');
        new Nomina();
        escribe(e1, e2);
        e2.incrAnyo();
        e2.incrAnyo();
        e2.incrAnyo();
        e1.setCategoria(9);
        escribe(e1, e2);
    }
}
