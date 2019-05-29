package com.codecool.bromberman;
import com.codecool.termlib.*;
import java.util.Scanner;


public class Game {
    static char[][] map = new char[67][64];
    static Scanner userInput = new Scanner(System.in);
    static int currentY = 5;
    static int currentX = 5;
    public static void moveChar() {
        char direction = userInput.next().charAt(0);
        if (direction == 'd') {
            Terminal.moveTo(currentY, currentX);
            characterPlacement(map, currentY, currentX + 8);
        }
    }

    public static void characterPlacement(char[][] board, int yIndex, int xIndex) {

        Terminal.moveTo(yIndex, xIndex);
        System.out.println(" [ ^ ^ ] ");
        Terminal.moveTo(yIndex+1, xIndex+1);
        System.out.println("  - -  ");
        Terminal.moveTo(yIndex+2, xIndex+1);
        System.out.println("  ] [  ");
        Terminal.moveTo(72, 1);



      /*
        board[yIndex][xIndex] = '[';
        board[yIndex][xIndex+1] = '^';
        board[yIndex][xIndex+2] = '^';
        board[yIndex][xIndex+3] = ']';
        board[yIndex+1][xIndex+1] = '-';
        board[yIndex+1][xIndex+2] = '-';
        board[yIndex+2][xIndex+1] = ']';
        board[yIndex+2][xIndex+2] = '[';
    */
  }

    public static char[][] buildBoard() {

        //Fill the border of our board with '▅'s
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++ ) {
                if (y == 0 || y == 1 || y == 65 || y == 66) {
                    map[y][x] = '▅';
                } else if (x == 0 || x == 1 || x == 62 || x == 63) {
                    map[y][x] = '▅';
                } else {
                    map[y][x] = ' ';
                }
            }
        }
        //Fill the inner parts of the board with non-destructable elements
        for (int y = 5; y < map.length-2; y += 5) {
            for (int x = 6; x < map.length-6; x += 8) {
                map[y][x] = '▅';
                map[y][x+1] = '▅';
                map[y][x+2] = '▅';
                map[y][x+3] = '▅';
                map[y+1][x] = '▅';
                map[y+1][x+1] = '▅';
                map[y+1][x+2] = '▅';
                map[y+1][x+3] = '▅';
            }
        }

        return map;
    }

    //Draw the actual board
    public static void drawBoard() {
        Terminal.clearScreen();
        for (int y = 0; y < buildBoard().length; y++) {
            for (int x = 0; x < buildBoard()[y].length; x++) {
                System.out.print(" " + buildBoard()[y][x]);
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        drawBoard();
        characterPlacement(map, currentY, currentX);
        moveChar();
        try {
        Thread.sleep(1000);
      } catch(InterruptedException i) {}
    }
}
