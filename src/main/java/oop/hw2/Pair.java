package oop.hw2;


public class Pair<T1, T2> {
    private T1 start;
    private T2 end;

    public Pair(T1 start, T2 end) {
        this.start = start;
        this.end = end;
    }

    public T1 getFirst() {
        return this.start;
    }

    public T2 getSecond() {
        return this.end;
    }
}
