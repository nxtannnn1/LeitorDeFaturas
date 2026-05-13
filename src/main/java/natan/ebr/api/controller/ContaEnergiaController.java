package natan.ebr.api.controller;

import lombok.RequiredArgsConstructor;
import natan.ebr.api.dto.response.ContaEnergiaResponse;
import natan.ebr.api.mapper.ContaEnergiaMapper;
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
    private final ContaEnergiaMapper contaEnergiaMapper;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaEnergiaResponse uploadConta(
            @RequestParam("arquivo") MultipartFile arquivo
    ) {

        ContaEnergia contaEnergia = contaEnergiaService.processarConta(arquivo);

        return contaEnergiaMapper.toResponse(contaEnergia);
    }
}