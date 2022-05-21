package br.com.perinity.test.gerenciamentotarefas.repository;

import br.com.perinity.test.gerenciamentotarefas.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query(value = "SELECT D.ID id, D.TITULO titulo, " +
            "(SELECT COUNT(P.id) FROM PESSOA P WHERE P.ID_DEPARTAMENTO = D.ID) quantidadePessoas, " +
            "(SELECT COUNT(T.id) FROM TAREFA T WHERE T.ID_DEPARTAMENTO = D.ID) quantidadeTarefas " +
            "FROM DEPARTAMENTO D", nativeQuery = true)
    List<Departamento.DepartamentoQtdPessoaTarefaResponse> findDepartamentosComQuantidadePessoasTarefas();
}
