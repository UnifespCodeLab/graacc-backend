package org.codelab.graacc.Agendamentos.repository;

import org.codelab.graacc.Agendamentos.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByNome(String nome);

    PatientEntity findByIdPaciente(Long idPaciente);

    void deleteByIdPaciente(long idPaciente);
}
