import java.util.*;

public abstract class Strategy {
    List<CSPStateListener> listeners = new
    ArrayList<CSPStateListener>();

    public void addCSPStateListener(CSPStateListener listener){
        listeners.add(listener);
    }

    public void removeCSPStateListener(CSPStateListener listener){
        listeners.remove(listener);
    }

    protected void fireStateChanged(CSPProblem csp){
        for(CSPStateListener listener : listeners){
            listener.stateChanged(csp.copyDomains());
        }
    }

    protected void fireStateChanged(Assignment assignment, CSPProblem csp){
        for(CSPStateListener listener : listeners){
            listener.stateChanged(assignment.copy(),
            csp.copyDomains());
        }
    }
    public abstract Assignment solve();
}
