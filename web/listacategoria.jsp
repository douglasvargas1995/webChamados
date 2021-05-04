<%-- 
    Document   : categoria
    Created on : Mar 15, 2021, 9:16:04 PM
    Author     : pretto
--%>

<%@page import="entidade.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            ArrayList<Categoria> categorias = new CategoriaDAO().consultarTodos();
        %>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>#</th>
                <th>Descrição</th>
                <th>Situação</th>
                <th>Valor Hora</th>
                <th>Observação</th>
                <th>   Ação</th>

                <%
                    for (int i = 0; i < categorias.size(); i++) {
                        Categoria categ = categorias.get(i);
                %>
                <tr>
                    <td><%= categ.getId()%></td>
                    <td><%= categ.getDescricao()%></td>
                    <td><%= categ.getSituacao()%></td>
                    <td><%= categ.getValor()%></td>
                    <td><%= categ.getObservacao()%></td>
                    <td><a href='/WebChamados/acao?param=exCategoria&id=<%= categ.getId()%>'><button style="background: #01DF3A; border-radius: 6px; padding: 5px; cursor: pointer; color: #fff; border: none; font-size: 10px;">Editar</button></a>
                        <a href='/WebChamados/acao?param=exCategoria&id=<%= categ.getId()%>'><button style="background: #FF0000; border-radius: 6px; padding: 5px; cursor: pointer; color: #fff; border: none; font-size: 10px;">Excluir</button></a>
                    <td>
                </tr>

                <%
                    }
                %>

            </table>
        </div>
        <br>
        <br>
        <a href='menu.jsp'>Voltar</a>
    </body>
</html>
