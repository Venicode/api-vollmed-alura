package med.voll.api.domain.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.agendamento.validacoes.cancelamento.ValidadorCancelamentoAntecedencia;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;
    @Autowired
    private List<ValidadorCancelamentoAntecedencia> validadoresCancelamento;
    public DadosDetalhamentoAgendamento agendar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() != null) {
            if (!medicoRepository.existsById(dados.idMedico())) {
                throw new ValidacaoException("Id do médico informado não existe.");
            }
        }
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe.");
        }
        //percorrendo a lista um por um e validando os dados
        validadoresAgendamento.forEach(v -> v.validar(dados));
        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Agendamento agendamento = new Agendamento(null,medico, paciente, dados.data(),dados.cancelamento(),dados.motivoCancelamento());
        agendamentoRepository.save(agendamento);
        return new DadosDetalhamentoAgendamento(agendamento);
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

    public void cancelar(DadosCancelamentoAgendamento dados) {
        if (!agendamentoRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        validadoresCancelamento.forEach(v -> v.validar(dados));
        var consulta = agendamentoRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
