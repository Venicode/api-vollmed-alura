package med.voll.api.domain.agendamento.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteInativo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    PacienteRepository pacienteRepository;
    public void validar(DadosAgendamentoConsulta dados){
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if(!paciente.isAtivo()){
            throw new ValidacaoException("Paciente está inativo no cadastro da clínica.");
        }
    }
}
