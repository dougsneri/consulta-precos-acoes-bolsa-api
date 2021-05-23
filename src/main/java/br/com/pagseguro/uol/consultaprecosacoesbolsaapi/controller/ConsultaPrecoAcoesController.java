package br.com.pagseguro.uol.consultaprecosacoesbolsaapi.controller;

import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.dto.AcaoDto;
import br.com.pagseguro.uol.consultaprecosacoesbolsaapi.entity.Acao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1")
public interface ConsultaPrecoAcoesController {

    @GetMapping("listar/acoes")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    List<Acao> listarTodasAcoes();

    @GetMapping("listar/acoes/precomedio/dezmaiores")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    List<AcaoDto> listarPrecoMedioDezMaioresAcoes();

    @PostMapping("adicionar/acao")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    void adicionarAcao(@RequestBody Acao acao);

    @GetMapping("buscar/acao/codigo/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Acao buscarAcaoPorCodigoAcao(@PathVariable("codigo") String codigo);

    @GetMapping("buscar/acao/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    Acao buscarAcaoPorNomeAcao(@PathVariable("nome") String nome);

    @DeleteMapping("deletar/acao/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    void deletarAcaoPorCodigoAcao(@PathVariable("codigo") String codigo);

}
