package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> tratarError400(MethodArgumentNotValidException exception){
        List<FieldError> erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }
    @ExceptionHandler({ValidacaoException.class})
    public ResponseEntity<Object> camposObrigatoriosCancelamento(ValidacaoException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> motivoCancelamentoInvalido(HttpMessageNotReadableException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    private record DadosErrosValidacao(String campo, String mensagem){
        public DadosErrosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
