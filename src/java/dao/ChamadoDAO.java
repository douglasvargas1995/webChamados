/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Chamado;
import entidade.Data;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class ChamadoDAO implements IDAO<Chamado> {

    @Override
    public String salvar(Chamado objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String atualizar(Chamado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Chamado> consultarTodos() {

        ArrayList<Chamado> chamados = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT c.id,l.email,c.descricao AS descricao_chamado, cl.descricao AS descricao_classifica,c.data_inicial,c.data_final,c.estado "
                    + "FROM chamado c "
                    + "INNER JOIN item_chamado it on it.id = c.id_item_chamado "
                    + "INNER JOIN login l on l.id=c.id_login "
                    + "INNER JOIN classifica cl on cl.id = it.id_classifica";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Chamado c = new Chamado();

                c.setId(resultado.getInt("id"));
                c.setDescricao_chamado(resultado.getString("descricao_chamado"));
                c.setDescricao_classifica(resultado.getString("descricao_classifica"));
                c.setEmail(resultado.getString("email"));
                c.setData_inicial(resultado.getDate("data_inicial"));
                c.setData_final(resultado.getDate("data_final"));
                c.setEstado(resultado.getString("estado"));

                chamados.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Chamados: " + e);
        }

        return chamados;
    }

    @Override
    public boolean registroUnico(Chamado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Chamado> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Chamado o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        public String finalizar(int id) {

        String saida = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "Update chamado "
                    + "set estado = 'I', "
                    + "data_final = '" + Data.formatarData(new Date()) + "' "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                saida = "ok";
            } else {
                saida = null;
            }

        } catch (Exception e) {
            System.out.println("Erro excluir login = " + e);
            return e.toString();
        }
        return saida;
    }
}
