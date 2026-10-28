<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Salario del empleado</title>
</head>
<body>
    <h1>Salario del empleado</h1>

    <p>DNI: <c:out value="${empleado.dni}"/></p>
    <p>Nombre: <c:out value="${empleado.nombre}"/></p>
    <p>Categoría: <c:out value="${empleado.categoria}"/></p>
    <p>Años trabajados: <c:out value="${empleado.anyos}"/></p>
    <p>Sueldo: <strong><c:out value="${sueldo}"/></strong></p>

    <a href="${pageContext.request.contextPath}/empleados?accion=listar">
        Volver a empleados
    </a>
</body>
</html>
