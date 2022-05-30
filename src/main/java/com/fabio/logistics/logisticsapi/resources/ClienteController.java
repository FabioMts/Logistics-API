package com.fabio.logistics.logisticsapi.resources;

import com.fabio.logistics.logisticsapi.model.Cliente;
import com.fabio.logistics.logisticsapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = repository.findById(clienteId);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }


}
