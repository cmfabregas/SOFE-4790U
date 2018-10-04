import java.net.*;
import java.io.*;

public class Server 
{

	public static void main(String args[]) 
	{
		/*if(args.length !=1) 
		{
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}
		
		int portNumber = Integer.parseInt(args[0]);*/
		Server es = new Server();
		es.startServer(3500);
		
	
	}
	
	public void startServer(int portNum) 
	{
		try {
				ServerSocket serverSocket = new ServerSocket(portNum);
				while(true) 
				{
					Socket client = serverSocket.accept();
					Connection cc = new Connection(client);
				} 	
			}catch(Exception e) 
			{
				System.out.println("Exception:" +e);	
			}
	}
	
	
	
}

class Connection extends Thread{
	Socket client;
	PrintWriter out;
	//BufferedReader in;
	
	DataInputStream r;
    DataOutputStream w;
	
	public Connection(Socket s) //constructor
	{
		client = s;
		
		try 
		{
			
			/*out = new PrintWriter(client.getOutputStream(),true);
			in = new BufferedReader(
				new InputStreamReader(client.getInputStream()));*/
			r = new DataInputStream(s.getInputStream());
			w = new DataOutputStream(s.getOutputStream());
			
			
			
			
		}catch (IOException e) 
		{
			try {
	             client.close();
	           } catch (IOException ex) {
	             System.out.println("Error while getting socket streams.."+ex);
	           }
	           return;
	    }
		this.start();
	}

	//calculates monthly payment
public double monthlyPayment(double loanAmount, double monthlyInterestRate, int numYears) {
	
	double payment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, numYears * 12)));
	 
	return payment;
}


	
public void run() {
	try {
		
		double annualInterestRate, monthlyInterestRate, loanAmount, monthlypayment, totalPayment;
		int numYears;
		
		annualInterestRate = r.readDouble();
		System.out.println("Annual Interest Rate is: "+ annualInterestRate);
		
		monthlyInterestRate = annualInterestRate / 1200;
		
		numYears = r.readInt();
		System.out.println("Number of years is " + numYears);
		
		loanAmount = r.readDouble();
		System.out.println("the loan amount is: $" + loanAmount);
		
		monthlypayment = monthlyPayment(loanAmount,monthlyInterestRate, numYears);
		
		totalPayment = monthlypayment * numYears * 12;
		
		w.writeInt((int)monthlypayment);
		w.flush();
		w.writeInt((int) totalPayment);
		System.out.println("the monthly payment is: "+ (int)monthlypayment);
		
		System.out.println("the total payment is: "+ (int)totalPayment);
			
		
		client.close();
	}catch (IOException e) {
        System.out.println("Exception caught...");
        System.out.println(e.getMessage());
    }
}
}
