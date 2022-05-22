package br.com.perinity.test.gerenciamentotarefas.resource;

import br.com.perinity.test.gerenciamentotarefas.model.dto.request.TarefaAdicaoRequest;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.TarefaResponse;
import br.com.perinity.test.gerenciamentotarefas.service.TarefaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Api("TarefaResource")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void adicionaTarefa(@RequestBody TarefaAdicaoRequest tarefaASerSalva) {
        this.tarefaService.adicionaTarefa(tarefaASerSalva);
    }

    @PutMapping("/finalizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void finalizaTarefa(@PathVariable("id") Long id) {
        this.tarefaService.finalizaTarefa(id);
    }

    @GetMapping("/pendentes")
    @ResponseStatus(HttpStatus.OK)
    public List<TarefaResponse> findTresTarefasSemPessoaAlocadaPrazosAntigos() {
        return this.tarefaService.findTresTarefasSemPessoaAlocadaPrazosAntigos();
    }

    @PutMapping("/alocar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TarefaResponse alocaPessoaTarefaMesmoDepartamento(@PathVariable("id") Long id) {
        return this.tarefaService.alocaPessoaTarefaMesmoDepartamento(id);
    }
}
