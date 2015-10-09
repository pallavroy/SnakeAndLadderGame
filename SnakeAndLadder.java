import java.util.Scanner;

/**
 * Created by Pallav on 9/10/2015.
 */
public class SnakeAndLadder {

    static int boardSize;
    static String[] playerNames;
    static int numberPlayers;
    static int numberSnakes;
    static int numberLadders;
    static int[] snakeDescend;
    static int[] ladderRise;
    static int[] playerPosition;
    static boolean gameOver;

    public static void main(String[] args) {
        //intialize the board parameters
        init();

        //take input from the players before starting the game
        input();

        //the gameplay begins from here
        System.out.println();
        System.out.println();
        System.out.println("***** Game starts *****");
        play();
    }

    static void init(){
        boardSize = 100;
        snakeDescend = new int[boardSize+5];
        ladderRise = new int[boardSize+5];
    }

    static void input(){
        Scanner sc = new Scanner(System.in);

        //input the number of players in the game
        System.out.println("Enter the number of players:");
        numberPlayers = sc.nextInt();

        //input player names
        System.out.println();
        System.out.println("Enter the names of players:");
        playerNames = new String[numberPlayers+5];
        for(int it=0; it<numberPlayers; it++){
            playerNames[it] = sc.next();
        }
        //display a welcome message to players
        System.out.println();
        for(int it=0; it<numberPlayers; it++){
            System.out.println("Welcome " + playerNames[it] + "!");
        }

        //set players position to 0
        playerPosition = new int[numberPlayers+5];
        for(int it=0; it<numberPlayers; it++){
            playerPosition[it] = 0;
        }

        //input the number of snakes on the board
        System.out.println();
        System.out.println("Enter the number of snakes:");
        numberSnakes = sc.nextInt();
        System.out.println();
        System.out.println("Enter the head and tail of the snakes:");
        int head;
        int tail;
        for(int it=0; it<numberSnakes; it++){
            head = sc.nextInt();
            tail = sc.nextInt();
            snakeDescend[head] = tail;
        }

        //input the number of ladders
        System.out.println();
        System.out.println("Enter the number of ladders:");
        numberLadders = sc.nextInt();
        int top;
        int bottom;
        System.out.println();
        System.out.println("Enter the position of ladders:");
        for(int it=0; it<numberLadders; it++){
            bottom = sc.nextInt();
            top = sc.nextInt();
            ladderRise[bottom] = top;
        }
    }

    static void play(){
        int turn = 0;
        int dice;
        Scanner sc = new Scanner(System.in);

        while(!gameOver){
            System.out.println();
            System.out.println("Enter the dice roll of " + playerNames[turn] + ":");
            dice = sc.nextInt();

            //check if a valid dice roll
            if(dice>=1 && dice<=6){
                if(playerPosition[turn] + dice <= boardSize){
                    int newPos = playerPosition[turn] + dice;

                    //check if a ladder or snake
                    if(snakeDescend[newPos] != 0){
                        System.out.println(playerNames[turn] + " encounters snake " + newPos + "," + snakeDescend[newPos] + " !");
                        newPos = snakeDescend[newPos];
                    }
                    else if(ladderRise[newPos] != 0){
                        System.out.println(playerNames[turn] + " gets ladder " + newPos + "," + ladderRise[newPos] + " !");
                        newPos = ladderRise[newPos];
                    }

                    //set new position of player [turn]
                    playerPosition[turn] = newPos;
                    displayboard();
                }
                else {
                    System.out.println();
                    System.out.println("Out of board!\n" + "Try again in next turn.");
                }
                turn = (turn + 1)%numberPlayers;
            }
            else {
                System.out.println();
                System.out.println("Invalid throw.\n" + "Roll again...");
            }
        }
    }

    static void displayboard(){
        gameOver = false;
        int winner = -1;
        System.out.println("Positions:");
        for(int it=0; it<numberPlayers; it++){
            System.out.println(playerNames[it] + " -> " + playerPosition[it]);
            if(playerPosition[it] == boardSize){
                gameOver = true;
                winner = it;
            }
        }
        if(gameOver){
            System.out.println();
            System.out.println("congratulations " + playerNames[winner] + ".\n" + "You have won the game!!");
        }
    }
}
