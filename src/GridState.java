import java.util.ArrayList;

public class GridState implements StateInfo {



    private int[][] currentGrid;
    public GridState(int[][] grid){

        currentGrid=grid;
    }


    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public ArrayList<StateInfo> getSuccessors() {
        return null;
    }

    @Override
    public double findCost() {
        return 0;
    }

    @Override
    public boolean equals(StateInfo s) {
        return false;
    }

    @Override
    public void printState() {

        for (int i = 0; i < currentGrid.length ; i++) {

                for (int j = 0; j < currentGrid.length ; j++) {

                    System.out.print(currentGrid[i][j]+"  ");
                }

                System.out.println("\n"+"---------");
            }

    }
}
