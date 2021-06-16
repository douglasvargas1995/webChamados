<%-- 
    Document   : relchamadosfiltros
    Created on : 21/05/2021, 13:10:13
    Author     : Douglas
--%>

<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="java.util.Map"%>
<%@page import="apoio.ConexaoBD"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.postgresql.core.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@page import="entidade.Chamado"%>
<%@page import="dao.ChamadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="menu.jsp" %>
<title>Relatório de Chamado</title>   
<body>        

    <h1>Ticket Chamado</h1>
    <form name='formChamado' method='post' action='relchamadosfiltros.jsp' target="_blank">

        <label>Data Inicial</label>    
        <input required type="date" id="dataIni" name="dataIni" value="">

        <label>Data Final</label>    
        <input required type="date" id="dataFim" name="dataFim" value="">

        <label>Situação</label>
        <select name="estado" id="estado">
            <option value="0">Selecione</option>
            <option value="1">ABERTO</option>
            <option value="2">FINALIZADO</option>
        </select>

        <input type="text" id="campoDeBusca" name="campoDeBusca" placeholder="Digite a descrição" value="">

        <input type="submit" value="Gerar Relatório">
    
    </form>

</body>
</html>
