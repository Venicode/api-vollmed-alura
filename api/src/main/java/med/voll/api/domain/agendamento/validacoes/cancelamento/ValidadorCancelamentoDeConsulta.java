package med.voll.api.domain.agendamento.validacoes.cancelamento;

import med.voll.api.domain.agendamento.DadosCancelamentoAgendamento;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoAgendamento dados);
}
