package com.codecool.bromberman;
import com.codecool.termlib.*;
import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.util.Arrays;


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
            } else if (direction == 'b') {

                activateBomb();
                try {

                      Thread.sleep(2000);
                      deactivateBomb();
                  } catch(InterruptedException i) {}

            } else if (direction == 'x') {
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
        } else if (direction == 'a') {
            if (map[yIndex-1][xIndex-2] == '▅' || map[yIndex][xIndex-2] == '▅'
            || map[yIndex+1][xIndex-2] == '▅') {
                return true;
            }
        } else if (direction == 'w') {
            //System.out.print(" " + yIndex + " ");
            //System.out.print(" " + xIndex + " ");
                if (map[yIndex-2][xIndex-1] == '▅' || map[yIndex-2][xIndex] == '▅'
                || map[yIndex-2][xIndex+1] == '▅' || map[yIndex-2][xIndex+2] == '▅'
                || map[yIndex-2][xIndex+3] == '▅' || map[yIndex-2][xIndex+4] == '▅'
                || map[yIndex-2][xIndex+5] == '▅') {
                    return true;
                }
            } else if (direction == 's') {
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
        Terminal.setColor(Color.CYAN_LETTER);
        Terminal.moveTo(yIndex, xIndex);
        System.out.print("[ ^ ^ ]");
        Terminal.moveTo(yIndex+1, xIndex+1);
        System.out.print(" - - ");
        Terminal.moveTo(yIndex+2, xIndex+1);
        System.out.print(" ] [ ");
        Terminal.resetStyle();
        currentY = yIndex;
        currentX = xIndex;
    }

    public static void bombPlacement() {
            for (int i = 33; i < 36; i++) {
                Terminal.moveTo(i, 63);
                System.out.println("XXXX");
            }
        }

    public static void activateBomb() {
        //activate the bomb vertical
        for (int i = 28; i < 41; i++) {
            Terminal.moveTo(i, 63);
            System.out.println("XXXX");
        }
        //active the bomb horizontal
        for (int i = 50; i < 76; i++) {
            Terminal.moveTo(33, i);
            System.out.println("XXXX");
            Terminal.moveTo(34, i);
            System.out.println("XXXX");
            Terminal.moveTo(35, i);
            System.out.println("XXXX");
        }
    }

    public static void deactivateBomb() {
        //deactivate the bomb vertical
        for (int i = 28; i < 41; i++) {
            Terminal.moveTo(i, 63);
            System.out.println("    ");
        }
        //deactive the bomb horizontal
        for (int i = 50; i < 76; i++) {
            Terminal.moveTo(33, i);
            System.out.println("    ");
            Terminal.moveTo(34, i);
            System.out.println("    ");
            Terminal.moveTo(35, i);
            System.out.println("    ");
        }
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
        for (int y = 5; y < map.length-2; y += 6) {
            for (int x = 11; x < map[y].length-6; x += 11) {
                map[y][x] = '▅';
                map[y][x+1] = '▅';
                map[y][x+2] = '▅';
                map[y][x+3] = '▅';
                map[y+1][x] = '▅';
                map[y+1][x+1] = '▅';
                map[y+1][x+2] = '▅';
                map[y+1][x+3] = '▅';
                map[y+2][x] = '▅';
                map[y+2][x+1] = '▅';
                map[y+2][x+2] = '▅';
                map[y+2][x+3] = '▅';
            }
        }

        //destructableWalls(map);
        /*Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int randomY = (random.nextInt(21) * 3) + 2;
            int randomX = (random.nextInt(11) * 11) + 5;
            if (map[randomY][randomX] == ' ' && map[randomY][randomX+1] == ' ' &&
                map[randomY][randomX+2] == ' ' && map[randomY][randomX+3] == ' ' &&
                map[randomY+1][randomX] == ' ' && map[randomY+1][randomX+1] == ' ' &&
                map[randomY+1][randomX+2] == ' ' && map[randomY+1][randomX+3] == ' ' &&
                map[randomY+2][randomX] == ' ' && map[randomY+2][randomX+1] == ' ' &&
                map[randomY+2][randomX+2] == ' ' && map[randomY+2][randomX+3] == ' ') {
                map[randomY][randomX] = '▆' + ' ';
                map[randomY][randomX+1] = '▆' + ' ';
                map[randomY][randomX+2] = '▆' + ' ';
                map[randomY][randomX+3] = '▆' + ' ';
                map[randomY+1][randomX] = '▆' + ' ';
                map[randomY+1][randomX+1] = '▆' + ' ';
                map[randomY+1][randomX+2] = '▆' + ' ';
                map[randomY+1][randomX+3] = '▆' + ' ';
                map[randomY+2][randomX] = '▆' + ' ';
                map[randomY+2][randomX+1] = '▆' + ' ';
                map[randomY+2][randomX+2] = '▆' + ' ';
                map[randomY+2][randomX+3] = '▆' + ' ';
                }
        }*/
        return map;
    }

    //Fill the map with random destructable elements
    public static void destructableWalls(char[][] board) {
        Random random = new Random();
        int[] randomYs = new int[150];
        int[] randomXs = new int[150];
        for (int i = 0; i < 150; i++) {
            randomYs[i] = (random.nextInt(21) * 3) + 2;
            randomXs[i] = (random.nextInt(11) * 11) + 5;
        }
        for (int i = 0; i < randomYs.length; i++) {
            if(randomYs[i] != 2) {
                Terminal.moveTo(randomYs[i] - 2, randomXs[i] + 1);
                if (board[randomYs[i]][randomXs[i]] == ' ' && board[randomYs[i]][randomXs[i]+1] == ' ' &&
                    board[randomYs[i]][randomXs[i]+2] == ' ' && board[randomYs[i]][randomXs[i]+3] == ' ' &&
                    board[randomYs[i]+1][randomXs[i]] == ' ' && board[randomYs[i]+1][randomXs[i]+1] == ' ' &&
                    board[randomYs[i]+1][randomXs[i]+2] == ' ' && board[randomYs[i]+1][randomXs[i]+3] == ' ' &&
                    board[randomYs[i]+2][randomXs[i]] == ' ' && board[randomYs[i]+2][randomXs[i]+1] == ' ' &&
                    board[randomYs[i]+2][randomXs[i]+2] == ' ' && board[randomYs[i]+2][randomXs[i]+3] == ' ') {
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 2, randomXs[i] + 2);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 2, randomXs[i]+3);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 2, randomXs[i]+4);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 2, randomXs[i]+5);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 1, randomXs[i] + 1);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 1, randomXs[i] + 2);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 1, randomXs[i]+3);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 1, randomXs[i]+4);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i] - 1, randomXs[i]+5);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i], randomXs[i]+1);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i], randomXs[i]+2);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i], randomXs[i]+3);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i], randomXs[i]+4);
                        System.out.print("▆");
                        Terminal.moveTo(randomYs[i], randomXs[i]+5);
                        System.out.print("▆");
                    }
                }
        }
    }

    //Draw the actual board
    public static void drawBoard() {
        Terminal.clearScreen();
        Terminal.moveTo(1, 1);
        Terminal.setColor(Color.YELLOW_LETTER);
        for (int y = 0; y < buildBoard().length; y++) {
            for (int x = 0; x < buildBoard()[y].length; x++) {
                System.out.print(buildBoard()[y][x]);
            }
            System.out.println();
        }
        //destructableWalls(map);
        Terminal.resetStyle();
    }

    public static void goodBye(){
      Terminal.clearScreen();
      Terminal.setColor(Color.YELLOW_LETTER);
      Terminal.moveTo(35, 70);
      System.out.print("GOOOOOOOOD BYEEEEEEEEEEEEEEEE");
      Terminal.moveTo(1, 1);
      try {
            Thread.sleep(1000);
          } catch(InterruptedException i) {}
      Terminal.clearScreen();
      Terminal.getCursorBack();
    }


    public static void main(String[] args) {
      Terminal.cursorInvisible();
      Terminal.clearScreen();
      boolean running = true;
      Terminal.setColor(Color.RED_LETTER);
      System.out.println("BROMBERMAN");
      Terminal.resetStyle();
      System.out.println("What would you like to do?");
        System.out.println("1: Exit");
        System.out.println("2: Play");

      while (running == true) {
        int userInp = userInput.nextInt();

        if (userInp == 1) {
          goodBye();
          running = false;
        } else if (userInp == 2) {
          drawBoard();
          characterPlacement(map, currentY, currentX);
          destructableWalls(map);
          //bombPlacement();
          while (playing==true) {
            moveChar();
          }
          goodBye();
          running = false;

        }
    }
  }
  }
