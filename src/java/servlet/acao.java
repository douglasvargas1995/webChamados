/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CategoriaDAO;
import dao.ChamadoDAO;
import dao.LoginDAO;
import entidade.Categoria;
import entidade.Chamado;
import entidade.Login;
import java.io.IOException;
import java.io.PrintWriter;
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

        ///EXCLUIR LOGIN
        if (param.equals("exLogin")) {
            String id = request.getParameter("id");
            String log = new LoginDAO().excluir(Integer.parseInt(id));

            if (log != null) {

                //request.setAttribute("objLogin", log);
                encaminharPagina("cadastroLogin.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }

        }

        ///EDITAR LOGIN
        if (param.equals("edLogin")) {
            String id = request.getParameter("id");

            Login log = new LoginDAO().consultarId(Integer.parseInt(id));

            if (log != null) {

                request.setAttribute("objLogin", log);

                encaminharPagina("cadastroLogin.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }

        //FINALIZAR CHAMADO -ok
        if (param.equals("finalizarChamado")) {

            String retorno = null;
            // capturar dados que vieram do REQUEST
            int id = Integer.parseInt(request.getParameter("id"));

            retorno = new ChamadoDAO().finalizar(id);

            if ("ok".equals(retorno)) {
                // deu certo
                request.setAttribute("tipoCadastro", "Chamado");

                request.setAttribute("paginaRetorno", "chamado.jsp");

                encaminharPagina("chamado.jsp", request, response);
                //exibir um alert
            } else {
                // deu errado
                encaminharPagina("erro.jsp", request, response);
            }
        }
        //EDITAR CHAMADO
        if (param.equals("editarChamado")) {
            String id = request.getParameter("id");

            Chamado chama = new ChamadoDAO().consultarId(Integer.parseInt(id));

            if (chama != null) {

                request.setAttribute("objChamado", chama);

                encaminharPagina("cadastraChamado.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }

        }
        
        //ATENDER CHAMADO
        if (param.equals("atenderChamado")) {
            String id = request.getParameter("id");

            Chamado chama = new ChamadoDAO().consultarId(Integer.parseInt(id));

            if (chama != null) {

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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // processRequest(request, response);
            System.out.println("ESTOU no POST");

            String param = request.getParameter("param");

            ///SALVAR LOGIN
            if (param.equals("salvarLogin")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
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
            
             // ================= CHAMADO ======================================
            if (param.equals("salvarChamado")) {
                // capturar dados que vieram do REQUEST
                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");
                String observacao = request.getParameter("observacao");
                // validacoes dos campos - não farei
                // criar OBJ do tipo que será salvo
                Chamado c = new Chamado();
                c.setId(id);
                c.setDescricao(descricao);
                c.setObservacao(observacao);

                // chamar o salvar e aguardar o retorno
                String retorno = null;
                if (id == 0) {
                    retorno = new ChamadoDAO().salvar(c);
                } else {
                    retorno = new ChamadoDAO().atualizar(c);
                }

                if (retorno == null) {
                    // deu certo
                    request.setAttribute("tipoCadastro", "Chamado");
                    request.setAttribute("paginaRetorno", "cadastraChamado.jsp");

                    encaminharPagina("sucesso.jsp", request, response);
                } else {
                    // deu errado
                    encaminharPagina("erro.jsp", request, response);
                }

            }
            

            ///AUTENTICAÇÃO DE LOGIN COM CONSULTA NO BANCO DE DADOS
            if (param.equals("login")) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");

                String loginOk = new LoginDAO().consultarLogin(email, senha);

                if ("ok".equals(loginOk)) {
                    // deu certo
                    HttpSession sessao = ((HttpServletRequest) request).getSession();

                    sessao.setAttribute("usuarioLogado", email);
                    encaminharPagina("chamado.jsp", request, response);

                } else if ("n".equals(loginOk)) {
                    // senha errada
                    encaminharPagina("erroSenha.jsp", request, response);
                    System.out.println("senha errada ou usuario inexistente");
                } else {
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
        public String getServletInfo
        
            () {
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
