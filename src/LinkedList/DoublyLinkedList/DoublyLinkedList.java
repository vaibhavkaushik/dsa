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

    public int size(){
        if(head == null){
            return 0;
        }

        DoublyNode temp = head;
        int list_size = 0;
        while(temp!=null){
            list_size++;
            temp = temp.next;
        }

        return list_size;
    }

    public void display(){
        System.out.print("Display : ");
        if(head == null){
            System.out.print("NULL");
        }
        DoublyNode curr = head;
        while(curr!=null){
            System.out.print(curr.data+"<->");
            curr = curr.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public void insertAtIndex(int idx, int data){

        int size = size();

        if(idx < 0){
            System.out.println("Invalid index");
            return;
        }

        if(idx == 0){
            addFirst(data);
        }

        if(idx == size){
            addLast(data);
        }

        DoublyNode temp = head;

        while(idx!=0){
            temp = temp.next;
            idx--;
        }

        DoublyNode newNode = new DoublyNode(data);
        DoublyNode previousNode = temp.prev;
        newNode.prev = previousNode;
        newNode.next = temp;
        previousNode.next = newNode;
        temp.prev = newNode;

    }
}
