package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Login;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Douglas
 */
public class LoginDAO implements IDAO<Login> {

    ResultSet resultadoQ;

    public boolean salvarInicial(Login login) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into login values "
                    + "(default,"
                    + " '" + login.getNome() + "',"
                    + " '" + login.getSobrenome() + "',"
                    + " '" + login.getEmail() + "',"
                    + " '" + login.getSenha() + "',"
                    + " '" + login.getEstado() + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar login: " + e);
            return false;
        }
    }

    @Override
    public String salvar(Login objeto) {
        try {
//            Statement stm = ferrados.Ferrados.conexao.createStatement();
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into login values "
                    + "(default,"
                    + " '" + objeto.getNome() + "',"
                    + " '" + objeto.getSobrenome() + "',"
                    + " '" + objeto.getEmail() + "',"
                    + " '" + objeto.getSenha() + "',"
                    + " 'A')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar login: " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Login o) {
        String saida = null;

        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update login "
                    + "set nome = '" + o.getNome() + "', "
                    + "sobrenome = '" + o.getSobrenome() + "', "
                    + "email = '" + o.getEmail() + "', "
                    + "senha = '" + o.getSenha() + "', "
                    + "estado = 'A' "
                    + "where id = " + o.getId();

            int retorno = stm.executeUpdate(sql);

            if (retorno != 0) {
                saida = null;
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar login: " + e);
            saida = e.toString();
        }

        return saida;
    }

    @Override
    public String excluir(int id) {

        String saida = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "Update login "
                    + "set estado = 'I' "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                saida = "ok";
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro excluir login = " + e);
            return e.toString();
        }
        return saida;
    }

    @Override
    public ArrayList<Login> consultarTodos() {
        ArrayList<Login> logins = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "login "
                    + "WHERE estado = 'A' "
                    + "order by id";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Login l = new Login();

                l.setId(resultado.getInt("id"));
                l.setNome(resultado.getString("nome"));
                l.setSobrenome(resultado.getString("sobrenome"));
                l.setEmail(resultado.getString("email"));
                l.setSenha(resultado.getString("senha"));
                l.setEstado(resultado.getString("estado"));

                logins.add(l);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login: " + e);
        }

        return logins;
    }

    @Override
    public boolean registroUnico(Login o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Login> consultar(String criterio) {

        ArrayList<Login> usuarios;
        usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * "
                    + "FROM login "
                    + "WHERE nome ilike '%" + criterio + "%' "
                    + "AND estado = 'A' "
                    + "ORDER BY nome";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Login u = new Login();

                u.setId(retorno.getInt("id"));
                u.setNome(retorno.getString("nome"));
                u.setSobrenome(retorno.getString("sobrenome"));
                u.setEmail(retorno.getString("email"));
                u.setEstado(retorno.getString("estado"));

                usuarios.add(u);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login: " + e);
            return null;
        }

        return usuarios;
    }

    @Override
    public Login consultarId(int id) {
        Login c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "login "
                    + "where "
                    + "id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Login();

                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome"));
                c.setSobrenome(resultado.getString("sobrenome"));
                c.setEmail(resultado.getString("email"));
                c.setSenha(resultado.getString("senha"));
                c.setEstado(resultado.getString("estado"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar login por ID: " + e);
        }

        return c;
    }

    @Override
    public boolean consultar(Login o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String consultarLogin(String usuario, String senha) {
        //usuario         = ok
        //senha invalida  = n
        //usuario inativo = null
        String estado = "A";

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from login "
                    + "where  "
                    + "email = '" + usuario + "' AND "
                    + "senha = '" + senha + "' AND "
                    + "estado = '" + estado + "'";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                return "ok";
            } else {
                return "n";
            }

        } catch (Exception e) {
            System.out.println("Erro consultar Usuario = " + e);
            return "n";
        }
    }

    public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relusuarios.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public boolean cadastrarLogin(HttpServletRequest request, HttpServletResponse response) {
        Login u = new Login();

        boolean ok = false;

        int id = Integer.parseInt(request.getParameter("id"));

        u.setId(id);
        u.setNome(request.getParameter("nome"));
        u.setSobrenome(request.getParameter("sobrenome"));
        u.setEmail(request.getParameter("email"));
        u.setSenha(request.getParameter("senha"));
        u.setContrasenha(request.getParameter("contrasenha"));
        u.setEstado(request.getParameter("estado"));

        String retorno = "";

        if (!"".equals(u.getNome()) && !"".equals(u.getSobrenome()) && !"".equals(u.getEmail()) && !"".equals(u.getSenha()) && (u.getSenha().equals(u.getContrasenha()))) {
            if (id == 0) {

                retorno = new LoginDAO().salvar(u);

            } else {
                retorno = new LoginDAO().atualizar(u);
            }
        }

        if (retorno == null) {
            ok = true;
        } else {
            ok = false;
        }

        return ok;
    }

    public int consultaQtdeUsuario(String situacao) {

        String sql = "select count(id) as quantidade from login where estado = '" + situacao + "'";

        try {

            ResultSet resultado = new ConexaoBD().getConnection().createStatement().executeQuery(sql);
            if (resultado.next()) {
                int quantidade = Integer.parseInt(resultado.getString("quantidade"));
                return quantidade;
            }

        } catch (Exception e) {
            System.out.println("Erro ao encontrar usuario = " + e.toString());
        }
        return 0;
    }
    
    public boolean netIsAvaliable() {
        try {
            final URL url = new URL("http://google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }

    }
}
