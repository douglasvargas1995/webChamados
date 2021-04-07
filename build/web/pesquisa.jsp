<%-- 
    Document   : pesquisa
    Created on : Mar 22, 2021, 9:35:23 PM
    Author     : pretto
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>

        <h1>Pesquisa de categorias</h1>

        <form method="post" action="/WebApp2021A/chupitz?param=pesquisa">

            <input type="text" name="campoDeBusca" placeholder="Digite o que deseja pesquisar">

            <input type="submit" value="Pesquisar">

        </form>

        <%
            ArrayList<Categoria> categs = (ArrayList) request.getAttribute("categoriasPesquisa");

            // testar se obj esta nulo.
            // quando viemos do Menu (direto), não há obj em categoriasPesquisa, logo, o cast será NULL
            if (categs != null) {

                if (categs.size() == 0) {
        %>  
        <p>Nenhum resultado encontrado.</p>

        <%
        } else {
        %>
        <h2>Resultados</h2>

        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>Id</th>
                <th>Descrição</th>
                <th>Situação</th>

                <%
                    for (int i = 0; i < categs.size(); i++) {
                        Categoria categ = categs.get(i);
                %>
                <tr>
                    <td><a href='/WebChamados/acao?param=edCategoria&id=<%= categ.getId()%>'><%= categ.getId()%></a></td>                
                    <td><%= categ.getDescricao()%></td>
                    <td><%= categ.getSituacao()%></td>
                </tr>

                <%
                    }
                %>

            </table>
        </div>
        <%
                }
            }
        %>

    </body>
</html>
