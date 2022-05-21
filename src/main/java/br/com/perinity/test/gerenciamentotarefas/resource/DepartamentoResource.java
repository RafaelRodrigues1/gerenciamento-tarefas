package br.com.perinity.test.gerenciamentotarefas.resource;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import br.com.perinity.test.gerenciamentotarefas.service.DepartamentoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@Api("DepartamentoResource")
public class DepartamentoResource {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Departamento.DepartamentoQtdPessoaTarefaResponse> findDepartamentosComQuantidadePessoasTarefas() {
        return this.departamentoService.findDepartamentosComQuantidadePessoasTarefas();
    }
}
