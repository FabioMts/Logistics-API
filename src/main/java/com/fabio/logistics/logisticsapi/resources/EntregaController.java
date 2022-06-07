package com.fabio.logistics.logisticsapi.resources;


import com.fabio.logistics.logisticsapi.domain.Entrega;
import com.fabio.logistics.logisticsapi.model.DestinatarioModel;
import com.fabio.logistics.logisticsapi.model.EntregaModel;
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
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
            return repository.findById(entregaId)
                    .map(entrega -> {
                        EntregaModel entregaModel = new EntregaModel();
                        entregaModel.setId(entrega.getId());
                        entregaModel.setNomeCliente(entrega.getCliente().getNome());
                        entregaModel.setDestinatario(new DestinatarioModel());
                        entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
                        entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                        entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                        entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                        entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                        entregaModel.setTaxa(entrega.getTaxa());
                        entregaModel.setStatus(entrega.getStatus());
                        entregaModel.setDataPedido(entrega.getDataPedido());
                        entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());

                        return ResponseEntity.ok(entregaModel);
                    })
                    .orElse(ResponseEntity.notFound().build());
    }


}
