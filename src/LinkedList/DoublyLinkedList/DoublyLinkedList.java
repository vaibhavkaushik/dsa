package LinkedList.DoublyLinkedList;

public class DoublyLinkedList {
    private DoublyNode head;
    private DoublyNode tail;
    private int size;

    public DoublyNode getHead() {
        return head;
    }

    public void addFirst(int data){
        DoublyNode newNode = new DoublyNode(data);
        if(head == null){
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        if(head.next == null){
            newNode.next = head;
            newNode.prev = null;
            head.prev = newNode;
            this.tail = head;
            this.head = newNode;
            return;
        }

        newNode.next = head;
        newNode.prev = null;
        head.prev = newNode;
        this.head = newNode;
    }

    public void addLast(int data){
        DoublyNode newNode = new DoublyNode(data);
        if(head == null){
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        this.tail = newNode;
    }

    public void removeFirst(){
        if(head == null){
            this.tail = null;
            return;
        }

        DoublyNode leftOverList = head.next;
        head.next = null;
        head.prev = null;
        leftOverList.prev = null;
        head = leftOverList;
    }

    public void removeLast(){
        if(head == null){
            this.tail = null;
            return;
        }

        DoublyNode leftOverList = tail.prev;
        tail.next = null;
        tail.prev = null;
        leftOverList.next = null;
        tail = leftOverList;
    }
}
