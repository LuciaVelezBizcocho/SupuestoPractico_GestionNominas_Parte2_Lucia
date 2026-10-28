package org.example.Prueba.Laboral.Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.Prueba.Laboral.BD.ConexionBD;
import org.example.Prueba.Laboral.BD.EmpleadoDAO;
import org.example.Prueba.Laboral.Empleado;
import org.example.Prueba.Laboral.Exception.DatosNoCorrectosException;

@WebServlet("/empleados")
public class NominaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "menu";
        }

        switch (accion) {
            case "listar":

                try {

                    EmpleadoDAO dao = new EmpleadoDAO();

                    List<Empleado> lista = dao.listar();

                    request.setAttribute("lista", lista);

                    request.getRequestDispatcher(
                            "/WEB-INF/views/empleados.jsp"
                    ).forward(request, response);

                } catch (SQLException | DatosNoCorrectosException e) {

                    throw new ServletException(
                            "Error al obtener los empleados",
                            e
                    );
                }

                break;

            case "crear":
                request.getRequestDispatcher(
                        "/WEB-INF/views/crear.jsp"
                ).forward(request, response);
                break;

            case "salario":
                request.getRequestDispatcher(
                        "/WEB-INF/views/salario.jsp"
                ).forward(request, response);
                break;

            case "modificar":
                request.getRequestDispatcher(
                        "/WEB-INF/views/modificar.jsp"
                ).forward(request, response);
                break;

            case "recalcular":
                request.getRequestDispatcher(
                        "/WEB-INF/views/recalcular.jsp"
                ).forward(request, response);
                break;

            case "recalcularTodos":
                request.getRequestDispatcher(
                        "/WEB-INF/views/recalcularTodos.jsp"
                ).forward(request, response);
                break;

            case "copia":
                request.getRequestDispatcher(
                        "/WEB-INF/views/copia.jsp"
                ).forward(request, response);
                break;

            default:
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            String sexoTexto = request.getParameter("sexo");
            int anyos = Integer.parseInt(request.getParameter("anyos"));
            int categoria = Integer.parseInt(request.getParameter("categoria"));

            try {

                char sexo = sexoTexto.charAt(0);

                Empleado empleado = new Empleado(
                        nombre,
                        dni,
                        sexo,
                        anyos,
                        categoria
                );

                EmpleadoDAO dao = new EmpleadoDAO();

                dao.insertar(empleado);

                // Después de guardar, volvemos a la lista
                response.sendRedirect(
                        request.getContextPath()
                                + "/empleados?accion=listar"
                );

            } catch (DatosNoCorrectosException e) {

                request.setAttribute("error", e.getMessage());

                request.getRequestDispatcher(
                        "/WEB-INF/views/crear.jsp"
                ).forward(request, response);

            } catch (SQLException e) {

                request.setAttribute(
                        "error",
                        "Error al guardar el empleado: " + e.getMessage()
                );

                request.getRequestDispatcher(
                        "/WEB-INF/views/crear.jsp"
                ).forward(request, response);
            }

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp"
            );
        }
    }


}
