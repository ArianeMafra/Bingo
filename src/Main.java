import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static final int tamanhoCartela = 5;
    static final int qntSorteados = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#####Seja Bem-Vindo ao Super Bingo!#####\n");
        System.out.println("Vamos cadastrar os jogadores dessa partida...\n");
        System.out.println("Entre com o nome dos jogadores separados por hífen e sem espaços. Exemplo: player1-player2-player3");

        String nomes = scanner.next();
        String[] nomesJogadores = nomes.split("-");

        int[][] cartelas = new int[nomesJogadores.length][tamanhoCartela];
        int [] sorteados = new int [60];
        for (int i = 0; i > 60; i++) {
            sorteados[i] = 0;
        }

        System.out.println("Selecione o modo de geração das cartelas.");
        manualOuAutomatico();
        int opGeracaoCartela = scanner.nextInt();

        switch (opGeracaoCartela) {
            case 1 -> gerarCartelaManual(nomesJogadores, cartelas);
            case 2 -> gerarCartelaAutomatico(nomesJogadores, cartelas);
            default -> System.out.println("Opção inválida :( Tente novamente");
        }

        System.out.println("Os Jogadores e as cartelas são:");
        mostrarJogadoresECartelas(nomesJogadores, cartelas);

        System.out.println("Selecione o modo de geração dos sorteios.");
        manualOuAutomatico();
        int opGeracaoSorteio = scanner.nextInt();

        switch (opGeracaoSorteio){
            case 1:
                sorteioManual(sorteados);
                //TODO marcar cartela
                //TODO consultar se alguem fez bingo
                break;
            case 2:
                sorteioAutomatico(sorteados);
                sorteioAutomatico(sorteados);


                //TODO implementar geração automatica do sorteio, consultar numeros iguais
                //TODO marcar cartela
                //TODO consultar se alguem fez bingo
                break;
            default:
                System.out.println("Opção inválida :( Tente novamente");
        }
    }

    private static void sorteioAutomatico(int[] sorteados) {
        int[] sorteio = new int[qntSorteados];

        for (int i = 0; i <sorteio.length; i++) {
            for (int k = 0; k < i; k++ ) {
                sorteio[i] = random.nextInt(1, 60);
                if (sorteio[i] == sorteio[k]) {
                    i--;
                }else {
                    for (int j = 0; j < sorteados.length; j++) {
                        if (sorteio[i] == sorteados[j]) {
                            i--;
                        } else {
                            sorteados[sorteio[i] - 1] = sorteio[i];
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(sorteio));


        System.out.println(Arrays.toString(sorteados));
    }

    private static void novoSorteioManual(int []sorteados) {
        System.out.println("Deseja fazer um novo sorteio?");
        System.out.println("1 - SIM");
        System.out.println("2 - SAIR DO JOGO");
        System.out.println("Digite o número da opção desejada.");

        int opNovoSorteio = scanner.nextInt();
        switch (opNovoSorteio) {
            case 1 -> sorteioManual(sorteados);
            case 2 -> System.out.println("Você saiu do jogo!");
            default -> System.out.println("Opção inválida :( Tente novamente");
        }
    }
    private static void sorteioManual(int [] sorteados) {
        int [] sorteio = new int[qntSorteados];
        System.out.printf("Você deve entrar com os %d números sortedos (entre 0 e 60), separados por vírgula e sem espaço." +
                "\nExemplo: 20,26,52,40,2" +
                "\nOBS.: Os números não devem se repetir.\n\n", qntSorteados);

        String numeros = scanner.next();
        String[] sorteadosManual = numeros.split(",");

        for (int i = 0; i < sorteadosManual.length; i++) {
            sorteio[i] = Integer.parseInt(sorteadosManual[i]);
            sorteados[sorteio[i] - 1] = sorteio[i];
        }

        System.out.println(Arrays.toString(sorteados));
        novoSorteioManual(sorteados);
    }

    private static void mostrarJogadoresECartelas(String[] nomesJogadores, int[][] cartelas) {
        for (int i = 0; i <nomesJogadores.length; i++){
            System.out.print("Jogador: " + nomesJogadores[i]);
            System.out.println(" => Cartela: " + Arrays.toString(cartelas[i]));
        }
    }
    private static void manualOuAutomatico() {
        System.out.println("1 - MANUAL");
        System.out.println("2 - AUTOMÁTICO");
        System.out.println("Digite o número da opção desejada.");
    }
    private static void gerarCartelaAutomatico(String[] nomesJogadores, int[][] cartelas) {
        int[] cartela = new int[tamanhoCartela];

        for (int i = 0; i <nomesJogadores.length; i++) {
            for (int j = 0; j < cartela.length; j++) {
                cartela[j] = random.nextInt(1, 60);
                for (int k = 0; k < j; k++ ) {
                    if (cartela[j] == cartela[k]) {
                        j--;
                    }
                }
                cartelas[i][j] = cartela[j];
            }
        }
    }
    private static void gerarCartelaManual(String[] nomesJogadores, int[][] cartelas) {
        int[] cartela = new int[tamanhoCartela];
        System.out.println("Então vamos cadastrar as cartelas do jogadores ... ");
        System.out.printf("Você deve entrar com os %d números da cartela (entre 0 e 60), separados por vírgula e sem espaço." +
                "\nExemplo: 20,26,52,40,2" +
                "\nOBS.: Os números não devem se repetir.\n\n", tamanhoCartela);

        for (int i = 0; i <nomesJogadores.length; i++) {
            System.out.printf("Entre com os números sorteados para o Jogador %s: \n", nomesJogadores[i]);
            String numeros = scanner.nextLine();
            String[] cartelaManual = numeros.split(",");

            for (int j = 0; j < cartelaManual.length; j++){
                cartela[j] = Integer.parseInt(cartelaManual[j]);
                cartelas[i][j] = cartela[j];
            }
        }
    }
}
