package med.voll.api.domain.agendamento;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long idConsulta,
        LocalDateTime dataCancelamento,
        @NotNull
        MotivosCancelamento motivoCancelamento
        ) {}
