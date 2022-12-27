
import java.util.Iterator;

class Stack<E> implements AbstractStack<E>{
    private Node<E> top;
    private int size;
    private Node<E> next;
    private Node<E> previous;
    private static class Node<E> {
        E element;
        Node<E> previous;
        public Node(E element){
            this(element, null);
        }
        public Node(E element, Node<E> previous){
            this.element = element;
            this.previous = previous;
        }
    }
    public void ensureNonEmpty()  {
        if(this.size==0) {
            throw new IllegalStateException("empty");
        }
    }

    @Override
    public String toString() {
        Node<E> current = top;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(" " + current.element + "\n ");
            current = current.previous;
        }
        return result.toString();
    }

    @Override
    public void push(E element) {
        Node<E> node = new Node<>(element);
        node.previous = this.top;
        this.top = node;
        size++;
    }

    @Override
    public E pop() {
        ensureNonEmpty();
        E element = top.element;
        Node<E> tempt = this.top.previous;
        this.top.previous = null;
        this.top = tempt;
        this.size --;
        return element;
    }

    @Override
    public E peek() {
        return (E) this.top;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == 0){
            return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                E element = current.element;
                this.current = current.previous;
                return element;
            }
        };
    }
}

