package med.voll.api.domain.agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;

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
    @Enumerated(EnumType.STRING)
    private MotivosCancelamento motivoCancelamento;

    public void cancelar(MotivosCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}
