<%-- 
    Document   : exemploinicial
    Created on : Mar 15, 2021, 9:28:28 PM
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
        <h1>Hello World!</h1>
        <%
            String dataAtual = String.valueOf(new Date());
            int x = 10;
            System.out.println("X = " + x);
        %>
        <p>Data atual</p>
        <h2> 
            <%= dataAtual + " e o nº: " + x%>
            <br>
            <%= Formatacao.getDataHoraAtual()%>
        </h2>

        <p>Data atual - 2</p>
        <h2> <%= Formatacao.getDataHoraAtual()%> </h2>

        <p> O valor da nota é: <%= x%></p>

        <hr>
        <form name="cadastroX" id="cadastroX" method="post" action="/WebApp2021A/acao?param=salvarPessoa">
            <label>Nome </label>
            <input type="text" name="nomePessoa" value="" required>

            <input type="submit" value="Enviar">
        </form>


        <table border='1'>
            <tr>
                <td>id</td>
                <td>nome</td>
                <td>endereço</td>
            </tr>
            <tr>
                <td><a href='/WebApp2021A/acao?param=edPessoa&id=1'>1</a></td>
                <td>Juca Bala</td>
                <td>Rua da Loucura, 666</td>
            </tr>
            <tr>
                <td><a href='/WebApp2021A/acao?param=edPessoa&id=2'>2</a></td>
                <td>Juliana da Silva</td>
                <td>Rua da Ju, 1000</td>
            </tr>
        </table>

        <br>
        <br>
        <br>

    </body>
</html>
