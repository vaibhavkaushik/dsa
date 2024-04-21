package LinkedList.SinglyLinkedList;

/*
When a member of a class has no access modifier specified, it defaults to package-private.
This means that the member is accessible to all classes within the same package,
but not accessible outside of that package
 */
public class Node {
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
    public Node(int data, Node node){
        this.data = data;
        this.next = node;
    }
}
