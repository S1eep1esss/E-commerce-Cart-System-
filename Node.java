
public class Node<T> {
	 public T data;
	 public Node<T> next;
	 public Product product;

	 public Node(T item) {
	        data = item;
	        next = null;
	    }

	 public void setData(T item) {
	        data = item;
	    }

	 public void setNext(Node<T> n) {
	        next = n;
	    }

	 public T getData() {
	        return data;
	    }

	 public Node<T> getNext() {
	        return next;
	    }
	 
	 public Product getProduct() {
	        return product;
	    }
}
