<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Prueba.Laboral.Empleado" %>

<%
    List<Empleado> lista =
        (List<Empleado>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados de búsqueda</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
</head>

<body>

<div class="container">

    <h1>Resultados de búsqueda</h1>

    <table>

        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años trabajados</th>
        </tr>

        <% if (lista != null && !lista.isEmpty()) {
            for (Empleado e : lista) { %>

                <tr>
                    <td><%= e.getNombre() %></td>
                    <td><%= e.getDni() %></td>
                    <td><%= e.getSexo() %></td>
                    <td><%= e.getCategoria() %></td>
                    <td><%= e.getAnyos() %></td>
                </tr>

        <%  }
           } else { %>

                <tr>
                    <td colspan="5">
                        No se encontraron empleados.
                    </td>
                </tr>

        <% } %>

    </table>

    <button class="volver"
            onclick="window.location.href='${pageContext.request.contextPath}/empleados?accion=buscar'">
        Volver a buscar
    </button>

    <button
        onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
        Volver al menú
    </button>

</div>

</body>
</html>
