package entidade;

/**
 *
 * @author Douglas
 */
public class Item_chamado {
    
    private int id;
    private int id_categoria;
    private int id_classifica;
    private double valor;
    private String descricao;

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
}
