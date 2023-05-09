package com.dev.backend.service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PessoaGerenciamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    public String solicitarCodigo(String email) {
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        pessoa.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(pessoa.getId()));
        pessoa.setDataEnvioCodigo(new Date());

        pessoaRepository.saveAndFlush(pessoa);

        emailService.enviarEmailTexto(
                pessoa.getEmail(),
                "Código de Recuperação de Senha",
                "Olá, o seu código para recuperação de senha é o seguinte: " +
                        pessoa.getCodigoRecuperacaoSenha());

        return "Código Enviado!";
    }

    public String alterarSenha(Pessoa pessoa) {
        Pessoa pessoaEntity = pessoaRepository.findByEmailAndCodigoRecuperacaoSenha(pessoa.getEmail(), pessoa.getCodigoRecuperacaoSenha());

        if (pessoaEntity != null) {
            Date diferenca = new Date(new Date().getTime() - pessoaEntity.getDataEnvioCodigo().getTime());

            if (diferenca.getTime() / 1000 < 900) {
                pessoaEntity.setSenha(pessoa.getSenha());
                pessoaEntity.setCodigoRecuperacaoSenha(null);
                pessoaRepository.saveAndFlush(pessoaEntity);

                return "Senha alterada com sucesso!";
            } else {
                return "Tempo expirado, solicite um novo código";
            }
        } else {
            return "Email ou código não encontrado!";
        }
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");

        return format.format(new Date()) + id;
    }
}
