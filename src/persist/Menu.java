package persist;


import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientePersist clientePersist         = new ClientePersist();
        ConsultaPersist consultaPersist       = new ConsultaPersist();
        PetPersist petPersist                 = new PetPersist();
        VeterinarioPersist veterinarioPersist = new VeterinarioPersist();
        while (true) {
            exibirMenuPrincipal();

            int escolhaPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaPrincipal) {
                case 1:
                    menuCliente(clientePersist);
                    break;
                case 2:
                    menuPet(petPersist);
                    break;
                case 3:
                    //menuConsulta(consultaPersist);
                    break;
                case 4:
                    menuVeterinario(veterinarioPersist);
                    break;
                case 5:
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void menuCliente(ClientePersist clientePersist) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenuOperacoesCliente();

            int escolhaCliente = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaCliente) {
                case 1:
                    clientePersist.cadastrarNovoCliente();
                    break;
                case 2:
                    clientePersist.consultarCliente();
                    break;
                case 3:
                    clientePersist.atualizarCliente();
                    break;
                case 4:
                    clientePersist.excluirCliente();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void menuPet(PetPersist petPersist) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenuOperacoesPet();

            int escolhaPet = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaPet) {
                case 1:
                    petPersist.cadastrarNovoPet();
                    break;
                case 2:
                    petPersist.consultarPet();
                    break;
                case 3:
                    petPersist.atualizarPet();
                    break;
                case 4:
                    petPersist.excluirPet();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void menuConsulta(ConsultaPersist consultaPersist) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenuOperacoesConsulta();

            int escolhaConsulta = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaConsulta) {
                case 1:
                    //consultaPersist.cadastrarNovaConsulta();
                    break;
                case 2:
                    //consultaPersist.listarConsultas();
                    break;
                case 3:
                    //consultaPersist.excluirConsulta();
                    break;
                case 5:
                    return; // Voltar ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void menuVeterinario(VeterinarioPersist veterinarioPersist) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenuOperacoesVeterinario();

            int escolhaVeterinario = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaVeterinario) {
                case 1:
                    veterinarioPersist.cadastrarNovoVeterinario();
                    break;
                case 2:
                    veterinarioPersist.consultarVeterinario();
                    break;
                case 3:
                    veterinarioPersist.atualizarVeterinario();
                    break;
                case 4:
                    veterinarioPersist.excluirVeterinario();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    private static void exibirMenuPrincipal() {
        System.out.println("\n### MENU PRINCIPAL ###");
        System.out.println("1 - Operações com Cliente");
        System.out.println("2 - Operações com Pet");
        System.out.println("3 - Operações com Consulta");
        System.out.println("4 - Operações com Veterinário");
        System.out.println("5 - Sair");
        System.out.println("Escolha uma opção:");
    }

    private static void exibirMenuOperacoesCliente() {
        System.out.println("\n### MENU CLIENTE ###");
        System.out.println("1 - Cadastrar Novo Cliente");
        System.out.println("2 - Consultar Cliente por Nome");
        System.out.println("3 - Atualizar Cliente");
        System.out.println("4 - Excluir Cliente");
        System.out.println("5 - Voltar ao Menu Principal");
        System.out.println("Escolha uma opção:");
    }

    private static void exibirMenuOperacoesPet() {
        System.out.println("\n### MENU PET ###");
        System.out.println("1 - Cadastrar Novo Pet");
        System.out.println("2 - Consultar Pet por Nome");
        System.out.println("3 - Atualizar Pet");
        System.out.println("4 - Excluir Pet");
        System.out.println("5 - Voltar ao Menu Principal");
        System.out.println("Escolha uma opção:");
    }

    private static void exibirMenuOperacoesConsulta() {
        System.out.println("\n### MENU CONSULTA ###");
        System.out.println("1 - Cadastrar Nova Consulta");
        System.out.println("2 - Listar Consultas do Cliente");
        System.out.println("3 - Excluir Consulta");
        System.out.println("5 - Voltar ao Menu Principal");
        System.out.println("Escolha uma opção:");
    }

    private static void exibirMenuOperacoesVeterinario() {
        System.out.println("\n### MENU VETERINÁRIO ###");
        System.out.println("1 - Cadastrar Novo Veterinário");
        System.out.println("2 - Consultar Veterinário por Nome");
        System.out.println("3 - Atualizar Veterinário");
        System.out.println("4 - Excluir Veterinário");
        System.out.println("5 - Voltar ao Menu Principal");
        System.out.println("Escolha uma opção:");
    }
}
