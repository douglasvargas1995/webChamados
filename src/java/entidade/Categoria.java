/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.math.BigDecimal;

/**
 *
 * @author pretto
 */
public class Categoria {

    private int id;
    private String descricao;
    private char situacao;
    private BigDecimal valor_hora;
    private String observacao;

    public BigDecimal getValor() {
        return valor_hora;
    }

    public void setValor(BigDecimal valor) {
        this.valor_hora = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getSituacao() {
        return situacao;
    }

    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }
}
