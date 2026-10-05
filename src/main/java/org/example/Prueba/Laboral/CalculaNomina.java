package org.example.Prueba.Laboral;

import org.example.Prueba.Laboral.BD.EmpleadoDAO;
import org.example.Prueba.Laboral.BD.GestorNominas;

import java.sql.SQLException;
import java.util.Scanner;

public class CalculaNomina {
    private static String escribe(Empleado e1, Empleado e2) {
        Nomina n1 = new Nomina();
        Nomina n2 = new Nomina();

        return "Empleado: " + e1 + "Sueldo: " + n1.sueldo(e1) + "Empleado: " + e2 + "Sueldo: " + n2.sueldo(e2);
    }

    private static int leerEntero(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static void modificarEmpleado(Scanner sc, EmpleadoDAO dao)
            throws SQLException, DatosNoCorrectosException {
        System.out.print("DNI del empleado a modificar: ");
        String dniOriginal = sc.nextLine().trim();
        Empleado e = dao.buscar(dniOriginal);
        if (e == null) {
            System.out.println("No existe ese empleado.");
            return;
        }

        int op;
        do {
            System.out.println("\n1. Nombre\n2. DNI\n3. Sexo (f/m)\n4. Categoría\n5. Años trabajados\n0. Guardar y volver");
            System.out.print("Campo a modificar: ");
            op = leerEntero(sc);
            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("Nuevo nombre: ");
                        e.setNombre(sc.nextLine().trim());
                    }
                    case 2 -> {
                        System.out.print("Nuevo DNI: ");
                        e.setDni(sc.nextLine().trim());
                    }
                    case 3 -> {
                        System.out.print("Nuevo sexo (f/m): ");
                        String s = sc.nextLine().trim().toLowerCase();
                        e.setSexo(s.isEmpty() ? ' ' : s.charAt(0));
                    }
                    case 4 -> {
                        System.out.print("Nueva categoría (1-10): ");
                        e.setCategoria(leerEntero(sc));
                    }
                    case 5 -> {
                        System.out.print("Años trabajados: ");
                        e.setAnyos(leerEntero(sc));
                    }
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (DatosNoCorrectosException ex) {
                System.out.println("Dato incorrecto: " + ex.getMessage());
            }
        } while (op != 0);

        dao.actualizar(dniOriginal, e); // guarda los datos y recalcula el sueldo
        System.out.println("Empleado actualizado.");
    }

    public static void main() throws DatosNoCorrectosException {
        EmpleadoDAO dao = new EmpleadoDAO();
        GestorNominas gestor = new GestorNominas();
        Scanner sc = new Scanner(System.in);

        int opcion;
        try{
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
                        for (Empleado e : dao.listar()) {
                            System.out.printf("%-20s %-9s %c  cat:%2d  años:%2d%n",
                                    e.getNombre(), e.getDni(), e.getSexo(),
                                    e.getCategoria(), e.getAnyos());
                        }
                        break;
                    case 2:
                        System.out.print("DNI: ");
                        Integer sueldo = dao.obtenerSueldo(sc.nextLine().trim());
                        System.out.println(sueldo == null
                                ? "No existe ese empleado o no tiene sueldo calculado."
                                : "Sueldo: " + sueldo + " €");
                        break;
                    case 3:
                        modificarEmpleado(sc, dao);
                        break;
                    case 4:
                        System.out.print("DNI: ");
                        System.out.println(dao.recalcularSueldo(sc.nextLine().trim())
                                ? "Sueldo actualizado."
                                : "No existe ese empleado.");
                        break;
                    case 5:
                        System.out.println("Sueldos actualizados: " + dao.recalcularTodos());
                        break;
                    case 6:
                        System.out.println("Copia de seguridad: pendiente de implementar.");
                        break;
                    case 0:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }while (opcion != 0);
        } catch (SQLException | DatosNoCorrectosException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        sc.close();
    }
}
