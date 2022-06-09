package com.fabio.logistics.logisticsapi.services;


import com.fabio.logistics.logisticsapi.domain.Entrega;
import com.fabio.logistics.logisticsapi.exception.EntidadeNaoEncontradaException;
import com.fabio.logistics.logisticsapi.exception.NegocioException;
import com.fabio.logistics.logisticsapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
    private EntregaRepository repository;

    public Entrega buscar(Long entregaId) {
        return repository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
