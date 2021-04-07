package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Login;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                    + " '" + objeto.getEstado() + "')";

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
                    + "set sobrenome = '" + o.getSobrenome() + "', "
                    + "set email = '" + o.getEmail() + "', "
                    + "set senha = '" + o.getSenha() + "', "
                    + "situacao = '" + o.getEstado() + "' "
                    + "where id = " + o.getId();

            System.out.println("SQL: " + sql);

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
                    + "DELETE FROM login "
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registroUnico(Login o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Login> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}