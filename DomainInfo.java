import java.util.*;
public class DomainInfo {
    private List<Pair<Variable, Domain>> savedDomains;
    private HashSet<Variable> affectedVariables;
    private boolean emptyDomainObserved;

    public DomainInfo(){
        savedDomains = new ArrayList<Pair<Variable, Domain>>();
        affectedVariables = new HashSet<Variable>();
    }

    public void clear (){
        savedDomains.clear();
        affectedVariables.clear();
    }

    public boolean isEmpty(){
        return savedDomains.isEmpty();
    }
    
    public void storeDomainFor(Variable var, Domain domain){
        if(!affectedVariables.contains(var)){
            savedDomains.add(new Pair<Variable, Domain>(var,domain));
            affectedVariables.add(var);
        }
    }
    
    public void setEmptyDomainFounnd (boolean b){
        emptyDomainObserved = b;
    }

    public DomainInfo compactify(){
        affectedVariables = null;
        return this;
    }

    public boolean isEmptyDomainFound(){
        return emptyDomainObserved;
    }

    public List<Pair<Variable, Domain>>
        getSavedDomains(){
        return savedDomains;
    }

    public void restoreDomains(CSPProblem csp){
        for(Pair<Variable, Domain> pair : getSavedDomains())
        csp.setDomain(pair.getFirst(), pair.getSecond());
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        savedDomains.stream().forEach((Pair<Variable, Domain>pair) -> {result.append(pair.getFirst()).append("=").append(pair.getSecond()).append(" ");});
        if(emptyDomainObserved) result.append("!");
        return result.toString();
    }

    class Pair<X,Y> {
        private final X a;
        private final Y b;
        
        public Pair (X a, Y b){
            this.a = a;
            this.b = b;
        }

        public X getFirst(){
            return a;
        }

        public Y getSecond(){
            return b;
        }
    }
} 