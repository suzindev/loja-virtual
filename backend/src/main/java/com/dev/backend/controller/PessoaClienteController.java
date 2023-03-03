package com.dev.backend.controller;

import com.dev.backend.dto.PessoaClienteRequest;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class PessoaClienteController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/")
    public Pessoa inserir(@RequestBody PessoaClienteRequest request) {
        Pessoa pessoa = new PessoaClienteRequest().converter(request);

        return pessoaService.inserir(pessoa);
    }
}
