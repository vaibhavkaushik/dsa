package Recursion;

public class RecursionBasics {

    public static void printFromNtoX(int n, int x){

        if(n == x){
            System.out.println(n);
            return;
        }

        System.out.println(n);
        printFromNtoX(n+1,x);
    }

    public static void printFromXtoNUsingPreRecursion(int n, int x){

        if(n == x){
            System.out.println(x);
            return;
        }

        System.out.println(x);
        printFromXtoNUsingPreRecursion(n,x-1);
    }

    public static void printFromXtoNUsingPostRecursion(int n, int x){

        if(n == x){
            System.out.println(n);
            return;
        }

        printFromXtoNUsingPostRecursion(n+1,x);
        System.out.println(n);
    }

    public static int NthFibonacciNumber(int n){
        if(n==0){
            return 0;
        }

        if(n==1){
            return 1;
        }

        int nMinusOneThFib = NthFibonacciNumber(n-1);
        int nMinusTwoThFib = NthFibonacciNumber(n-2);

        int nthFib = nMinusOneThFib+nMinusTwoThFib;

        return nthFib;
    }

    //We need to take start and end value in the function arguments as these values are changing in every call and are
    //responsible for shortening the call stack
    public static boolean binarySearchUsingRecursion(int[] arr,int start,int end, int searchValue){

        int mid = (start+end)/2;

        if(arr[mid] == searchValue){
            return true;
        }

        if(end<0 || start>=arr.length){
            return false;
        }

        boolean foundInLeft = false;
        boolean foundInRight = false;

        if(searchValue < arr[mid]){
            foundInLeft = binarySearchUsingRecursion(arr,start,mid-1,searchValue);
        }
        else{
            foundInRight = binarySearchUsingRecursion(arr,mid+1,end,searchValue);
        }

        boolean finallyFound = foundInLeft || foundInRight;

        return finallyFound;
    }

    public static int factorial(int n){
        if(n==0){
            return 1;
        }
        int nMinusOneThFactorial = factorial(n-1);

        //Answer of nth factorial is, n times of (n-1)th factorial
        return n*nMinusOneThFactorial;
    }

    static int search(int[] arr, int target, int s, int e) {
        if (s > e) {
            return -1;
        }
        int m = s + (e - s) / 2;
        if (arr[m] == target) {
            return m;
        }
        if (target < arr[m]) {
            return search(arr, target, s, m - 1);
        }
        return search(arr, target, m + 1, e);
    }

    static int sumOntoNthNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int sumOntoNMinusOneThNumber = sumOntoNthNumber(n-1);
        return n+sumOntoNMinusOneThNumber;
    }

    static int sumOfDigits(int n){

        if(n==0){
            return 0;
        }

        int currentDigit = n%10;
        int sumOfRemainingNumber = sumOfDigits(n/10);

        int finalAns = currentDigit + sumOfRemainingNumber;

        return finalAns;
    }

    static int productOfDigits(int n){

        if((n%10) == n){
            return n;
        }

        int currentDigit = n%10;
        int sumOfRemainingNumber = productOfDigits(n/10);

        int finalAns = currentDigit * sumOfRemainingNumber;

        return finalAns;
    }

    public static int reverseANumber(int n){
        if(n%10==n){
            return n;
        }

        int extractedDigit = n%10;
        int reverseOfRemainingNumber = reverseANumber(n/10);

        int number = reverseOfRemainingNumber;
        int digit_count = 0;
        while (number!=0){
            digit_count++;
            number= number/10;
        }
        int reversedNumber = (int) (extractedDigit*Math.pow(10,digit_count) + reverseOfRemainingNumber);

        return reversedNumber;
    }

    public static int digitCount(int num){
        if(num%10 == num){
            return 1;
        }

        return  1 + digitCount(num/10);
    }

    public static boolean isPalindrome(int n){

        if(n%10 == n){
            return true;
        }

        int digits = digitCount(n);
        int firstDigit = (int) (n/Math.pow(10,digits-1));
        int lastDigit = n%10;
        int newNum = n - (int) (firstDigit*Math.pow(10,digits-1)) - lastDigit;
        boolean palindrome = (firstDigit==lastDigit) && isPalindrome(newNum/10);

        return palindrome;
    }

    public static int findInArrayUsingRecursion(int[] arr, int idx, int val){

        if(arr.length == idx){
            return -1;
        }

        if(arr[idx]==val){
            return idx;
        }

        return findInArrayUsingRecursion(arr,idx+1,val);
    }

    public static int countZeroes(int num){

        if(num==0){
            return 0;
        }

        int countInThisNum = 0;
        int digit = num%10;

        if(digit == 0){
            countInThisNum+=1;
        }

        int ans = countInThisNum + countZeroes(num/10);
        return ans;

    }

    public static int countZeroesParameterApproach(int num, int count){

        if(num==0){
            return count;
        }

        int digit = num%10;

        if(digit == 0){
            return countZeroesParameterApproach(num/10,count+1);
        }

        return countZeroesParameterApproach(num/10,count);
    }

    //if num is even, divide by 2
    //if num is odd, subtract 1
    public static int stepsToZeroParameterApproach(int num, int steps){
        if(num == 0){
            return steps;
        }

        if(num%2==0){
            return stepsToZeroParameterApproach(num/2,steps+1);
        }else{
            return stepsToZeroParameterApproach(num-1,steps+1);
        }

    }

    public static int stepsToZero(int num){
        if(num == 0){
            return 0;
        }

        int divisonCount = 0;
        int subtractCount = 0;

        if(num%2==0){
            divisonCount = 1 + divisonCount + stepsToZero(num/2);
        }else{
            subtractCount = 1 + subtractCount + stepsToZero(num-1);
        }

        int finalAns = divisonCount+subtractCount;

        return finalAns;

    }

    public static boolean findIfArraySorted(int[] arr, int idx){
        if(arr.length-1 == idx){
            return true;
        }

        boolean isSmall = false;
        if(idx < arr.length - 1){
            isSmall = arr[idx] <= arr[idx+1];
        }

        boolean finalAns = isSmall && findIfArraySorted(arr,idx+1);

        return finalAns;
    }

    //f(n--) wont work, f(--n) works

    public static void main(String[] args) {
        printFromNtoX(3,10);
        System.out.println("------");
        printFromXtoNUsingPreRecursion(3,10);
        System.out.println("------");
        printFromXtoNUsingPostRecursion(3,10);
        System.out.println("------");
        System.out.println(NthFibonacciNumber(8));
        int[] arr = new int[]{5,6,7,12};
        System.out.println(binarySearchUsingRecursion(arr,0, arr.length-1, 4));
        System.out.println(factorial(5));
        System.out.println(sumOntoNthNumber(4));
        System.out.println(sumOfDigits(2543));
        System.out.println(productOfDigits(2543));
        System.out.println(reverseANumber(175678));
        System.out.println(digitCount(2346));
        System.out.println(isPalindrome(1231));
        System.out.println(findInArrayUsingRecursion(arr,0,16));
        System.out.println(countZeroes(10204050));
        System.out.println(countZeroesParameterApproach(10204050,0));
        System.out.println(stepsToZero(45)); // 23 22 11 10 5 4 2 1 0
        System.out.println(findIfArraySorted(arr,0));
    }

}
