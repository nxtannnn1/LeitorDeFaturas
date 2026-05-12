package natan.ebr.infrastructure.parser;

import natan.ebr.domain.model.ContaEnergia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ContaEnergiaParser {

    public ContaEnergia parse(String texto) {

        texto = normalizarTexto(texto);

        System.out.println(texto);

        String cliente = extrair(
                texto,
                "DADOS DO CLIENTE\\s*(.*?)\\s*CPF:"
        );

        String referencia = formatarMesReferencia(
                extrair(
                        texto,
                        "MÊS/ANO.*?(\\d{2}/\\d{4})"
                ));

        Double consumoKwh = parseNumero(
                extrair(
                        texto,
                        "Consumo Ativo\\(kWh\\)-TUSD\\s*(\\d+,\\d{2})"
                )
        );

        BigDecimal valorTotal = BigDecimal.valueOf(
                parseNumero(
                        extrair(
                                texto,
                                "TOTAL DA FATURA\\s*(\\d+,\\d{2})"
                        )
                )
        );

        return new ContaEnergia(
                cliente,
                referencia,
                consumoKwh,
                valorTotal,
                texto
        );
    }

    private String extrair(String texto, String regex) {

        Pattern pattern = Pattern.compile(
                regex,
                Pattern.CASE_INSENSITIVE | Pattern.DOTALL
        );

        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        throw new IllegalArgumentException(
                "Não foi possível extrair informação usando regex: "
                        + regex
        );
    }

    private Double parseNumero(String valor) {

        return Double.parseDouble(
                valor.replace(".", "")
                        .replace(",", ".")
        );
    }

    private String normalizarTexto(String texto) {

        return texto.replaceAll("\\s+", " ")
                .trim();
    }

    private String formatarMesReferencia(String referencia) {

        String[] partes = referencia.split("/");

        return partes[1] + "-" + partes[0];
    }
}