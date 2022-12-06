import java.util.List;

public interface Constraint {
    List<Variable> getScope();
    abstract boolean satisfied (Assignment asgn);
}