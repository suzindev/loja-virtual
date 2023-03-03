package com.dev.backend.service;

import com.dev.backend.entity.Produto;
import com.dev.backend.entity.ProdutoImagem;
import com.dev.backend.repository.ProdutoImagemRepository;
import com.dev.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoImagemService {

    @Autowired
    private ProdutoImagemRepository produtoImagemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoImagem> buscarTodos() {
        return produtoImagemRepository.findAll();
    }

    public ProdutoImagem inserir(Long idProduto, MultipartFile file) {
        Produto produto = produtoRepository.findById(idProduto).get();

        ProdutoImagem imagem = new ProdutoImagem();
        imagem.setProduto(produto);
        imagem.setDataCriacao(new Date());

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String nomeImagem = produto.getId() + file.getOriginalFilename();
                Path caminho = Paths.get("C:/Desenvolvimento/Imagens/" + nomeImagem);
                Files.write(caminho, bytes);

                imagem.setNome(nomeImagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagem = produtoImagemRepository.saveAndFlush(imagem);

        return imagem;
    }

    public ProdutoImagem alterar(ProdutoImagem imagem) {
        imagem.setDataAtualizacao(new Date());

        return produtoImagemRepository.saveAndFlush(imagem);
    }

    public void excluir(Long id) {
        ProdutoImagem imagem = produtoImagemRepository.findById(id).get();
        produtoImagemRepository.delete(imagem);
    }
}
