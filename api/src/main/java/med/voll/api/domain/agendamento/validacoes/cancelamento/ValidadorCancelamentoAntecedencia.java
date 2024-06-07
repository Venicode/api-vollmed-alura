package med.voll.api.domain.agendamento.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosCancelamentoAgendamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoAntecedencia implements ValidadorCancelamentoDeConsulta {
    public void validar(DadosCancelamentoAgendamento dados){
        LocalDateTime dataConsulta = dados.dataCancelamento();
        LocalDateTime dataAgora = LocalDateTime.now();
        long diferencaMinutos = Duration.between(dataAgora,dataConsulta).toMinutes();

        if(diferencaMinutos<30){
            throw new ValidacaoException(("Consulta deve ser cancelada com antecedência mínima de 30 minutos."));
        }
    }
}
