# Guia Inicial Spring Boot - MapStruct + Lombok + Maven

## 📋 Índice
1. [Dependências Essenciais](#dependências-essenciais)
2. [Validação com @Valid](#validação-com-valid)
3. [Comandos Maven Essenciais](#comandos-maven-essenciais)
4. [O Problema MapStruct + Lombok](#o-problema-mapstruct--lombok)
5. [Por que Acontece](#por-que-acontece)
6. [Solução Definitiva](#solução-definitiva)
7. [Configuração Correta](#configuração-correta)
8. [Boas Práticas](#boas-práticas)
9. [Checklist](#checklist)
10. [Exemplos Práticos](#exemplos-práticos)

---

## 📦 Dependências Essenciais

### Dependências que Vêm do Spring Initializr ✅

Essas dependências você seleciona ao criar o projeto no [start.spring.io](https://start.spring.io):

```xml
<dependencies>
    <!-- Spring Boot Starter Web - Controllers REST -->
    <!-- ✅ SELECIONAR: "Spring Web" no Initializr -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Data JPA - Banco de dados -->
    <!-- ✅ SELECIONAR: "Spring Data JPA" no Initializr -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Validation - Validações @Valid -->
    <!-- ✅ SELECIONAR: "Validation" no Initializr -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- PostgreSQL Driver - Banco de dados -->
    <!-- ✅ SELECIONAR: "PostgreSQL Driver" no Initializr -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- DevTools - Hot reload durante desenvolvimento -->
    <!-- ✅ SELECIONAR: "Spring Boot DevTools" no Initializr -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    
    <!-- Spring Boot Test - Testes unitários -->
    <!-- ✅ SEMPRE VEM automaticamente (não precisa selecionar) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Dependências que Você Precisa Adicionar Manualmente ⚠️

Essas dependências **NÃO ESTÃO** no Spring Initializr, você precisa adicionar no pom.xml:

```xml
<dependencies>
    <!-- Lombok - Gera getters/setters automaticamente -->
    <!-- ⚠️ ADICIONAR MANUALMENTE no pom.xml -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- MapStruct - Geração automática de mappers -->
    <!-- ⚠️ ADICIONAR MANUALMENTE no pom.xml -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.5.5.Final</version>
    </dependency>
    
    <!-- MapStruct Processor - Processador para gerar implementações -->
    <!-- ⚠️ ADICIONAR MANUALMENTE no pom.xml -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>1.5.5.Final</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Configuração Maven que Você Precisa Adicionar Manualmente ⚠️

O plugin maven-compiler-plugin também precisa ser configurado manualmente:

```xml
<!-- ⚠️ ADICIONAR MANUALMENTE na seção <build><plugins> -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <source>17</source>
        <target>17</target>
        <annotationProcessorPaths>
            <!-- ORDEM IMPORTANTE -->
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.40</version>
            </path>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-mapstruct-binding</artifactId>
                <version>0.2.0</version>
            </path>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.5.5.Final</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

### Como Usar o Spring Initializr

1. **Acesse**: [https://start.spring.io](https://start.spring.io)
2. **Configure**:
   - Project: Maven
   - Language: Java
   - Spring Boot: 3.5.6 (ou mais recente)
   - Java: 17
3. **Selecione essas dependências**:
   - ✅ Spring Web
   - ✅ Spring Data JPA  
   - ✅ Validation
   - ✅ PostgreSQL Driver
   - ✅ Spring Boot DevTools
4. **Baixe** o projeto
5. **Adicione manualmente**: Lombok, MapStruct e configuração do plugin

### Dependências de Desenvolvimento

```xml
<dependencies>
    <!-- DevTools - Hot reload durante desenvolvimento -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    
    <!-- Spring Boot Test - Testes unitários -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### POM.xml Completo de Referência

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>
    
    <groupId>com.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>meu-projeto</name>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <!-- Spring Boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Banco de dados -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.5.Final</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>1.5.5.Final</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- Desenvolvimento -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        
        <!-- Testes -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                    <annotationProcessorPaths>
                        <!-- ORDEM IMPORTANTE -->
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.40</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok-mapstruct-binding</artifactId>
                            <version>0.2.0</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.5.5.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## ✅ Validação com @Valid

### Como Funciona

A anotação `@Valid` é parte do **Bean Validation** (JSR-303/JSR-380) e funciona da seguinte forma:

1. **Spring intercepta** requisições HTTP nos controllers
2. **Deserializa** o JSON para objeto Java (DTO)
3. **Valida** o objeto usando as anotações de validação
4. **Se válido**: continua execução normal
5. **Se inválido**: retorna erro 400 com detalhes dos problemas

### Configuração Básica

#### 1. Dependência Necessária
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

#### 2. DTO com Anotações de Validação
```java
package com.exemplo.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String name;
    
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter 10 ou 11 dígitos")
    private String phone;
    
    @Email(message = "Email deve ter formato válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    @Min(value = 18, message = "Idade mínima é 18 anos")
    @Max(value = 120, message = "Idade máxima é 120 anos")
    private Integer age;
    
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthDate;
}
```

#### 3. Controller com @Valid
```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserDto dto) {
        // Se chegou aqui, dto está válido!
        UserModel saved = userService.create(dto);
        return ResponseEntity.ok(saved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDto dto) {
        UserModel updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
```

### Principais Anotações de Validação

#### Validações de String
```java
@NotNull         // Não pode ser null
@NotEmpty        // Não pode ser null nem vazio ("")
@NotBlank        // Não pode ser null, vazio nem só espaços ("   ")
@Size(min=2, max=50)  // Tamanho entre 2 e 50 caracteres
@Pattern(regexp="\\d+") // Deve seguir regex (só números)
@Email           // Formato de email válido
```

#### Validações Numéricas
```java
@Min(18)         // Valor mínimo 18
@Max(100)        // Valor máximo 100
@Positive        // Deve ser positivo (> 0)
@PositiveOrZero  // Deve ser positivo ou zero (>= 0)
@Negative        // Deve ser negativo (< 0)
@DecimalMin("0.0") // Valor mínimo decimal
@DecimalMax("999.99") // Valor máximo decimal
```

#### Validações de Data
```java
@Past            // Data no passado
@PastOrPresent   // Data no passado ou presente
@Future          // Data no futuro
@FutureOrPresent // Data no futuro ou presente
```

### Exemplos Práticos de Uso

#### UserDto Completo
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve ter 10 ou 11 dígitos")
    private String phone;
    
    @Min(value = 18, message = "Idade mínima é 18 anos")
    @NotNull(message = "Idade é obrigatória")
    private Integer age;
    
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthDate;
}
```

#### Resposta de Erro Automática
Quando validation falha, Spring Boot retorna automaticamente:

```json
{
  "timestamp": "2025-09-27T15:53:23.798+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for object='userDto'. Error count: 2",
  "errors": [
    {
      "field": "name",
      "rejectedValue": "",
      "message": "Nome é obrigatório"
    },
    {
      "field": "email",
      "rejectedValue": "email-inválido",
      "message": "Email inválido"
    }
  ],
  "path": "/users/"
}
```

### Validações Customizadas

#### Criando Sua Própria Validação
```java
// 1. Criar anotação
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFValidator.class)
public @interface ValidCPF {
    String message() default "CPF inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// 2. Implementar validador
public class CPFValidator implements ConstraintValidator<ValidCPF, String> {
    
    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return true; // Use @NotBlank para obrigatório
        }
        
        // Lógica de validação de CPF aqui
        return cpf.matches("\\d{11}") && isValidCPF(cpf);
    }
    
    private boolean isValidCPF(String cpf) {
        // Implementar algoritmo de validação de CPF
        return true;
    }
}

// 3. Usar no DTO
public class UserDto {
    @ValidCPF(message = "CPF inválido")
    private String cpf;
}
```

### Validação em Grupos

```java
// 1. Criar interfaces de grupo
public interface CreateValidation {}
public interface UpdateValidation {}

// 2. Usar nos DTOs
public class UserDto {
    @NotNull(groups = UpdateValidation.class, message = "ID obrigatório para update")
    private Long id;
    
    @NotBlank(groups = {CreateValidation.class, UpdateValidation.class})
    private String name;
    
    @NotBlank(groups = CreateValidation.class, message = "Senha obrigatória na criação")
    private String password;
}

// 3. Usar no Controller
@PostMapping
public ResponseEntity<UserModel> create(
    @Validated(CreateValidation.class) @RequestBody UserDto dto) {
    // Valida apenas campos marcados com CreateValidation.class
}

@PutMapping("/{id}")
public ResponseEntity<UserModel> update(
    @PathVariable Long id,
    @Validated(UpdateValidation.class) @RequestBody UserDto dto) {
    // Valida apenas campos marcados com UpdateValidation.class
}
```

### Dicas Importantes sobre @Valid

#### ✅ Boas Práticas
- **Sempre use @Valid** nos controllers para DTOs de entrada
- **Mensagens claras**: Customize mensagens para o usuário final
- **@NoArgsConstructor**: Essencial nos DTOs para deserialização JSON
- **Validação no DTO**: Nunca no Controller ou Service
- **Use @NotBlank** em vez de @NotNull para strings

#### ⚠️ Problemas Comuns
```java
// ❌ ERRADO - Esqueceu @Valid
@PostMapping
public ResponseEntity<UserModel> create(@RequestBody UserDto dto) {
    // Não vai validar!
}

// ❌ ERRADO - DTO sem @NoArgsConstructor
@AllArgsConstructor // Só este
public class UserDto {
    // Jackson não consegue deserializar
}

// ✅ CORRETO
@PostMapping
public ResponseEntity<UserModel> create(@Valid @RequestBody UserDto dto) {
    // Vai validar automaticamente
}

@NoArgsConstructor  // Essencial
@AllArgsConstructor
public class UserDto {
    // Jackson consegue deserializar
}
```

---

## 🚀 Comandos Maven Essenciais

### Comandos Básicos do Dia a Dia

#### **`mvn compile`**
- **O que faz**: Compila apenas o código fonte principal (src/main/java)
- **Quando usar**: Para verificar se o código compila sem erros
- **Resultado**: Classes compiladas em `target/classes/`

#### **`mvn clean`**
- **O que faz**: Remove a pasta `target/` completamente
- **Quando usar**: Para limpar compilações antigas e forçar recompilação total
- **Resultado**: Pasta target deletada

#### **`mvn clean compile`**
- **O que faz**: Limpa tudo e recompila do zero
- **Quando usar**: Quando há problemas de cache ou conflitos de compilação
- **⭐ Mais usado**: Este é um dos comandos mais utilizados

### Comandos Spring Boot

#### **`mvn spring-boot:run`**
- **O que faz**: Compila e executa a aplicação Spring Boot
- **Mais prático**: Não precisa gerar JAR, roda direto
- **Para desenvolvimento**: Ideal durante codificação

#### **`mvn clean package`**
- **O que faz**: Limpa, compila, testa e gera o JAR final
- **Para produção**: Gera JAR executável
- **Resultado**: `target/[nome-do-projeto].jar`

#### **`java -jar target/[nome].jar`**
- **O que faz**: Executa o JAR gerado pelo `mvn package`
- **Para produção**: Modo mais comum em servidores
- **Exemplo**: `java -jar target/ecommerce-fkmodas-0.0.1-SNAPSHOT.jar`

### Comandos de Testes e Dependências

#### **`mvn test`**
- **O que faz**: Compila e executa todos os testes unitários
- **Localização**: Executa testes em `src/test/java/`
- **Resultado**: Relatórios em `target/surefire-reports/`

#### **`mvn dependency:tree`**
- **O que faz**: Mostra todas as dependências do projeto em árvore
- **Útil para**: Identificar conflitos de versões
- **Exemplo de saída**:
```
com.braza:ecommerce-fkmodas:jar:0.0.1-SNAPSHOT
├── org.springframework.boot:spring-boot-starter-web:jar:3.5.6
│   ├── org.springframework:spring-web:jar:6.2.11
│   └── org.springframework:spring-webmvc:jar:6.2.11
```

#### **`mvn dependency:resolve`**
- **O que faz**: Baixa todas as dependências listadas no pom.xml
- **Quando usar**: Depois de adicionar novas dependências

### Comandos para Troubleshooting

#### **`mvn clean install -U`**
- **-U**: Força atualização de dependências
- **Quando usar**: Quando há problemas com dependências
- **Resolve**: Problemas de cache de dependências

#### **`mvn compile -X`**
- **-X**: Mode debug (muito verboso)
- **Quando usar**: Para investigar problemas de compilação
- **Mostra**: Todos os detalhes do processo de build

### Fluxo Típico de Desenvolvimento

```bash
# 1. Limpeza e compilação
mvn clean compile

# 2. Executar testes
mvn test

# 3. Executar aplicação
mvn spring-boot:run

# 4. Para produção
mvn clean package
java -jar target/ecommerce-fkmodas-0.0.1-SNAPSHOT.jar
```

### Comandos Mais Usados no Dia a Dia

#### **Durante desenvolvimento:**
1. `mvn clean compile` - Para verificar se compila
2. `mvn spring-boot:run` - Para testar a aplicação
3. `mvn test` - Para rodar testes
4. `mvn clean` - Quando algo não funciona

#### **Quando adicionar dependências:**
1. Editar `pom.xml`
2. `mvn dependency:resolve`
3. `mvn clean compile`

#### **Para problemas:**
1. `mvn clean` - Primeiro comando sempre
2. `mvn compile -X` - Para ver detalhes
3. `mvn dependency:tree` - Para ver conflitos

---

## 🚨 O Problema MapStruct + Lombok

### Sintomas
- MapStruct gera mappers "vazios" que não copiam campos
- Dados chegam corretos no DTO mas ficam nulos no Model
- Erro 400 Bad Request em validações obrigatórias
- Erro 500 de integridade no banco de dados

### Exemplo do Mapper Quebrado
```java
@Generated(value = "org.mapstruct.ap.MappingProcessor")
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserModel toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        
        UserModel userModel = new UserModel();
        // ❌ NÃO COPIA OS CAMPOS!
        return userModel;
    }
}
```

---

## 🔍 Por que Acontece

### 1. Conflito de Processamento de Anotações
- **Lombok**: Gera getters/setters em tempo de compilação
- **MapStruct**: Processa anotações para gerar mappers
- **Problema**: MapStruct não "enxerga" os métodos que o Lombok vai gerar

### 2. Ordem de Processamento
- MapStruct precisa processar **DEPOIS** do Lombok
- Sem configuração correta, a ordem fica incorreta
- Resultado: mapper gerado sem acesso aos getters/setters

### 3. Falta de Ponte de Comunicação
- Não há dependência `lombok-mapstruct-binding`
- Os dois processadores não se comunicam
- Cada um trabalha independentemente

---

## ✅ Solução Definitiva

### 1. Dependências no pom.xml
```xml
<dependencies>
    <!-- MapStruct -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.5.5.Final</version>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### 2. Configuração do Plugin Maven
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
                <annotationProcessorPaths>
                    <!-- ORDEM CRÍTICA: Lombok primeiro! -->
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>1.18.40</version>
                    </path>
                    <!-- Ponte de comunicação -->
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok-mapstruct-binding</artifactId>
                        <version>0.2.0</version>
                    </path>
                    <!-- MapStruct por último -->
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.5.5.Final</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

---

## 🛠️ Configuração Correta

### UserDto.java
```java
package com.exemplo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor    // Essencial para Jackson JSON
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    private String phone;
}
```

### UserModel.java
```java
package com.exemplo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String phone;
}
```

### IUserMapper.java
```java
package com.exemplo.domain;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    
    // Conversão DTO -> Model
    UserModel toEntity(UserDto userDto);
    
    // Conversão Model -> DTO  
    UserDto toDto(UserModel userModel);
    
    // Update parcial (ignora campos nulos)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto userDto, @MappingTarget UserModel userModel);
}
```

**Nota**: Com a configuração correta, não precisa dos `@Mapping` explícitos para campos com mesmo nome!

---

## 📋 Boas Práticas

### 1. Verificação Pós-Compilação
Sempre verifique o mapper gerado em:
```
target/generated-sources/annotations/[seu-pacote]/IUserMapperImpl.java
```

### 2. Mapper Correto (Esperado)
```java
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserModel toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        
        // ✅ COPIA OS CAMPOS CORRETAMENTE
        userModel.setName(userDto.getName());
        userModel.setPhone(userDto.getPhone());
        
        return userModel;
    }
}
```

### 3. Teste Simples
```java
@Test
void testMapper() {
    UserDto dto = new UserDto("João", "11999887766");
    UserModel model = userMapper.toEntity(dto);
    
    assertThat(model.getName()).isEqualTo("João");
    assertThat(model.getPhone()).isEqualTo("11999887766");
}
```

---

## ✅ Checklist para Novos Projetos

### Configuração Inicial
- [ ] Dependências MapStruct e Lombok adicionadas
- [ ] Plugin maven-compiler-plugin configurado
- [ ] Ordem correta dos annotation processors
- [ ] Dependência lombok-mapstruct-binding incluída

### Classes e Interfaces  
- [ ] DTOs com `@NoArgsConstructor` para JSON
- [ ] Models com getters/setters (Lombok)
- [ ] Mapper interface com `@Mapper(componentModel = "spring")`

### Validação
- [ ] Compilação sem erros
- [ ] Mapper gerado copia campos corretamente
- [ ] Testes unitários passando
- [ ] Requisições HTTP funcionando

---

## 💡 Exemplos Práticos

### Cenário 1: CRUD Básico
```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserDto dto) {
        UserModel saved = userService.create(dto);
        return ResponseEntity.ok(saved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDto dto) {
        UserModel updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
```

### Cenário 2: Service com Mapper
```java
@Service
public class UserService {
    
    @Autowired
    private IUserRepository repository;
    
    @Autowired
    private IUserMapper mapper;
    
    public UserModel create(UserDto dto) {
        UserModel model = mapper.toEntity(dto);
        return repository.save(model);
    }
    
    public UserModel update(Long id, UserDto dto) {
        UserModel existing = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
        
        mapper.updateUserFromDto(dto, existing);
        return repository.save(existing);
    }
}
```

---

## 🚫 Erros Comuns e Soluções

### Erro 1: Mapper vazio gerado
**Causa**: Configuração incorreta dos annotation processors
**Solução**: Seguir ordem correta no pom.xml

### Erro 2: `@NoArgsConstructor` ausente
**Causa**: Jackson não consegue deserializar JSON
**Solução**: Adicionar anotação no DTO

### Erro 3: Versões incompatíveis
**Causa**: Lombok e MapStruct com versões conflitantes
**Solução**: Usar versões testadas (Lombok 1.18.40 + MapStruct 1.5.5.Final)

### Erro 4: Campos não mapeados
**Causa**: Nomes diferentes entre DTO e Model
**Solução**: Usar `@Mapping(source = "campo1", target = "campo2")`

---

## 🔧 Troubleshooting

### 1. Se o mapper ainda estiver vazio:
```bash
# Limpar completamente
mvn clean

# Recompilar
mvn compile

# Verificar gerado
cat target/generated-sources/annotations/[pacote]/IUserMapperImpl.java
```

### 2. Se houver erros de compilação:
- Verificar versões das dependências
- Confirmar ordem dos annotation processors  
- Executar `mvn dependency:resolve`

### 3. Se a API retornar Bad Request:
- Verificar se mapper copia campos corretamente
- Testar deserialização JSON separadamente
- Validar anotações de validação

---

## 📝 Resumo Final

O problema MapStruct + Lombok é **muito comum** mas tem solução definitive:

1. **Configure corretamente** os annotation processors no pom.xml
2. **Mantenha a ordem**: Lombok → binding → MapStruct  
3. **Sempre verifique** o código gerado após compilar
4. **Use @NoArgsConstructor** nos DTOs
5. **Teste sempre** após mudanças

Com essas práticas, você nunca mais terá problemas de mappers vazios!

---

## 📚 Referências

- [MapStruct Documentation](https://mapstruct.org/)
- [Lombok Documentation](https://projectlombok.org/)
- [MapStruct + Lombok Integration](https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-lombok)

---

*Guia criado em 27/09/2025 - Versões: Spring Boot 3.5.6, MapStruct 1.5.5.Final, Lombok 1.18.40*
