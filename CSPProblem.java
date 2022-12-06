import java.util.*;

public class CSPProblem {
    private List<Variable> variables;
    private List<Domain> domains;
    private List<Constraint> constraints;
    private Hashtable<Variable, Integer> varIndexHash;
    private Hashtable<Variable, List<Constraint>> cnet;

    public CSPProblem(){
        variables = new ArrayList<Variable>();
        domains = new ArrayList<Domain>();
        constraints = new ArrayList<Constraint>();
        varIndexHash = new Hashtable<Variable,Integer>();
        cnet = new Hashtable<Variable,List<Constraint>>();
    }

    public CSPProblem(List<Variable> vars){
        this();
        for(Variable v : vars){
            addVariable(v);
        }
    }

    protected void addVariable(Variable var){
        if(!varIndexHash.containsKey(var)){
            Domain emptyDomain = new
            Domain(Collections.emptyList());
            variables.add(var);
            domains.add(emptyDomain);
            varIndexHash.put(var, variables.size()-1);
            cnet.put(var, new ArrayList<Constraint>());
            } else{
            throw new IllegalArgumentException("Variable with same name already exists.");
        }
    }

    public List<Variable> getVariables(){
        return Collections.unmodifiableList(variables);
    }

    public int indexOf(Variable var){
        return varIndexHash.get(var);
    }

    public Domain getDomain(Variable var){
        return domains.get(varIndexHash.get(var));
    }

    public void setDomain(Variable var, Domain domain){
        domains.set(indexOf(var), domain);
    }

    public void removeValueFromDomain(Variable var, Object value){
        Domain currDomain = getDomain(var);
        List<Object> values = new ArrayList<Object>(currDomain.size());
        
        for(Object v : currDomain){
            if(!v.equals(value)){
                values.add(v);
            } setDomain(var, new Domain(values));
        }
    }

    public void addConstraint(Constraint constraint){
        constraints.add(constraint);

        for(Variable var : constraint.getScope()){
            cnet.get(var).add(constraint);
        }
    }

    public List<Constraint> getConstraints(){
        return constraints;
    }

    public List<Constraint> getConstraints(Variable var){
        return cnet.get(var);
    }

    public CSPProblem copyDomains(){
        CSPProblem result = new CSPProblem();
        result.variables = variables;
        result.domains = new ArrayList<Domain>(domains.size());
        result.domains.addAll(domains);
        result.constraints = constraints;
        result.varIndexHash = varIndexHash;
        result.cnet = cnet;
        return result;
    }
}