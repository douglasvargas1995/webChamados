<%-- 
    Document   : listachamado
    Created on : 02/05/2021, 14:10:36
    Author     : Douglas
--%>

<%@page import="apoio.Formatacao"%>
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
                <th>Aberto em</th>
                <th>Observação</th>
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
                    <td><%= Formatacao.ajustaDataDMA(String.valueOf(chama.getData_inicial()))%></td>
                    <td><%= chama.getObservacao()%></td>
                    <td><%= chama.getEstado()%></td>
                    <td><a href='/WebChamados/acao?param=atenderChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-success" value="Atender">Atender</a>
                        <a href='/WebChamados/acao?param=editarChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-primary" value="Editar">Editar</a>
                        <a href='/WebChamados/acao?param=finalizarChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-danger" value="Finalizar">Finalizar</a>
                        <a href='/WebChamados/itensChamado?param=gerar&id=<%= chama.getId()%>'<input type="button" class="btn btn-info" value="Gerar">Gerar PDF</a>
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
