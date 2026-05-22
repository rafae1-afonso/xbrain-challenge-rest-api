# Desafio Técnico Backend - Java Spring Boot

## Para rodar o projeto

### 1. Clone o repositório

```
git clone https://github.com/rafae1-afonso/xbrain-challenge-rest-api
```
### 2. Mude para o diretório do projeto

```
cd xbrain-challenge-rest-api
```
### 3. Inicie o Docker Desktop e rode o docker compose

```
docker compose up --build
```

#### REST API

```
localhost:8080
```

#### RabbitMQ Management

```
localhost:15672

username: guest
password: guest
```

## Rotas da API

### H2 Console (Banco de Dados)
```
localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./db.sql
User Name: sa
Password: (vazio)
```
### GET
```
localhost:8080/v1/pedidos - Listar pedidos

localhost:8080/v1/entregas - Listar entregas
```
### POST
```
localhost:8080/v1/pedidos - Criar pedido
```
#### Formato do Body
```
{
        "customerCode": "CUSTOMER-135", - (Deve serguir o formato CUSTOMER-000)
        "deliveryAddress": "Rua Street, 35",
        "products": [
            { - (Caso o objeto de product for deixado em branco será gerado valores aleatórios nas propriedades)
                "productCode": "PRODUCT-204",
                "price": 199.90,
                "quantity": 3
            }
        ]
}
```
---
## Explicação da Solução

### Fluxo de Criação de Pedido

A API foi modelada utilizando o padrão arquitetural em camadas, separando de forma clara as responsabilidades e garantindo o isolamento das regras de negócio contra detalhes de infraestrutura.

*   O cliente envia uma requisição POST `/v1/pedidos` contendo o código do cliente, lista de produtos e endereço. O valor total é calculado através dos preços dos produtos colocados multiplicados por sua quantidade.
*   O OrderController recebe os dados, aciona a camada de validação e repassa ao OrderService.
*   O pedido é persistido no banco de dados em memória H2.
*   Imediatamente após o sucesso da persistência, um evento contendo os dados básicos do pedido é publicado na fila `restapi-order-queue` do RabbitMQ.

## Decisões Técnicas Tomadas

1.  **Spring Boot 3.x & Java 17:** Optou-se pela utilização do Java 17 LTS combinado com o Spring Boot 3 devido ao suporte nativo a Records (excelentes para transporte de DTOs e mensagens de fila de forma imutável) e ganho geral de performance em relação a versões legadas.
2.  **RabbitMQ com Docker-Compose Integrado:** Para atender ao requisito de "único comando", o Dockerfile utiliza multi-stage build (compila com Maven e roda com OpenJDK fino) e o compose gerencia a dependência de inicialização da aplicação esperando o RabbitMQ estar totalmente "saudável" (healthy) antes de subir a API.
3.  **Banco de Dados H2:** Configurado com persistência em arquivo, sem necessidade de instalar ou provisionar bancos de dados externos pesados.
4.  **Spring AMQP com Jackson2JsonMessageConverter:** Configuração do RabbitMQ alterada para trafegar payloads em formato JSON estruturado em vez de serialização em bytes nativa do Java, facilitando auditoria e integridade de dados na fila.

## Resposta aos Pontos de Atenção

* **API de Pedido:** Endpoint funcional validando obrigatoriedade de campos via jakarta.validation.
* **Persistência Dupla:** Entidades Pedido e Entrega mapeadas corretamente via JPA/Hibernate e salvas no H2.
* **Mensageria Assíncrona:** Publicador e Consumidor integrados simulando o ciclo completo do negócio de logística.
* **Containerização Total:** Arquivos Dockerfile e docker-compose.yml redondos e prontos para execução limpa.
* **Testes Automatizados:** Testes unitários focados nas regras do PedidoService e EntregaService isolando as dependências com Mockito.

## Pontos de Melhoraria

* Implementação consistente do Pipeline CI/CD utilizando o Github Actions/Workflow.
* Testes unitários mais completos e complementados com testes de integração.
* Implementação de mais erros customizados para aprimorar a utilização da API.
