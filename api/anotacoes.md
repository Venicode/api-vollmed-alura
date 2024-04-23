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

## STRING DATA JPA
O Spring DATA JPA facilita a implementação e o acesso aos dados da nossa aplicação, fazendo a integração através dos nossos repositórios. No caso do projeto atual, criamos uma interface chamada MedicoRepository que herda o JpaRepository, onde é passado por parâmetro a nossa model e o tipo de id:

    public interface MedicoRepository extends JpaRepository<Medico,Long> {
    
    }

Porém a model não pode ser uma simples classe do Java, ela precisa possuir as anotações do JPA para realizar a criação da tabela conforme a estrutura da classe:
    
        @Table(name = "medicos") // Anotação do JPA para identificar a tabela
        @Entity(name = "Medico") // Anotação do JPA para nomear a entidade
        @Getter -- Anotação do Lombok para reduzir escrita de código
        @NoArgsConstructor // Anotação do Lombok
        @AllArgsConstructor // Anotação do Lombok
        @EqualsAndHashCode(of = "id") // Anotação do Lombok
        public class Medico {
        @Id // Identifica o atributo como um ID
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Incrementa a função para numerar id automático
        private Long id;
        private String nome;
        private String email;
        private String crm;
        @Enumerated(EnumType.STRING) // Identifica o campo como uma classe enum do tipo String
        private Especialidade especialidade;
        @Embedded // Identifica o atributo com o relacionamento de outra classe, pegando os seus atributos
        private Endereco endereco;
        
        // Cria o construtor que se alimenta dos dados vindo do record DadosCadastroMedico
        public Medico(DadosCadastroMedico dados) {
            this.nome = dados.nome();
            this.email = dados.email();
            this.crm = dados.crm();
            this.especialidade = dados.especialidade();
            this.endereco = new Endereco(dados.endereco());
        }
    }

## Ferramentas de Migrações / Migrations - Flyway
## Validação com o Spring Boot
