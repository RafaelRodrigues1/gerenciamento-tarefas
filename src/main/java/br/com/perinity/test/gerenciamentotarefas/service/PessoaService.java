package br.com.perinity.test.gerenciamentotarefas.service;

import br.com.perinity.test.gerenciamentotarefas.exception.DepartamentoNotFoundException;
import br.com.perinity.test.gerenciamentotarefas.exception.PessoaNotFoundException;
import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.PessoaRequestConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.PessoaMediaGastoHorasConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.converter.PessoaTotalGastoHorasConverter;
import br.com.perinity.test.gerenciamentotarefas.model.dto.request.PessoaRequest;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaMediaGastoHorasResponse;
import br.com.perinity.test.gerenciamentotarefas.model.dto.response.PessoaTotalGastoHorasResponse;
import br.com.perinity.test.gerenciamentotarefas.repository.DepartamentoRepository;
import br.com.perinity.test.gerenciamentotarefas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    private PessoaRequestConverter pessoaConverter = new PessoaRequestConverter();;

    public Pessoa adicionaPessoa(PessoaRequest pessoaASerSalva) {
        Pessoa pessoa = this.pessoaConverter.convertToModel(pessoaASerSalva);
        pessoa = this.pessoaRepository.save(pessoa);
        return this.findById(pessoa.getId());
    }

    public Pessoa alteraPessoa(Long id, PessoaRequest pessoaDadosNovos) {
        this.verificaSeExiste(id);
        Pessoa pessoa = this.pessoaConverter.convertToModel(pessoaDadosNovos);
        pessoa.setId(id);
        this.pessoaRepository.save(pessoa);
        return this.findById(pessoa.getId());
    }

    public void removePessoa(Long id) {
        this.verificaSeExiste(id);
        this.pessoaRepository.deleteById(id);
    }

    private Pessoa findById(Long id) {
        Pessoa pessoa = this.verificaSeExiste(id);
        pessoa.setDepartamento(this.findDepartamentoById(pessoa.getDepartamento().getId()));
        return pessoa;
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<PessoaTotalGastoHorasResponse> listaPessoasTotalGastoHoras() {
        PessoaTotalGastoHorasConverter pessoaConverter = new PessoaTotalGastoHorasConverter();
        List<Pessoa> pessoas = this.pessoaRepository.findAll();
        return pessoas
                    .stream()
                    .map(pessoaConverter::convertToResponse).
                    collect(Collectors.toList());
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<PessoaMediaGastoHorasResponse> listaPessoasMediaGastoHoras(String nomePesquisa, LocalDate dataInicio, LocalDate dataFinal) {
        PessoaMediaGastoHorasConverter pessoaConverter = new PessoaMediaGastoHorasConverter();
        List<Pessoa> pessoas = this.pessoaRepository.findByNomeContainsIgnoreCase(nomePesquisa);
        pessoas = pessoas.stream().map(pessoa -> {
            pessoa.setTarefas(pessoa.getTarefas()
                    .stream()
                    .filter(tarefa -> tarefa.getPrazo().compareTo(dataInicio) > 0 && tarefa.getPrazo().compareTo(dataFinal) < 0)
                    .collect(Collectors.toList()));
            return pessoa;
            }).collect(Collectors.toList());
        return pessoas.stream().map(pessoaConverter::convertToResponse).collect(Collectors.toList());
    }

    private Pessoa verificaSeExiste(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada, id = " + id));
    }

    private Departamento findDepartamentoById(Long id) {
        return this.departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoNotFoundException("Departamento não encontrado, id = " + id));
    }
}
