package com.codecool.bromberman;
import com.codecool.termlib.*;
import java.util.Scanner;
import java.io.*;


public class Game {
    static char[][] map = new char[67][125];
    static Scanner userInput = new Scanner(System.in);
    static int currentY = 3;
    static int currentX = 5;
    private static boolean playing = true;

    public static void moveChar() {
        char direction = '0';
        Terminal.rawModeNoEcho();
        while (playing) {

            direction = readInput();
            if (direction == 'd') {
                if (!checkBlock(direction, currentY, currentX)) {
                    clearCharacter(currentY, currentX);
                    Terminal.moveTo(currentY, currentX);
                    characterPlacement(map, currentY, currentX + 1);
                    System.out.print(map[currentY][currentX]);
                }
            } else if (direction == 'a') {
                if (!checkBlock(direction, currentY, currentX)) {
                    clearCharacter(currentY, currentX);
                    Terminal.moveTo(currentY, currentX);
                    characterPlacement(map, currentY, currentX - 1);
                }
            } else if (direction == 'w') {
                if (!checkBlock(direction, currentY, currentX)) {
                    clearCharacter(currentY, currentX);
                    Terminal.moveTo(currentY, currentX);
                    characterPlacement(map, currentY - 1, currentX);
                }
            } else if (direction == 's') {
                if (!checkBlock(direction, currentY, currentX)) {
                    clearCharacter(currentY, currentX);
                    Terminal.moveTo(currentY, currentX);
                    characterPlacement(map, currentY + 1, currentX);
                }
            }
            else if (direction == 'x') {
                playing = false;
            }
        }
        Terminal.cookedModeEcho();
    }
    //Reads terminal input
    public static char readInput(){
       try{
         if(System.in.available() > 0){
           return (char) System.in.read();
         }
       }catch(IOException e){
         System.out.print("hibaaaaa");
       }
         return 'v';
     }


    public static boolean checkBlock(char direction, int yIndex, int xIndex) {
        if (direction == 'd') {
            if (map[yIndex-1][xIndex+6] == '▅' || map[yIndex][xIndex+6] == '▅'
            || map[yIndex+1][xIndex+6] == '▅') {
                return true;
            }
    }
        else if (direction == 'a') {
            if (map[yIndex-1][xIndex-2] == '▅' || map[yIndex][xIndex-2] == '▅'
            || map[yIndex+1][xIndex-2] == '▅') {
                return true;
            }
        }
        else if (direction == 'w') {
            //System.out.print(" " + yIndex + " ");
            //System.out.print(" " + xIndex + " ");
                if (map[yIndex-2][xIndex-1] == '▅' || map[yIndex-2][xIndex] == '▅'
                || map[yIndex-2][xIndex+1] == '▅' || map[yIndex-2][xIndex+2] == '▅'
                || map[yIndex-2][xIndex+3] == '▅' || map[yIndex-2][xIndex+4] == '▅'
                || map[yIndex-2][xIndex+5] == '▅') {
                    return true;
                }
            }
            else if (direction == 's') {
                //System.out.print(" " + yIndex + " ");
                //System.out.print(" " + xIndex + " ");
                    if (map[yIndex+2][xIndex-1] == '▅' || map[yIndex+2][xIndex] == '▅'
                    || map[yIndex+2][xIndex+1] == '▅' || map[yIndex+2][xIndex+2] == '▅'
                    || map[yIndex+2][xIndex+3] == '▅' || map[yIndex+2][xIndex+4] == '▅'
                    || map[yIndex+2][xIndex+5] == '▅') {
                        return true;
                    }
                }
     return false;
}

    public static void characterPlacement(char[][] board, int yIndex, int xIndex) {
        currentY = yIndex;
        currentX = xIndex;
        Terminal.moveTo(yIndex, xIndex);
        System.out.print("[ ^ ^ ]");
        Terminal.moveTo(yIndex+1, xIndex+1);
        System.out.print(" - - ");
        Terminal.moveTo(yIndex+2, xIndex+1);
        System.out.print(" ] [ ");
        currentY = yIndex;
        currentX = xIndex;
        //System.out.print(currentY + " ");
        //System.out.print(currentX);

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

    public static void clearCharacter(int yIndex, int xIndex) {
        Terminal.moveTo(yIndex, xIndex);
        System.out.println("       ");
        Terminal.moveTo(yIndex+1, xIndex+1);
        System.out.println("     ");
        Terminal.moveTo(yIndex+2, xIndex+1);
        System.out.println("     ");
        //Terminal.moveTo(72, 1);
    }

    public static char[][] buildBoard() {

        //Fill the border of our board with '▅'s
        for (int y = 0; y < map.length ; y++) {
            for (int x = 0; x < map[y].length; x++ ) {
                if (y == 0 || y == 1 || y == 65 || y == 66) {
                    map[y][x] = '▅';
                } else if (x == 0 || x == 1 || x == 2 || x == 3 || x == 121 || x == 122 || x == 123 || x == 124) {
                    map[y][x] = '▅';
                } else {
                    map[y][x] = ' ';
                }
            }
        }
        //Fill the inner parts of the board with indestructable elements
        for (int y = 5; y < map.length-2; y += 5) {
            for (int x = 11; x < map[y].length-6; x += 11) {
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

    //Fill the map with random destructable elements
    public static void destructableWalls() {
        int randomY = (int) (Math.random() * 67);
        /*        67*125
                map[32][60] = '▆' + ' ';
                map[32][61] = '▆' + ' ';
                map[32][62] = '▆' + ' ';
                map[32][63] = '▆' + ' ';
                map[33][60] = '▆' + ' ';
                map[33][61] = '▆' + ' ';
                map[33][62] = '▆' + ' ';
                map[33][63] = '▆' + ' ';
                map[34][60] = '▆' + ' ';
                map[34][61] = '▆' + ' ';
                map[34][62] = '▆' + ' ';
                map[34][63] = '▆' + ' ';

                map[32][66] = '▆' + ' ';
                map[32][67] = '▆' + ' ';
                map[32][68] = '▆' + ' ';
                map[32][69] = '▆' + ' ';
                map[33][66] = '▆' + ' ';
                map[33][67] = '▆' + ' ';
                map[33][68] = '▆' + ' ';
                map[33][69] = '▆' + ' ';
                map[34][66] = '▆' + ' ';
                map[34][67] = '▆' + ' ';
                map[34][68] = '▆' + ' ';
                map[34][69] = '▆' + ' ';

        */
    }


    //Draw the actual board
    public static void drawBoard() {
        Terminal.clearScreen();
        Terminal.moveTo(1, 1);
        for (int y = 0; y < buildBoard().length; y++) {
            for (int x = 0; x < buildBoard()[y].length; x++) {
                System.out.print(buildBoard()[y][x]);
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        Terminal.cursorInvisible();
        drawBoard();
        //destructableWalls();
        characterPlacement(map, currentY, currentX);
        while (playing==true) {
          moveChar();
        }
        Terminal.getCursorBack();
    }
}
