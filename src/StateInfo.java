import java.util.ArrayList;

public interface StateInfo {
    boolean isGoal();

    ArrayList<StateInfo> getSuccessors();

    double findCost();

    boolean equals(StateInfo s);

    void printState();
}
