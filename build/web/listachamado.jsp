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
                <th>Solicitante</th>
                <th>Descrição</th>
                <th>Classificação</th>
                <th>Aberto em</th>
                <th>Finalizado em</th>
                <th>Situação</th>
                <th>Ação</th>

                <%
                    for (int i = 0; i < chamados.size(); i++) {
                        Chamado chama = chamados.get(i);
                %>
                <tr>
                    <td><%= chama.getId()%></td>
                    <td><%= chama.getEmail()%></td>
                    <td><%= chama.getDescricao_chamado()%></td>
                    <td><%= chama.getDescricao_classifica()%></td>
                    <td><%= chama.getData_inicial()%></td>
                    <td><%= chama.getData_final()%></td>
                    <td><%= chama.getEstado()%></td>
                    <td><a href='/WebChamados/acao?param=addItem&id=<%= chama.getId()%>'><button style="background: #2E2E2E; border-radius: 6px; padding: 5px; cursor: pointer; color: #fff; border: none; font-size: 10px;">+</button></a>
                        <a href='/WebChamados/acao?param=finalizarChamado&id=<%= chama.getId()%>'><button style="background: #FF0000; border-radius: 6px; padding: 5px; cursor: pointer; color: #fff; border: none; font-size: 10px;">Finalizar</button></a>
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
