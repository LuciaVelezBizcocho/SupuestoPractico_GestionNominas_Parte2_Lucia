<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/estilos.css">
</head>

<body>

<div class="container">

    <h1>Se ha producido un error</h1>

    <div class="error">

        <p>
            <%= request.getAttribute("error") != null
                ? request.getAttribute("error")
                : "Ha ocurrido un error inesperado." %>
        </p>

    </div>

    <button class="volver"
            onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
        Volver al menú
    </button>

</div>

</body>
</html>
