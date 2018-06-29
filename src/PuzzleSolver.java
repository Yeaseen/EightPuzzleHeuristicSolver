

public class PuzzleSolver {

    public static void main(String[] args) {

        System.out.println(args[0]);



        int[] startingStateBoard=dispatchEightPuzzle(args,1);

        //for (int i = 0; i <startingStateBoard.length ; i++) {
        //    System.out.printf(startingStateBoard[i]+" ");
        //}


    }

    private static int[] dispatchEightPuzzle(String[] a,int p)
    {
        int[] initState = new int[9];

        for (int i = p; i < a.length; i++)
        {
            initState[i - p] = Integer.parseInt(a[i]);
        }
        return initState;
    }

}
