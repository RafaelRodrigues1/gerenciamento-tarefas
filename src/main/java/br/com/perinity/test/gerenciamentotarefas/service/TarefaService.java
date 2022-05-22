package br.com.perinity.test.gerenciamentotarefas.service;

import br.com.perinity.test.gerenciamentotarefas.exception.TarefaAddException;
import br.com.perinity.test.gerenciamentotarefas.exception.TarefaNotFoundException;
import br.com.perinity.test.gerenciamentotarefas.model.Tarefa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.TarefaResponseConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.TarefaAdicaoRequest;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.TarefaResponse;
import br.com.perinity.test.gerenciamentotarefas.repository.PessoaRepository;
import br.com.perinity.test.gerenciamentotarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    private TarefaResponseConverter tarefaConverter = new TarefaResponseConverter();

    private static final int LIMITE_BUSCA_TAREFA_SEM_PESSOA_ALOCADA = 3;

    public void adicionaTarefa(TarefaAdicaoRequest tarefaASerSalva) {
        try {
            this.tarefaRepository.adicionaTarefa(tarefaASerSalva.getTitulo(), tarefaASerSalva.getDescricao(),
                    LocalDate.parse(tarefaASerSalva.getPrazo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    tarefaASerSalva.getDepartamento(), tarefaASerSalva.getDuracao(),
                    tarefaASerSalva.getPessoaAlocada(), tarefaASerSalva.getFinalizado());
        } catch(DateTimeParseException ex) {
            throw new TarefaAddException("Erro ao adicionar Tarefa, formato de data inválido. Formato aceito: DD/MM/YYYY");
        } catch (Exception ex) {
            throw new TarefaAddException("Erro ao adicionar Tarefa: " + ex.getMessage());
        }

    }

    public void finalizaTarefa(Long id) {
        this.tarefaRepository.finalizaTarefa(id);
    }

    public List<TarefaResponse> findTresTarefasSemPessoaAlocadaPrazosAntigos() {
        List<Tarefa> tarefasSemPessoaAlocadaPrazosAntigos = this.tarefaRepository.findTarefasSemPessoaAlocadaPrazosAntigos(LIMITE_BUSCA_TAREFA_SEM_PESSOA_ALOCADA);
        return tarefasSemPessoaAlocadaPrazosAntigos
                                            .stream()
                                            .map(this.tarefaConverter::convertToResponse)
                                            .collect(Collectors.toList());
    }

    public TarefaResponse alocaPessoaTarefaMesmoDepartamento(Long id) {
        Tarefa tarefa = this.verificaSeExiste(id);
        Long idPEssoa = this.pessoaRepository.getPessoaComMenosTarefasAlocadasByDepartamento(tarefa.getDepartamento().getId());
        this.tarefaRepository.alocaPessoaTarefaMesmoDepartamento(idPEssoa, tarefa.getId());
        return this.tarefaConverter.convertToResponse(this.verificaSeExiste(tarefa.getId()));
    }

    private Tarefa verificaSeExiste(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new TarefaNotFoundException("Tarefa não encontrada, id = " + id));
    }
}
