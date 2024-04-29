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
    }

}
