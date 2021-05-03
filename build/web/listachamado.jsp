<%-- 
    Document   : listachamado
    Created on : 02/05/2021, 14:10:36
    Author     : Douglas
--%>

<%@page import="dao.ChamadoDAO"%>
<%@page import="entidade.Chamado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            ArrayList<Chamado> chamados = new ChamadoDAO().consultarTodos();
        %>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>#</th>
                <th>Ação</th>
                <th>Descrição</th>
                <th>Aberto em</th>
                <th>Situação</th>

                <%
                    for (int i = 0; i < chamados.size(); i++) {
                        Chamado chama = chamados.get(i);
                %>
                <tr>
                    <td><%= chama.getId()%></td>
                    <td><a href='/WebChamados/acao?param=edChamado=<%= chama.getId()%>'>Editar</a>   <a href='/WebChamados/acao?param=exChamado&id=<%= chama.getId()%>'>Excluir</a></td>
                    <td><%= chama.getDescricao()%></td>
                    <td><%= chama.getData_inicial()%></td>
                    <td><%= chama.getEstado()%></td>
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
