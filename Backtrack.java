public class Backtrack extends Strategy {
    CSPProblem problem;

    public Backtrack(CSPProblem prob){
        this.problem = prob;
    }

    public Assignment solve(){
        return recursiveSolve(this.problem, new Assignment());
    }

    private Assignment recursiveSolve(CSPProblem csp,Assignment assignment){
        Assignment result = null;

        if(assignment.isComplete(csp.getVariables())){
            result = assignment;
        }
        else{
            Variable var = unassignedVar(assignment,csp);
            for(Object value : orderValues(var,assignment,csp)){
                assignment.assign(var, value);
                fireStateChanged(assignment, csp);

                if(assignment.isConsistent(csp.getConstraints(var))){
                    DomainInfo info = inference(var,assignment,csp);
                if(!info.isEmpty()){
                    fireStateChanged(csp);
                } if(!info.isEmptyDomainFound()){
                    result = recursiveSolve(csp,assignment);
                    if (result != null){
                        break;
                    }
                }
                info.restoreDomains(csp);
            }
            assignment.removeAssignment(var);
            }
        }
        return result;
    }

    protected Variable unassignedVar(Assignment assignment, CSPProblem csp){
        for(Variable var : csp.getVariables()){
            if(!(assignment.hasAssignmentFor(var))){
                return var;
            }
        }
        return null;
    }

    protected Iterable<?> orderValues(Variable var, Assignment assignment, CSPProblem csp){
        return csp.getDomain(var);
    }

    protected DomainInfo inference(Variable var,Assignment assignment, CSPProblem csp){
        return new DomainInfo().compactify();
    }
}