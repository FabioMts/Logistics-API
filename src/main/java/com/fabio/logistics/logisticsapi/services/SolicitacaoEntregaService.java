package com.fabio.logistics.logisticsapi.services;

import com.fabio.logistics.logisticsapi.exception.NegocioException;
import com.fabio.logistics.logisticsapi.model.Cliente;
import com.fabio.logistics.logisticsapi.model.Entrega;
import com.fabio.logistics.logisticsapi.model.enums.StatusEntrega;
import com.fabio.logistics.logisticsapi.repository.ClienteRepository;
import com.fabio.logistics.logisticsapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private EntregaRepository Entregarepository;
    private CatalogoClienteService Clienteservice;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        Cliente cliente = Clienteservice.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return Entregarepository.save(entrega);
    }




}
