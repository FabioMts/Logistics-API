package com.fabio.logistics.logisticsapi.resources;


import com.fabio.logistics.logisticsapi.model.Entrega;
import com.fabio.logistics.logisticsapi.repository.EntregaRepository;
import com.fabio.logistics.logisticsapi.services.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService service;
    private EntregaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return repository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId) {
            return repository.findById(entregaId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }


}
