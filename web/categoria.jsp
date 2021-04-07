<%-- 
    Document   : categoria
    Created on : Mar 15, 2021, 9:32:26 PM
    Author     : pretto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        
        <%
            Categoria categoria = (Categoria) request.getAttribute("objCategoria");
            
            if (categoria == null) {
                categoria = new Categoria();
                
                categoria.setId(0);
                categoria.setDescricao("");
                categoria.setSituacao("");
            }
        %>

        <h1>Cadastro de categorias</h1>

        <form name='formCategoria' method='post' action='/WebChamados/acao?param=salvarCategoria'>
            <input type="hidden" name="id" value="<%= categoria.getId()%>">
            
            Descrição
            <input type='text' name='descricao' value='<%= categoria.getDescricao() %>'>

            <br>
            <br>

            Situação
            <input type='text' name='situacao' value='<%= categoria.getSituacao()%>'>

            <br>
            <br>
            <input type='submit' value='Salvar'>

        </form>

        <h4>Listagem de categorias</h4>
        <%@include file="listacategoria.jsp" %>
    </body>
</html>
