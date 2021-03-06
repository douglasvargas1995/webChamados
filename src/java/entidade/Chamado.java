package entidade;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Chamado {
    
    private int id;
    private int id_item_chamado;
    private int id_login;
    private Date data_inicial;
    private Date data_final;
    private String estado;
    private String descricao;
    private String email;
    private String descricao_chamado;
    private String descricao_classifica;
    private Double valor_total;
    private String Observacao;

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao_chamado() {
        return descricao_chamado;
    }

    public void setDescricao_chamado(String descricao_chamado) {
        this.descricao_chamado = descricao_chamado;
    }

    public String getDescricao_classifica() {
        return descricao_classifica;
    }

    public void setDescricao_classifica(String descricao_classifica) {
        this.descricao_classifica = descricao_classifica;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_item_chamado() {
        return id_item_chamado;
    }

    public void setId_item_chamado(int id_item_chamado) {
        this.id_item_chamado = id_item_chamado;
    }

    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
     public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
