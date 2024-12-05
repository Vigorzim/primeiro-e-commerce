package br.projeto.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "forma_pgto")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormaPgto;

    private String descricao;

    @OneToMany(mappedBy = "formaPagamento", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("formaPagamento")
    private List<Pagamento> pagamentos;

    // Getters e Setters
    public Integer getIdFormaPgto() {
        return Math.toIntExact(idFormaPgto);
    }

    public void setIdFormaPgto(Long idFormaPgto) {
        this.idFormaPgto = idFormaPgto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
