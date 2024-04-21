//package LinkedList.SinglyLinkedList;
//
//
//public class SinglyLinkedList {
//
//    Node head;
//
//    SinglyLinkedList(){
//        this.head = null;
//    }
//
//    public void insertNodeAtIndex(int data, int idx){
//
//        if(idx<0){
//            System.out.println("Invalid Index");
//            return;
//        }
//
//        Node newNode = new Node(data);
//        Node curr = head;
//
//        if(idx == 0){
//            newNode.next = curr;
//            head = newNode;
//            return;
//        }
//
//        while(idx > 0) {
//            if(--idx == 0){
//                break;
//            }
//            curr = curr.next;
//        }
//        newNode.next = curr.next;
//        curr.next = newNode;
//    }
//
//    public void insertNodeAtStart(int data){
//        Node newNode = new Node(data);
//        newNode.next = head;
//        head=newNode;
//    }
//
//    public void insertNodeAtLast(int data){
//        Node newNode = new Node(data);
//        if(head ==  null){
//            head = newNode;
//            return;
//        }
//        Node curr = head;
//        while(curr.next!=null) {
//            curr = curr.next;
//        }
//        curr.next = newNode;
//    }
//
//    public void deleteLastNode(){
//        if(head == null){
//            return;
//        }
//
//        if (head.next == null) {
//            head = null; // List has only one node, delete it by setting head to null
//            return;
//        }
//
//        Node curr = head;
//        while(curr.next.next != null) {
//            curr = curr.next;
//        }
//        // Set the next reference of the second-to-last node to null
//        curr.next = null;
//    }
//
//    public void deleteFirstNode(){
//        if(head!=null){
//            head=head.next;
//        }
//    }
//
//    public void deleteNodeWithData(int data){
//        if (head == null)
//            return;
//
//        if (head.data == data) {
//            head = head.next;
//            return;
//        }
//
//        Node current = head;
//        while (current.next != null) {
//            if (current.next.data == data) {
//                current.next = current.next.next;
//                return;
//            }
//            current = current.next;
//        }
//    }
//
//    public static void display(SinglyLinkedList linkedList){
//        if(linkedList.head == null) {
//            System.out.print("NULL");
//            System.out.println();
//            System.out.println();
//            return;
//        }
//
//        Node curr = linkedList.head;
//
//        while (curr!=null){
//            System.out.print(curr.data+"->");
//            curr = curr.next;
//        }
//
//        System.out.print("NULL");
//
//        System.out.println();
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        SinglyLinkedList linkedList  = new SinglyLinkedList();
//        display(linkedList);
//        linkedList.deleteFirstNode();
//        display(linkedList);
//        linkedList.deleteLastNode();
//        display(linkedList);
//        linkedList.insertNodeAtLast(23);
//        display(linkedList);
//        linkedList.insertNodeAtLast(24);
//        display(linkedList);
//        linkedList.insertNodeAtLast(28);
//        display(linkedList);
//        linkedList.insertNodeAtLast(29);
//        display(linkedList);
//        linkedList.insertNodeAtLast(3);
//        display(linkedList);
//        linkedList.insertNodeAtLast(32);
//        display(linkedList);
//        linkedList.insertNodeAtStart(100);
//        display(linkedList);
//        linkedList.insertNodeAtStart(20);
//        display(linkedList);
//        linkedList.insertNodeAtIndex(233,2);
//        display(linkedList);
//        linkedList.deleteFirstNode();
//        display(linkedList);
//        linkedList.deleteLastNode();
//        display(linkedList);
//        linkedList.deleteNodeWithData(100);
//        display(linkedList);
//    }
//
//}
