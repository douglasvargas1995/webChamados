package entidade;

/**
 *
 * @author Douglas
 */
public class Item_chamado {
    
    private int id;
    private int id_categoria;
    private int id_classifica;
    private int id_chamado;
    private double valor;
    private String descricao;
    private String categoria;
    private String classificacao;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_classifica() {
        return id_classifica;
    }

    public void setId_classifica(int id_classifica) {
        this.id_classifica = id_classifica;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getId_chamado() {
        return id_chamado;
    }

    public void setId_chamado(int id_chamado) {
        this.id_chamado = id_chamado;
    }
}
