package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.controller.impl;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.controller.ConsultaPrecoAcoesController;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.dto.AcaoDto;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.service.AcoesService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultaPrecoAcoesControllerImpl implements ConsultaPrecoAcoesController {

    private final AcoesService acoesService;

    public ConsultaPrecoAcoesControllerImpl(final AcoesService acoesService) {
        this.acoesService = acoesService;
    }

    @Override
    public List<Acao> listarTodasAcoes() {
        return acoesService.listarTodasAcoes();
    }

    @Override
    public List<AcaoDto> listarPrecoMedioDezMaioresAcoes() {
        return acoesService.listarPrecoMedioDezMaioresAcoes();
    }

    @Override
    public void adicionarAcao(Acao acao) {
        acoesService.adicionarAcao(acao);
    }

    @Override
    public Acao buscarAcaoPorCodigoAcao(String codigo) {
        return acoesService.buscarAcaoPorCodigoAcao(codigo);
    }

    @Override
    public Acao buscarAcaoPorNomeAcao(String nome) {
        return acoesService.buscarAcaoPorNomeAcao(nome);
    }

    @Override
    public void deletarAcaoPorCodigoAcao(String codigo) {
        acoesService.deletarAcaoPorCodigoAcao(codigo);
    }

}
