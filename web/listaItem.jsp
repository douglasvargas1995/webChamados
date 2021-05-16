<%-- 
    Document   : listaItem
    Created on : 09/05/2021, 20:17:51
    Author     : Douglas
--%>

<%@page import="entidade.Item_chamado"%>
<%@page import="dao.ChamadoDAO"%>
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
            ArrayList<Item_chamado> ite = new ChamadoDAO().consultarItem();
        %>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>#</th>
                <th>Descrição</th>
                <th>Classificação</th>
                <th>Valor</th>
                <th>Categoria</th>

                <%
                    for (int i = 0; i < ite.size(); i++) {
                        Item_chamado categ = ite.get(i);
                %>
                <tr>
                    <td><%= categ.getId()%></td>
                    <td><%= categ.getDescricao()%></td>
                    <td><%= categ.getClassificacao()%></td>
                    <td><%= categ.getValor()%></td>
                    <td><%= categ.getCategoria()%></td>
                        <a href='/WebChamados/acao?param=exItemChamado&id=<%= categ.getId()%>'><button style="background: #FF0000; border-radius: 6px; padding: 5px; cursor: pointer; color: #fff; border: none; font-size: 10px;">Excluir</button></a>
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
