<%-- 
    Document   : teste
    Created on : 14/05/2021, 17:00:01
    Author     : Douglas
--%>

<%@page import="entidade.Login"%>
<%@page import="dao.LoginDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listagem de usuários</h1>


        <%            
            String criterio = request.getParameter("criterio");

            if (criterio == null) {
                criterio = "";
            }
        %>
        <div class="table-responsive">
            <div class="row col-md-6">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Situação</th>
                            <th>Edição</th>
                            <th>Exclusão</th>
                        </tr>
                    </thead>
                    <%
                        ArrayList<Login> usuarios = new LoginDAO().consultar(criterio);

                        for (int i = 0; i < usuarios.size(); i++) {
                            Login u = (Login) usuarios.get(i);
                    %>
                    <tr>
                        <td><%= u.getId()%></td>
                        <td><%= u.getNome()%></td>
                        <td><%= u.getEmail()%></td>
                        <td><%= u.getEstado()%></td>
                        <td><a href="'/WebChamados/acao?param=edLogin&id=<%= u.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                        <td><a href="'/WebChamados/acao?param=edLogin&id=<%= u.getId()%>"><span class="pull-left glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
