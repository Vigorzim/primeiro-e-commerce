package br.projeto.projeto.controller;

import br.projeto.projeto.model.FormaPagamento;
import br.projeto.projeto.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @PostMapping
    public ResponseEntity<FormaPagamento> criarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento novaFormaPagamento = formaPagamentoRepository.save(formaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarFormasPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarFormaPagamentoPorId(@PathVariable Integer id) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
        return formaPagamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizarFormaPagamento(@PathVariable Integer id, @RequestBody FormaPagamento formaPagamentoAtualizada) {
        return formaPagamentoRepository.findById(id).map(formaPagamento -> {
            formaPagamento.setDescricao(formaPagamentoAtualizada.getDescricao());
            FormaPagamento formaPagamentoSalva = formaPagamentoRepository.save(formaPagamento);
            return ResponseEntity.ok(formaPagamentoSalva);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormaPagamento(@PathVariable Integer id) {
        if (formaPagamentoRepository.existsById(id)) {
            formaPagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
