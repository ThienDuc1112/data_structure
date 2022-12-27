import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 300;
    private int rear;
    private E[] queue;

    public Queue(){
        rear = -1;
        queue = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public void ensureNonEmpty(){
        if(this.rear == -1){
            throw new IllegalStateException("queue is empty");
        }
    }
    @Override
    public void offer(E element) {
        if(rear == queue.length - 1) {
            throw new IllegalStateException("Queue overflow");
        }
        rear++;
        queue[rear] = element;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = queue[0];
        for(int i = 0; i < rear; i++){
            queue[i] = queue[i+1];
        }
        rear--;
        return element;
    }

    @Override
    public E peek() {
        return queue[0];
    }

    @Override
    public int size() {
        return rear+1;
    }

    @Override
    public boolean isEmpty() {
        if(rear <= -1){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        int current = 0;
        StringBuilder sb = new StringBuilder();
        while (current <= rear){
            sb.append(queue[current] + "\n ");
            current++;
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index <= rear;
            }

            @Override
            public E next() {
                return queue[index++];
            }
        };
    }


}
