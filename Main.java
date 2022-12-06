public class Main {
        public static void main(String[] args) {
            MapCSP map = new MapCSP();
            Backtrack bts = new Backtrack(map);

            bts.addCSPStateListener(new CSPStateListener() {
                @Override
                public void stateChanged(Assignment assignment, CSPProblem csp) {
                    System.out.println("processing..."+assignment);
                }

                @Override
                public void stateChanged(CSPProblem csp){
                    System.out.println(csp);
                }
            });

            System.out.println("Map Coloring each city on Sumatra Island\n");
            System.out.println("Backtacking start : ");
            Assignment sol = bts.solve();
            System.out.println("Backtracking completed!\n");
            sol.printSolution();
        }
}

class MapCSP extends CSPProblem {
    public static final Variable AC = new Variable("Aceh");
    public static final Variable SU = new Variable("Sumatra Utara");
    public static final Variable RI = new Variable("Riau");
    public static final Variable SB = new Variable("Sumatra Barat");
    public static final Variable JB = new Variable("Jambi");
    public static final Variable SS = new Variable("Sumatra Selatan");
    public static final Variable BK = new Variable("Bengkulu");
    public static final Variable LP = new Variable("Lampung");
    public static final String R = "Red";
    public static final String G = "Green";
    public static final String B = "Blue";

    public MapCSP(){
        collectVariables();
        Domain colors = new Domain(new Object[]{R, G, B});

        for(Variable var : getVariables()){
            setDomain(var, colors);
        }

        addConstraint(new NotEqualConstraint(AC, SU));
        addConstraint(new NotEqualConstraint(SU, RI));
        addConstraint(new NotEqualConstraint(SU, SB));
        addConstraint(new NotEqualConstraint(RI, JB));
        addConstraint(new NotEqualConstraint(SB, JB));
        addConstraint(new NotEqualConstraint(SB, RI));
        addConstraint(new NotEqualConstraint(JB, BK));
        addConstraint(new NotEqualConstraint(JB, SS));
        addConstraint(new NotEqualConstraint(SU, RI));
        addConstraint(new NotEqualConstraint(BK, SS));
        addConstraint(new NotEqualConstraint(SS, LP));
    }

    private void collectVariables(){
        addVariable(AC);
        addVariable(SU);
        addVariable(RI);
        addVariable(SB);
        addVariable(JB);
        addVariable(BK);
        addVariable(SS);
        addVariable(LP);
    }
}