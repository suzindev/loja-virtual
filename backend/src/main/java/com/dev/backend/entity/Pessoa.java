package com.dev.backend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private String endereco;

    private String cep;

    private String codigoRecuperacaoSenha;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvioCodigo;

    @ManyToOne
    @JoinColumn(name = "idCidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Setter(value = AccessLevel.NONE)
    private List<PermissaoPessoa> permissaoPessoas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public void setPermissaoPessoas(List<PermissaoPessoa> lista) {
        for (PermissaoPessoa pp : lista) {
            pp.setPessoa(this);
        }
        this.permissaoPessoas = lista;
    }
}
