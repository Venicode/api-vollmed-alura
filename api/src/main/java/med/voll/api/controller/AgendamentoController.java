package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.agendamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamentos")
@SecurityRequirement(name = "bearer-key")
public class AgendamentoController {
    @Autowired
    private AgendaDeConsultas agendaDeConsultas;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancelar(@RequestBody @Valid DadosCancelamentoAgendamento dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
