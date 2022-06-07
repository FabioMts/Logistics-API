package com.fabio.logistics.logisticsapi.resources;

import com.fabio.logistics.logisticsapi.domain.Cliente;
import com.fabio.logistics.logisticsapi.repository.ClienteRepository;
import com.fabio.logistics.logisticsapi.services.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/clientes")
@AllArgsConstructor
@RestController
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    private CatalogoClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
     return repository.findById(clienteId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
        if(!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);

        cliente = service.salvar(cliente);

        return ResponseEntity.ok(cliente);

    }
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {

        if(!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

}
