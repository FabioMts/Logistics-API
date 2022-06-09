package com.fabio.logistics.logisticsapi.services;


import com.fabio.logistics.logisticsapi.domain.Entrega;
import com.fabio.logistics.logisticsapi.domain.Ocorrencia;
import com.fabio.logistics.logisticsapi.exception.NegocioException;
import com.fabio.logistics.logisticsapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService service;


    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = service.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);

    }

}
