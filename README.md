# 🧩 Simulador de Sudoku em Java
Este projeto é um simulador de Sudoku executado via terminal, desenvolvido em Java com princípios básicos de organização orientada a objetos. Permite iniciar jogos com células fixas, inserir e remover números, 
verificar status e conflitos, e finalizar o jogo apenas quando estiver completo e sem erros.

##

## 🛠 Tecnologias Utilizadas
- Java (versão 24.0.1 - Oracle OpenJDK);
- IntelliJ IDEA (ou qualquer IDE compatível com Java);
- Console/Terminal para entrada de dados.

##

## ✅ Funcionalidades

### 🎯 Inicialização
- Iniciar um novo jogo com valores fixos passados como argumentos no formato `linha,coluna,valor` (1 a 9).  
  Exemplo: `1,1,5` define o valor 5 na posição linha 1, coluna 1 como fixo.

### ➕ Manipulação de Números
- Colocar número em uma posição (respeitando regras de Sudoku e não sobrescrevendo fixos);
- Remover número (desde que não seja fixo);
- Limpar todos os números não fixos.

### 🔍 Verificação e Status
- Exibir o tabuleiro atual com formatação clara;
- Verificar se o jogo foi iniciado;
- Obter o status do jogo: Não iniciado, Incompleto ou Completo;
- Detectar conflitos (erros) no tabuleiro conforme regras de linha, coluna e subgrade 3x3;
- Impedir finalizar enquanto o jogo estiver incompleto ou com conflitos.

### 📄 Saída
- Tabuleiro impresso com coordenadas e separadores visuais;
- Mensagens informativas sobre operações válidas/invalidas, como:
  - `"O número foi inserido com sucesso!"`
  - `"A posição é fixa e não pode ser modificada."`
  - `"O número selecionado é conflitante com as regras."`
  - `"Parabéns! Você terminou o sudoku!"` (apenas se completo e sem erros);
  - Erros de inicialização ou argumentos malformados.

##

## 🔐 Validações
- Verifica se o jogo foi iniciado antes de permitir operações;
- Impede alteração de posições fixas;
- Valida que inserções não criem conflitos em linha, coluna ou bloco 3x3;
- Garante que valores estejam no intervalo válido (1 a 9);
- Entrada de usuário tratada para evitar falhas ao ler números.

##

## 📦 Requisitos
- Java 24.0.1 (ou compatível) instalado;
- IntelliJ IDEA ou outra IDE para compilar e executar o código;
- Terminal/Console ativo para interação.

## 📁 Estrutura do Projeto
````
src/
├── app/
│   └── SudokuApp.java
├── interfaces/
│   └── IConta.java
└── models/
   ├── Posicao.java
   ├── Sudoku.java
   └── Tabuleiro.java
````

##
## ⚙️ Como Usar  
1. Clone ou copie o repositório para sua máquina;  
2. Abra o projeto no IntelliJ IDEA;  
3. Execute a classe `SudokuApp` (pacote `app`);  
4. Siga as instruções no terminal, informando os dados solicitados.
