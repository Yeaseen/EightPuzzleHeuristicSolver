
import java.util.*;

public class PuzzleSolver {



    public static void main(String[] args) {


        int heur=Integer.parseInt(args[0]);



        int[] startingStateBoard=dispatchEightPuzzle(args,1);

        //EPSingleState s= new EPSingleState(startingStateBoard);
        //double x=s.getnMaxCost();
        //System.out.println(x);

        AStarSearch(startingStateBoard,heur);

    }

    public static void AStarSearch(int[] board, int heur){
        //System.out.println("Astar");

        StoredNode root = new StoredNode(new EPSingleState(board));

        Queue<StoredNode> q=new LinkedList<StoredNode>();
        q.add(root);

        int stateCounter=1;

        while(!q.isEmpty()){
            //System.out.println("in q");
            StoredNode temp =(StoredNode) q.poll();

            if(!temp.getCurrentStae().isGoal()){
                //System.out.println("1");
                ArrayList<StateInfo> tempSuccessors= temp.getCurrentStae().getSuccessors();
                //System.out.println("majhe");
                ArrayList<StoredNode> nodeSuccessors = new ArrayList<StoredNode>();
                //System.out.println("22");
                for (int i = 0; i < tempSuccessors.size() ; i++) {
                    //System.out.println("2");
                    StoredNode checkN = null;

                    if(heur ==1 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                        tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getOutOfOrderValue());
                    }
                    else if(heur ==2){
                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getManhattanDistance());
                    }

                    else if(heur ==3 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getEucllDist());
                    }
                    else if(heur ==4 ){
                        //System.out.println("3");

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getOutofRC());
                    }
                    else if(heur ==5 ){

                        checkN = new StoredNode(temp, tempSuccessors.get(i), temp.getCost()+
                                tempSuccessors.get(i).findCost(), ((EPSingleState)tempSuccessors.get(i)).getnMaxCost());
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

                //System.out.println("The cost = "+temp.getCost());

                System.out.println("The number of node travelled= "+ stateCounter);

                System.exit(0);


            }



        }




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

}
