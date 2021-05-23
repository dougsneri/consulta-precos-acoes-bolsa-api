package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcaoDto {

    @JsonProperty("codigo_acao")
    private String codigo;

    @JsonProperty("nome_acao")
    private String nomeAcao;

    @JsonProperty("quantidade_teorica")
    private BigInteger quantidadeTeorica;

    private BigDecimal participacao;

    @JsonProperty("preco_medio")
    private BigDecimal precoMedio;

}
