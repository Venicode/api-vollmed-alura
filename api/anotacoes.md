## Spring Boot Framework
- Auto configuração
- Possui as dependências necessárias para realizar a aplicação.
- Spring Data JPA
- Spring Security

## Problema de CORS
Por padrão, uma aplicação front-end consegue acessar apenas recursos que possuem a mesma origem da solicitação (same-origin policy), um mecanismo de segurança dos browsers.

## Access-Allow-Origin
Dentro do header(cabeçalho) da api, é preciso informar a origem que terá a permissão e os métodos que ele poderá realizar. Caso seja uma api pública, basta dizer que todas as origens serão aceitas:
Access-Control-Allow-Origin: *

## Habilitando diferentes origens no Spring Boot

public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}

