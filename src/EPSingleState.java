import java.util.ArrayList;
import java.util.Arrays;

public class EPSingleState implements StateInfo {


    private final int puzzleSize =9;

    private int OutofOrder=0;

    private double ManhattanDist=0;

    private final int[] Goal={1,2,3,4,5,6,7,8,0};

    private int[] currentBoard;


    public EPSingleState(int[] board){
        currentBoard=board;
        setOutOfOrder();
    }

    private void setOutOfOrder(){
        for (int i = 0; i <currentBoard.length ; i++) {
            if(currentBoard[i] !=Goal[i]){
                OutofOrder++;
            }
        }
    }

    private void setManhattanDistance(){

        int idx=-1;
        for (int i = 0; i < 3 ; i++) {

            for (int j = 0; j < 3 ; j++) {

                idx++;

                int val=currentBoard[idx]-1;

                if(val!=-1){
                    int horix= val%3;
                    int verty= val/3;

                    ManhattanDist+= Math.abs(horix - j)+ Math.abs(verty - i);
                }

            }

        }
    }

    public double getManhattanDistance(){
        return ManhattanDist;
    }

    public double getOutOfOrderValue(){
        return OutofOrder;
    }

    @Override
    public boolean isGoal() {
        if(Arrays.equals(currentBoard,Goal)){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<StateInfo> getSuccessors() {
        int holeIdx=getHoleIndex();

        ArrayList<StateInfo> successors= new ArrayList<StateInfo>();

        if(holeIdx !=0 && holeIdx !=3 && holeIdx !=6){
            swapAndStore(holeIdx-1,holeIdx,successors);
        }

        if(holeIdx !=2 && holeIdx !=5 && holeIdx !=8){
            swapAndStore(holeIdx+1,holeIdx,successors);
        }

        if(holeIdx !=0 && holeIdx !=1 && holeIdx !=2){
            swapAndStore(holeIdx-3,holeIdx,successors);
        }

        if(holeIdx !=6 && holeIdx !=7 && holeIdx !=8){
            swapAndStore(holeIdx+3,holeIdx,successors);
        }

        return successors;
    }

    private void swapAndStore(int s,int c,ArrayList<StateInfo> state){

        int[] cpy= copyBoard(currentBoard);
        int temp=cpy[s];
        cpy[s]=currentBoard[c];
        cpy[c]=temp;
        state.add(new EPSingleState(cpy));
    }

    @Override
    public double findCost() {

        return 1;
    }

    @Override
    public boolean equals(StateInfo s) {
        if(Arrays.equals(currentBoard, ((EPSingleState)s).getCurBoard())){
            return true;
        }

        return false;
    }

    @Override
    public void printState()
    {
        System.out.println(currentBoard[0] + " | " + currentBoard[1] + " | "
                + currentBoard[2]);
        System.out.println("---------");
        System.out.println(currentBoard[3] + " | " + currentBoard[4] + " | "
                + currentBoard[5]);
        System.out.println("---------");
        System.out.println(currentBoard[6] + " | " + currentBoard[7] + " | "
                + currentBoard[8]);

    }

    public int[] getCurBoard()
    {
        return currentBoard;
    }

    private int[] copyBoard(int[] arr){

        int[] ret= new int[puzzleSize];
        for (int i = 0; i < puzzleSize ; i++) {
            ret[i]=arr[i];
        }
        return ret;
    }

    private int getHoleIndex(){
        int holeIdx=-1;
        for (int i = 0; i < puzzleSize ; i++) {
            if(currentBoard[i]==0) holeIdx=i;
        }
        return holeIdx;
    }



}
