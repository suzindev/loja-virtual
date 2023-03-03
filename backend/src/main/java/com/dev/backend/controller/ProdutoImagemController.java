package com.dev.backend.controller;

import com.dev.backend.entity.ProdutoImagem;
import com.dev.backend.service.ProdutoImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/produtoImagem")
public class ProdutoImagemController {

    @Autowired
    private ProdutoImagemService produtoImagemService;

    @GetMapping("/")
    public List<ProdutoImagem> buscarTodos() {
        return produtoImagemService.buscarTodos();
    }

    @PostMapping("/")
    public ProdutoImagem inserir(@RequestParam("idProduto") Long idProduto, @RequestParam("file") MultipartFile file) {
        return produtoImagemService.inserir(idProduto, file);
    }

    @PutMapping("/")
    public ProdutoImagem alterar(@RequestBody ProdutoImagem produtoImagem) {
        return produtoImagemService.alterar(produtoImagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        produtoImagemService.excluir(id);

        return ResponseEntity.ok().build();
    }
}
