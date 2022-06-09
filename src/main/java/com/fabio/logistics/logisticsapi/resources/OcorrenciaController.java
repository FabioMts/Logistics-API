package com.fabio.logistics.logisticsapi.resources;


import com.fabio.logistics.logisticsapi.assembler.OcorrenciaAssembler;
import com.fabio.logistics.logisticsapi.domain.Entrega;
import com.fabio.logistics.logisticsapi.domain.Ocorrencia;
import com.fabio.logistics.logisticsapi.model.OcorrenciaModel;
import com.fabio.logistics.logisticsapi.model.input.OcorrenciaInput;
import com.fabio.logistics.logisticsapi.services.BuscaEntregaService;
import com.fabio.logistics.logisticsapi.services.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {


    private RegistroOcorrenciaService service;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscaEntregaService buscaEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId
            , @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
       Ocorrencia ocorrenciaRegistrada =  service.registrar(entregaId, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }


}
