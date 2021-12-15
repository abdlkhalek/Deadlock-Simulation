package com.deadlock;


import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws InterruptedException {

		Scanner getter = new Scanner(System.in);
		int available[]={3,3,2}; // available resources.    
		int maximum[][]={
                {7,5,3},
                {3,2,2},
                {9,0,2},
                {2,2,2},
                {4,3,3}
        }; // maximum number of the resources that processes will need  
		int allocation[][]={
                {0,1,0},
                {2,0,0},
                {3,0,2},
                {2,1,1},
                {0,0,2}
        };; // 
        
        
		int processesNumber; // the number of process .
		int resoucesNumber;  // the number of resources.
		
		System.out.print("Enter the number of process . " );
		processesNumber = getter.nextInt();
		System.out.print("\nEnter the number of resources : ");
		resoucesNumber = getter.nextInt();
		
		int need[][]= new int[processesNumber][resoucesNumber];
		
		BankerAlgorithm b = new BankerAlgorithm( available,  need,  maximum, 
				 allocation,  processesNumber, resoucesNumber);
		// start working
		int req[]= {1,0,2};
		b.request(1, req); // calling request function.
		System.out.println("All Works Done");
		getter.close(); // closing the getter scanner to avoid resource leak.
	}

}
