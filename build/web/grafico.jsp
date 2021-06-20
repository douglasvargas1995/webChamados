<%-- 
    Document   : grafico
    Created on : 22/05/2021, 09:57:51
    Author     : Douglas
--%>

<%@page import="dao.LoginDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ChamadoDAO"%>
<%@page import="entidade.Chamado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<%@include file="menu.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estatística - Web Chamados</title>
        <!-- CUSTOM STYLE  -->

    </head>
    <body>
        <%
            int chamadoAberto = new ChamadoDAO().consultaQtdeChamado("ABERTO");
            int chamadoFechado = new ChamadoDAO().consultaQtdeChamado("FINALIZADO");

            int usuariosAtivo = new LoginDAO().consultaQtdeUsuario("A");
            int usuariosInativo = new LoginDAO().consultaQtdeUsuario("I");


        %>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">

            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChartChamado);
            google.setOnLoadCallback(drawChartUsuario);
            function drawChartChamado() {
                var dataVeiculo = google.visualization.arrayToDataTable([
                    ['Descrição', 'Quantidade'],
                    ['Abertos', <%= chamadoAberto%>],
                    ['Finalizados', <%= chamadoFechado%>],
                ]);

                var chartChamado = new google.visualization.PieChart(document.getElementById('piechartchamados'));

                chartChamado.draw(dataVeiculo);

            }
            function drawChartUsuario() {
                var dataUsuario = google.visualization.arrayToDataTable([
                    ['Descrição', 'Quantidade'],
                    ['Ativo', <%= usuariosAtivo%>],
                    ['Inativo', <%= usuariosInativo%>],
                    ['', 0],
                ]);

                var chartUsuario = new google.visualization.PieChart(document.getElementById('piechartusuario'));

                chartUsuario.draw(dataUsuario);

            }



        </script>
        <div class="content-wrapper">
            <div class="container">
                <legend>Estatísticas - Web Chamados </legend>
                <%
                    if (new LoginDAO().netIsAvaliable()) {

                %>

                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3>Usuários</h3>
                                <br>
                                Ativos   = <%= usuariosAtivo %>
                                <br>
                                Inativos = <%= usuariosInativo %>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">

                                <div id="piechartusuario" style="width:500px; height: 400px;"></div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3>Chamados</h3>
                                <br>
                                Abertos     = <%= chamadoAberto %>
                                <br>
                                Finalizados = <%= chamadoFechado %>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">

                                <div id="piechartchamados" style="width:500px; height: 400px;"></div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                </div>
                <%                } else {
                %>
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    Gráficos indisponíveis.
                </div>
                <%
                    }
                %>
            </div>
        </div>

        <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
        <!-- CORE JQUERY  -->
        <script src="js/jquery-1.10.2.js"></script>
        <!-- BOOTSTRAP SCRIPTS  -->
        <script src="js/bootstrap.js"></script>
        <!-- CUSTOM SCRIPTS  -->
        <!--  <script src="assets/js/custom.js"></script> -->


    </body>
</html>