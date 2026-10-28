<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
    <title>Crear empleado</title>
</head>
<body>
    <h1>Nuevo empleado</h1>

    <form action="${pageContext.request.contextPath}/empleados"
          method="post">

        <input type="hidden" name="accion" value="crear">

        <p>
            Nombre:
            <input type="text" name="nombre"
                   minlength="2" maxlength="20" required>
        </p>

        <p>
            DNI:
            <input type="text" name="dni"
                   minlength="9" maxlength="9" required>
        </p>

        <p>
            Sexo:
            <select name="sexo" required>
                <option value="m">Masculino</option>
                <option value="f">Femenino</option>
            </select>
        </p>

        <p>
            Años trabajados:
            <input type="number" name="anyos"
                   min="0" required>
        </p>

        <p>
            Categoría:
            <input type="number" name="categoria"
                   min="1" max="10" required>
        </p>

        <button type="submit">Guardar empleado</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/index.jsp">
            Volver al menú
        </a>
    </p>
</body>
</html>