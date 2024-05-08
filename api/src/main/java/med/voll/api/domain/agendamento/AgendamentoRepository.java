package med.voll.api.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    @Query("""
            select a from Agendamento a
            where a.paciente.id = :idPaciente
            and a.data = :dataConsulta
            """)
    public Agendamento verificarConsultaIdPaciente(Long idPaciente, LocalDateTime dataConsulta);

    @Query("""
            select a from Agendamento a
            where a.medico.id = :idMedico
            and a.data = :dataConsulta
            """)
    public Agendamento verificarConsultaIdMedico(Long idMedico, LocalDateTime dataConsulta);
}
