package natan.ebr.api.mapper;

import natan.ebr.api.dto.response.ContaEnergiaResponse;
import natan.ebr.domain.model.ContaEnergia;
import org.springframework.stereotype.Component;

@Component
public class ContaEnergiaMapper {

    public ContaEnergiaResponse toResponse(ContaEnergia contaEnergia) {

        return new ContaEnergiaResponse(
                contaEnergia.getNomeCliente(),
                contaEnergia.getMesReferencia(),
                contaEnergia.getConsumoKwh(),
                contaEnergia.getValorTotal()
        );
    }
}
