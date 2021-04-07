<%-- 
    Document   : cadastroCategoria
    Created on : 29/03/2021, 21:43:14
    Author     : Douglas
--%>

<%@page import="java.math.BigDecimal"%>
<html lang="en">
    <%@include file="menu.jsp" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro de categoria</title>
    <link rel="stylesheet" href="css/style.css">    
    <%
        Categoria categoria = (Categoria) request.getAttribute("objCategoria");

        if (categoria == null) {
            categoria = new Categoria();

            categoria.setId(0);
            categoria.setDescricao("");
            categoria.setSituacao(' ');
            categoria.setValor(null);
            categoria.setObservacao("");

        }
    %>
    <body>        
        <div id="main-container">
            <h1>Cadastro de categoria</h1>
            <form name='formCategoria' method='post' action='/WebChamados/acao?param=salvarCategoria'>
                <input type="hidden" name="id" value="<%= categoria.getId()%>">
                <div class="full-box">
                    <label for="descricao">Descrição</label>
                    <input required="required" type="text" name="descricao" value="<%= categoria.getDescricao()%>" placeholder="Digite a descrição da categoria" data-required data-only-letters>
                </div>
                <div class="full-box">
                    <label for="valor">Valor</label>
                    <input required="required" type="number" name="valor" value="<%= categoria.getValor()%>" placeholder="Digite o valor da categoria em R$" data-required data-only-letters>
                </div>
                <div class="full-box">
                    <label for="observacao">Observação</label>
                    <input type="text" name="observacao" value="<%= categoria.getObservacao()%>" placeholder="Digite a observação da categoria" data-required data-only-letters>
                </div>
                <div class="full-box">
                    <label for="situacao">Situação</label>
                    <input required="required" type="text" name="situacao" value="<%= categoria.getSituacao()%>" placeholder="Digite a situação da categoria" data-required data-only-letters>
                </div>

                <div class="full-box">
                    <input id="btn-submit" type="submit" value="Registrar">
                </div>
            </form>
        </div>
        <p class="error-validation template"></p>
        <script src="js/scriptCadastroLogin.js"></script>
        <%@include file="listacategoria.jsp" %>
    </body>
</html>