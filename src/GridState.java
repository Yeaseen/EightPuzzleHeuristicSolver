import java.util.ArrayList;

public class GridState implements StateInfo {



    private int gridSize;
    private int x,y;
    private int[][] currentGrid;
    private double ManhattanDistance=0;
    private double EuclidianDistance=0;
    public GridState(int[][] grid,int xx,int yy){

        currentGrid=grid;
        gridSize=currentGrid.length;
        x=xx;
        y=yy;
        setManhattanDist();

    }

    private void setManhattanDist(){
        double p= Math.abs(x - gridSize-1);
        double q= Math.abs(y - gridSize-1);
        ManhattanDistance+=p+q;
        EuclidianDistance+=Math.sqrt((p*p) +(q*q));
    }

    public double getManhattanDistance() {
        return ManhattanDistance;
    }

    public double getEuclidianDistance() {
        return EuclidianDistance;
    }

    @Override
    public boolean isGoal() {
        if(currentGrid[gridSize-1][gridSize-1]==2) return true;
        return false;
    }

    @Override
    public ArrayList<StateInfo> getSuccessors() {

        ArrayList<StateInfo> successors= new ArrayList<StateInfo>();

        if(y+1<gridSize && currentGrid[x][y+1]==0){
            //System.out.println("Right");
            swapAndStore(x,y+1,successors);
        }

        if(y-1>=0 && currentGrid[x][y-1]==0){
            //System.out.println("Left");
            swapAndStore(x,y-1,successors);
        }

        if(x+1<gridSize && currentGrid[x+1][y]==0){
            //System.out.println("down");
            swapAndStore(x+1,y,successors);
        }

        if(x-1>=0 && currentGrid[x-1][y]==0){
            //System.out.println("up");
            swapAndStore(x-1,y,successors);
        }

        return successors;
    }

    private void swapAndStore(int x, int y, ArrayList<StateInfo> state){
        int[][] cpy=copyBoard(currentGrid);

        cpy[x][y]=2;
        state.add(new GridState(cpy,x,y));

    }



    @Override
    public double findCost() {
        return 1;
    }

    @Override
    public boolean equals(StateInfo s) {

        return false;
    }

    private int[][] copyBoard(int[][] arr){

        int[][] ret= new int[gridSize][gridSize];
        for (int i = 0; i < gridSize ; i++) {
            for (int j = 0; j < gridSize ; j++) {
                ret[i][j]= arr[i][j];
            }
        }
        return ret;
    }

    public int[][] getCurrentGrid(){
        return currentGrid;
    }

    @Override
    public void printState() {

        for (int i = 0; i < currentGrid.length ; i++) {

                for (int j = 0; j < currentGrid.length ; j++) {


                    System.out.print(currentGrid[i][j]+" |  ");
                }
            System.out.println();
            for (int j = 0; j < 5*gridSize -2 ; j++) {
                System.out.print("-");
            }
            System.out.println();

            }

    }
}
