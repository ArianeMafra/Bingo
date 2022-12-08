import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static final int tamanhoCartela = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#####Seja Bem-Vindo ao Super Bingo!#####\n");
        System.out.println("Vamos cadastrar os jogaores dessa partida...\n");

        System.out.println("Entre com o nome dos jogadores separados por hífen e sem espaços. Exemplo: player1-player2-player3");

        String nomes = scanner.next();
        String[] nomesJogadores = nomes.split("-");

        int[][] cartelas = new int[nomesJogadores.length][tamanhoCartela];

        System.out.println("Selecione o modo de geração das cartelas.");
        manualOuAutomatico();
        int opGeracaoCartela = scanner.nextInt();

        switch (opGeracaoCartela){
            case 1:
                gerarCartelaManual(nomesJogadores, cartelas);
                break;
            case 2:
                gerarCartelaAutomatico(nomesJogadores, cartelas);
                break;
            default:
                System.out.println("Opção inválida :( Tente novamente");
        }

        System.out.println("Os Jogadores e as cartelas são:");
        mostrarJogadoresECartelas(nomesJogadores, cartelas);

        System.out.println("Selecione o modo de geração dos sorteios.");
        manualOuAutomatico();
        int opGeracaoSorteio = scanner.nextInt();

        switch (opGeracaoSorteio){
            case 1:
                //TODO implementar geração manual do sorteio, consultar numeros iguais e pedir nova cartela
                //TODO marcar cartela
                //TODO consultar se alguem fez bingo
                break;
            case 2:
                //TODO implementar geração automatica do sorteio, consultar numeros iguais
                //TODO marcar cartela
                //TODO consultar se alguem fez bingo
                break;
            default:
                System.out.println("Opção inválida :( Tente novamente");
        }
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
    private static int[] gerarCartelaAutomatico(String[] nomesJogadores, int[][] cartelas) {
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
        return cartela;
    }
    private static int[][] gerarCartelaManual(String[] nomesJogadores, int[][] cartelas) {
        int[] cartela = new int[tamanhoCartela];
        System.out.println("Então vamos cadastrar as cartelas do jogadores ... ");
        System.out.printf("Você deve entrar com os %d números sorteados (entre 0 e 60), separados por vírgula e sem espaço." +
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
        return cartelas;
    }
}