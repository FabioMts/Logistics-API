package com.fabio.logistics.logisticsapi.resources;


import com.fabio.logistics.logisticsapi.model.Entrega;
import com.fabio.logistics.logisticsapi.services.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }


}
