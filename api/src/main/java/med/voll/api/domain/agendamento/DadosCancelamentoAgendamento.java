package med.voll.api.domain.agendamento;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.ValidacaoException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long idConsulta,
        LocalDateTime dataCancelamento,
        @NotNull
        MotivosCancelamento motivoCancelamento
        ) {}
