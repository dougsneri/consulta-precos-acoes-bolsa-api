package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.repository;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcoesRepository extends JpaRepository<Acao, String> {
    Acao findByNomeAcao(String nomeAcao);
}
