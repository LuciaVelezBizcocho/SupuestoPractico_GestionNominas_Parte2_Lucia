package org.example.Prueba.Laboral.BD;

import org.example.Prueba.Laboral.Exception.DatosNoCorrectosException;
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
            String sql = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?) "
                    + "ON DUPLICATE KEY UPDATE sueldo = VALUES(sueldo)";

            try (Connection c = ConexionBD.conectar();
                 PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setString(1, dni);
                ps.setInt(2, sueldo);

                ps.executeUpdate();
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

        /** Obtiene el sueldo de un empleado mediante su DNI. */
        public Integer obtenerSueldo(String dni) throws SQLException {

            String sql = "SELECT sueldo FROM Nominas WHERE dni = ?";

            try (Connection c = ConexionBD.conectar();
                 PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setString(1, dni);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        return rs.getInt("sueldo");
                    }

                    return null;
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

    /**
     * Busca empleados utilizando diferentes criterios.
     * Los campos vacíos no se tienen en cuenta.
     */
    public List<Empleado> buscarPorFiltros(
            String nombre,
            String dni,
            String sexo,
            String categoria,
            String anyos)
            throws SQLException, DatosNoCorrectosException {

        List<Empleado> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM Empleados WHERE 1=1"
        );

        List<Object> parametros = new ArrayList<>();

        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" AND nombre LIKE ?");
            parametros.add("%" + nombre.trim() + "%");
        }

        if (dni != null && !dni.trim().isEmpty()) {
            sql.append(" AND dni LIKE ?");
            parametros.add("%" + dni.trim() + "%");
        }

        if (sexo != null && !sexo.trim().isEmpty()) {
            sql.append(" AND sexo = ?");
            parametros.add(sexo.trim().toUpperCase());
        }

        if (categoria != null && !categoria.trim().isEmpty()) {
            sql.append(" AND categoria = ?");
            parametros.add(Integer.parseInt(categoria));
        }

        if (anyos != null && !anyos.trim().isEmpty()) {
            sql.append(" AND anyos = ?");
            parametros.add(Integer.parseInt(anyos));
        }

        sql.append(" ORDER BY nombre");

        try (Connection c = ConexionBD.conectar();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }

        return lista;
    }

}
