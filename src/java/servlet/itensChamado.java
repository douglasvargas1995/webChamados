/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CategoriaDAO;
import dao.ChamadoDAO;
import entidade.Categoria;
import entidade.Chamado;
import entidade.Item_chamado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas
 */
@WebServlet(name = "itensChamado", urlPatterns = {"/itensChamado"})
public class itensChamado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet itensChamado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet itensChamado at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        System.out.println("ESTOU no GET itens");

        String param = request.getParameter("param");

        // ================= adicionar CATEGORIA ======================================        
        if (param.equals("addCategoria")) {
            String id_categoria = request.getParameter("id");

            int id_chamado = Integer.parseInt(request.getParameter("id_chamado"));
            Categoria categ = new CategoriaDAO().consultarId(Integer.parseInt(id_categoria));

            Chamado chama = new ChamadoDAO().consultarId(id_chamado);

            if (categ != null) {

                request.setAttribute("objItemCategoria", categ);
                request.setAttribute("objChamado", chama);
                encaminharPagina("itemChamado.jsp", request, response);
                System.out.println("id chamado ->" + chama.getDescricao());
            } else {
                encaminharPagina("erro.jsp", request, response);
            }

        }

        //EXCLUIR ITEM DE CHAMADO
        if (param.equals("exItemChamado")) {

            String id = request.getParameter("id");
            String id_chamado = request.getParameter("id_chamado");

            String item = new ChamadoDAO().excluirItem(Integer.parseInt(id));

            Chamado chama = new ChamadoDAO().consultarId(Integer.parseInt(id_chamado));

            if (item != null) {

                request.setAttribute("objChamado", chama);
                encaminharPagina("itemChamado.jsp", request, response);
                System.out.println("excluiu item chamado");
            } else {
                //encaminharPagina("erro.jsp", request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        String param = request.getParameter("param");

        if (param.equals("comboCategoria")) {
            System.out.println("Valor do Select: " + param);
        }

        if (param.equals("pesquisa")) {
            String criterio = request.getParameter("campoDeBusca");

            System.out.println("CampoDeBusca: " + criterio);

            ArrayList<Categoria> categorias = new CategoriaDAO().consultar(criterio);

            request.setAttribute("categoriasPesquisa", categorias);

            encaminharPagina("pesquisa1.jsp", request, response);
        }

        if (param.equals("pesquisaItensCategoria")) {
            String criterio = request.getParameter("campoDeBusca");

            String id_chamado = request.getParameter("id_chamado");
            Chamado chama = new ChamadoDAO().consultarId(Integer.parseInt(id_chamado));
            System.out.println("CampoDeBusca: " + criterio);

            ArrayList<Categoria> categorias = new CategoriaDAO().consultar(criterio);

            request.setAttribute("categoriasPesquisa", categorias);

            request.setAttribute("objChamado", chama);
            //
            encaminharPagina("itemChamado.jsp", request, response);

        }

        if (param.equals("pesquisaChamado")) {
            String criterio = request.getParameter("campoDeBusca");
            String dt_inicial = request.getParameter("dataIni");
            String dt_final = request.getParameter("dataFim");
            String estado = request.getParameter("estado");

            System.out.println("CampoDeBusca: " + criterio);
            System.out.println("Data Inicial: " + dt_inicial);
            System.out.println("Data Final: " + dt_final);
            System.out.println("Estado: " + estado);

            ArrayList<Chamado> chamados = new ChamadoDAO().consultarChamado(criterio, dt_inicial, dt_final, estado);

            request.setAttribute("objPesquisaChamado", chamados);

            encaminharPagina("chamado.jsp", request, response);
        }
        
        //gerar relatório chamados com filtros
        if (param.equals("gerarRelatorioChamado")) {
            
            String criterio = request.getParameter("campoDeBusca");
            String dt_inicial = request.getParameter("dataIni");
            String dt_final = request.getParameter("dataFim");
            String estado = request.getParameter("estado");

            System.out.println("CampoDeBusca: " + criterio);
            System.out.println("Data Inicial: " + dt_inicial);
            System.out.println("Data Final: " + dt_final);
            System.out.println("Estado: " + estado);

            ArrayList<Chamado> chamados = new ChamadoDAO().consultarChamado(criterio, dt_inicial, dt_final, estado);

            request.setAttribute("objChamado", chamados);

            encaminharPagina("relchamadosfiltros.jsp", request, response);
        }
        ///inserir item de chamado
        if (param.equals("inserirItemChamado")) {

            int id = 0;
            int id_categoria = Integer.parseInt(request.getParameter("categoria"));
            int id_classifica = Integer.parseInt(request.getParameter("classificacao"));
            String valor = request.getParameter("valor");
            String descricao = request.getParameter("descricao");
            int id_chamado = Integer.parseInt(request.getParameter("chamado"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            
            Item_chamado item = new Item_chamado();
            item.setId_categoria(id_categoria);
            item.setId_classifica(id_classifica);
            item.setValor(Double.parseDouble(valor));
            item.setDescricao(descricao);
            item.setId_chamado(id_chamado);
            item.setQuantidade(quantidade);
            Chamado chama = new ChamadoDAO().consultarId(id_chamado);

            String retorno = null;
            if (id == 0) {
                retorno = new ChamadoDAO().salvarItem(item);
            } 
            
            if (retorno == null) {
                // deu certo
                request.setAttribute("tipoCadastro", "Chamado");
                request.setAttribute("paginaRetorno", "itemChamado.jsp");
                request.setAttribute("objChamado", chama);

                encaminharPagina("itemChamado.jsp", request, response);
            } else {
                // deu errado
                encaminharPagina("erro.jsp", request, response);
            }
        }

        // ================= CATEGORIA ======================================
        if (param.equals("salvarCategoria")) {
            // capturar dados que vieram do REQUEST
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");
            String valor = request.getParameter("valor");
            String observacao = request.getParameter("observacao");
            // validacoes dos campos - não farei
            // criar OBJ do tipo que será salvo
            Categoria c = new Categoria();
            c.setId(id);
            c.setDescricao(descricao);
            c.setSituacao(situacao);
            c.setValor(valor);
            c.setObservacao(observacao);

            // chamar o salvar e aguardar o retorno
            String retorno = null;
            if (id == 0) {
                retorno = new CategoriaDAO().salvar(c);
            } else {
                retorno = new CategoriaDAO().atualizar(c);
            }

            if (retorno == null) {
                // deu certo
                request.setAttribute("tipoCadastro", "Categoria");
                request.setAttribute("paginaRetorno", "cadastroCategoria.jsp");

                encaminharPagina("sucesso.jsp", request, response);
            } else {
                // deu errado
                encaminharPagina("erro.jsp", request, response);
            }

        }
    }

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
