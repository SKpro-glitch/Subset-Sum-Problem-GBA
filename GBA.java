/**
 * Objective: To solve the Subset Sum Problem
 * Name: Greedy and Backtracking Algorithm
 */
import java.util.*;
public class GBA
{
    int s[]; //This is the array that holds the dataset.
    boolean found = false; //This is the boolean flag that indicates if a solution is found.
    int sub[]; //This is the array that is meant to hold the solution set.
    int sum = 0, cont = 0, n;
    /* Variable sum holds the total sum of all elements in the set.
     * Variable cont holds the unmber of continuous elements obtained in the recursive stages.
     * Variable n holds the size of the data set.
     */

    //This constructor initialises the dataset as random numbers in increasing order.
    //The difference between each consecutive element can be from 10 to n.
    //Comment this function and uncomment the next one to use the other function.
    GBA(int x)
    {
        int n = x;
        s = new int[n];
        sub = new int[n];
        s[0] = (int)(n*Math.random()) + 10; sum += s[0];
        for(int i=1; i<s.length; i++)
        {            
            s[i] = s[i-1] + (int)(n*Math.random()) + 10;
            sum += s[i];
        }     
    }

    //This constructor initialises the dataset to n multiples of 10.
    //Comment the previous function and uncomment this one to use this function.
    /*GBA(int n)
    {
    s = new int[n];
    sub = new int[n];
    for(int i=0; i<n; i++)
    {
    s[i] = 10 * (i + 1);
    sum += s[i];
    }
    }
     */

    //This function performs binary search on a desired value within a given range.
    int binarySearch(int k, long x)
    {
        //The pointer k indicates the size of the set to be searched in. This is virtual reduction of the dataset.
        int l = 0, r = k, m=0;
        while (l <= r) 
        {
            m = (r + l) / 2;
            if (s[m] == x)
                return m;
            if (s[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }

        //If the desired value is found, it is returned. Else the next largest value is returned.
        if(s[m]<x)
            return m;
        else
            return (m-1);
    }

    //This is the function that implements the Greedy and Backtracking Algorithm to 'Search' for the solution.
    void Search(int p, int k0, int k, long cap)
    {
        int i;
        //The cont variable is incremented if the current and previous selected elements are adjacent.
        if(k0-k==1)
            cont++;
        else //If even one non-adjacent element is encountered, cont is reset.
            cont = 0;
        
        //This loop iterates through every element before the selected element.
        for(i=k; i>=0 && !found; i--)
        {
            long c = cap - s[i];
            //sub[p] = s[i]; //This line creates an array of the solution set. Uncomment to use.
            if(c==0)
            {
                found = true;
                //print(sub); //This line prints the solution set once found. Uncomment to use.
            }
            else if(p<s.length-1)
                Search(p+1, k, binarySearch(i-1, c), c);
            //sub[p] = 0; //This line removes the unwanted values from the array of the solution set. Uncomment to use.
            if(cont>=k)
                return;
        }
    }

    //This function is meant to print the Solution Set array.
    void print(int arr[])
    {
        System.out.println("The first subset that has the desired sum is:");
        int i = 0;
        for(i=0; i<arr.length && arr[i]!=0; i++)
            System.out.print(" " + arr[i]);
        System.out.println("\nNumber of elements: " + i);
    }

    //This code was implmented in BlueJ hence there is no argument in main function.
    //To implement this code in another terminal, please use the function name "main(String ags[])".
    public static void main()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n:");
        int n = sc.nextInt(); //Size of State Space
        GBA ob = new GBA(n);
        System.out.println("Enter the desired sum: ");
        //int cap = ob.sum; //This line uses the sum of all elements as the Target Sum. Uncomment this line to use it. 
        int cap = sc.nextInt(); //This line takes Target Sum as user input. Comment this line to use the previous one.
        System.out.println(ob.sum); //Prints the sum of all elements, for the user's reference.
        int k = ob.s.length-1;
        
        long start = System.nanoTime(); //Timer is started.
        ob.Search(0, 0, k, cap);
        long end = System.nanoTime(); //Timer is ended.
        if(!ob.found)
            System.out.println("None");
        else
            System.out.println("Found");
        System.out.println("\nTime taken = " + (end - start) + " ns"); //Time taken to find the solution in Nanoseonds is printed.
    }
}
