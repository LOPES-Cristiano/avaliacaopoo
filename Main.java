package avaliacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gerenciador gerenciador = new Gerenciador();

        System.out.println("==========================================================");
        System.out.println("================ Olá seja bem vindo(a)! ==================");
        System.out.println("==========================================================");

        boolean continuar = true;
        while (continuar) {

            System.out.println("\nSistema de gerenciamento de animais");
            System.out.println("Escolha uma opção abaixo, por gentileza use apenas números:");
            System.out.println("1 - Cadastrar novo animal");
            System.out.println("2 - Atualizar cadastro de um animal");
            System.out.println("3 - Apagar cadastro de um animal");
            System.out.println("4 - Listar todos os animais cadastrados");
            System.out.println("5 - Encerrar programa");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> cadastrarAnimal(scanner, gerenciador);
                case 2 -> atualizarAnimal(scanner, gerenciador);
                case 3 -> apagarAnimal(scanner, gerenciador);
                case 4 -> {
                    gerenciador.listarAnimais();
                    gerenciador.exibirEstatisticas();
                }
                case 5 -> {
                    System.out.println("Programa encerrado.");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }

            if (continuar) {
                System.out.println("\nDeseja continuar? (S/N)");
                String resposta = scanner.next();
                continuar = resposta.equalsIgnoreCase("S");
                System.out.println("\nPrograma Encerrado");
            }
        }

        scanner.close();
    }

    private static void cadastrarAnimal(Scanner scanner, Gerenciador gerenciador) {
        Animal animal = new Animal();
        animal.setValorArroba(267.50);

        System.out.println("==========================================================");
        System.out.println("Informe o peso do animal em kg:");
        animal.setPeso(scanner.nextDouble());

        boolean codigoValido = false;
        while (!codigoValido) {
            System.out.println("Informe o código do animal:");
            int codigo = scanner.nextInt();

            if (gerenciador.codigoExiste(codigo)) {
                System.out.println("O código " + codigo + " já está em uso. Deseja substituir o animal existente? (S/N)");
                String resposta = scanner.next();

                if (resposta.equalsIgnoreCase("S")) {
                    gerenciador.removerAnimal(codigo);
                    animal.setCodigo(codigo);
                    codigoValido = true; 
                } else {
                    int novoCodigo = gerenciador.proximoCodigoDisponivel();
                    System.out.println("O cadastro será realizado com o próximo código disponível: " + novoCodigo);
                    animal.setCodigo(novoCodigo);
                    codigoValido = true; 
                }
            } else {
                animal.setCodigo(codigo);
                codigoValido = true; 
            }
        }

        boolean sexoValido = false;
        while (!sexoValido) {
            System.out.println("Informe o sexo do animal (M/F):");
            String sexo = scanner.next();

            if (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F")) {
                animal.setSexo(sexo.toUpperCase());
                sexoValido = true;
            } else {
                System.out.println("Sexo inválido. Por favor, informe 'M' para macho ou 'F' para fêmea.");
            }
        }

        gerenciador.adicionarAnimal(animal);

        System.out.println("\n********************************************************");
        System.out.println("******** Animal cadastrado com sucesso! ********");
        System.out.println("******** Código atribuído: " + animal.getCodigo() + " ********");
        System.out.println("********************************************************");

        gerenciador.exibirEstatisticas();
    }

    private static void atualizarAnimal(Scanner scanner, Gerenciador gerenciador) {
        if (gerenciador.getQuantidadeAnimais() == 0) {
            System.out.println("Nenhum animal cadastrado. Deseja cadastrar um novo animal? (S/N)");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("S")) {
                cadastrarAnimal(scanner, gerenciador);
            } else {
                System.out.println("Voltando ao menu principal...");
            }
            return;
        }

        gerenciador.listarAnimais();

        System.out.println("Informe o código do animal que deseja atualizar:");
        int codigo = scanner.nextInt();
        Animal animal = gerenciador.buscarAnimalPorCodigo(codigo);

        if (animal != null) {
            System.out.println("\nQual alteração deseja fazer?");
            System.out.println("1 - Alterar código");
            System.out.println("2 - Alterar peso");
            System.out.println("3 - Alterar sexo");
            System.out.println("4 - Sair");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> {
                    System.out.println("Informe o novo código:");
                    animal.setCodigo(scanner.nextInt());
                    System.out.println("Código atualizado com sucesso!");
                }
                case 2 -> {
                    System.out.println("Informe o novo peso:");
                    animal.setPeso(scanner.nextDouble());
                    System.out.println("Peso atualizado com sucesso!");
                }
                case 3 -> {
                    System.out.println("Informe o novo sexo (M/F):");
                    animal.setSexo(scanner.next());
                    System.out.println("Sexo atualizado com sucesso!");
                }
                case 4 -> System.out.println("Saindo da atualização...");
                default -> System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Animal com código " + codigo + " não encontrado.");
        }
    }

    private static void apagarAnimal(Scanner scanner, Gerenciador gerenciador) {
        if (gerenciador.getQuantidadeAnimais() == 0) {
            System.out.println("Nenhum animal cadastrado. Voltando ao menu principal...");
            return;
        }

        gerenciador.listarAnimais();
        System.out.println("Informe o código do animal que deseja apagar:");
        int codigo = scanner.nextInt();
        boolean sucesso = gerenciador.removerAnimal(codigo);

        if (sucesso) {
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Animal com código " + codigo + " não encontrado.");
        }
    }
}

class Gerenciador {
    private List<Animal> animais = new ArrayList<>();
    public boolean codigoExiste(int codigo) {
        return animais.stream().anyMatch(animal -> animal.getCodigo() == codigo);
    }

    public int proximoCodigoDisponivel() {
        int nextCodigo = 1; 
        
        boolean codigoDisponivel = false;
        while (!codigoDisponivel) {
            codigoDisponivel = true; 
            
            for (Animal animal : animais) {
                if (animal.getCodigo() == nextCodigo) { 
                    nextCodigo++; // 
                    codigoDisponivel = false; 
                    break;
                }
            }
        }
        return nextCodigo; 
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }

    public boolean removerAnimal(int codigo) {
        return animais.removeIf(animal -> animal.getCodigo() == codigo);
    }

    public Animal buscarAnimalPorCodigo(int codigo) {
        for (Animal animal : animais) {
            if (animal.getCodigo() == codigo) {
                return animal;
            }
        }
        return null;
    }

    public void listarAnimais() {
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            System.out.println("\nAnimais cadastrados:");
            for (Animal animal : animais) {
                System.out.printf("Código: %d, Peso: %.2f KG, Sexo: %s, Valor: R$ %.2f\n",
                        animal.getCodigo(), animal.getPeso(), animal.getSexo(), animal.getValor());
            }
        }
    }

    public void exibirEstatisticas() {
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado para exibir estatísticas.");
            return;
        }

        int total = animais.size();
        int machos = (int) animais.stream().filter(a -> "M".equalsIgnoreCase(a.getSexo())).count();
        int femeas = total - machos;
        double pesoTotal = animais.stream().mapToDouble(Animal::getPeso).sum();
        double pesoMachos = animais.stream().filter(a -> "M".equalsIgnoreCase(a.getSexo()))
                                   .mapToDouble(Animal::getPeso).sum();
        double pesoFemeas = pesoTotal - pesoMachos;
        double mediaPeso = pesoTotal / total;
        double pesoMaximo = animais.stream().mapToDouble(Animal::getPeso).max().orElse(0);
        double pesoMinimo = animais.stream().mapToDouble(Animal::getPeso).min().orElse(0);

        System.out.printf("\nTotal de animais: %d\n", total);
        System.out.printf("Machos: %d (%.2f%%), Fêmeas: %d (%.2f%%)\n",
                machos, (machos * 100.0) / total, femeas, (femeas * 100.0) / total);
        System.out.printf("Peso total: %.2f KG\n", pesoTotal);
        System.out.printf("Peso de machos: %.2f%%, Peso de fêmeas: %.2f%%\n",
                (pesoMachos * 100.0) / pesoTotal, (pesoFemeas * 100.0) / pesoTotal);
        System.out.printf("Média de peso: %.2f KG\n", mediaPeso);
        System.out.printf("Animal mais pesado: %.2f KG,\nPeso mais leve: %.2f KG\n", pesoMaximo, pesoMinimo);
    }

    public int getQuantidadeAnimais() {
        return animais.size();
    }
}
