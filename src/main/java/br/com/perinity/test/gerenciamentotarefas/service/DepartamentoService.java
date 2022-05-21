package br.com.perinity.test.gerenciamentotarefas.service;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import br.com.perinity.test.gerenciamentotarefas.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento.DepartamentoQtdPessoaTarefaResponse> findDepartamentosComQuantidadePessoasTarefas() {
        return this.departamentoRepository.findDepartamentosComQuantidadePessoasTarefas();
    }
}
