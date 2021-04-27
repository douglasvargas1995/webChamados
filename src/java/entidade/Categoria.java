package entidade;

/**
 *
 * @author douglas
 */
public class Categoria {

    private int id;
    private String descricao;
    private String situacao;
    private String valor_hora;
    private String observacao;

    public String getValor() {
        return valor_hora;
    }

    public void setValor(String valor) {
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
