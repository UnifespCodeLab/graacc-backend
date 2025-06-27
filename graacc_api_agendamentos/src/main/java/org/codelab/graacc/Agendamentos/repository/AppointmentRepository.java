package org.codelab.graacc.Agendamentos.repository;

import org.codelab.graacc.Agendamentos.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findAllByIdPaciente(Long idPaciente);
}
