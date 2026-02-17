//package SLIST;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class SLinkedList<T> implements Iterable<T> {
    class Node<T>{
        T data;
        Node<T> next;
        Node(T data){
            this.data=data;
            next=null;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size=0;
    public SLinkedList(){
        head=null;
        head=null;
        size=0;
}
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        if(tail==null){
            tail=newNode;
        }
        size++;
    }
    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail=newNode;
        } else {
            tail.next = newNode;
            tail = newNode;      }
        size++;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T deleteFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) {
            tail = null; 
        }
        return data;
    }
    public T deleteLast(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) { 
            T data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }
    public void display(){
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public boolean contains(T data){
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public void clear(){
        head = null;
        tail=null;
        size=0;
    }
    public T getFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }
    public T getLast(){
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    public void remove(T e){
        
        if (head == null) return;

        // Case 1: head is the node to remove
        if (head.data.equals(e)) {
            head = head.next;
            if (head == null) tail = null; // list became empty
            size--;
            return;
        }

        Node<T> prev = head;
        Node<T> current = head.next;
        while (current != null) {
            if (current.data.equals(e)) {
                prev.next = current.next;
                if (current == tail) tail = prev; 
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }

    }

    public void reverse(){
        if (head == null || head.next == null) return;

        tail = head; 

        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev; 
    }

    public void deleteConsecutiveDuplicates(){
        if (head == null) return;

        Node<T> current = head;
        while (current != null && current.next != null) {
            if (current.data.equals(current.next.data)) {
                // skip the duplicate
                if (current.next == tail) tail = current;
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || !(obj instanceof SLinkedList)) return false;

        SLinkedList<?> other = (SLinkedList<?>) obj;
        if (this.size != other.size) return false;

       
        java.util.Iterator<T> it1 = this.iterator();
        java.util.Iterator<?> it2 = other.iterator();
        while (it1.hasNext()) {
            if (!it1.next().equals(it2.next())) return false;
        }
        return true;
    }
}