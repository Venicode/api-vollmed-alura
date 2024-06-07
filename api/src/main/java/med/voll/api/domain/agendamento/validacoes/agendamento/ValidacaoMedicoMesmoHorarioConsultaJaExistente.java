package med.voll.api.domain.agendamento.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.AgendamentoRepository;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoMesmoHorarioConsultaJaExistente implements ValidadorAgendamentoDeConsulta{
    @Autowired
    AgendamentoRepository agendamentoRepository;
    public void validar(DadosAgendamentoConsulta dados) {
        boolean medicoPossuiOutraConsultaNoMesmoHorario = agendamentoRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
