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
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

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
                    + "situacao = '" + o.getSituacao() + "', "
                    + "valor_hora = '" + o.getValor() + "', "
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
                c.setSituacao(resultado.getString("situacao"));
                c.setValor(resultado.getString("valor_hora"));
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
                c.setSituacao(resultado.getString("situacao"));
                c.setValor(resultado.getString("valor_hora"));
                c.setObservacao(resultado.getString("observacao"));

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
                c.setSituacao(resultado.getString("situacao"));
                c.setValor(resultado.getString("valor_hora"));
                c.setObservacao(resultado.getString("observacao"));
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
    
    public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relcategorias.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

}
