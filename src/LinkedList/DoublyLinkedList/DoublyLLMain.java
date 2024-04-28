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
        dll.removeFirst();
        dll.removeLast();
    }
}
