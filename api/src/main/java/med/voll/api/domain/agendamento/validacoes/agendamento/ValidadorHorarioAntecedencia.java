package med.voll.api.domain.agendamento.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime dataAgora = LocalDateTime.now();
        long diferencaMinutos = Duration.between(dataAgora,dataConsulta).toMinutes();

        if(diferencaMinutos<30){
            throw new ValidacaoException(("Consulta deve ser agendada com antecedência mínima de 30 minutos."));
        }
    }
}
