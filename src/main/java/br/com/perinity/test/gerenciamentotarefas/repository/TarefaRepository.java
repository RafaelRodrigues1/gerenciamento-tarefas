package br.com.perinity.test.gerenciamentotarefas.repository;

import br.com.perinity.test.gerenciamentotarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "UPDATE TAREFA SET FINALIZADO = TRUE WHERE ID = :id", nativeQuery = true)
    void finalizaTarefa(Long id);

    @Query(value = "SELECT * FROM TAREFA " +
            "WHERE ID_PESSOA IS NULL " +
            "ORDER BY PRAZO " +
            "LIMIT :limiteBusca ", nativeQuery = true)
    List<Tarefa> findTresTarefasSemPessoaAlocadaPrazosAntigos(@Param("limiteBusca") int limiteBusca);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "INSERT INTO TAREFA (TITULO, DESCRICAO, PRAZO, ID_DEPARTAMENTO, DURACAO, ID_PESSOA, FINALIZADO) " +
            "VALUES (:titulo, :descricao, :prazo, :idDepartamento, :duracao, :idPessoa, :finalizado)", nativeQuery = true)
    void adicionaTarefa(@Param("titulo") String titulo, @Param("descricao") String descricao,
                        @Param("prazo") LocalDate prazo, @Param("idDepartamento") Long idDepartamento,
                        @Param("duracao") Long duracao, @Param("idPessoa") Long idPessoa,
                        @Param("finalizado") Boolean finalizado);
}
