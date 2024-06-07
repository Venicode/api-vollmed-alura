package med.voll.api.domain.agendamento.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.AgendamentoRepository;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoMaisDeUmaConsultaMesmoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    AgendamentoRepository agendamentoRepository;
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(18);
        boolean pacientePossuiOutraConsultaNoDia = agendamentoRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
