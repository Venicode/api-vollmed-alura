package med.voll.api.domain.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    public Agendamento agendar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() != null) {
            if (!medicoRepository.existsById(dados.idMedico())) {
                throw new ValidacaoException("Id do médico informado não existe.");
            }
        }
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe.");
        }
        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Agendamento agendamento = new Agendamento(null,medico, paciente, dados.data(),1,dados.motivoCancelamento());
        agendamentoRepository.save(agendamento);
        return  agendamento;
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especiliadade é um campo obrigatório quando não informado o médico");
        }
        return medicoRepository.escolherMedicoAleatorioLivre(dados.especialidade(), dados.data());
    }
}
