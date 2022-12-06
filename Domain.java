import java.util.*;

public class Domain implements Iterable<Object> {
    private final Object[] values;

    public Domain(List<?> values){
        this.values = new Object[values.size()];

        for (int i = 0; i < values.size(); i++) {
            this.values[i] = values.get(i);
        }
    }

    public Domain(Object[] values){
        this.values = new Object[values.length];

        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    public int size(){
        return values.length;
    }

    public Iterator<Object> iterator(){
        return new ArrayIterator<Object>(values);
    }

    class ArrayIterator<T> implements Iterator<T>{
        T[] values;
        int counter;

        public ArrayIterator(T[] values){
            this.values = values;
            counter = 0;
        }

        public boolean hasNext() {
            return counter < values.length;
        }

        public T next() {
            return values[counter++];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
}
    