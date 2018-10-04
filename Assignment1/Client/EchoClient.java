package Client;

import java.io.*;
import java.net.*;

public class EchoClient {
	
	
	private double annualInterestRate, loanAmount;
	private int numYears , totalMonthly,totalPayment;
	
	Socket echo;
    DataInputStream br;
    DataOutputStream dos;
	
	public EchoClient(double annualInterestRate , int numYears, double loanAmount) {
		
		this.annualInterestRate = annualInterestRate;
		this.loanAmount = loanAmount;
		this.numYears = numYears;
		
	}
	
	public int getTotalMonthly() {
		return totalMonthly;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	
	public void startClient() throws IOException {
		echo = new Socket("localhost", 3500);
	    br = new DataInputStream(echo.getInputStream());
	    dos = new DataOutputStream(echo.getOutputStream());
	    
	    
	    
	    dos.writeDouble(annualInterestRate);
	    dos.flush();
	    
	    dos.writeInt(numYears);
	    dos.flush();
	    
	    dos.writeDouble(loanAmount);
	    dos.flush();
	    
	    

	    totalMonthly = br.readInt();
	    totalPayment = br.readInt();  
	     System.out.println("The total monthly payment is $"+ totalMonthly);
	     System.out.println("The total payment is $"+ totalPayment);
	}	
}

