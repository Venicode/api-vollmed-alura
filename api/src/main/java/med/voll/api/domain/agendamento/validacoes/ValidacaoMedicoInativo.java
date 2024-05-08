package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoMedicoInativo {
    @Autowired
    MedicoRepository medicoRepository;
    public void validar(DadosAgendamentoConsulta dados){
        Medico medico = medicoRepository.getReferenceById(dados.idMedico());
        if(!medico.isAtivo()){
            throw new ValidacaoException("Médico está inativo no cadastro da clínica.");
        }
    }
}
