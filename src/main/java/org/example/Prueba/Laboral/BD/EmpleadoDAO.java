package org.example.Prueba.Laboral.BD;

import org.example.Prueba.Laboral.DatosNoCorrectosException;
import org.example.Prueba.Laboral.Empleado;
import org.example.Prueba.Laboral.Nomina;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

        private final Nomina nomina = new Nomina();

        private Empleado mapear(ResultSet rs) throws SQLException, DatosNoCorrectosException {
            return new Empleado(rs.getString("nombre"), rs.getString("dni"),
                    rs.getString("sexo").charAt(0), rs.getInt("anyos"), rs.getInt("categoria"));
        }

        /** Inserta el empleado y calcula y guarda su sueldo. */
        public void insertar(Empleado e) throws SQLException {
            String sql = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES (?,?,?,?,?)";
            try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, e.getDni());
                ps.setString(2, e.getNombre());
                ps.setString(3, String.valueOf(e.getSexo()));
                ps.setInt(4, e.getCategoria());
                ps.setInt(5, e.getAnyos());
                ps.executeUpdate();
            }
            guardarSueldo(e.getDni(), nomina.sueldo(e));
        }

        /** Inserta o actualiza el sueldo de un empleado en Nominas. */
        public void guardarSueldo(String dni, int sueldo) throws SQLException {
            try (Connection c = ConexionBD.conectar()) {
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE nominas SET Sueldo = ? WHERE empleado = ?")) {
                ps.setInt(1, sueldo);
                ps.setString(2, dni);
                if (ps.executeUpdate() > 0) return;   // ya existía la nómina
            }
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO nominas (Sueldo, empleado) VALUES (?, ?)")) {
                ps.setInt(1, sueldo);
                ps.setString(2, dni);
                ps.executeUpdate();
            }
        }

        }

        /** Devuelve todos los empleados. */
        public List<Empleado> listar() throws SQLException, DatosNoCorrectosException {
            List<Empleado> lista = new ArrayList<>();
            try (Connection c = ConexionBD.conectar();
                 Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM Empleados ORDER BY nombre")) {
                while (rs.next()) lista.add(mapear(rs));
            }
            return lista;
        }

        /** Busca un empleado por DNI; devuelve null si no existe. */
        public Empleado buscar(String dni) throws SQLException, DatosNoCorrectosException {
            try (Connection c = ConexionBD.conectar();
                 PreparedStatement ps = c.prepareStatement("SELECT * FROM Empleados WHERE dni = ?")) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? mapear(rs) : null;
                }
            }
        }

        public Integer obtenerSueldo(String dni) throws SQLException {
            try (Connection c = ConexionBD.conectar();
                 PreparedStatement ps = c.prepareStatement("SELECT Sueldo FROM nominas WHERE empleado = ?")) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? rs.getInt("Sueldo") : null;
                }
            }
        }

        /** Actualiza todos los datos (incluido el DNI) y recalcula el sueldo automáticamente. */
        public void actualizar(String dniOriginal, Empleado e) throws SQLException {
            String sql = "UPDATE Empleados SET nombre=?, dni=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
            try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, e.getNombre());
                ps.setString(2, e.getDni());
                ps.setString(3, String.valueOf(e.getSexo()));
                ps.setInt(4, e.getCategoria());
                ps.setInt(5, e.getAnyos());
                ps.setString(6, dniOriginal);
                ps.executeUpdate();
            }
            guardarSueldo(e.getDni(), nomina.sueldo(e));
        }

        /** Recalcula el sueldo de un empleado. Devuelve false si no existe. */
        public boolean recalcularSueldo(String dni) throws SQLException, DatosNoCorrectosException {
            Empleado e = buscar(dni);
            if (e == null) return false;
            guardarSueldo(dni, nomina.sueldo(e));
            return true;
        }

        /** Recalcula el sueldo de todos los empleados y devuelve cuántos se han actualizado. */
        public int recalcularTodos() throws SQLException, DatosNoCorrectosException {
            List<Empleado> todos = listar();
            for (Empleado e : todos) guardarSueldo(e.getDni(), nomina.sueldo(e));
            return todos.size();
        }
}
