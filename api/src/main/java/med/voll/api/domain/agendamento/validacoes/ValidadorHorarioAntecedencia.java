package med.voll.api.domain.agendamento.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.agendamento.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime dataAgora = LocalDateTime.now();
        long diferencaMinutos = Duration.between(dataAgora,dataConsulta).toMinutes();

        if(diferencaMinutos<30){
            throw new ValidacaoException(("Consulta deve ser agendada com antecedência mínima de 30 minutos."));
        }
    }
}
