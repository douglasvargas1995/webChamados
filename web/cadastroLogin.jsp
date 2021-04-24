<%-- 
    Document   : cadastroLogin
    Created on : 29/03/2021, 20:57:22
    Author     : Douglas
--%>

<%@page import="entidade.Login"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="menu.jsp" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro de login</title>
    <link rel="stylesheet" href="css/style.css">  
    <%
        Login login = (Login) request.getAttribute("objLogin");

        if (login == null) {
            login = new Login();

            login.setId(0);
            login.setNome("");
            login.setSobrenome("");
            login.setEmail("");
            login.setSenha("");
            login.setEstado("");
        }
    %>
    <body> 
        <div id="main-container">
            <h1>Cadastro de login</h1>
            <form name='formLogin' method='post' action='/WebChamados/acao?param=salvarLogin'>
                <input type="hidden" name="id" value="<%= login.getId()%>">
                <div class="full-box">
                    <label for="email">E-mail</label>
                    <input required="required" type="email" name="email" value="<%= login.getEmail()%>" placeholder="Digite seu e-mail" data-min-length="2" data-email-validate>
                </div>
                <div class="half-box spacing">
                    <label for="name">Nome</label>
                    <input required="required" type="text" name="nome" pattern="[a-zA-Z\s]+$" value="<%= login.getNome()%>" placeholder="Digite seu nome" data-required data-min-length="3" data-max-length="16">
                </div>
                <div class="half-box">
                    <label for="lastname">Sobrenome</label>
                    <input required="required" type="text" name="sobrenome" pattern="[a-zA-Z\s]+$" value="<%= login.getSobrenome()%>" placeholder="Digite seu sobrenome" data-required data-only-letters>
                </div>
                <div class="half-box spacing">
                    <label for="lastname">Senha</label>
                    <input required="required"type="password" name="senha" value="<%= login.getSenha()%>" placeholder="Digite sua senha" data-password-validate data-required>
                </div>
                <div class="half-box">
                    <label for="passconfirmation">Confirmação de senha</label>
                    <input required="required" type="password" name="passconfirmation" id="passwordconfirmation" placeholder="Digite novamente sua senha" data-equal="senha" data-password-validate data-required>
                </div>
                <div>
                    <input type="checkbox" name="agreement" id="agreement" required>
                    <label for="agreement" id="agreement-label">Eu li e aceito os <a href="#">termos de uso</a></label>
                </div>
                <div class="full-box">
                    <input id="btn-submit" type="submit" value="Registrar">
                </div>
            </form>
        </div>
        <p class="error-validation template"></p>
        <script src="js/scriptCadastroLogin.js"></script>
        <%@include file="listausuario.jsp" %>
    </body>
</html>
