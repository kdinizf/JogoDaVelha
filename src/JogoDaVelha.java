import java.util.Scanner;

public class JogoDaVelha {

    static int pontuacaoJogadorO = 0;
    static int pontuacaoJogadorX = 0;
    static int partidasJogadas = 1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] tabuleiro = {{'*', '*', '*'}, {'*', '*', '*'}, {'*', '*', '*'}};

        int qtdPartidas;
        System.out.println("**********JOGO DA VELHA**********");
        System.out.println("Quantas partidas você quer jogar?");
        qtdPartidas = scanner.nextInt();

        while (partidasJogadas <= qtdPartidas) {

            System.out.println("Iniciando Partida!");

            int jogadorDaVez = 0;
            while (true) {

                System.out.println();
                imprimeTabuleiro(tabuleiro);
                System.out.println();

                char jogadorDaVezXOuO = defineJogadorDaVez(jogadorDaVez);

                try {
                    solicitaJogada(tabuleiro, jogadorDaVezXOuO);
                } catch (RuntimeException e) {
                    continue;
                }

                jogadorDaVez++;

                boolean finalJogo = validaFinalDoJogo(tabuleiro);
                if (finalJogo) {
                    break;
                }

            }

        }

        System.out.println();
        System.out.println("Fim Jogo da Velha!");
        System.out.println("Pontuação Jogador O: " + pontuacaoJogadorO);
        System.out.println("Pontuação Jogador X: " + pontuacaoJogadorX);
        System.out.println();

        if (pontuacaoJogadorO > pontuacaoJogadorX) {
            System.out.println("*********** Jogador 0 venceu o jogo ***********");
        }
        if (pontuacaoJogadorX > pontuacaoJogadorO) {
            System.out.println("*********** Jogador X venceu o jogo ***********");
        }
        if (pontuacaoJogadorO == pontuacaoJogadorX) {
            System.out.println("*********** Empate ***********");
        }

    }

    public static void imprimeTabuleiro(char[][] tabuleiro) {

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j]);
                System.out.print(" ");
            }
            System.out.println();

        }

    }

    private static char defineJogadorDaVez(int jogadorDaVez) {

        // Se for par será O
        // Se for ímpar será X

        if (jogadorDaVez % 2 == 0) {
            System.out.println("É a vez do jogador O!");
            return 'O';
        } else {
            System.out.println("É a vez do jogador X!");
            return 'X';
        }

    }

    public static void solicitaJogada(char[][] tabuleiro, char jogador) {

        System.out.println("Informe a linha (1-3):");
        int linha = scanner.nextInt() - 1;

        if (linha < 0 || linha > 2) {
            System.out.println("Posição inválida. Tente novamente!");
            throw new RuntimeException("Posição inválida. Tente novamente!");
        }

        System.out.println("Informe a coluna (1-3):");
        int coluna = scanner.nextInt() - 1;

        if (coluna < 0 || coluna > 2) {
            System.out.println("Posição inválida. Tente novamente!");
            throw new RuntimeException("Posição inválida. Tente novamente!");
        }

        char posicao = tabuleiro[linha][coluna];

        if (posicao != '*') {
            System.out.println("Posição já preenchida. Tente novamente!");
            throw new RuntimeException("Posição já preenchida. Tente novamente!");
        }

        tabuleiro[linha][coluna] = jogador;

    }

    public static boolean validaFinalDoJogo(char[][] tabuleiro) {

        // Linhas

        if (tabuleiro[0][0] == tabuleiro[0][1] && tabuleiro[0][0] == tabuleiro[0][2] && tabuleiro[0][0] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][0]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }
        if (tabuleiro[1][0] == tabuleiro[1][1] && tabuleiro[1][0] == tabuleiro[1][2] && tabuleiro[1][0] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[1][0]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }
        if (tabuleiro[2][0] == tabuleiro[2][1] && tabuleiro[2][0] == tabuleiro[2][2] && tabuleiro[2][0] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[2][0]);
            reiniciaTabuleiro(tabuleiro);
            return true;

        }

        // Colunas

        if (tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[0][0] == tabuleiro[2][0] && tabuleiro[0][0] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][0]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }
        if (tabuleiro[0][1] ==  tabuleiro[1][1] && tabuleiro[0][1] == tabuleiro[2][1] && tabuleiro[0][1] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][1]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }
        if (tabuleiro[0][2] == tabuleiro[1][2] && tabuleiro[0][2] == tabuleiro[2][2] && tabuleiro[0][2] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][2]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }

        // Diagonais

        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[0][0] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][0]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0] && tabuleiro[0][2] != '*') {
            marcaPlacar(tabuleiro, tabuleiro[0][2]);
            reiniciaTabuleiro(tabuleiro);
            return true;
        }

        // Todas as posições preenchidas;

        if (tabuleiro[0][0] != '*' && tabuleiro[0][1] != '*' && tabuleiro[0][2] != '*'
                && tabuleiro[1][0] != '*' && tabuleiro[1][1] != '*' && tabuleiro[1][2] != '*'
                && tabuleiro[2][0] != '*' && tabuleiro[2][1] != '*' && tabuleiro[2][2] != '*') {
            System.out.println();
            imprimeTabuleiro(tabuleiro);
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("Deu velha! Partida reiniciada! ");
            System.out.println("-------------------------------");
            reiniciaTabuleiro(tabuleiro);
            return true;
        }

        return false;

    }

    public static void marcaPlacar(char[][] tabuleiro, char campeao) {

        System.out.println();
        imprimeTabuleiro(tabuleiro);
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("O campeão dessa partida foi " + campeao + "!");
        System.out.println("-------------------------------");

        if (campeao == 'O') {
            pontuacaoJogadorO++;
        } else {
            pontuacaoJogadorX++;
        }
        partidasJogadas++;

    }

    public static void reiniciaTabuleiro(char[][] tabuleiro) {
        tabuleiro[0][0] = '*';
        tabuleiro[0][1] = '*';
        tabuleiro[0][2] = '*';
        tabuleiro[1][0] = '*';
        tabuleiro[1][1] = '*';
        tabuleiro[1][2] = '*';
        tabuleiro[2][0] = '*';
        tabuleiro[2][1] = '*';
        tabuleiro[2][2] = '*';

    }

}


