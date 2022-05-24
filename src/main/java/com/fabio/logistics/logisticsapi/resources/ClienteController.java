package com.fabio.logistics.logisticsapi.resources;

import com.fabio.logistics.logisticsapi.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente = new Cliente();
        cliente.setId(1L);
        cliente.setName("Fabio");
        cliente.setTelefone("19 9999 9999");
        cliente.setEmail("fabio.couter");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setName("Maria");
        cliente2.setTelefone("19 8888 8888");
        cliente2.setEmail("maria.couter");

        return Arrays.asList(cliente, cliente2);
    }

}
