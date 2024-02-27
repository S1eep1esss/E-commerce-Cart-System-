
public class LinkedList<T> {
	public Node<T> head;

    public LinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        int count = 0;

        Node<T> c = head;
        while (c != null) {
            count++;
            c = c.getNext();
        }
        return count;
    }
    
    public Node<T> get(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        Node<T> c = head;
        int ci = 0;

        while (c != null && ci < index) {
            c = c.getNext();
            ci++;
        }

        return c;
    }

    public void prepend(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void append(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> c = head;
            while (c.getNext() != null) {
                c = c.getNext();
            }
            c.setNext(newNode);
        }
    }

    public void clear() {
        head = null;
    }

    public void remove(T item) {
        if (head == null) {
            return; // List is empty
        }

        if (head.getData().equals(item)) {
            head = head.getNext(); // Remove the head node
            return;
        }

        Node<T> c = head;
        while (c.getNext() != null &&
        		!c.getNext().getData().equals(item)) {
            c = c.getNext();
        }
        
        if(c.getNext() != null) {
        	c.next = c.next.next;// Remove the node
        }
    }

    public boolean equals(LinkedList<T> secondList) {
        if (size() != secondList.size()) {
            return false;
        }

        Node<T> c1 = head;
        Node<T> c2 = secondList.head;

        while (c1 != null) {
            if (!((Comparable<T>) c1.getData()).equals(c2.getData())) {
                return false;
            }
            c1 = c1.getNext();
            c2 = c2.getNext();
        }
        return true;
    }
	
}
