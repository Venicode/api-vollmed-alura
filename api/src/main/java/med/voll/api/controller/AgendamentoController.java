package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.agendamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendaDeConsultas agendaDeConsultas;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados){
        Agendamento agendamento= agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAgendamento(agendamento.getId(),
                agendamento.getMedico().getId(),
                agendamento.getPaciente().getId(),
                agendamento.getData(),1));
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancelarAgendamento(@RequestBody @Valid DadosCancelamentoAgendamento dados){
        Agendamento agendamento = agendamentoRepository.getReferenceById(dados.idConsulta());
        agendamento.cancelarAgendamento(agendamento.getData(), dados.dataCancelamento());
        return ResponseEntity.noContent().build();
    }
}
