
/*
 * @author   Vaishakhi Kulkarni
 * @Net Id : vpk140230
 * 
 */

/* *******
Net Id:vpk140230
Name: Vaishakhi Kulkarni 
*/

package DAY1;
import java.util.Scanner;

public class FibonacciSeriesF {
	
	private static int phase = 0;
    private static long startTime, endTime, elapsedTime;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long n,fib=0;
		int p;
		
		//Base Matrix
	    long[][] calmatrix = { {1,1},{1,0} };
	    
	    //Store the calculated value
	    long[][] fib1 = new long[2][2];
	    
		Scanner in = new Scanner(System.in);
		
		//Enter Long number
		System.out.println("Enter a long number");
	    n = in.nextLong();
	    //Enter value of p
		System.out.println("Enter an integer");
	    p = in.nextInt();
	    in.close();
	   
	    //Iterative Approach 
	    timer();
	    System.out.println("Iterative approach:");
	    fib = fibitr(n,p);
	    System.out.println(fib);
	    timer();
	    
	    //Divide and Conquer Approach
	    timer();   
	    System.out.println("Divide and Conquer");
	    if(n==0)
	    	System.out.println(n);
	    else
	    {
	    fib1 = fibdac(n-1,p,calmatrix);
	    System.out.println(fib1[0][0]);
	    }
	    timer();
	    
	}
	
	//Iterative Function
	public static long fibitr (long n,int p)
	{
	    long temp=0,temp1=0,result=0;
	    if(n<2)
	    {
	    	return n;
	    }
	    else
	    {
	    	temp = 0; // when fib[0]= 0
		    temp1 = 1; //when fib[1]=1
	    	for(int i=2;i<=n;i++)
	    	{
	    		result = (temp + temp1) % p;
	    		temp = temp1;
	    		temp1 =result;	
	    	}
	    	return result;
	    }	
	}
	
	//Divide and Conquer with recursion
	public static long[][] fibdac(long n,int p,long[][] calmatrix)
	{
	    
		long[][] fibbydac1 = new long[2][2];
	    long[][] fibbydac2 = new long[2][2];
	    // When n =1 
		if(n==1)
			return calmatrix;
		//if(n > 1)
	    else
		{
			fibbydac1 =  fibdac(n/2,p,calmatrix);
		    if(n % 2 != 0)
	    	{
		    	// When n is odd
		      fibbydac2 = multiply(fibbydac1,fibbydac1,n,p);
		       return multiply(fibbydac2,calmatrix,n,p);
	    	}
		    else
			{	
		    	//When n  is even 
		    	return multiply(fibbydac1,fibbydac1,n,p);
			}
		}
	}
	
	//Matrix multiplication 
	public static long[][] multiply(long first[][],long second[][],long n,int p)
	{

        long[][] fibbydac = new long[2][2];
        int i,j;
        
		for(i=0;i<2;i++)
		{
	        for(j=0;j<2;j++)
	        {
	        	for(int k=0;k<2;k++)
	        	{
	                fibbydac[i][j] = (fibbydac[i][j] + (first[i][k] * second[k][j]));   
	            }
	        	fibbydac[i][j] = fibbydac[i][j]%p;
	        }
		
        }
		return fibbydac;
	}
	
	//Function to find the performance in nanosec
	public static void timer()
    {
        if(phase == 0) {
	    startTime = System.nanoTime();
	    phase = 1;
	    } else {
	    endTime = System.nanoTime();
            elapsedTime = endTime-startTime;
            System.out.println("Time: " + elapsedTime + " nsec.");
            memory();
            phase = 0;
        }
    }

	//Function to find the memory usage
    public static void memory() {
        long memAvailable = Runtime.getRuntime().totalMemory();
        long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
    }
}


