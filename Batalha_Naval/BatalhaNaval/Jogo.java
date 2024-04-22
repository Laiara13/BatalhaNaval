package BatalhaNaval;

import java.util.Scanner;
import java.util.Random;

public class Jogo {
    public static void main(String[] args) throws InterruptedException{
        Scanner ler = new Scanner(System.in);
        char[][] oceano1 = new char[10][10];
        char[][] oceano2 = new char[10][10];
        CriarOceano(oceano1, oceano2);
        int opcao;
        System.out.println("\t Batalha Naval");
        System.out.println("\n(1) Jogador vs Jogador");
        System.out.println("(2) Jogador vs Máquina\n");
        for(opcao=0; opcao!=1 && opcao!=2;){
            System.out.print("Opcao: ");
            opcao = ler.nextInt();
        }
        int player=1;
        boolean rob = false, again;
        switch(opcao){
            case 1->{
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n----------------- Jogador 1 -----------------\n");
                carregarMapa(oceano1);
                ColocarBarcos(oceano1, rob);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n----------------- Jogador 2 -----------------\n");
                carregarMapa(oceano2);
                ColocarBarcos(oceano2, rob);

                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                boolean winner = false;
                for(;winner!=true;){
                    again=true;
                    for(;again==true && winner==false;){
                        System.out.println("\n\n\n\n Jogador 1 Atacando:\n");
                        carregarMapa(oceano2);
                        again = atacar(oceano2, rob);
                        player = 1;
                        winner = testeWin(oceano2);
                    }
                    if(winner!=true){
                        again=true;
                        for(;again==true && winner==false;){
                            System.out.println("\n\n\n\n Jogador 2 Atacando:\n");
                            carregarMapa(oceano1);
                            again = atacar(oceano1, rob);
                            player = 2;
                            winner = testeWin(oceano1);
                        }
                    }
                }
                win(player, rob);
            }
            case 2->{
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------- Jogador 1 -----------------\n");
                carregarMapa(oceano1);
                ColocarBarcos(oceano1, rob);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n-------------------- Maquina -------------------\n");
                rob = true;
                carregarMapa(oceano2);
                ColocarBarcos(oceano2, rob);
                System.out.println("\n\t Maquina colocando barcos");
                Thread.sleep(5000);

                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                boolean winner = false;
                for(;winner!=true;){
                    rob = false;
                    again = true;
                    for(;again==true && winner == false;){
                        System.out.println("\n\n\n\n\n\n\n\n Jogador 1  Atacando:\n");
                        carregarMapa(oceano2);
                        again = atacar(oceano2, rob);
                        player = 1;
                        winner = testeWin(oceano2);
                    }
                    if(winner!=true){
                        again = true;
                        rob = true;
                        for(;again==true && winner==false;){
                            System.out.println("\n\n\n\n\n\n\n\n Maquina Atacando:\n");
                            carregarMapa(oceano1);
                            System.out.println("Maquina está jogando");
                            again = atacar(oceano1, rob);
                            player = 2;
                            winner = testeWin(oceano1);
                        }
                    }
                }
                win(player, rob);
            }
        }
    }

    public static void win(int player, boolean rob){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if(rob == false)
            System.out.println("-------Jogador "+player+" ganhou-------");
        else
            System.out.println("---------Maquina ganhou ---------");
    }

    public static boolean testeWin(char[][] mar){
        int cont=0;
        for(int l=0; l<mar.length; l++){
            for(int c=0; c<mar[l].length; c++){
                if(mar[l][c] == 'B')
                    cont++;
            }
        }
        if(cont>0)
            return false;
        else
            return true;
    }

    public static boolean atacar(char[][] mar, boolean rob) throws InterruptedException{
        Random ale = new Random();
        Scanner ler = new Scanner(System.in);
        int x, y;
        boolean again = false;
        x=-1;
        y=-1;
        if(rob==false){
            for(;x<0 || x>9;){
                System.out.print(" - x: ");
                x = ler.nextInt();
            }
            for(;y<0 || y>9;){
                System.out.print(" - y: ");
                y = ler.nextInt();
            }
            if(mar[y][x] == 'B'){
                mar[y][x] = 'b';
                again = true;
                System.out.println("\nAcertou, jogue novamente\n");
            }
            else if(mar[y][x] == 'A'){
                mar[y][x] = 'a';
                System.out.println("\nErrou, vez de outro jogador");
            }
            else{
                System.out.println("\nlugar já atacado, Jogue novamente\n");
                again = true;
            }
            Thread.sleep(2500);
        }
        else{
            x = ale.nextInt(0, 10);
            y = ale.nextInt(0, 10);
            if(mar[y][x] == 'B'){
                mar[y][x] = 'b';
                again = true;
                Thread.sleep(1500);
                System.out.println("\nAcertou, Maquina joga novamente\n");
                Thread.sleep(3000);
            }
            else if(mar[y][x] == 'A'){
                mar[y][x] = 'a';
                Thread.sleep(1500);
                System.out.println("\nErrou, sera a vez de Jogador 1");
                Thread.sleep(3000);
            }
            else{
                again = true;
            }
        }
        return again;
    }


    public static void CriarOceano(char[][] oceano1, char[][] oceano2){
        for(int l=0; l<oceano1.length; l++){
            for(int c=0; c<oceano1[l].length; c++){
                oceano1[l][c] = '~';
            }
        }
        for(int l=0; l<oceano2.length; l++){
            for(int c=0; c<oceano2[l].length; c++){
                oceano2[l][c] = '~';
            }
        }
    }

    public static void carregarMapa(char[][] mar){
        System.out.println("     0   1   2   3   4   5   6   7   8   9  ");
        System.out.println("--------------------------------------------");
        String linha;
        for(int l=0; l<mar.length; l++){
            linha = "";
            linha += String.format(" %d | ", l);
            for(int c=0; c<mar[l].length; c++){
                if(mar[l][c] == 'A' || mar[l][c] == 'B')
                    linha += String.format("  | ");
                else if(mar[l][c]=='a'){
                    linha += String.format("º | ");
                }
                else
                    linha += String.format("X | ");
            }
            System.out.println(linha);
            System.out.println("--------------------------------------------");
        }
    }

    public static void ColocarBarcos(char[][] mar, boolean rob){
        Scanner ler = new Scanner(System.in);
        Random ale = new Random();
        boolean hitbox;
        boolean again;
        int[] propriedadesBarcos = {4, 3, 2, 1};
        int x, y;
        char direcao;
        for(int i=propriedadesBarcos.length-1; i>=0; i--){
            for(int j=1; j<=propriedadesBarcos[i]; j++){
                again = false;
                hitbox = false;
                for(;hitbox==false;){
                    x=-1;
                    y=-1;
                    direcao = 'e';
                    if(again==true && rob==false)
                        System.out.println("\nNão é possível alocar o barco\n");
                    for(;direcao != 'v' && direcao != 'h' && rob==false && i>0;){
                        System.out.print("De a direção horizontal(h) e vertical(v) do "+j+"º barco com "+(i+1)+" espaços: ");
                        direcao = ler.next().toLowerCase().charAt(0);
                    }
                    if(i==0)
                        direcao = 'v';
                    if(rob==false)
                        System.out.println(" Selecione um lugar para colocar o "+j+"º barco de "+(i+1)+" espaços: ");
                    if(rob==false){
                        for(;x<0 || x>9;){
                            System.out.print("   - x: ");
                            x = ler.nextInt();
                        }
                        for(;y<0 || y>9;){
                            System.out.print("   - y: ");
                            y = ler.nextInt();
                        }
                    }
                    if(rob==true){
                        x = ale.nextInt(0, 10);
                        y = ale.nextInt(0, 10);
                        int d = ale.nextInt(0, 2);
                        if(d==0)
                            direcao = 'v';
                        else
                            direcao = 'h';
                    }
                    switch(direcao){
                        case 'v' ->{
                            if(y+i <= 9){
                                hitbox = true;
                                for(int t=y; t<=(y+i); t++){
                                    if(mar[t][x]!='~' && mar[t][x] != 'a'){
                                        hitbox = false;
                                        again = true;
                                    }
                                }
                                if(hitbox==true){
                                    for(int l=y; l<=(y+i); l++)
                                        mar[l][x] = 'B';
                                }
                            }
                            else{
                                again = true;
                            }
                        }
                        case 'h' ->{
                            if(x+i <= 9){
                                hitbox = true;
                                for(int t=x; t<=(x+i); t++){
                                    if(mar[y][t]!='~' && mar[y][t] != 'a'){
                                        hitbox = false;
                                        again = true;
                                    }
                                }
                                if(hitbox==true){
                                    for(int c=x; c<=(x+i); c++)
                                        mar[y][c] = 'B';
                                }
                            }
                            else{
                                again = true;
                            }
                        }
                    }
                }
            }
        }
    }
}