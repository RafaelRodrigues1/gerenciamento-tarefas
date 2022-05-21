package br.com.perinity.test.gerenciamentotarefas.resource;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.PessoaRequest;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaMediaGastoHorasResponse;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaTotalGastoHorasResponse;
import br.com.perinity.test.gerenciamentotarefas.service.PessoaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Api("PessoaResource")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionaPessoa(@RequestBody PessoaRequest pessoaASerSalva) {
        return this.pessoaService.adicionaPessoa(pessoaASerSalva);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pessoa alteraPessoa(@PathVariable("id")Long id, @RequestBody PessoaRequest pessoaDadosNovos) {
        return this.pessoaService.alteraPessoa(id, pessoaDadosNovos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePessoa(@PathVariable("id")Long id) {
        this.pessoaService.removePessoa(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaTotalGastoHorasResponse> listaPessoasTotalGastoHoras() {
        return this.pessoaService.listaPessoasTotalGastoHoras();
    }

    @GetMapping("/gastos")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaMediaGastoHorasResponse> listaPessoasMediaGastoHoras(@RequestParam String nomePesquisa,
                                                                           @RequestParam LocalDate dataInicio,
                                                                           @RequestParam LocalDate dataFinal) {
        return this.pessoaService.listaPessoasMediaGastoHoras(nomePesquisa, dataInicio, dataFinal);
    }
}
