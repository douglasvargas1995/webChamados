<%-- 
    Document   : cadastroChamado
    Created on : 24/04/2021, 21:43:14
    Author     : Douglas
--%>

<%@page import="entidade.Classifica"%>
<%@page import="entidade.Item_chamado"%>
<%@page import="java.util.Date"%>
<%@page import="entidade.Chamado"%>
<html lang="en">
    <%@include file="menu.jsp" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro de categoria</title>
    <link rel="stylesheet" href="css/style.css">    
    <%
        Chamado chamado = (Chamado) request.getAttribute("objChamado");
        Item_chamado item_chamado = (Item_chamado) request.getAttribute("objItem_chamado");

        if (chamado == null) {
            chamado = new Chamado();

            chamado.setId(0);
            chamado.setId_item_chamado(0);
            chamado.setId_login(0);
            chamado.setData_inicial(new Date());
            chamado.setData_final(null);
            chamado.setEstado("");
        }
    %>
    <body>        

        <h1>Ticket Chamado</h1>
        <form name='formChamado' method='post' action='/WebChamados/acao?param=salvarChamado'>
            <input type="hidden" name="id" value="<%= chamado.getId()%>">
            <div class="half-box spacing">
                <label for="name">Data Inicial</label>
                <input required="required" type="date" name="data_inicial" value="#">
            </div>
            <div class="half-box">
                <label for="lastname">Data final</label>
                <input required="required" type="date" name="data_final" value="#">
            </div>
            <input type="text" name="campoDeBusca" placeholder="Digite o que deseja pesquisar">

            <input type="submit" value="Pesquisar">

        </form>
        <p class="error-validation template"></p>
        <script src="js/scriptCadastroLogin.js"></script>
        <%@include file="listachamado.jsp" %>
    </body>
</html>