# Microserviço - Sistema de Cálculo de Frete

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Este projeto implementa um microserviço para o cálculo de frete, desenvolvido em Java, Spring Boot e Maven, utilizando JUnit para testes e JaCoCo para análise de cobertura.

## Índice
- [Funcionalidades](#funcionalidades)
- [Instalação](#instalação)
- [Uso do Projeto](#uso-do-projeto)
- [Design do Sistema](#design-do-sistema)
- [Autor](#autor)

## Funcionalidades

- ✅ Calcular o valor do frete de entregas (`POST` em `/frete/calcular`).

## Pré-requisitos
- [Java 17+](https://docs.oracle.com/en/java/javase/24/)
- [Maven 3.3.2+](https://maven.apache.org/guides/index.html)
- [Git](https://git-scm.com/doc)
  
## Instalação

### 1️⃣ Clone o repositório
Abra o terminal de sua preferência, que tenha o Git instalado e configurado corretamente, no local onde deseja clonar o repositório.

Execute o comando **git clone https://github.com/joaorafaelleite/Microservico-Calculo-De-Frete.git**

### 2️⃣ Instale as dependências
Navegue até o diretório raiz do projeto.

Execute o comando **mvn clean install**

## Uso do Projeto

### Execute o projeto
Execute a aplicação a partir da classe MsCalculodefreteApplication.

### Endpoint

**Exemplo de Requisição**
```markdown
POST /frete/calcular - Realiza o cálculo do valor do frete de entrega para o tipo de transporte selecionado
```
```json
{
    "pesoDoPacote": 100.00,
    "distanciaDaEntrega": 100.00,
    "tipoDeTransporte": "NORMAL"
}
```
Caso queira utilizar o Postman para realizar testes mais completos, incluindo cenários de erro, utilize a coleção:
[MS-CalculoDeFrete Collection](https://www.postman.com/joaorafaelleite/workspace/ms-calculodefrete/collection/26395023-70a56168-8b96-4523-b00e-99153bf6ce70?action=share&source=copy-link&creator=26395023)

### (OPCIONAL) Execução via terminal (OPCIONAL)
Navegue até o diretório principal.

Execute o comando **mvn clean package**

Execute o comando **mvn spring-boot:run**

Exemplo de requisição via terminal:

**curl -X POST -H "Content-Type: application/json" -d "{\"pesoDoPacote\":100.0,\"distanciaDaEntrega\":100.0,\"tipoDeTransporte\":\"NORMAL\"}" http://localhost:8080/frete/calcular**

## Execução de Testes e Métricas de Testes

### 1️⃣ Execução
Execute o comando **mvn clean verify**

### 2️⃣ Verificando métricas de teste
Dentro da pasta do projeto, navegue até o diretório **\target\site\jacoco**

Execute o arquivo **index.html**

## Design do Sistema
### Arquitetura
Este projeto implementa a arquitetura hexagonal, separando as responsabilidades entre as camadas de domínio, aplicação e infraestrutura. Os adaptadores de entrada (controllers, DTOs, converters) e saída (estratégias de frete, configurações) são independentes do núcleo de negócio, permitindo fácil manutenção, testes e evolução do sistema sem impacto no domínio.

### SOLID
O projeto foi desenvolvido tendo-se em mente os princípios SOLID, visando um projeto mais coeso, reaproveitável, com baixo acoplamento e fácil manutenção.

**S**: Single Responsibility Principle (Princípio da responsabilidade única)
  - As classes e métodos foram pensados de forma a terem apenas uma razão para serem modificados.

**O**: Open-Closed Principle (Princípio Aberto-Fechado)
  - Novas estratégias de frete podem ser adicionadas sem modificar o core.

**L**: Liskov Substitution Principle (Princípio da substituição de Liskov)
  - Estratégias de frete podem ser substituídas sem alterar o comportamento do sistema.

**I**: Interface Segregation Principle (Princípio da Segregação da Interface)
  - Interfaces são pequenas e específicas.

**D**: Dependency Inversion Principle (Princípio da inversão da dependência)
  - Casos de uso e serviços dependem de abstrações e recebem dependências via injeção.

### Design Patterns
Para o desenvolvimento deste projeto eu escolhi aplicar o Design Pattern Strategy, ao encapsular as diferentes formas de cálculo de frete em classes separadas (FreteNormal, FreteExpresso), todas seguindo uma interface comum (FreteStrategyInterface). O contexto (FreteContext) recebe a estratégia adequada em tempo de execução, permitindo que o cálculo do frete seja flexível e facilmente extensível para novos tipos de frete, sem alterar o código existente. Isso garante baixo acoplamento, alta coesão e facilita a manutenção e evolução do sistema, seguindo as melhores práticas do padrão Strategy.

## Autor

<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/112492208?s=400&u=d9c75b76dd2b8ebed82d5b37ac031c6da8600948&v=4" width="100px;" alt=""/>

Feito por João Rafael, entre em contato via:

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/joao-rafael-leite/)
[![Microsoft Outlook](https://img.shields.io/badge/Microsoft_Outlook-0078D4?style=for-the-badge&logo=microsoft-outlook&logoColor=whitem)](mailto:joaorafael.leite@hotmail.com)
