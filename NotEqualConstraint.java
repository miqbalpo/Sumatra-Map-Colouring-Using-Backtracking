import java.util.*;

public class NotEqualConstraint implements Constraint {
    private final Variable var1;
    private final Variable var2;
    private final List<Variable> scope;

    public NotEqualConstraint (Variable var1, Variable var2){
        this.var1 = var1;
        this.var2 = var2;
        scope = new ArrayList<Variable>(2);
        scope.add(var1);
        scope.add(var2);
    }

    public List<Variable> getScope() {
        return scope;
    }

    public boolean satisfied(Assignment assignment){
        Object value1 = assignment.getValue(var1);
        return value1 == null ||
        !value1.equals(assignment.getValue(var2));
    }
}
