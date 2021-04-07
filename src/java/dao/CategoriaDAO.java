/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Categoria;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author pretto
 */
public class CategoriaDAO implements IDAO<Categoria> {

    ResultSet resultadoQ;

    public boolean salvarInicial(Categoria categoria) {
        try {
//            Statement stm = ferrados.Ferrados.conexao.createStatement();
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into categoria values "
                    + "(default,"
                    + " '" + categoria.getDescricao() + "',"
                    + " '" + categoria.getSituacao() + "',"
                    + " " + categoria.getValor() + ","
                    + " '" + categoria.getObservacao()+ "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e);
            return false;
        }
    }

    @Override
    public String salvar(Categoria o) {
        try {
//            Statement stm = ferrados.Ferrados.conexao.createStatement();
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into categoria values "
                    + "(default,"
                    + " '" + o.getDescricao() + "',"
                    + " '" + o.getSituacao() + "',"
                    + "" + o.getValor() + ","
                    + " '" + o.getObservacao()+ "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Categoria o) {
        String saida = null;

        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update categoria "
                    + "set descricao = '" + o.getDescricao() + "', "
                    + "set situacao = '" + o.getSituacao() + "', "
                    + "set valor_hora = '" + o.getValor() + "', "
                    + "observacao = '" + o.getObservacao() + "' "
                    + "where id = " + o.getId();

            System.out.println("SQL: " + sql);

            int retorno = stm.executeUpdate(sql);

            if (retorno != 0) {
                saida = null;
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Categoria: " + e);
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
                    + "DELETE FROM categoria "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);
            
            if (resultado != 0) {
                saida = "ok";
            } else {
                saida = "Erro";
            }
            
        } catch (Exception e) {
            System.out.println("Erro excluir categoria = " + e);
            return e.toString();
        }
        return saida;
    }

    @Override
    public ArrayList<Categoria> consultarTodos() {

        ArrayList<Categoria> categorias = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "categoria "
                    + "order by descricao";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Categoria c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setSituacao(resultado.getString("situacao").charAt(0));
                c.setValor(resultado.getBigDecimal("valor_hora"));
                c.setObservacao(resultado.getString("observacao"));
                

                categorias.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Categorias: " + e);
        }

        return categorias;
    }

    @Override
    public ArrayList<Categoria> consultar(String criterio) {
        ArrayList<Categoria> categorias = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "categoria "
                    + "where "
                    + "descricao ilike '%" + criterio + "%' "
                    + "order by descricao";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Categoria c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setSituacao(resultado.getString("situacao").charAt(0));

                categorias.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Categorias: " + e);
        }

        return categorias;
    }

    @Override
    public Categoria consultarId(int id) {
        Categoria c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "categoria "
                    + "where "
                    + "id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setSituacao(resultado.getString("situacao").charAt(0));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Categoria por ID: " + e);
        }

        return c;
    }

    @Override
    public boolean registroUnico(Categoria o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Categoria o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}