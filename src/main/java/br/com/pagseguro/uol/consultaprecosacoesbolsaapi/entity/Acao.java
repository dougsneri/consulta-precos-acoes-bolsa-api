package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Acao implements Serializable {

    private static final long serialVersionUID = -1165309119219446289L;

    @Id
    @JsonProperty("codigo_acao")
    private String codigo;

    @JsonProperty("nome_acao")
    @Column(unique=true)
    private String nomeAcao;

    @JsonProperty("quantidade_teorica")
    private BigInteger quantidadeTeorica;

    @JsonProperty("participacao")
    private BigDecimal participacao;

}