<%-- 
    Document   : cadastroChamado
    Created on : 24/04/2021, 21:43:14
    Author     : Douglas
--%>

<%@page import="entidade.Formatacao"%>
<%@page import="dao.ChamadoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Chamado"%>
<html lang="en">
    <%@include file="menu.jsp" %>
    <title>Cadastro de chamado</title>  
    <%
        Chamado chamado = (Chamado) request.getAttribute("objChamado");

        if (chamado == null) {
            chamado = new Chamado();

            chamado.setId(0);
            chamado.setDescricao("");
            chamado.setEmail("");
            chamado.setData_inicial(null);
            chamado.setData_final(null);
            chamado.setEstado("");
            chamado.setId_login(0);
            chamado.setId_item_chamado(0);
            chamado.setObservacao("");

        }
    %>
    <body>        

        <h1>Ticket Chamado</h1>
        <form name='formChamado' method='post' action='/WebChamados/itensChamado?param=pesquisaChamado'>

            <label>Data Inicial</label>    
            <input required type="date" name="dataIni" value="<%= chamado.getData_inicial()%>">

            <label>Data Final</label>    
            <input required type="date" name="dataFim" value="<%= chamado.getData_final()%>">

            <label>Situação</label>
            <select name="estado">
                <option value="0">Selecione</option>
                <option value="1">ABERTO</option>
                <option value="2">FINALIZADO</option>
            </select>

            <input type="text" name="campoDeBusca" placeholder="Digite a descrição" value="<%= chamado.getDescricao()%>">

            <input type="submit" value="Pesquisar">
            
            
            

        </form>
        <br>
         <%
            ArrayList<Chamado> c = (ArrayList) request.getAttribute("objPesquisaChamado");
            
            if (c != null) {

                if (c.size() == 0) {
        %>  
        <p>Nenhum resultado encontrado.</p>

        <%
        } else {
        %>

        <h4>Resultados</h4>

        <div class="table-responsive">
            <table class="table table-striped table-sm" id="tabelaItens">
                <th>#</th>
                <th>Solicitante</th>
                <th>Descrição</th>
                <th>Aberto em</th>
                <th>Observação</th>
                <th>Situação</th>
                <th>Ação</th>

                <%
                    for (int i = 0; i < c.size(); i++) {
                        Chamado chama = c.get(i);
                %>
                <tr>
                    <td><%= chama.getId()%></td>
                    <td><%= chama.getEmail()%></td>
                    <td><%= chama.getDescricao()%></td>
                    <td><%= Formatacao.ajustaDataDMA(String.valueOf(chama.getData_inicial()))%></td>
                    <td><%= chama.getObservacao()%></td>
                    <td><%= chama.getEstado()%></td>
                    <td><a href='/WebChamados/acao?param=atenderChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-success" value="Atender">Atender</a>
                        <a href='/WebChamados/acao?param=editarChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-primary" value="Editar">Editar</a>
                        <a href='/WebChamados/acao?param=finalizarChamado&id=<%= chama.getId()%>'<input type="button" class="btn btn-danger" value="Finalizar">Finalizar</a>
                    <td>
                </tr>

                <%
                    }
                %>

            </table>
        </div>
        <%
                }
            }
        %>
    </body>
</html>