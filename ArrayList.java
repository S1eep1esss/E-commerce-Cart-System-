
public class ArrayList<T> {
	Object arr[];
	int s;
	int capacity;
	ArrayList(){
		capacity = 0;
		s = 0;
		arr = new Object[capacity]; 
	}
	
	ArrayList(int c) {
		capacity = c;
		s = 0;
		arr = new Object[capacity]; 
	}
	
	void add(T item) {
		if(s==capacity) {
			Object brr[] = new Object[s];
			for(int i=0;i<s;i++) {
				brr[i]=arr[i];
			}
			capacity = capacity+1;
			arr = new Object[capacity];
			for(int i=0;i<s;i++) {
				arr[i]=brr[i];
			}	
		}
		arr[s++] = item;	
	}
	
	void remove(int index) {
		if (index < 0 || index >= s) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + s);
        }
		 System.arraycopy(arr, index + 1, arr, index, s - index - 1);
		 capacity = capacity-1;
	     arr[--s] = null;
	}

	T get(int index) {
		return (T)arr[index];
	}
	
	int size() {
		return s;
	}
	
	boolean isEmpty() {
		if(s==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
