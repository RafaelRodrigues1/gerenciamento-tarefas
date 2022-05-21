package br.com.perinity.test.gerenciamentotarefas.service;

import br.com.perinity.test.gerenciamentotarefas.model.Tarefa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.TarefaAdicaoConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.TarefaResponseConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.TarefaAdicaoRequest;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.TarefaResponse;
import br.com.perinity.test.gerenciamentotarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    private static final int LIMITE_BUSCA_TAREFA_SEM_PESSOA_ALOCADA = 3;

    public void adicionaTarefa(TarefaAdicaoRequest tarefaASerSalva) {
        this.tarefaRepository.adicionaTarefa(tarefaASerSalva.getTitulo(), tarefaASerSalva.getDescricao(),
                tarefaASerSalva.getPrazo(), tarefaASerSalva.getDepartamento(), tarefaASerSalva.getDuracao(),
                tarefaASerSalva.getPessoaAlocada(), tarefaASerSalva.getFinalizado());
    }

    public void finalizaTarefa(Long id) {
        this.tarefaRepository.finalizaTarefa(id);
    }

    public List<TarefaResponse> findTresTarefasSemPessoaAlocadaPrazosAntigos() {
        TarefaResponseConverter tarefaConverter = new TarefaResponseConverter();
        List<Tarefa> tresTarefasSemPessoaAlocadaPrazosAntigos = this.tarefaRepository.findTresTarefasSemPessoaAlocadaPrazosAntigos(LIMITE_BUSCA_TAREFA_SEM_PESSOA_ALOCADA);
        return tresTarefasSemPessoaAlocadaPrazosAntigos
                                            .stream()
                                            .map(tarefaConverter::convertToResponse)
                                            .collect(Collectors.toList());
    }
}
