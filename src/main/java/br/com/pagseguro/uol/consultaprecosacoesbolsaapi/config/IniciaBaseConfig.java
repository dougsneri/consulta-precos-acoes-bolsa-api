package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.config;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.repository.AcoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class IniciaBaseConfig {

    private final AcoesRepository acoesRepository;

    @Autowired
    public IniciaBaseConfig(final AcoesRepository acoesRepository) {
        this.acoesRepository = acoesRepository;
    }

    @Bean
    public void iniciaBD() {
        List<Acao> acoes = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("./para_test_Backend_Java_Kotlin.txt"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] acao = str.split(";");
                acoes.add(acaoTextAsObject(acao));
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

        acoes.forEach(acoesRepository::save);
    }

    private Acao acaoTextAsObject(String[] acao) {
        return new Acao(
                acao[0],
                acao[1],
                new BigInteger(acao[2]),
                new BigDecimal(acao[3])
        );
    }
}
