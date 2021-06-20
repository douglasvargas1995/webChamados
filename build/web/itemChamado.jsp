<%-- 
    Document   : itemChamado
    Created on : 14/05/2021, 17:38:43
    Author     : Douglas
--%>

<%@page import="entidade.Formatacao"%>
<%@page import="entidade.Chamado"%>
<%@page import="entidade.Classifica"%>
<%@page import="entidade.Categoria"%>
<%@page import="dao.ChamadoDAO"%>
<%@page import="entidade.Item_chamado"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<script type="text/javascript">

</script>
<html>
    <head>
        <title>Ticket Chamado - Itens</title>
    </head>
    <body>
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
        <%
            Categoria categoria = (Categoria) request.getAttribute("objItemCategoria");

            if (categoria == null) {
                categoria = new Categoria();

                categoria.setId(0);
                categoria.setDescricao("");
                categoria.setSituacao("");
                categoria.setValor("");
                categoria.setObservacao("");

            }
        %>
        <%@include file="menu.jsp" %>

        <h2>Dados do Ticket - Chamado</h2>
        <label>Código</label>
        <input type="hidden" name="id" value="<%= chamado.getId()%>">
        <input type="text" name="id_chamado" value="<%= chamado.getId()%>" disabled="">

        <label>Descrição</label>
        <input type="text" name="descricao_chamado" value="<%= chamado.getDescricao()%>" disabled="">

        <label>Situação</label>
        <input type="text" name="estado_chamado" value="<%= chamado.getEstado()%>" disabled="">

        <label>Data Abertura</label>
        <input type="text" name="datainicial" value="<%= Formatacao.ajustaDataDMA(String.valueOf(chamado.getData_inicial()))%>" disabled="">

        <br>
        <br>
        <hr>

        <h6>Itens do chamado</h6>
        <%
            ArrayList<Item_chamado> itens = new ChamadoDAO().consultarItens(chamado.getId());
        %>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <th>#</th>
                <th>Descrição</th>
                <th>Classificação</th>
                <th>Valor Hora R$</th>
                <th>Qtde</th>
                <th>Ação</th>

                <%
                    for (int i = 0; i < itens.size(); i++) {
                        Item_chamado item = itens.get(i);
                %>
                <tr>
                    <td><%= item.getId()%></td>
                    <td><%= item.getDescricao()%></td>
                    <td><%= item.getClassificacao()%></td>
                    <td><%= item.getValor()%></td>
                    <td><%= item.getQuantidade()%></td>
                    <td><a href='/WebChamados/itensChamado?param=exItemChamado&id=<%= item.getId()%>&id_chamado=<%=chamado.getId()%>'<input type="button" class="btn-sm btn-danger" value="Remover">Remover</a>
                    <td>


                </tr>

                <%
                    }
                %>

            </table>
        </div>
        <br>
        <br> 

        <a href='cadastraChamado.jsp'>Voltar</a>
        <br>
        <%
            Chamado soma_itens = new ChamadoDAO().somaItens(chamado.getId());
        %>
        <h3>Total: R$ <%= soma_itens.getValor_total()%></h3>
        <br>

        <hr>
        <h6>Pesquisa de itens</h6>

        <form method="post" action="/WebChamados/itensChamado?param=pesquisaItensCategoria">
            
            <input type="text" name="id_chamado" value="<%= chamado.getId()%>">

            <input type="text" name="campoDeBusca" placeholder="Digite o que deseja pesquisar">

            <input type="submit" value="Pesquisar">

        </form>


        <br>
        <form name='' method='post' action='/WebChamados/itensChamado?param=inserirItemChamado'>
            <label>Código Chamado</label>
            <input size ="2" type="text" name="chamado" value="<%= chamado.getId()%>" required="">
            
            <label>Código</label>
            <input size ="2" type="text" name="categoria" value="<%= categoria.getId()%>" required="">

            <label>Descrição</label>
            <input type="text" name="descricao" value="<%= categoria.getDescricao()%>" required="">
            
            <label>R$</label>
            <input size ="2" type="text" name="valor" value="<%= categoria.getValor()%>" required="">

            <label>Qtde de horas</label>
            <input type="number" name="quantidade" required="">

            <select name="classificacao" id="classificacao" required="">
                <option value="0">Selecione Classificação</option>

                <%
                    ArrayList<Classifica> clas = new ChamadoDAO().consultarClassificacao();

                    for (int i = 0; i < clas.size(); i++) {
                %>           
                <option value="<%= clas.get(i).getId()%>"><%= clas.get(i).getDescricao()%></option>
                <%
                    }
                %>
            </select>
            
            <input type="submit" class="btn-sm btn-primary" value="Inserir">
            
        </form>
        <%
            ArrayList<Categoria> categs = (ArrayList) request.getAttribute("categoriasPesquisa");

            if (categs != null) {

                if (categs.size() == 0) {
        %>  
        <p>Nenhum resultado encontrado.</p>

        <%
        } else {
        %>

        <h4>Resultados</h4>

        <div class="table-responsive">
            <table class="table table-striped table-sm" id="tabelaItens">
                <th>#</th>
                <th>Descrição</th>
                <th>Valor Hora</th>
                <th>Situação</th>
                <th>Ação</th>

                <%
                    for (int i = 0; i < categs.size(); i++) {
                        Categoria categ = categs.get(i);
                %>
                <tr>

                    <td><%= categ.getId()%></td>
                    <td><%= categ.getDescricao()%></td>
                    <td><%= categ.getValor()%></td>
                    <td><%= categ.getSituacao()%></td>
                    <td><a href='/WebChamados/itensChamado?param=addCategoria&id=<%= categ.getId()%>&id_chamado=<%=chamado.getId()%>'<input type="button" class="btn-sm btn-outline-success" value="Selecionar">Selecionar</a>
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
    
         <br>

        <hr>
        
        <form method="post" action="/WebChamados/itensChamado?param=finalizarChamado">
            
            <label>Código Chamado</label>
            <input type="text" name="id" value="<%= chamado.getId()%>" required="">

            <label>R$</label>
            <input type="text" name="valor" value="<%=soma_itens.getValor_total()%>" required="">

            <input type="submit" class = "btn-danger" value="Finalizar">

        </form>
    </body>
</html>
