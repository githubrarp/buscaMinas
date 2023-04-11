import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int tries = 5;
    public static ArrayList userMoves = new ArrayList();
    public static ArrayList userMovesMatched = new ArrayList();
    public static int[][] board = new int[5][5];// = createBoard();



    public static void main(String[] args) {

        String userInput;
        ArrayList minedCells = new ArrayList();


        minedCells = generateMinedCells();
        System.out.println(minedCells);

        mineBoard(minedCells, board);
//        drawBoard(board);

        do{
            userInput = getUserInput();
            executeUserMove(userInput, userMoves);
            validateUserMove(minedCells, userMoves);
//            System.out.println("SIZE DEL USER MOVES IS: " + userMoves.size());
//            drawBoard(board);
            /*
            if(userMoves.size() == 16){
                System.out.println("YOU WON!");
                System.exit(0);
            }*/
        } while (tries > 0);

        if(userMovesMatched.size()==5){
            System.out.println("You are dead!");
            System.exit(0);
        }

        System.out.println("GAME OVER!!!");

    }

    private static void validateUserMove(ArrayList minedCells, ArrayList userMoves) {

        for (Object move: userMoves
             ) {

            for (Object mined: minedCells
            ) {
                if (!move.equals(mined)){
                    board[Integer.valueOf(move.toString().substring(0,1))][Integer.valueOf(move.toString().substring(1,2))] = 1;

                }
//                if(!mined.equals(move)){
//                    board[Integer.valueOf(move.toString().substring(0,1))][Integer.valueOf(move.toString().substring(1,2))] = 1;
  //              }else
                    if (mined.equals(move) && !userMovesMatched.contains(move)){
                    board[Integer.valueOf(move.toString().substring(0,1))][Integer.valueOf(move.toString().substring(1,2))] = 9;
                    System.out.println("BOOM * BOOM * BOOM * BOOM * BOOM * BOOM : " + move);
                    userMovesMatched.add(move);
                    tries--;
                    System.out.println("Lo que tenemos en MOVE es: " + move);
//                } else{
                    }
            }
        }
        drawBoard(board);
    }

    private static void executeUserMove(String userInput, ArrayList userMoves){

        if (userMoves.contains(userInput)){
            System.out.println("Move invalid, try again.");
        }else {
            userMoves.add(userInput);
        }

        System.out.println("Moves log:");

        for (Object move: userMoves) {
            System.out.println(move);
        }

    }

    private static String getUserInput() {
        String userInput = "";
        boolean entryIsValid = true;

        do{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduzca coordenada: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.exit(0);
            }else if (userMoves.contains(userInput)) {
                entryIsValid = false;
                System.out.println("You already tried this coordinate. Please try again...");
            }else {
                entryIsValid = true;
            }
        }while (!entryIsValid);

        return userInput;

    }

    private static void mineBoard(ArrayList minedCells, int[][] board) {
        minedCells.forEach((cel)->{
            int x = Integer.valueOf(cel.toString().substring(0,1));
            int y = Integer.valueOf(cel.toString().substring(1,2));
            board[x][y] = 0;
        });
    }

    private static void drawBoard(int[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    public static ArrayList generateMinedCells(){

        Random random = new Random();
        int x_cell; int y_cell;
        ArrayList minedCells = new ArrayList();

        while (minedCells.size() < 5){
            x_cell = random.nextInt(0,5);
            y_cell = random.nextInt(0,5);
            String string_xy = String.valueOf(x_cell) + String.valueOf(y_cell);

            if(!minedCells.contains(string_xy)){
                minedCells.add(string_xy);
            }

        }
        return minedCells;
    }
}