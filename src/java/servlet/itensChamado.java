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

//        String parametro = request.getParameter("nome");
//        System.out.println("Nome que veio no parâmetro: " + parametro);
//
//        String parametro2 = request.getParameter("id");
//        System.out.println("ID: " + parametro2);
        String param = request.getParameter("param");

        // ================= adicionar CATEGORIA ======================================        
        if (param.equals("addCategoria")) {
            String id = request.getParameter("id");

            Categoria categ = new CategoriaDAO().consultarId(Integer.parseInt(id));

            if (categ != null) {

                request.setAttribute("objItemCategoria", categ);

                encaminharPagina("itemChamado.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }
        
        //EXCLUIR ITEM DE CHAMADO
        if (param.equals("exItemChamado")) {
            
            String id = request.getParameter("id");
            String id_chamado = request.getParameter("id_chama");
            
            String item = new ChamadoDAO().excluirItem(Integer.parseInt(id));
            
            Chamado chama = new ChamadoDAO().consultarId(Integer.parseInt("1"));
            
            
            if (item != null) {
                
                request.setAttribute("objChamado", chama);
                encaminharPagina("itemChamado.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
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

            System.out.println("CampoDeBusca: " + criterio);

            ArrayList<Categoria> categorias = new CategoriaDAO().consultar(criterio);

            request.setAttribute("categoriasPesquisa", categorias);

            encaminharPagina("itemChamado.jsp", request, response);
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
