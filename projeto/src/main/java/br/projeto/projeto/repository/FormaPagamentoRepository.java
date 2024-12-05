package br.projeto.projeto.repository;

import br.projeto.projeto.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {
}
