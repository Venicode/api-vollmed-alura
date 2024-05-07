package med.voll.api.domain.agendamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        int cancelamento,
        MotivosCancelamento motivoCancelamento) {

}
