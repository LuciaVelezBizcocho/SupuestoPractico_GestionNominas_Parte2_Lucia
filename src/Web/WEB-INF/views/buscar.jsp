<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar empleados</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
</head>

<body>

<div class="container">

    <h1>Buscar empleados</h1>

    <p class="mensaje">
        Introduce uno o varios datos para realizar la búsqueda.
    </p>

    <form action="${pageContext.request.contextPath}/empleados"
          method="post">

        <input type="hidden"
               name="accion"
               value="buscarEmpleados">

        <label for="nombre">Nombre:</label>
        <input type="text"
               id="nombre"
               name="nombre"
               placeholder="Nombre del empleado">

        <label for="dni">DNI:</label>
        <input type="text"
               id="dni"
               name="dni"
               maxlength="9"
               placeholder="DNI del empleado">

        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo">
            <option value="">Todos</option>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select>

        <label for="categoria">Categoría:</label>
        <input type="number"
               id="categoria"
               name="categoria"
               min="1"
               max="10"
               placeholder="Entre 1 y 10">

        <label for="anyos">Años trabajados:</label>
        <input type="number"
               id="anyos"
               name="anyos"
               min="0"
               placeholder="Años trabajados">

        <button type="submit">
            Buscar empleados
        </button>

    </form>

    <button class="volver"
            onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
        Volver al menú
    </button>

</div>

</body>
</html>
