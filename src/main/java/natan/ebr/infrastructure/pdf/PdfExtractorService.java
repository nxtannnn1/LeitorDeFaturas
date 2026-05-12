package natan.ebr.infrastructure.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfExtractorService {

    public String extrairTexto(MultipartFile arquivoPdf) {

        try (PDDocument document =
                     Loader.loadPDF(arquivoPdf.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao extrair texto do PDF", e);
        }
    }
}