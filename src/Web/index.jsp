    <%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de empleados</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
</head>

<body>

<div class="container">

    <h1>Gestión de empleados y nóminas</h1>

    <p class="mensaje">
        Bienvenido al sistema de gestión de empleados.
        Selecciona una opción:
    </p>

    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/empleados?accion=listar">
                    Mostrar todos los empleados
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/empleados?accion=salario">
                    Consultar salario de un empleado
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/empleados?accion=buscar">
                    Buscar empleados
                </a>
            </li>
        </ul>
    </nav>

</div>

</body>
</html>
