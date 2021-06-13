/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Categoria;
import entidade.Chamado;
import entidade.Classifica;
import entidade.Data;
import entidade.Item_chamado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Douglas
 */
public class ChamadoDAO implements IDAO<Chamado> {

    @Override
    public String salvar(Chamado chamado) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into chamado values "
                    + "(default,"
                    + " 7 ,"
                    + " '" + Data.formatarData(new Date()) + "',"
                    + " '" + Data.formatarData(new Date()) + "',"
                    + " 'ABERTO',"
                    + " '" + chamado.getDescricao() + "',"
                    + " 0.0,"
                    + " '" + chamado.getObservacao() + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar chamado: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Chamado o) {
        String saida = null;

        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update chamado "
                    + "set descricao = '" + o.getDescricao() + "', "
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
            System.out.println("Erro ao atualizar Chamado: " + e);
            saida = e.toString();
        }

        return saida;

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

            String sql = "SELECT c.id,c.observacao,l.email,c.descricao AS descricao_chamado,c.data_inicial,c.data_final,c.estado "
                    + "FROM chamado c "
                    + "INNER JOIN login l on l.id=c.id_login ";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Chamado c = new Chamado();

                c.setId(resultado.getInt("id"));
                c.setDescricao_chamado(resultado.getString("descricao_chamado"));
                c.setEmail(resultado.getString("email"));
                c.setData_inicial(resultado.getDate("data_inicial"));
                c.setData_final(resultado.getDate("data_final"));
                c.setEstado(resultado.getString("estado"));
                c.setObservacao(resultado.getString("observacao"));

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
    public Chamado consultarId(int id) {
        Chamado c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "chamado "
                    + "where "
                    + "id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Chamado();

                c.setId(resultado.getInt("id"));
                c.setId_login(resultado.getInt("id_login"));
                c.setData_inicial(resultado.getDate("data_inicial"));
                c.setData_final(resultado.getDate("data_final"));
                c.setEstado(resultado.getString("estado"));
                c.setDescricao(resultado.getString("descricao"));
                c.setValor_total(resultado.getDouble("valor_total"));
                c.setObservacao(resultado.getString("observacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar chamado por ID: " + e);
        }

        return c;
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
                    + "set estado = 'FINALIZADO', "
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

    //metodo consultar todas as clasisificações, retorna um aray list
    public ArrayList<Classifica> consultarClassificacao() {

        ArrayList<Classifica> classificas = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from classifica";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Classifica c = new Classifica();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));

                classificas.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar classificação: " + e);
        }

        return classificas;
    }

    public ArrayList<Item_chamado> consultarItem() {

        ArrayList<Item_chamado> itens = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from item_chamado";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Item_chamado c = new Item_chamado();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));

                itens.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar classificação: " + e);
        }

        return itens;
    }

    public ArrayList<Categoria> consultarCategoria() {

        ArrayList<Categoria> categorias = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from categoria";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Categoria c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setValor("valor_hora");

                categorias.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar categoria: " + e);
        }

        return categorias;
    }

    //retorna um aray list de todos os itens refente daquele chamado
    public ArrayList<Item_chamado> consultarItens(int id) {
        ArrayList<Item_chamado> itens = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT it.id,it.quantidade,it.id_chamado,it.id_categoria,it.id_classifica,it.descricao AS descricao_item,cl.descricao AS descricao_classifica,it.valor "
                    + "FROM item_chamado it "
                    + "INNER JOIN classifica cl on cl.id = it.id_classifica "
                    + "where id_chamado = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Item_chamado i = new Item_chamado();

                i.setId(resultado.getInt("id"));
                i.setQuantidade(resultado.getInt("quantidade"));
                i.setId_categoria(resultado.getInt("id_categoria"));
                i.setId_classifica(resultado.getInt("id_classifica"));
                i.setClassificacao(resultado.getString("descricao_classifica"));
                i.setValor(resultado.getDouble("valor"));
                i.setDescricao(resultado.getString("descricao_item"));
                i.setId_chamado(resultado.getInt("id_chamado"));

                itens.add(i);
            }
            System.out.println("--" + sql);
        } catch (Exception e) {
            System.out.println("Erro ao consultar itens de chamado: " + e);

        }

        return itens;

    }

    public String excluirItem(int id) {
        String saida = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "DELETE FROM item_chamado "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                saida = "ok";
            } else {
                saida = "Erro";
            }

        } catch (Exception e) {
            System.out.println("Erro excluir item de chamado = " + e);
            return e.toString();
        }
        return saida;
    }

    //metodo recebe id do chamado e soma todos os itens com suas respectivas quantidades
    public Chamado somaItens(int id) {
        Chamado c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT SUM((valor * quantidade)) AS total "
                    + "FROM item_chamado where id_chamado = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Chamado();

                c.setValor_total(resultado.getDouble("total"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar soma dos itens do chamado: " + e);
        }

        return c;
    }

    public ArrayList<Chamado> consultarChamado(String criterio, String dt_inicial, String dt_final, String estado) {
        ArrayList<Chamado> chamados = new ArrayList();

        if ("1".equals(estado)) {
            estado = "ABERTO";
        } else if ("2".equals(estado)) {
            estado = "FINALIZADO";
        }

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select c.id,l.email,c.id_login,c.data_inicial, c.data_final, c.estado, c.descricao, c.valor_total, c.observacao from chamado c INNER JOIN login l on l.id = c.id_login "
                    + "WHERE c.data_inicial BETWEEN '" + dt_inicial + "' "
                    + "AND '" + dt_final + "' "
                    + "AND c.estado = '" + estado + "' "
                    + "AND c.descricao ilike '%" + criterio + "%' "
                    + "order by c.descricao";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Chamado c = new Chamado();

                c.setId(resultado.getInt("id"));
                c.setEmail(resultado.getString("email"));
                c.setId_login(resultado.getInt("id_login"));
                c.setData_inicial(resultado.getDate("data_inicial"));
                c.setData_final(resultado.getDate("data_final"));
                c.setEstado(resultado.getString("estado"));
                c.setDescricao(resultado.getString("descricao"));
                c.setValor_total(resultado.getDouble("valor_total"));
                c.setObservacao(resultado.getString("observacao"));

                chamados.add(c);
            }
            System.out.println("-->" + sql);
        } catch (Exception e) {
            System.out.println("Erro ao consultar chamados com filtro: " + e);
        }

        return chamados;
    }

    public String salvarItem(Item_chamado item) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into item_chamado values "
                    + "(default,"
                    + " " + item.getId_categoria() + " ,"
                    + " " + item.getId_classifica() + " ,"
                    + " " + item.getValor() + " ,"
                    + " '" + item.getDescricao() + "' ,"
                    + " " + item.getId_chamado() + " ,"
                    + " " + item.getQuantidade() + ")";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar item de chamado: " + e);
            return e.toString();
        }
    }

    public byte[] gerarRelatorio() {

        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relchamados.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public byte[] gerarRelatorioFiltros(Chamado chamado) {
        
        System.out.println("entro pra gerar relatorio");
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relchamadosfiltros.jrxml"));

            Map parameters = new HashMap();

            parameters.put("data_inicial", chamado.getData_inicial());
            parameters.put("data_final", chamado.getData_final());
            parameters.put("estado", chamado.getEstado());

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    
    public int consultaQtdeChamado(String situacao) {

        String sql = "select count(id) as quantidade from chamado where estado = '" + situacao + "'";

        try {

            ResultSet resultado = new ConexaoBD().getConnection().createStatement().executeQuery(sql);
            if (resultado.next()) {
                int quantidade = Integer.parseInt(resultado.getString("quantidade"));
                return quantidade;
            }

        } catch (Exception e) {
            System.out.println("Erro ao encontrar chamado = " + e.toString());
        }
        return 0;
    }

}
