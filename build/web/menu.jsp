<%-- 
    Document   : menu
    Created on : Mar 22, 2021, 8:36:37 PM
    Author     : Douglas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/navbar.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Web Chamados</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample04">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="inicial.jsp">Início</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Relatórios</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown03">
                                <li><a class="dropdown-item" href="relcategoria.jsp">Categoria</a></li>
                                <li><a class="dropdown-item" href="relusuarios.jsp">Login</a></li>
                                <li><a class="dropdown-item" href="relchamados.jsp">Chamados</a></li>
                                <li><a class="dropdown-item" href="chamadoFiltro.jsp">Chamados Filtros</a></li>
                                <li><a class="dropdown-item" href="grafico.jsp">Gráfico</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Cadastros</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown05">
                                <li><a class="dropdown-item" href="cadastroLogin.jsp">Login</a></li>
                                <li><a class="dropdown-item" href="cadastroCategoria.jsp">Categoria</a></li>
                                <li><a class="dropdown-item" href="cadastraChamado.jsp">Chamado</a></li>
                                <li><a class="dropdown-item" href="itemChamado.jsp">item chamado</a></li>
                                <li><a class="dropdown-item" href="chamado.jsp">Chamado central</a></li>

                            </ul>
                        </li>

                    </ul>
                    <a class="nav-link active" aria-current="page" href="/WebChamados/acao?param=logout">Sair</a>
                </div>

            </div>
        </nav>

        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/sweetalert.min.js" type="text/javascript"/>
        <script src="js/jquery-3.4.1.js" type="text/javascript"></script>
    </body>
</html>
