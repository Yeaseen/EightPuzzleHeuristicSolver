public class StoredNode {



    StateInfo currState;

    StoredNode parentState;

    private double cost;

    private double hcost;

    private double fcost;


    public StoredNode(StateInfo s){

        parentState=null;
        currState=s;
        cost=0;
        hcost=0;
        fcost=0;

    }


    public StoredNode(StoredNode p,StateInfo s, double c, double h){
        parentState=p;
        currState=s;
        cost=c;
        hcost=h;
        fcost=cost+hcost;

    }


    public StateInfo getCurrentStae(){
        return currState;
    }

    public double getfCost(){
        return fcost;
    }

    public double gethCost(){
        return hcost;
    }

    public double getCost(){
        return cost;
    }

    public StoredNode getParent(){
        return parentState;
    }

}
