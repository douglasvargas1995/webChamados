<%-- 
    Document   : relchamadosfiltros
    Created on : 21/05/2021, 13:44:42
    Author     : Douglas
--%>

<%@page import="entidade.Chamado"%>
<%@page import="dao.ChamadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório Chamados</title>
    </head>

    <body>
        <%      
            String dataIni = request.getParameter("dataIni");
            String dataFim = request.getParameter("dataFim");
            String estado  = request.getParameter("estado");
            String descricao  = request.getParameter("campoDeBusca");
            
            dataIni = apoio.Formatacao.ajustaDataDMA(dataIni);
            dataFim = apoio.Formatacao.ajustaDataDMA(dataFim);
            
            System.out.println("--"+dataIni);
            System.out.println("--"+dataFim);
            System.out.println("--"+estado);
            
            byte[] bytes = new ChamadoDAO().gerarRelatorioFiltros(dataIni,dataFim,estado,descricao);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
