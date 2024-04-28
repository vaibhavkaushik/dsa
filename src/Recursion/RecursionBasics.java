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
    }

}
