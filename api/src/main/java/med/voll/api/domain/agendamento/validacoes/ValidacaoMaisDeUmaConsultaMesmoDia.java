package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.AgendamentoRepository;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ValidacaoMaisDeUmaConsultaMesmoDia {
    @Autowired
    AgendamentoRepository agendamentoRepository;
    public  void validar(DadosAgendamentoConsulta dados){

    }
}
