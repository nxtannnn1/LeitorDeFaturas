package natan.ebr.api.controller;

import lombok.RequiredArgsConstructor;
import natan.ebr.domain.model.ContaEnergia;
import natan.ebr.domain.service.ContaEnergiaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaEnergiaController {

    private final ContaEnergiaService contaEnergiaService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaEnergia uploadConta(
            @RequestParam("arquivo") MultipartFile arquivo
    ) {

        return contaEnergiaService.processarConta(arquivo);
    }
}