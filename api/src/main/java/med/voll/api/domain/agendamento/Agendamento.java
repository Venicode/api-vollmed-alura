package med.voll.api.domain.agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "agendamentos")
@Entity(name = "Agendamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
    private int cancelamento;

    public void cancelarAgendamento(LocalDateTime dataConsulta, LocalDateTime dataCancelamento){
        if(dataConsulta.toLocalDate().isEqual(dataCancelamento.toLocalDate())){
            throw new ValidacaoException("O cancelamento deve ser feito em até 1 dia antes da consulta.");
        }
        if(dataCancelamento.toLocalDate().isAfter(dataConsulta.toLocalDate())){
            throw new ValidacaoException("Data de cancelamento inválida.");
        }
        cancelamento = 0;
    }
}
