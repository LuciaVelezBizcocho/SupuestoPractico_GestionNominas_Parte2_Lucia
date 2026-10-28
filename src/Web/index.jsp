<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Nóminas</title>
</head>
<body>
    <h1>Gestión de Nóminas</h1>

    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/empleados?accion=listar">
                Mostrar todos los empleados
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/empleados?accion=crear">
                Crear empleado
            </a>
        </li>
    </ul>
</body>
</html>
