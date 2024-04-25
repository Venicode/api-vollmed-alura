package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListagemMedico> listarPorPaginaAtivos(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional //O transactional serve para escrever e sobrescrever no banco de dados
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
    /*
    Exclusão diretamente no banco de dados
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
       repository.deleteById(id);
    }
    */

    //Exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
