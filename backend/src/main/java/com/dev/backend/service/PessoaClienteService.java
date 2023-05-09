package com.dev.backend.service;

import com.dev.backend.dto.PessoaClienteRequest;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaClienteService {

    @Autowired
    private PessoaClienteRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa registrar(PessoaClienteRequest pessoaClienteRequest) {
        Pessoa pessoa = new PessoaClienteRequest().converter(pessoaClienteRequest);
        pessoa.setDataCriacao(new Date());

        Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(pessoaNova);

        Map<String, Object> map = new HashMap<>();
        map.put("nome", pessoaNova.getNome());
        map.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!!");

        emailService.enviarEmailTemplate(
                pessoaNova.getEmail(),
                "Cadastro na Loja SuzinTech",
                map);

//        emailService.enviarEmailTexto(
//                pessoaNova.getEmail(),
//                "Cadastro na Loja SuzinTech",
//                "O registro na loja foi realizado com sucesso. " +
//                        "Em breve você receberá a senha de acesso por e-mail!!!");

        return pessoaNova;
    }
}
