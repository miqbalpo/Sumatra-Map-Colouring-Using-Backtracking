import java.util.*;

public class Assignment {
    List<Variable> variables;
    Hashtable<Variable, Object> variableToValue;

    public Assignment(){
        variables = new ArrayList<Variable>();
        variableToValue = new Hashtable<Variable,Object>();
    }

    public List<Variable> getVariables(){
        return Collections.unmodifiableList(variables);
    }

    public Object getValue(Variable var){
        return variableToValue.get(var);
    }

    public void assign(Variable var, Object value){
        if(!variableToValue.containsKey(var)){
            variables.add(var);
        } variableToValue.put(var, value);
    }

    public void removeAssignment(Variable var){
        if(hasAssignmentFor(var)){
            variables.remove(var);
            variableToValue.remove(var);
        }
    }

    public boolean hasAssignmentFor(Variable var){
        return variableToValue.get(var) != null;
    }

    public boolean isConsistent(List<Constraint>constraints){
        for(Constraint cons : constraints){
            if(!cons.satisfied(this)){
                return false;
            }
        } return true;
    }

    public boolean isComplete(List<Variable> vars){
        for(Variable var : vars){
            if(!hasAssignmentFor(var)){
            return false;
            }
        } return true;
    }

    public Assignment copy(){
        Assignment copy = new Assignment();
        for(Variable var : variables){
            copy.assign(var, variableToValue.get(var));
        } return copy;
    }

    public String toString(){
        boolean comma = false;
        StringBuilder result = new StringBuilder();
        for(Variable var : variables){
            if(comma){
            result.append(", ");
            }result.append(":").append(variableToValue.get(var));
            comma = true;
        }
        return result.toString();
    }

    public void printSolution(){
        System.out.println("Solution");
        for(Variable var : variables){
            System.out.printf("%-19s:%s\n",var,variableToValue.get(var));
        }
    }
}
