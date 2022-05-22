package br.com.perinity.test.gerenciamentotarefas.repository;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContainsIgnoreCase(String nome);

    @Query(value = "SELECT P.* " +
            "FROM PESSOA P " +
            "LEFT JOIN TAREFA TAR " +
            "ON TAR.ID_PESSOA = P.ID " +
            "WHERE P.ID_DEPARTAMENTO = :idDepartamento " +
            "GROUP BY P.ID " +
            "ORDER BY COUNT(" +
            "(SELECT TAR.ID FROM TAREFA T " +
            "WHERE T.FINALIZADO = FALSE " +
            "AND P.ID = T.ID_PESSOA)) " +
            "LIMIT 1", nativeQuery = true)
    Long getPessoaComMenosTarefasAlocadasByDepartamento(@Param("idDepartamento") Long idDepartamento);
}
