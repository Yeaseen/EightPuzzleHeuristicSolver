import java.util.ArrayList;

public interface StateInfo {
    boolean isGoal();

    ArrayList<StateInfo> getSuccessors();

    double getCost();

    boolean equals(StateInfo s);
}
