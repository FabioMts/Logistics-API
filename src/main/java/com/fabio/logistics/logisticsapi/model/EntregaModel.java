package com.fabio.logistics.logisticsapi.model;


import com.fabio.logistics.logisticsapi.domain.Destinatario;
import com.fabio.logistics.logisticsapi.domain.enums.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {

    private Long id;
    private ClienteResumoModel cliente;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
