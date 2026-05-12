package natan.ebr.domain.service;

import lombok.RequiredArgsConstructor;
import natan.ebr.domain.model.ContaEnergia;
import natan.ebr.domain.repository.ContaEnergiaRepository;
import natan.ebr.infrastructure.parser.ContaEnergiaParser;
import natan.ebr.infrastructure.pdf.PdfExtractorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ContaEnergiaService {

    private final PdfExtractorService pdfExtractorService;
    private final ContaEnergiaParser contaEnergiaParser;
    private final ContaEnergiaRepository contaEnergiaRepository;

    public ContaEnergia processarConta(MultipartFile arquivoPdf) {

        String textoExtraido = pdfExtractorService.extrairTexto(arquivoPdf);

        ContaEnergia contaEnergia =
                contaEnergiaParser.parse(textoExtraido);

        return contaEnergiaRepository.save(contaEnergia);
    }
}