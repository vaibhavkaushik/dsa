package LinkedList.SinglyLinkedList;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Node addTwoLinkedList(Node firstHead, Node secondHead) {
        if (firstHead == null || secondHead == null) {
            return firstHead == null ? secondHead : firstHead;
        }

        Node tempFirstHead = firstHead;
        Node tempSecondHead = secondHead;
        Node tempAns = new Node(-1);
        Node tempAnsHead = tempAns;
        int carry = 0;

        while (tempFirstHead != null && tempSecondHead != null) {
            int num1 = tempFirstHead.data;
            int num2 = tempSecondHead.data;

            int sum = num1 + num2 + carry;
            carry = sum / 10;
            int unit_dig = sum % 10;

            tempAns.next = new Node(unit_dig);
            tempAns = tempAns.next;
            tempFirstHead = tempFirstHead.next;
            tempSecondHead = tempSecondHead.next;
        }
        if (carry != 0) {
            if (tempFirstHead != null) {
                while (tempFirstHead != null) {
                    int sum = tempFirstHead.data + carry;
                    carry = sum / 10;
                    int unit_dig = sum % 10;

                    tempAns.next = new Node(unit_dig);
                    tempAns = tempAns.next;
                    tempFirstHead = tempFirstHead.next;
                }
            } else if (tempSecondHead != null) {
                while (tempSecondHead != null) {
                    int sum = tempSecondHead.data + carry;
                    carry = sum / 10;
                    int unit_dig = sum % 10;

                    tempAns.next = new Node(unit_dig);
                    tempAns = tempAns.next;
                    tempSecondHead = tempSecondHead.next;
                }
            } else{
                tempAns.next = new Node(carry);
            }
        } else {
            if(tempFirstHead!=null){
                tempAns.next = tempFirstHead;
            }else{
                tempAns.next = tempSecondHead;
            }
        }

        if(carry!=0){
            tempAns.next = new Node(carry);
        }


        return tempAnsHead.next;
    }

    public Node finsIntersectionPointOfLists(Node firstListHead, Node secondListHead){
            if(firstListHead==null || secondListHead==null){
                return null;
            }

            int firstListSize = size(firstListHead);
            int secondListSize = size(secondListHead);

            Node preTraverseHead = (firstListSize > secondListSize) ? firstListHead : secondListHead;
            boolean isFirstHeadTraverseReq = (firstListSize > secondListSize);
            int preTraverse = Math.abs((firstListSize-secondListSize));

            while (preTraverse > 0){
                preTraverseHead = preTraverseHead.next;
                preTraverse--;
            }

            Node firstHeadTemp = isFirstHeadTraverseReq ? preTraverseHead : firstListHead;
            Node secondHeadTemp = isFirstHeadTraverseReq ? secondListHead : preTraverseHead;


            while(firstHeadTemp!=null && secondHeadTemp!=null){
                if(firstHeadTemp==secondHeadTemp){
                    return firstHeadTemp;
                }
                firstHeadTemp = firstHeadTemp.next;
                secondHeadTemp = secondHeadTemp.next;
            }
            return null;
    }

    // Given a linked list : 1->2->3->4->5->6->7->8->9
    // Fold of a linked list : 1->9->2->8->3->7->4->6->5
    // Algo : Find middle, reverse the other list, start joining pointers
    public Node foldOfALinkedList(Node head){

            if(head == null || head.next == null){
                return head;
            }

            Node middleNode = middleOfLinkedList(head);
            Node secondHalf = middleNode.next;
            middleNode.next = null;

            //First Half : 1->2->3->4->5
            //Second Half : 9->8->7->6
            Node reversedSecondHalf = reverseLinkedListHelper(secondHalf);

            //Start connecting the pointers
            Node ans = new Node(-1);
            Node ansHead = ans;
            Node temp = head;
            Node secondTemp = reversedSecondHalf;
            ans.next = temp;
            ans = ans.next;
            int forward_connect = 1;
            while(temp!=null && secondTemp!=null){
                if(forward_connect % 2 == 1){
                    temp = temp.next;
                    ans.next = secondTemp;
                    ans = ans.next;

                }else{
                    secondTemp = secondTemp.next;
                    ans.next = temp;
                    ans = ans.next;

                }
                forward_connect++;
            }

            return ansHead.next;
    }

    // Given a linked list : 1->9->2->8->3->7->4->6->5
    // Unfold of a linked list : 1->2->3->4->5->6->7->8->9
    // Algo : Connect alternative nodes,
    public Node unFoldOfALinkedList(Node head){

        if(head == null || head.next == null){
            return head;
        }

        Node firstHalf = head;
        Node tempFirst = firstHalf;
        Node secondHalf = head.next;
        Node tempSecond = secondHalf;

        while(firstHalf!=null && firstHalf.next!=null && secondHalf!=null && secondHalf.next!=null){
            firstHalf.next = firstHalf.next.next;
            firstHalf = firstHalf.next;
            secondHalf.next = secondHalf.next.next;
            secondHalf = secondHalf.next;
        }
        tempSecond = reverseLinkedListHelper(tempSecond);
        if(firstHalf!=null)
            firstHalf.next = tempSecond;

        return tempFirst;
    }

    // Function to detect a loop in a linked list
    // using the Tortoise and Hare Algorithm
    public boolean detectCycle(Node head) {
        // Initialize two pointers, slow and fast,
        // to the head of the linked list
        Node slow = head;
        Node fast = head;

        // Step 2: Traverse the linked list
        // with the slow and fast pointers
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;
            // Move fast two steps
            fast = fast.next.next;

            // Check if slow and fast pointers meet
            if (slow == fast) {
                return true;  // Loop detected
            }
        }

        // If fast reaches the end of the
        // list, there is no loop
        return false;
    }

    //Not optimized
    public Node detectLoop(Node head) {
        // Use temp to traverse the linked list
        Node temp = head;

        // HashMap to store all visited nodes
        HashMap<Node, Integer> nodeMap = new HashMap<>();

        // Traverse the list using temp
        while (temp != null) {
            // Check if temp has been encountered again
            if (nodeMap.containsKey(temp)) {
                // A loop is detected, hence return temp
                return temp;
            }

            // Store temp as visited
            nodeMap.put(temp, 1);

            // Iterate through the list
            temp = temp.next;
        }

        // If no loop is detected, return null
        return null;
    }

    //Optimal Approach. No extra space required
    public Node firstNode(Node head) {
        // Initialize a slow and fast
        // pointers to the head of the list
        Node slow = head;
        Node fast = head;

        // Phase 1: Detect the loop
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;

            // Move fast two steps
            fast = fast.next.next;

            // If slow and fast meet,
            // a loop is detected
            if (slow == fast) {
                // Reset the slow pointer
                // to the head of the list
                slow = head;

                // Phase 2: Find the first node of the loop
                while (slow != fast) {
                    // Move slow and fast one step
                    // at a time
                    slow = slow.next;
                    fast = fast.next;

                    // When slow and fast meet again,
                    // it's the first node of the loop
                }

                // Return the first node of the loop
                return slow;
            }
        }

        // If no loop is found, return null
        return null;
    }

    //We need to pre-process the list since it can contain padded 0s, which we don't want
    public Node preProcessList(Node head){
        if(head == null){
            return null;
        }

        Node temp = head;

        while(temp!=null){
            if(temp.data != 0){
                break;
            }
            temp = temp.next;
        }

        return temp;
    }

    //Assumption :
    //Linked list can contain  initial 0s, one list can be bigger than other
    public Node subtractLists(Node firstListHead, Node secondListHead){

            firstListHead = preProcessList(firstListHead);
            secondListHead = preProcessList(secondListHead);

            Node savedFirstHead = firstListHead;
            Node savedSecondHead = secondListHead;

        int sizeFirstList = size(firstListHead);
        int sizeSecondList = size(secondListHead);

        if(sizeFirstList != sizeSecondList) {
            firstListHead = (sizeFirstList > sizeSecondList) ? savedFirstHead : savedSecondHead;
            secondListHead = (sizeFirstList > sizeSecondList) ? savedSecondHead : savedFirstHead;
        }
        else {
            firstListHead = (firstListHead.data > secondListHead.data) ? savedFirstHead : savedSecondHead;
            secondListHead = (firstListHead.data > secondListHead.data) ? savedSecondHead : savedFirstHead;
        }

            firstListHead = reverseLinkedListHelper(firstListHead);
            secondListHead = reverseLinkedListHelper(secondListHead);



            Node tempFirst = firstListHead;
            Node tempSecond = secondListHead;
            Node ansNode = new Node(-1);
            Node ansHead = ansNode;
            int borrow = 0;

            //Assumption, always make the bigger list as the firstListHead;
            while(tempFirst!=null || tempSecond!=null){

                int val1 = (tempFirst!=null) ? tempFirst.data : 0;
                int val2 = (tempSecond!=null) ? tempSecond.data : 0;

                int ans = val1 - val2 + borrow;
                if(ans < 0){
                    borrow = -1;
                    ans+=10;
                }else{
                    borrow = 0;
                }

                ansNode.next = new Node(ans);
                ansNode = ansNode.next;

                if(tempFirst!=null) tempFirst = tempFirst.next;
                if(tempSecond!=null) tempSecond = tempSecond.next;
            }

            ansHead.next = reverseLinkedListHelper(ansHead.next);

            ansHead.next = preProcessList(ansHead.next);

        return ansHead.next;

    }

    public Node multiplyLinkedLists(Node firstListHead, Node secondListHead){

            if(firstListHead == null || secondListHead == null){
                return null;
            }

            //Assume for now that complete list1 > list2
            Node multiplicand = firstListHead;
            Node multiplier = secondListHead;

            multiplicand = reverseLinkedListHelper(multiplicand);
            multiplier = reverseLinkedListHelper(multiplier);
            ArrayList<Node> ansArrayList = new ArrayList<>();
            int k = 0;
            while(multiplier!=null){
                Node multiplicandItr = multiplicand;
                int multiplier_val = multiplier.data;
                int carry = 0;
                int zeroes = k;
                Node ansNode = new Node(-1);
                Node ansNodeHead = ansNode;
                while (zeroes > 0){
                    ansNode.next = new Node(0);
                    ansNode = ansNode.next;
                    zeroes--;
                }
                while(multiplicandItr!=null){
                    int multiplicand_val = multiplicandItr.data;
                    int product = multiplier_val*multiplicand_val + carry;
                    carry = product/10;
                    ansNode.next = new Node(product%10);
                    ansNode = ansNode.next;
                    multiplicandItr = multiplicandItr.next;
                }
                if(carry!=0){
                    ansNode.next = new Node(carry);
                }
                ansNodeHead.next = reverseLinkedListHelper(ansNodeHead.next);
                ansArrayList.add(ansNodeHead.next);
                multiplier = multiplier.next;
                k++;
            }

            Node finalAns = new Node(0);
            //Too much reversal is being done here, since the add two linked list function is not taking care of it
            //If we take care of that, then no need to do multiple reverse
            for(Node head : ansArrayList){
                finalAns = reverseLinkedListHelper(finalAns);
                head = reverseLinkedListHelper(head);
                finalAns = reverseLinkedListHelper(addTwoLinkedList(finalAns,head));
            }

            return finalAns;
    }



    private static void seperator(){
        System.out.println("==================================");
    }
}
