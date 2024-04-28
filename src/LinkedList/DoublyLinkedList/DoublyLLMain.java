package LinkedList.DoublyLinkedList;

public class DoublyLLMain {
    public static void main(String[] args) {
//        DoublyLinkedList dll = new DoublyLinkedList();
//        dll.addFirst(4);
//        dll.addFirst(5);
//        dll.addFirst(6);
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addLast(4);
        dll.addLast(6);
        dll.addLast(8);
        dll.addLast(2);
        dll.addLast(9);
        dll.display();
        dll.removeFirst();
        dll.display();
        dll.removeLast();
        dll.display();
        dll.insertAtIndex(2,100);
        dll.display();
        dll.removeAtIndex(2);
        dll.display();
        //TODO : Add before node, Remove before node, Add after node, Remove after node
    }
}
