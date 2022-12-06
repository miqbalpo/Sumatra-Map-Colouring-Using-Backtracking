public interface CSPStateListener {
    void stateChanged(Assignment assignment, CSPProblem csp);
    void stateChanged(CSPProblem csp);
}
