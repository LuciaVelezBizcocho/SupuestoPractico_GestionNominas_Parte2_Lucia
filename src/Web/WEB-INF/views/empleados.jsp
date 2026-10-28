<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de empleados</title>
</head>
<body>
    <h1>Empleados</h1>

    <p>
        <a href="${pageContext.request.contextPath}/index.jsp">Menú</a>

        <a href="${pageContext.request.contextPath}/empleados?accion=crear">
            Nuevo empleado
        </a>
    </p>

    <table border="1" cellpadding="6">
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Sexo</th>
                <th>Años trabajados</th>
                <th>Categoría</th>
                <th>Salario</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${lista}">
                <tr>
                    <td><c:out value="${e.dni}"/></td>
                    <td><c:out value="${e.nombre}"/></td>
                    <td><c:out value="${e.sexo}"/></td>
                    <td><c:out value="${e.anyos}"/></td>
                    <td><c:out value="${e.categoria}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/empleados?accion=salario&amp;dni=${e.dni}">
                            Consultar salario
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/empleados?accion=editar&amp;dni=${e.dni}">
                            Editar
                        </a>
                        |
                        <a href="${pageContext.request.contextPath}/empleados?accion=eliminar&amp;dni=${e.dni}"
                           onclick="return confirm('¿Eliminar este empleado?');">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
