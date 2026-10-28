<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consultar salario</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
</head>

<body>

<div class="container">

    <h1>Consultar salario de un empleado</h1>

    <form action="${pageContext.request.contextPath}/empleados"
          method="post">

        <input type="hidden"
               name="accion"
               value="consultarSalario">

        <label for="dni">DNI del empleado:</label>

        <input type="text"
               id="dni"
               name="dni"
               maxlength="9"
               required
               value="<%= request.getAttribute("dni") != null
                    ? request.getAttribute("dni") : "" %>">

        <button type="submit">
            Consultar salario
        </button>

    </form>

    <%
        Integer sueldo =
            (Integer) request.getAttribute("sueldo");

        if (sueldo != null) {
    %>

        <div class="resultado">

            <h2>Resultado de la consulta</h2>

            <p>
                DNI:
                <strong><%= request.getAttribute("dni") %></strong>
            </p>

            <p>
                Salario:
                <strong><%= sueldo %> €</strong>
            </p>

        </div>

    <%
        } else if (request.getAttribute("dni") != null) {
    %>

        <div class="error">
            No se ha encontrado ningún salario asociado a ese DNI.
        </div>

    <%
        }
    %>

    <button class="volver"
            onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
        Volver al menú
    </button>

</div>

</body>
</html>
