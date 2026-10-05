package org.example.Prueba.Laboral.BD;

import org.example.Prueba.Laboral.BD.EmpleadoDAO;
import org.example.Prueba.Laboral.Empleado;

import java.sql.SQLException;

/** Lógica de alta de empleados sobre la base de datos. */
public class GestorNominas {
    private final EmpleadoDAO dao = new EmpleadoDAO();

    public void altaEmpleado(Empleado e) throws SQLException {
        dao.insertar(e);
    }
}