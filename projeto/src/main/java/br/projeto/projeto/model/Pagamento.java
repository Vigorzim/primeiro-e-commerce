package br.projeto.projeto.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")  // Definindo o nome da coluna no banco de dados
    private Long idPagamento;

    @Column(name = "id_pedido")  // Definindo o nome da coluna no banco de dados
    private Long idPedido;

    @Column(name = "valor_pago")  // Definindo o nome da coluna no banco de dados
    private BigDecimal valorPago;

    @Column(name = "data_pagamento")  // Definindo o nome da coluna no banco de dados
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)  // Definindo o nome da coluna da chave estrangeira
    private FormaPagamento formaPagamento;

    // Getters e Setters
    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
