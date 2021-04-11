<%-- 
    Document   : menu
    Created on : Mar 22, 2021, 8:36:37 PM
    Author     : pretto
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
                            <a class="nav-link active" aria-current="page" href="#">Início</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Relatórios</a>
                        </li>
                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-bs-toggle="dropdown" aria-expanded="false">Cadastros</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="cadastroLogin.jsp">Login</a></li>
                                <li><a class="dropdown-item" href="cadastroCategoria.jsp">Categoria</a></li>
                                <li><a class="dropdown-item" href="pesquisa.jsp">Pesquisar</a></li>
                                <li><a class="dropdown-item" href="categoria.jsp">categoria antes</a></li>
                                <li><a class="dropdown-item" href="listarUsuario.jsp">listar usuario</a></li>
                            </ul>
                        </li>
                        
                        
                        
                        <li class="nav-item">
                            <a class="nav-link" href="/WebChamados/acao?param=logout">Sair</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <script src="js/bootstrap.bundle.min.js"></script>

    </body>
</html>
