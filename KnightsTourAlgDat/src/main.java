import java.util.Scanner;

// Java program for Knight Tour problem
public class main {
    static int N;
    static int startXpos;
    static int startYPos;

    //Initiates the grid and possible move combinations, sets the start position and runs a method to find a route for the Knight to move
    static boolean init() {
        int route[][] = new int[N][N];

        /* Initialization of solution matrix */
        for (int x = 0; x < N; x++){
            for (int y = 0; y < N; y++){
                route[x][y] = -1;
            }
        }

       //movement combinations. route[2][1], route[1][2] ect
        int moveXPos[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int moveYPos[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Sets the chosen startingpos to be 0, every other pos on the grid is -1 at this point
        route[startXpos][startYPos] = 0;
 
        //Starts at the given startpos, and checks every possible route using the findRoute function. counters counts the steps it takes. if it finds a viable route, it runs the printRoute function
        if (!findRoute(startXpos, startYPos, 1, route, moveXPos, moveYPos)) {
            System.out.println("Solution does not exist");
            return false;
        }
        else{
            printRoute(route);
        }
        return true;
    }

    // finds a viable route recursively for the knight to move.
    static boolean findRoute(int x, int y, int counter, int route[][], int moveXPos[], int moveYPos[]) {
        int k, nextXPos, nextYPos;
        // if the counter reaches this number, every square on the chess board has been visited.
        if (counter == N * N){
            return true;
        }

        // tries every legal move combination from the position of the knight(x,y)
        for (k = 0; k < 8; k++) {
            nextXPos = x + moveXPos[k];
            nextYPos = y + moveYPos[k];
            if (freeAndOnGrid(nextXPos, nextYPos, route)) {
                route[nextXPos][nextYPos] = counter;
                if (findRoute(nextXPos, nextYPos, counter + 1, route, moveXPos, moveYPos)){
                    System.out.println( "Step:" + counter + " From: (" + (x+1) + ", " + (y+1) + ")" + " to: ("+ (nextXPos+1) +", " + (nextYPos+1) +")" );
                    return true;
                }
                else{
                    // backtracks by setting the given position to be -1 instead of the current counter value. this position can then be used again
                    route[nextXPos][nextYPos] = -1;
                }
            }
        }

        return false;
    }

    //prints the grid and the knights position for each given step.
    static void printRoute(int route[][]) {
        System.out.println();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++){
                System.out.print(route[x][y] + " ");
            }
            System.out.println();
        }
    }

    // a method for checking if the given position is on the grid, and not previously used.
    static boolean freeAndOnGrid(int x, int y, int route[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && route[x][y] == -1);
    }

    //main method. sets the size of the bord, as well as the startpos for the knight. also runs the init function.
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size: ");
        N = sc.nextInt();
        System.out.println("Enter horizontal starting position: ");
        startXpos = sc.nextInt() -1 ;
        System.out.println("Enter vertical starting position: ");
        startYPos = sc.nextInt() -1;

        init();
    }
}