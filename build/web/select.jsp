<%-- 
    Document   : select
    Created on : Mar 22, 2021, 9:15:40 PM
    Author     : pretto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoriaDAO"%>
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

        <h1>Exemplo de Select</h1>

        <form method="post" action="/WebChamados/chupitz?param=select">

            <select name="comboCategoria" class="form-select form-select-lg" aria-label=".form-select-sm example">

                <option value="0">Selecione</option>

                <%
                    ArrayList<Categoria> categorias = new CategoriaDAO().consultarTodos();

                    for (int i = 0; i < categorias.size(); i++) {
                %>           
                <option value="<%= categorias.get(i).getId()%>"><%= categorias.get(i).getDescricao()%></option>
                <%
                    }
                %>
            </select>
            
            <input type="submit" class="btn btn-secondary" value="Enviar...">

        </form>
    </body>
</html>
