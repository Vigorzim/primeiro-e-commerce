package br.projeto.projeto.controller;

import br.projeto.projeto.model.Pagamento;
import br.projeto.projeto.repository.PagamentoRepository;
import br.projeto.projeto.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable Integer id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamentoAtualizado) {
        return pagamentoRepository.findById(id).map(pagamento -> {
            pagamento.setIdPedido(pagamentoAtualizado.getIdPedido());
            pagamento.setValorPago(pagamentoAtualizado.getValorPago());
            pagamento.setDataPagamento(pagamentoAtualizado.getDataPagamento());

            // Buscando a forma de pagamento correta pelo ID
            if (pagamentoAtualizado.getFormaPagamento() != null && pagamentoAtualizado.getFormaPagamento().getIdFormaPgto() != null) {
                formaPagamentoRepository.findById(pagamentoAtualizado.getFormaPagamento().getIdFormaPgto())
                        .ifPresent(pagamento::setFormaPagamento);
            }

            Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
            return ResponseEntity.ok(pagamentoSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
