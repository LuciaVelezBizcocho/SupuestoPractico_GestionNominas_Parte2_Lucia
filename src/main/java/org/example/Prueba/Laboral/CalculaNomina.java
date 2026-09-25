package org.example.Prueba.Laboral;

import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println("       GESTIÓN DE NÓMINAS");
            System.out.println("==============================");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Mostrar salario de un empleado");
            System.out.println("3. Modificar empleado");
            System.out.println("4. Recalcular salario de un empleado");
            System.out.println("5. Recalcular salarios de todos");
            System.out.println("6. Realizar copia de seguridad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    // 5.1
                    break;

                case 2:
                    // 5.2
                    break;

                case 3:
                    // 5.3
                    break;

                case 4:
                    // 5.4
                    break;

                case 5:
                    // 5.5
                    break;

                case 6:
                    // 5.6
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }while (opcion != 0);

    }
}
