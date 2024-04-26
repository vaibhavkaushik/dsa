package LinkedList.SinglyLinkedList;

public class SinglyLinkedListPractice {

        private Node head;
        private Node tail;
        private int size;
        public SinglyLinkedListPractice(){
            this.size = 0;
        }

        public Node getHead(){
            return head;
        }

        public void addLast(int data){
            System.out.println("Adding "+data+" at last");
            Node newNode = new Node(data);

            //Agar abhi ek bhi node nhi h list mein.
            //Nayi node ko hi head banao and return
            if(head == null){
                head = newNode;
                return;
            }

            Node temp = head;
            //This loop helps to get to end of the list
            while (temp.next!=null){
                temp = temp.next;
            }
            temp.next = newNode;
            return;
        }

        public void deleteFromIndex(int idx){

            int size = size();

            if(idx == 0){
                deleteFirst();
                return;
            }

            if(idx == size-1){
                deleteLast();
                return;
            }

            if(idx < 0){
                System.out.println("Invalid index (Cannot be less than 0)");
                return;
            }

            if(idx > size){
                System.out.println("Invalid index (Greater than size of list)");
                return;
            }

            Node temp = head;
            for(int i=0;i<idx-1;i++){
                temp=temp.next;
            }

            if(temp.next!=null){
                temp.next = temp.next.next;
            }
        }

        public void deleteFromListRecursively(int idx){
            int size = size();

            if(idx == 0){
                deleteFirst();
                return;
            }

            if(idx == size-1){
                deleteLast();
                return;
            }

            if(idx < 0){
                System.out.println("Invalid index (Cannot be less than 0)");
                return;
            }

            if(idx > size){
                System.out.println("Invalid index (Greater than size of list)");
                return;
            }

            deleteFromListRecursively(idx-1,this.head);
        }

        private void deleteFromListRecursively(int idx,Node catcherNode){
            if(idx==0){
                if(catcherNode.next!=null){
                    catcherNode.next = catcherNode.next.next;
                    return;
                }
            }
            if(idx > 0) {
                deleteFromListRecursively(idx - 1, catcherNode.next);
            }
        }

    private Node addAtIndexRecursively(int data, int idx, Node catcherNode){
        if(idx == 0){
            Node newNode = new Node(data);
            newNode.next = catcherNode;
            return newNode;
        }

        Node recursiveAnswerNode = addAtIndexRecursively(data,idx-1,catcherNode.next);
        catcherNode.next = recursiveAnswerNode;

        return catcherNode;
    }

        private int size(){

            if(head==null){
                return 0;
            }

            Node temp = head;
            int size = 0;
            while(temp!=null){
                size++;
                temp=temp.next;
            }

            return size;
        }

        public void addAtIndex(int data, int idx){

            int size = size();

            if(idx == 0){
                addFirst(data);
                return;
            }

            if(idx < 0){
                System.out.println("Invalid index (Cannot be less than 0)");
                return;
            }

            if(idx == size){
                addLast(data);
            }

            if(idx > size){
                System.out.println("Invalid index (Greater than size of list)");
                return;
            }

            Node newNode = new Node(data);

            Node temp = head;
            for(int i=0;i<idx-1;i++){
                temp =  temp.next;
            }

            Node restOfList = temp.next;
            temp.next = newNode;
            newNode.next = restOfList;

        }



        public void addAtIndexRecursively(int data, int idx){

            int size = size();

            if(idx == 0){
                addFirst(data);
                return;
            }

            if(idx < 0){
                System.out.println("Invalid index (Cannot be less than 0)");
                return;
            }

            if(idx == size){
                addLast(data);
                return;
            }

            if(idx > size){
                System.out.println("Invalid index (Greater than size of list)");
                return;
            }

            addAtIndexRecursively(data,idx,this.head);
        }

        public void addLastRecursively(int data){ System.out.println("Adding "+data+" at last"); addLastRecursively(data,head); }
        private Node addLastRecursively(int data, Node curr){
            if(curr == null){
                return new Node(data);
            }
            Node lastAddedNodeFromTailSide = addLastRecursively(data,curr.next);
            curr.next = lastAddedNodeFromTailSide;
            return curr;
        }

        public void addFirst(int data){
            System.out.println("Adding "+data+" at start");
            Node newNode = new Node(data);

            //If the list is empty
            //The node itself will the new head
            if(head == null){
                head=newNode;
                return;
            }

            //New node ka next should point head, as it the place from where existing linked list start
            //Head should point the new node, since new node become the first node now
            newNode.next = head;
            head = newNode;

            return;
        }

        public void deleteFirst(){

            if(head == null){
                return;
            }

            head = head.next;
        }

        public void deleteLast(){

            if(head == null){
                return;
            }

            if(head.next == null){
                head = null;
                return;
            }

            Node temp = head;
            Node prev = null;
            while(temp.next != null){
                prev = temp;
                temp = temp.next;
            }

            prev.next = null;
        }

    public void deleteLastNodeApproach2() {
        if (head == null || head.next == null) {
            head = null; // List is empty or has only one node
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null; // Set the next reference of the second-to-last node to null
    }

        //The basic logic here is to start from a head node ptr and keep traversing the list until we end up with
        // null ptr ( which can indicate list end)
        public void display(){

            System.out.print("Display :");
            Node temp = head;
            while(temp!=null){
                System.out.print(temp.data+"->");
                temp = temp.next;
            }
            System.out.println("NULL");

        }

    public void display(Node head){

        System.out.print("Display :");
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("NULL");

    }

        public void isPalindrome(){

            System.out.print("Checking if list is a palindrome : ");

            if(head==null || head.next==null) {
                System.out.println("List is a palindrome");
                return;
            }

            Node middle = middleOfLinkedList();
            Node secondHalf = reverseLinkedListHelper(middle.next);
            middle.next = null;

            if(compare(head,secondHalf)){
                System.out.println("List is a palindrome");
            }else{
                System.out.println("List is not a palindrome");
            }
        }

        private boolean compare(Node firstHead, Node secondHead){

            Node temp1 = firstHead;
            Node temp2 = secondHead;

            while(temp1!=null && temp2!=null){
                if(temp1.data!= temp2.data){
                    return false;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return true;
        }

        public void reverseLinkedList(){
            System.out.println("Reversing the linked list");
            this.head = reverseLinkedListHelper(this.head);
        }
        private Node reverseLinkedListHelper(Node head){
            if(head==null || head.next==null){
                return head;
            }

            Node prev = null;
            Node curr = head;
            Node next = head.next;

            while(curr!=null){
                curr.next = prev;
                prev = curr;
                curr = next;
                if(curr!=null){
                    next = curr.next;
                }
            }
            head = prev;
            return head;
        }

        public void reverseLinkedListRecursively(){
            System.out.println("Reversing the linked list using recursion");
            if(head==null || head.next==null){
                return;
            }
            reverseLinkedListRecursivelyHelper(this.head);
        }
        private Node reverseLinkedListRecursivelyHelper(Node catcherNode) {
            //This node is the last node in list
            if (catcherNode == null) {
                return null;
            }

            Node recursiveAnswerNode = reverseLinkedListRecursivelyHelper(catcherNode.next);
            if (recursiveAnswerNode == null) {
                this.head = catcherNode;
                return catcherNode;
            }
            recursiveAnswerNode.next = catcherNode;
            catcherNode.next = null;

            return catcherNode;
        }

        public void findMiddleOfLinkedList(){
            seperator();
            Node ans = middleOfLinkedList();
            System.out.println("Finding middle for given list");
            display();
            System.out.print("Middle : ");
            if(ans == null){
                System.out.println("NULL");
                seperator();
                return;
            }
            System.out.println(ans.data);
            seperator();
        }

        private Node middleOfLinkedList(){

            if(head == null || head.next == null){
                return head;
            }

            Node slow = head;
            Node fast = head;

            while (fast.next != null && fast.next.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

    private Node middleOfLinkedList(Node head){

        if(head == null || head.next == null){
            return head;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public void mergeSort(){
          this.head = mergeSort(this.head);
    }

        private Node mergeSort(Node head){

            if(head==null || head.next==null){
                return head;
            }

            Node middleNode = middleOfLinkedList(head);
            Node listSecondHalf = middleNode.next;
            middleNode.next = null;
            Node leftHalfAns = mergeSort(head);
            Node rightHalfAns = mergeSort(listSecondHalf);


            return mergeTwoSortedLists(leftHalfAns,rightHalfAns);
        }

        public Node mergeTwoSortedLists(Node firstListHead, Node secondListHead){

            if(firstListHead == null || secondListHead == null){
                return firstListHead == null ? secondListHead:firstListHead;
            }

            //Dummy
            Node ans = new Node(-1);
            Node newHead = ans;

            while(firstListHead!=null && secondListHead!=null){
                if(firstListHead.data < secondListHead.data){
                    ans.next = firstListHead;
                    ans = ans.next;
                    firstListHead = firstListHead.next;
                }else{
                    ans.next = secondListHead;
                    ans =  ans.next;
                    secondListHead = secondListHead.next;
                }
            }

            if(firstListHead == null){
                ans.next = secondListHead;
            }

            if(secondListHead == null){
                ans.next = firstListHead;
            }

            return newHead.next;
        }

        //Instead of using the normal method of first counting the nodes, then subtracting from it by k
        //We will focus on 2 pointer method, first and second pointer
        //We will move any one pointer k steps ahead and then move both together, this way, once other pointer
        //reaches null, the first pointer will be k away from end
        public void removeKthElementFromEnd(int k){

            if(k<=0){
                System.out.println("Invalid value");
            }

            Node slow = this.head;
            Node fast = this.head;

            for(int i=0; i<k; i++){
                fast = fast.next;
            }
            if(fast == null){
                this.head = this.head.next;
            }

            while(fast.next!=null){
                fast=fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;

        }

        public void removeDuplicatesFromSortedLinkedList(){

            Node temp = head;

            while(temp!=null){
                Node isDuplicate = temp.next;
                while(isDuplicate!=null && (isDuplicate.data == temp.data)){
                    isDuplicate = isDuplicate.next;
                }
                temp.next = isDuplicate;
                temp = temp.next;
            }
        }

        public void oddEvenListSeparator(){
            //Either No Node or only One Node, so no need to do anything
            if(head==null || head.next==null){
                return;
            }

            Node even = new Node(-1);
            Node odd = new Node(-1);
            Node temp = head;
            Node oddHead = odd;
            Node evenHead = even;

            while(temp!=null){
                if(temp.data % 2 == 0){
                    even.next = temp;
                    even = even.next;
                }else{
                    odd.next = temp;
                    odd = odd.next;
                }
                temp = temp.next;
            }
            even.next = null;
            odd.next = evenHead.next;

            this.head = oddHead.next;
        }

    public int size(Node head) {

        if (head == null) {
            return 0;
        }

        Node temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }

    public void kReverseLinkedList(int k) {

        if (k == 1) {
            return;
        }

        Node temp = head;
        Node prev = null;
        int found = 0;
        int listSize = size(head);
        int visited = 0;

        while (temp != null) {
            int revLimit = 0;
            Node kTemp = temp;
            while (revLimit != (k - 1) && kTemp != null) {
                kTemp = kTemp.next;
                revLimit++;
            }
            visited = visited+revLimit+1;

            Node leftOverList = null;
            if (kTemp != null) {
                leftOverList = kTemp.next;
                kTemp.next = null;
            }

            Node reversedList = reverseLinkedListHelper(temp);

            found++;
            if (found == 1) {
                head = reversedList;
            }
            if (prev != null) {
                prev.next = reversedList;
            }
            prev = temp;
            temp = leftOverList;

            if((listSize-visited)/k == 0){
                prev.next = leftOverList;
                break;
            }

        }
    }

    private static void seperator(){
        System.out.println("==================================");
    }
}
