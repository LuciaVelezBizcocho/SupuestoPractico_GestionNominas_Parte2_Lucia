package org.example.Prueba.Laboral.Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.Prueba.Laboral.BD.EmpleadoDAO;
import org.example.Prueba.Laboral.Empleado;
import org.example.Prueba.Laboral.Exception.DatosNoCorrectosException;

@WebServlet("/empleados")
public class NominaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        switch (accion) {

            case "listar":
                try {
                    List<Empleado> lista = dao.listar();

                    request.setAttribute("lista", lista);

                    request.getRequestDispatcher(
                            "/WEB-INF/views/empleados.jsp"
                    ).forward(request, response);

                } catch (SQLException | DatosNoCorrectosException e) {
                    throw new ServletException(e);
                }
                break;

            case "salario":
                request.getRequestDispatcher(
                        "/WEB-INF/views/salario.jsp"
                ).forward(request, response);
                break;

            case "buscar":
                request.getRequestDispatcher(
                        "/WEB-INF/views/buscar.jsp"
                ).forward(request, response);
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/index.jsp"
                );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            if ("crear".equals(accion)) {

                String nombre = request.getParameter("nombre");
                String dni = request.getParameter("dni");
                char sexo = request.getParameter("sexo").toUpperCase().charAt(0);
                int anyos = Integer.parseInt(request.getParameter("anyos"));
                int categoria = Integer.parseInt(request.getParameter("categoria"));

                Empleado empleado =
                        new Empleado(nombre, dni, sexo, anyos, categoria);

                dao.insertar(empleado);

                response.sendRedirect(
                        request.getContextPath() + "/empleados?accion=listar"
                );
            }

            // CONSULTAR SALARIO
            else if ("consultarSalario".equals(accion)) {

                String dni = request.getParameter("dni");

                Integer sueldo = dao.obtenerSueldo(dni);

                request.setAttribute("sueldo", sueldo);
                request.setAttribute("dni", dni);

                request.getRequestDispatcher(
                        "/WEB-INF/views/salario.jsp"
                ).forward(request, response);
            }

            // BUSCAR EMPLEADOS
            else if ("buscarEmpleados".equals(accion)) {

                String nombre = request.getParameter("nombre");
                String dni = request.getParameter("dni");
                String sexo = request.getParameter("sexo");
                String categoria = request.getParameter("categoria");
                String anyos = request.getParameter("anyos");

                List<Empleado> resultados =
                        dao.buscarPorFiltros(
                                nombre, dni, sexo, categoria, anyos
                        );

                request.setAttribute("lista", resultados);

                request.getRequestDispatcher(
                        "/WEB-INF/views/resultados.jsp"
                ).forward(request, response);
            } else if ("actualizar".equals(accion)) {

                String dni = request.getParameter("dni");
                String nombre = request.getParameter("nombre");
                char sexo = request.getParameter("sexo").toUpperCase().charAt(0);
                int anyos = Integer.parseInt(request.getParameter("anyos"));
                int categoria = Integer.parseInt(request.getParameter("categoria"));

                Empleado empleado =
                        new Empleado(nombre, dni, sexo, anyos, categoria);

                dao.actualizar(dni, empleado);

                response.sendRedirect(
                        request.getContextPath() + "/empleados?accion=listar"
                );
            }

        } catch (SQLException | DatosNoCorrectosException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                    "/WEB-INF/views/error.jsp"
            ).forward(request, response);
        }
    }
}
