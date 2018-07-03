
import java.util.*;

public class PuzzleSolver {



    public static void main(String[] args) {






        if(args.length>2) {
            int heur=Integer.parseInt(args[0]);
            int[] startingStateBoard = dispatchEightPuzzle(args, 1);
            AStarSearch(startingStateBoard, heur);
        }
        else {
            int gridSize=Integer.parseInt(args[0]);
            int heur=Integer.parseInt(args[1]);
            int[][] startingGrid= dispatchGrid(gridSize);
            AstarSearch2(startingGrid,0,0,heur);


        }

    }

    public static double EBF_Guess(double b,double d,int n){
        int sum=-n;
        for (int i = 0; i <d ; i++) {
            sum+=Math.pow(b,d+1);

        }
        return sum;
    }

    public static double EBF_Calculate(double d, int n){
        double l=0;
        double r=4;
        double mid=0;

        double lval=EBF_Guess(l,d,n);

        for (int i = 0; i < 250 ; i++) {
             mid=(l+r)/2;

            double midval=EBF_Guess(mid,d,n);

            if((lval * midval) >0) {
                l=mid;
                lval=midval;
            }
            else if(midval==0){
                return mid;
            }
            else {
                r=mid;
            }

        }
        return mid;

    }



    public static void AstarSearch2(int[][] grid,int x,int y,int heur){


        StoredNode root = new StoredNode(new GridState(grid,x,y));
        String heuristice=null;
        Queue<StoredNode> q=new LinkedList<StoredNode>();
        q.add(root);
        int stateCount=1;

        while(!q.isEmpty()){
            //System.out.println("in q");
            StoredNode temp =(StoredNode) q.poll();

            if(!temp.getCurrentStae().isGoal()){
                ArrayList<StateInfo> tempSuccessors= temp.getCurrentStae().getSuccessors();

                ArrayList<StoredNode> nodeSuccessors = new ArrayList<StoredNode>();

                for (int i = 0; i < tempSuccessors.size() ; i++) {

                    StoredNode checkN = null;

                    if(heur ==1 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((GridState)tempSuccessors.get(i)).getManhattanDistance());
                        heuristice=" Manhattan Distance";
                    }
                    if(heur ==2 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((GridState)tempSuccessors.get(i)).getEuclidianDistance());
                        heuristice=" Eucledian Distance";
                    }


                    if (!checkRepeats(checkN)) {

                        nodeSuccessors.add(checkN);
                    }

                }

                if(nodeSuccessors.size()==0){
                    continue;
                }

                StoredNode lowestNode=nodeSuccessors.get(0);

                for (int i = 0; i < nodeSuccessors.size() ; i++) {
                    if(lowestNode.getfCost() > nodeSuccessors.get(i).getfCost()){
                        lowestNode = nodeSuccessors.get(i);
                    }

                }

                double lowCost=lowestNode.getfCost();

                for (int i = 0; i <nodeSuccessors.size() ; i++) {

                    if(nodeSuccessors.get(i).getfCost() == lowCost){
                        q.add(nodeSuccessors.get(i));
                    }

                }

                stateCount++;


            }

            else {

                temp.getCurrentStae().printState();

                System.out.println();

                System.out.println("========="+heuristice+"==========");

                System.out.println();

                System.out.println("The depth, d = "+temp.getCost());

                System.out.println("The number of node travelled, n = "+ stateCount);

                System.out.println("Effective Branching Factor, b = "+EBF_Calculate(temp.getCost(),stateCount));

                System.exit(0);


            }



        }


        System.out.println("There is no path to go to the goal State");

    }

    public static void AStarSearch(int[] board, int heur){


        StoredNode root = new StoredNode(new EPSingleState(board));
        String heuristice=null;

        Queue<StoredNode> q=new LinkedList<StoredNode>();
        q.add(root);

        int stateCounter=1;

        while(!q.isEmpty()){

            StoredNode temp =(StoredNode) q.poll();

            if(!temp.getCurrentStae().isGoal()){

                ArrayList<StateInfo> tempSuccessors= temp.getCurrentStae().getSuccessors();

                ArrayList<StoredNode> nodeSuccessors = new ArrayList<StoredNode>();

                for (int i = 0; i < tempSuccessors.size() ; i++) {

                    StoredNode checkN = null;

                    if(heur ==1 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                        tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getOutOfOrderValue());
                        heuristice=" Place Out Of Order";
                    }
                    else if(heur ==2){
                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getManhattanDistance());
                        heuristice=" Manhattan Distance";
                    }

                    else if(heur ==3 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getEucllDist());
                        heuristice=" Eucledian Distance";
                    }
                    else if(heur ==4 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getOutofRC());
                        heuristice=" Out of Row and Column";
                    }
                    else if(heur ==5 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getnMaxCost());
                        heuristice=" N-Max Swap";
                    }



                    if (!checkRepeats(checkN)) {

                        nodeSuccessors.add(checkN);
                    }
                    
                }

                if(nodeSuccessors.size()==0){
                    continue;
                }

                StoredNode lowestNode=nodeSuccessors.get(0);

                for (int i = 0; i < nodeSuccessors.size() ; i++) {
                    if(lowestNode.getfCost() > nodeSuccessors.get(i).getfCost()){
                        lowestNode = nodeSuccessors.get(i);
                    }

                }

                double lowCost=lowestNode.getfCost();

                for (int i = 0; i <nodeSuccessors.size() ; i++) {

                    if(nodeSuccessors.get(i).getfCost() == lowCost){
                        q.add(nodeSuccessors.get(i));
                    }

                }

                stateCounter++;


            }

            else {

                Stack<StoredNode> paths= new Stack<StoredNode>();

                paths.push(temp);

                temp=temp.getParent();

                while(temp.getParent() != null){
                    paths.push(temp);
                    temp=temp.getParent();
                }

                paths.push(temp);

                int pathsize= paths.size();

                for (int i = 0; i < pathsize ; i++) {

                    temp=paths.pop();
                    temp.getCurrentStae().printState();
                    System.out.println();
                    System.out.println();
                }

                System.out.println();

                System.out.println("========="+heuristice+"==========");

                System.out.println();
                System.out.println("The depth, d = "+temp.getCost());

                System.out.println("The number of node travelled, n = "+ stateCounter);

                System.out.println("Effective Branching Factor, b = "+EBF_Calculate(temp.getCost(),stateCounter));

                System.exit(0);


            }



        }

        System.out.println("There is no way to go to the goal state");



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

    private static boolean checkRepeats(StoredNode n)
    {
        boolean retValue = false;
        StoredNode checkNode = n;


        while (n.getParent() != null && !retValue)
        {
            if (n.getParent().getCurrentStae().equals(checkNode.getCurrentStae()))
            {
                retValue = true;
            }
            n = n.getParent();
        }

        return retValue;
    }

    private static int[][] dispatchGrid(int n){

        Random rand = new Random();
        int cnt= (n*n*20)/100;

        int[][] initState= new int[n][n];
        for (int i = 0; i < n ; i++) {

            for (int j = 0; j < n ; j++) {
                if(i==0 && j==0) initState[i][j]=2;
                //else if(j==1) initState[i][j]=1;
                else initState[i][j]=0;
            }

        }

        while (cnt!=0){
            int x=rand.nextInt(n-1);
            int y=rand.nextInt(n-1);
            if(initState[x][y]==0){
                initState[x][y]=1;
                cnt--;
            }
        }

        return initState;

    }

}
