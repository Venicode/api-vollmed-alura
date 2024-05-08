package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidadorHorarioFuncionamentoClinica {
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