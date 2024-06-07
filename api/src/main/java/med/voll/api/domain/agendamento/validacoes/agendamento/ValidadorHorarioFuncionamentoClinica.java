package med.voll.api.domain.agendamento.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        boolean isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaClinica = dataConsulta.getHour()<7;
        boolean depoisDoFechamentoClinica = dataConsulta.getHour()>18;
        if(isDomingo || antesDaAberturaClinica || depoisDoFechamentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}