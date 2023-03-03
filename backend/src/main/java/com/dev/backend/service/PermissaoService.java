package com.dev.backend.service;

import com.dev.backend.entity.Permissao;
import com.dev.backend.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodos() {
        return permissaoRepository.findAll();
    }

    public Permissao inserir(Permissao permissao) {
        permissao.setDataCriacao(new Date());

        Permissao permissaoNova = permissaoRepository.saveAndFlush(permissao);
        return permissaoNova;
    }

    public Permissao alterar(Permissao permissao) {
        permissao.setDataAtualizacao(new Date());

        return permissaoRepository.saveAndFlush(permissao);
    }

    public void excluir(Long id) {
        Permissao permissao = permissaoRepository.findById(id).get();
        permissaoRepository.delete(permissao);
    }
}
