/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CategoriaDAO;
import dao.LoginDAO;
import entidade.Categoria;
import entidade.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pretto
 */
public class acao extends HttpServlet {

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
            out.println("<title>Esse é o título do Servlet ACAO</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>SERVLET do meu coração ACAO " + request.getContextPath() + "</h1>");
            out.println("<h6>Data:" + new Date() + "</h6>");
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
        //  processRequest(request, response);
        System.out.println("ESTOU no GET");

//        String parametro = request.getParameter("nome");
//        System.out.println("Nome que veio no parâmetro: " + parametro);
//
//        String parametro2 = request.getParameter("id");
//        System.out.println("ID: " + parametro2);
        String param = request.getParameter("param");

        // ================= PESSOA =========================================
        if (param.equals("edPessoa")) {
            String id = request.getParameter("id");
            System.out.println("ID para edição: " + id);
        } else if (param.equals("exPessoa")) {

        }

        // ================= CATEGORIA ======================================        
        if (param.equals("edCategoria")) {
            String id = request.getParameter("id");

            Categoria categ = new CategoriaDAO().consultarId(Integer.parseInt(id));

            if (categ != null) {

                request.setAttribute("objCategoria", categ);

                encaminharPagina("cadastroCategoria.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }
        
        // ================= CATEGORIA ====================================== 
        if (param.equals("exCategoria")) {
            String id = request.getParameter("id");

            String categ = new CategoriaDAO().excluir(Integer.parseInt(id));

            if (categ != null) {

               // request.setAttribute("objCategoria", categ);

                encaminharPagina("cadastroCategoria.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }
        
        // =================== LOGIN ===================================
        if (param.equals("logout")) {
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            response.sendRedirect("login.jsp");
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
        System.out.println("ESTOU no POST");

        String param = request.getParameter("param");

        // ================= PESSOA =========================================
        if (param.equals("salvarPessoa")) {
            String nome = request.getParameter("nomePessoa");
            System.out.println("Nome pessoa: " + nome);

            // inacabado
        }
        
        if (param.equals("salvarLogin")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String estado = request.getParameter("estado");
            Login l = new Login();
            l.setId(id);
            l.setNome(nome);
            l.setSobrenome(sobrenome);
            l.setEmail(email);
            l.setSenha(senha);
            l.setEstado("A");
            
            String retorno = null;
            if (id == 0) {
                retorno = new LoginDAO().salvar(l);
            } else {
                retorno = new LoginDAO().atualizar(l);
            }

            if (retorno == null) {
                // deu certo
                request.setAttribute("tipoCadastro", "Login");
                request.setAttribute("paginaRetorno", "cadastroLogin.jsp");

                encaminharPagina("sucesso.jsp", request, response);
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
            String valor = (String) request.getParameter("valor_hora");
            String observacao = request.getParameter("observacao");            
            // validacoes dos campos - não farei
            // criar OBJ do tipo que será salvo
            Categoria c = new Categoria();
            c.setId(id);
            c.setDescricao(descricao);
            c.setSituacao('A');
            //BigDecimal bigInteger = new BigDecimal(valor);
            //c.setValor(bigInteger);
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
        
        if (param.equals("login")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            String loginOk = new LoginDAO().consultarLogin(email, senha);
            
            if ("ok".equals(loginOk)) {
                // deu certo
                encaminharPagina("menu.jsp", request, response);
            }else if("n".equals(loginOk)){
                // senha errada
                encaminharPagina("erroSenha.jsp", request, response);
                System.out.println("senha errada ou usuario inexistente");
            }else{
                System.out.println("usuario inativo");
            }
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

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }
}