package natan.ebr.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContaEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String mesReferencia;

    @Column(nullable = false)
    private Double consumoKwh;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String textoExtraido;

    protected ContaEnergia() {}

    public ContaEnergia(
            String nomeCliente,
            String mesReferencia,
            Double consumoKwh,
            BigDecimal valorTotal,
            String textoExtraido
    ) {
        this.nomeCliente = validarTexto(
                nomeCliente,
                "Nome do cliente inválido"
        );

        this.mesReferencia = validarMesReferencia(
                mesReferencia
        );

        this.consumoKwh = validarConsumo(
                consumoKwh
        );

        this.valorTotal = validarValor(
                valorTotal
        );

        this.textoExtraido = validarTexto(
                textoExtraido,
                "Texto extraído inválido"
        );
    }

    private String validarTexto(
            String valor,
            String mensagem
    ) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }

        return valor.trim();
    }

    private String validarMesReferencia(
            String mesReferencia
    ) {

        if (mesReferencia == null ||
                !mesReferencia.matches("\\d{4}-\\d{2}")) {

            throw new IllegalArgumentException(
                    "Mês de referência inválido"
            );
        }

        return mesReferencia;
    }

    private Double validarConsumo(
            Double consumo
    ) {

        if (consumo == null || consumo < 0) {

            throw new IllegalArgumentException(
                    "Consumo inválido"
            );
        }

        return consumo;
    }

    private BigDecimal validarValor(
            BigDecimal valorTotal
    ) {

        if (valorTotal == null ||
                valorTotal.signum() < 0) {

            throw new IllegalArgumentException(
                    "Valor total inválido"
            );
        }

        return valorTotal;
    }
}