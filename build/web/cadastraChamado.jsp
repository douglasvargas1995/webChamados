<%-- 
    Document   : cadastraChamado
    Created on : 09/05/2021, 19:59:32
    Author     : Douglas
--%>

<%@page import="entidade.Classifica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Categoria"%>
<%@page import="entidade.Chamado"%>
<%@page import="dao.ChamadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de chamados</title>
    <link rel="stylesheet" href="css/style2.css">
    <%
        Chamado chamado = (Chamado) request.getAttribute("objChamado");

        if (chamado == null) {
            chamado = new Chamado();

            chamado.setId(0);
            chamado.setDescricao("");
            chamado.setData_inicial(null);
            chamado.setData_final(null);
            chamado.setEstado("");
            chamado.setId_login(0);
            chamado.setId_item_chamado(0);
            chamado.setObservacao("");

        }
    %>
    <%@include file="menu.jsp" %>
    <body>        
        <div id="main-container">
            <h1>Cadastro de chamado</h1>
            <form name='formChamado' method='post' action='/WebChamados/acao?param=salvarChamado'>
                <input type="hidden" name="id" value="<%= chamado.getId()%>">
                <div class="full-box">
                    <label for="descricao">Descrição</label>
                    <input required="required" type="text" name="descricao" value="<%= chamado.getDescricao()%>" placeholder="Digite a descrição do chamado" data-required data-only-letters>
                </div>
                
                <div class="full-box">
                    <label for="descricao">Observação</label>
                    <input required="required" type="text" name="observacao" value="<%= chamado.getObservacao()%>" placeholder="Digite a observação do chamado" data-required data-only-letters>
                <br>
                </div>
                <br>
                <br>

                <div class="full-box">
                    <input id="btn-submit" type="submit" value="Registrar">
                </div>
            </form>
                <br>
                <br>
        </div>
        <p class="error-validation template"></p>
        <script src="js/scriptCadastroLogin.js"></script>
        <%@include file="listachamado.jsp" %>
    </body>
</html>
