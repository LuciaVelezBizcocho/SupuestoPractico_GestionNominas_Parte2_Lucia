<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
    <title>Editar empleado</title>
</head>
<body>
    <h1>Editar empleado</h1>

    <form action="${pageContext.request.contextPath}/empleados"
          method="post">

        <input type="hidden" name="accion" value="actualizar">

        <p>
            DNI:
            <input type="text" name="dni"
                   value="<c:out value='${empleado.dni}'/>"
                   readonly>
        </p>

        <p>
            Nombre:
            <input type="text" name="nombre"
                   value="<c:out value='${empleado.nombre}'/>"
                   minlength="2" maxlength="20" required>
        </p>

        <div>
            <label>Sexo:</label>

            <input type="radio"
                   name="sexo"
                   value="M"
                   ${empleado.sexo == 'M' ? 'checked' : ''}>
            <label>Hombre</label>

            <input type="radio"
                   name="sexo"
                   value="F"
                   ${empleado.sexo == 'F' ? 'checked' : ''}>
            <label>Mujer</label>
        </div>

        <p>
            Años trabajados:
            <input type="number" name="anyos"
                   min="0" value="${empleado.anyos}" required>
        </p>

        <p>
            Categoría:
            <input type="number" name="categoria"
                   min="1" max="10"
                   value="${empleado.categoria}" required>
        </p>

        <button type="submit">Guardar cambios</button>
    </form>
</body>
</html>
