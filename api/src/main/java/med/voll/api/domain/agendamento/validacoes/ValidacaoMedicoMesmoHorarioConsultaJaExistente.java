package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.AgendamentoRepository;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoMedicoMesmoHorarioConsultaJaExistente {
    @Autowired
    AgendamentoRepository agendamentoRepository;
    public void avaliar(DadosAgendamentoConsulta dados){
        if(agendamentoRepository.verificarConsultaIdMedico(dados.idMedico(), dados.data())!=null){
            throw new ValidacaoException("Já existe uma consulta agendada com este médico no mesmo dia");
        }
    }
}
