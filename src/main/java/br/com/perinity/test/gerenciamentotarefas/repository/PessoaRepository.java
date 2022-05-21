package br.com.perinity.test.gerenciamentotarefas.repository;

import br.com.perinity.test.gerenciamentotarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContainsIgnoreCase(String nome);
}
