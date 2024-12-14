# Sistema de Gerenciamento de Animais

Este código implementa um sistema de gerenciamento de cadastro de animais, incluindo funcionalidades para cadastrar, atualizar, remover e listar os animais, além de exibir estatísticas relacionadas ao banco de dados de animais cadastrados. O sistema é baseado em um menu interativo, e a lógica é gerida por uma classe `Gerenciador` que manipula os dados dos animais.

## Estrutura Principal

### Classe `Main`

A classe `Main` contém o método `main`, que inicializa o programa e apresenta o menu de opções ao usuário. O programa utiliza um objeto da classe `Gerenciador` para gerenciar os animais cadastrados.

#### Fluxo do Programa

1. **Saudação e Início**:
   O programa começa com uma mensagem de boas-vindas ao usuário.

2. **Menu Interativo**: 
   O usuário é apresentado com um menu de opções que permite:
   - Cadastrar um novo animal.
   - Atualizar o cadastro de um animal existente.
   - Remover o cadastro de um animal.
   - Listar todos os animais cadastrados e visualizar estatísticas.
   - Encerrar o programa.

3. **Ações**:
   - **Cadastrar Animal**: Se o usuário escolhe a opção de cadastro, o método `cadastrarAnimal` é chamado.
   - **Atualizar Animal**: Caso o usuário deseje atualizar informações de um animal, o método `atualizarAnimal` é chamado.
   - **Remover Animal**: Se a opção de remoção for selecionada, o método `apagarAnimal` é chamado.
   - **Listar Animais e Estatísticas**: Ao escolher essa opção, o programa lista todos os animais e exibe estatísticas, como a quantidade de machos e fêmeas, peso total e médio, etc.
   
4. **Encerramento**:
   - O usuário pode optar por continuar ou encerrar o programa após cada operação.
   - Caso escolha encerrar, o programa finaliza e a conexão com o `Scanner` é fechada.

### Métodos da Classe `Main`

1. **cadastrarAnimal(Scanner, Gerenciador)**:
   - Cria um novo objeto `Animal`.
   - Solicita o peso do animal.
   - Valida e atribui um código único ao animal. Caso o código já exista, o programa oferece ao usuário a opção de substituir o animal ou escolher um novo código.
   - Solicita o sexo do animal (M/F).
   - Adiciona o animal ao gerenciador e exibe uma mensagem de sucesso.

2. **atualizarAnimal(Scanner, Gerenciador)**:
   - Exibe os animais cadastrados e permite ao usuário escolher um animal pelo código para atualizar.
   - Permite alterar o código, peso ou sexo do animal.

3. **apagarAnimal(Scanner, Gerenciador)**:
   - Exibe a lista de animais cadastrados e permite ao usuário escolher um animal para remoção pelo código.

### Classe `Gerenciador`

A classe `Gerenciador` é responsável pela gestão dos animais, oferecendo métodos para adicionar, remover, buscar, listar e exibir estatísticas.

#### Principais Métodos da Classe `Gerenciador`

1. **codigoExiste(int codigo)**:
   - Verifica se o código informado já existe na lista de animais.

2. **proximoCodigoDisponivel()**:
   - Retorna o próximo código disponível para um novo animal, garantindo que não haja duplicidade.

3. **adicionarAnimal(Animal animal)**:
   - Adiciona um novo animal à lista interna de animais.

4. **removerAnimal(int codigo)**:
   - Remove um animal da lista com base no código fornecido.

5. **buscarAnimalPorCodigo(int codigo)**:
   - Retorna o animal correspondente ao código, se encontrado. Caso contrário, retorna `null`.

6. **listarAnimais()**:
   - Exibe todos os animais cadastrados, incluindo código, peso, sexo e valor.

7. **exibirEstatisticas()**:
   - Exibe estatísticas sobre os animais cadastrados, como:
     - Total de animais.
     - Quantidade de machos e fêmeas.
     - Peso total, peso de machos e peso de fêmeas.
     - Média de peso dos animais.
     - Peso máximo e mínimo.

8. **getQuantidadeAnimais()**:
   - Retorna o número de animais cadastrados.

### Classe `Animal`

Embora não tenha sido fornecido seu código, a classe `Animal` possui métodos básicos, como:
- `setCodigo(int codigo)`: Define o código do animal.
- `getCodigo()`: Retorna o código do animal.
- `setPeso(double peso)`: Define o peso do animal.
- `getPeso()`: Retorna o peso do animal.
- `setSexo(String sexo)`: Define o sexo do animal.
- `getSexo()`: Retorna o sexo do animal.
- `setValorArroba(double valor)`: Define o valor da arroba do animal (valor utilizado para calcular o preço do animal).
- `getValor()`: Retorna o valor do animal.

## Detalhes da Implementação

- **Estrutura de Dados**: A classe `Gerenciador` utiliza uma lista (`List<Animal>`) para armazenar os animais.
- **Interatividade**: O programa é baseado em interações via terminal com o usuário, que escolhe opções para realizar diferentes operações.
- **Validações**: Há validações para garantir que o código do animal seja único e que o sexo informado seja válido (M ou F).
- **Estatísticas**: O programa calcula estatísticas detalhadas sobre os animais, como distribuição entre machos e fêmeas, peso médio e total, e outros dados úteis para a gestão.

## Exemplo de Execução

```bash
==========================================================
================ Olá seja bem vindo(a)! ==================
==========================================================

Sistema de gerenciamento de animais
Escolha uma opção abaixo, por gentileza use apenas números:
1 - Cadastrar novo animal
2 - Atualizar cadastro de um animal
3 - Apagar cadastro de um animal
4 - Listar todos os animais cadastrados
5 - Encerrar programa
