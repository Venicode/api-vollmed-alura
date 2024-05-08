package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoPacienteInativo {
    @Autowired
    PacienteRepository pacienteRepository;
    public void validar(DadosAgendamentoConsulta dados){
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if(!paciente.isAtivo()){
            throw new ValidacaoException("Paciente está inativo no cadastro da clínica.");
        }
    }
}
