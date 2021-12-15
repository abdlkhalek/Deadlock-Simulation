package com.deadlock;

/**
 * <h1> Banker Algorithm Class<h1>
 * a simulation for Banker Algorithm 
 * <p> 
 * @author Abdlkhalek Alalimi , Osama Alrohani , Norhan , Rokaia 
 * @since 04 - 2021
 * @version 1.0
 * @see mustafa sadiq on youtube video # 44 to get everything clearly.
 * 	Ensure that system will never enter deadlock state(deadlock avoidance)
 * 
 *  1- an algorithm for multiple instances for each type of resources
 *  2- each process declare the maximum number of resources of each type that may need (a priori information).
 *  3- when a resources gets all its resources it must return them in finite amount of time.
 *  
 *  data structure needed in this application (Allocation , Maximum , available , need).
 *  
 **/

public class BankerAlgorithm{

	// the dataStructure we use
	private int available[]; // available resources.
	private int need[][];   //  maximum[i][j] - allocation[i][j]
	private int maximum[][]; // maximum number of the resources that processes will need  
	private int allocation[][]; // 
	private int processesNumber; // the number of process .
	private int resoucesNumber;  // the number of resources.
	
	
	/**
	 *  @param no parameters
	 *  a default constructor 
	 */ 
	public BankerAlgorithm(){
		
	} // end of default constructor
	
	/**
	 * @param int[] available, int[][] need, int[][] maximum,int[][] allocation, int processesNumber,int resoucesNumber
	 * parametarized constructor
	 * <p>
	 **/
	public BankerAlgorithm(int available[], int need[][], int maximum[][], 
			int allocation[][], int processesNumber,int resoucesNumber) {
		
		this.available = available;
		this.need = need;
		this.maximum = maximum;
		this.allocation = allocation;
		this.processesNumber = processesNumber;
		this.resoucesNumber = resoucesNumber;
		allocateNeed();
	} // end of parametarized constructor 

	/**
	 *	@param no parameters
	 *	a function to allocate the need array with (need[i][j]=maximum[i][j]-allocation[i][j];).
	 *	<p>
	 **/
	private void allocateNeed()
    {
        for(int i=0;i<processesNumber;i++)
        {
            for(int j=0;j< resoucesNumber;j++)
            {
                need[i][j]=maximum[i][j]-allocation[i][j];
            }
        }
    } // end of allocateNeed
	
	
	
	/**
	 * 	@param int processNumber_,int [] req
	 *	@throws InterruptedException
	 *	<h1>request function</h1>
	 *	that will simulate the request by doing all function system do.
	 *	<p>  
	 *
	 **/
	public void request(int processNumber_,int [] req) throws InterruptedException {
		
		boolean helper = true; // to check if the req[i] < need[processNumber_][i] && req[i] <= available
		System.out.println("Checking the difference between req , need ,and available ....");
		for (int i=0;i<resoucesNumber;++i) {
			if (req[i] > need[processNumber_][i] && req[i] >available[i]) {
				helper=false;
				break;
			}
		}
		
		if (helper) { // means if the request is good to be implemented so get the need
			System.out.print("Safe request(approved).\nProcessing... ");
			Thread.sleep(2000);
			System.out.println("available: ");
			print1D(available);
			System.out.println("aallocation: ");
			print2D(allocation);
			System.out.println("need:");
			print2D(need);
			for (int i=0;i<resoucesNumber;++i) {
				available[i]-= req[i];
				allocation[processNumber_][i]+=req[i];
				need[processNumber_][i]-=req[i];
			}
			System.out.println("_______________________________________\nafter proceesing");
			System.out.println("available: ");
			print1D(available);
			System.out.println("aallocation: ");
			print2D(allocation);
			System.out.println("need:");
			print2D(need);
			System.out.println("Releasing...");
			release(processNumber_,req);
			return;
		}
		System.out.println("Unsafe request(Denied).");
	} // end of request 
	
	/**
	 *	@param arr[]
	 *	<h1>print1D</h1>
	 *	to print 1D array will be used in other functions
	 *	<p>
	 **/
	private void print1D( int arr[]){
		for (int i=0;i<arr.length;++i) {
			System.out.println(arr[i] + "  ");
		}
	} // end of print1D
	
	/**
	 *	@param arr[]
	 *	<h1>print2D</h1>
	 *	to print 2D array will be used in other functions
	 *	<p>
	 **/
	private void print2D(int arr[][]) {
		for (int[] i : arr)
		{
		   for (int j : i)
		   {
		        System.out.print(j + " ");
		   }
		   System.out.println();
		}
	} // end of print2D
	
	/**
	 * 	@param int processNumber_,int [] req
	 *	<h1>release function</h1>
	 *	that will simulate the release by doing all function system do.
	 *	<p>  
	 *
	 **/
	private void release(int processNumber_,int req[]) {
		
		boolean helper=false;
		for (int i=0;i<resoucesNumber;++i) {
			if (allocation[processNumber_][i] < req[i]) {
				helper=true;
				break;
			}
		}
		
		if (!helper) {
			for (int i=0;i<resoucesNumber;++i) {
				available[i]+= req[i];
				allocation[processNumber_][i]-=req[i];
				need[processNumber_][i]+=req[i];
			}
			System.out.println("available: ");
			print1D(available);
			System.out.println("aallocation: ");
			print2D(allocation);
			System.out.println("need:");
			print2D(need);
		}
		
	} // end of release 
	
}// end of class