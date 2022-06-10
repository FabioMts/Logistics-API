package com.fabio.logistics.logisticsapi.resources;


import com.fabio.logistics.logisticsapi.assembler.EntregaAssembler;
import com.fabio.logistics.logisticsapi.domain.Entrega;
import com.fabio.logistics.logisticsapi.model.DestinatarioModel;
import com.fabio.logistics.logisticsapi.model.EntregaModel;
import com.fabio.logistics.logisticsapi.model.input.EntregaInput;
import com.fabio.logistics.logisticsapi.repository.EntregaRepository;
import com.fabio.logistics.logisticsapi.services.FinalizacaoEntregaService;
import com.fabio.logistics.logisticsapi.services.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private EntregaAssembler entregaAssembler;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregainput) {

        Entrega novaEntrega = entregaAssembler.toEntity(entregainput);
        Entrega entregaSolicitada = service.solicitar(novaEntrega);

        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
            return repository.findById(entregaId)
                    .map(entrega ->  ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                    .orElse(ResponseEntity.notFound().build());
    }


}
