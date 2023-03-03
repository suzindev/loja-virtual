package com.dev.backend.dto;

import com.dev.backend.entity.Cidade;
import com.dev.backend.entity.Pessoa;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PessoaClienteRequest {

    private String nome;

    private String cpf;

    private String email;

    private String endereco;

    private String cep;

    private Cidade cidade;

    public Pessoa converter(PessoaClienteRequest dto) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);

        return pessoa;
    }
}
