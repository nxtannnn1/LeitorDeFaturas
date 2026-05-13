package natan.ebr.api.dto.response;

import java.math.BigDecimal;

public record ContaEnergiaResponse(String nomeCliente, String mesReferencia, Double consumoKwh, BigDecimal valorTotal) {
}
