package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class InterestCalculator {

	private JFrame frame;
	private final Action action = new SwingAction();
	
	private int monthlyPayment;
	private int totalPayment;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterestCalculator window = new InterestCalculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterestCalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c) && c !=8 && c !=46) {
					e.consume();
				}
			}
		});
		textField.setBounds(159, 70, 130, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c) && c !=8 && c !=46) {
					e.consume();
				}
			}
		});
		textField_1.setBounds(159, 150, 130, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAnnualInterestRate = new JLabel("Annual Interest Rate:");
		lblAnnualInterestRate.setBounds(20, 70, 136, 16);
		frame.getContentPane().add(lblAnnualInterestRate);
		
		JLabel lblNewLabel = new JLabel("Loan Amount:");
		lblNewLabel.setBounds(20, 150, 88, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		for(int i=1; i <=40; i++) {
			comboBox.addItem(i);
		}
		comboBox.setBounds(159, 110, 130, 30);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNumberOfYears = new JLabel("Number of Years");
		lblNumberOfYears.setBounds(20, 110, 126, 16);
		frame.getContentPane().add(lblNumberOfYears);
		
		
		JButton btnNewButton = new JButton("Result");
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int numYears = Integer.parseInt(comboBox.getSelectedItem().toString());
				double interestRate = Double.parseDouble(textField.getText());
				double loanAmount = Double.parseDouble(textField_1.getText());
				EchoClient client = new EchoClient(interestRate, numYears, loanAmount); // annualInterestRate, years, loan amount
				
				try {
					client.startClient();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				monthlyPayment = client.getTotalMonthly();
				totalPayment = client.getTotalPayment();
				
				JOptionPane.showMessageDialog(null, "Your monthly payment is $"+ monthlyPayment + " Your total payment is $" + totalPayment, "Results", JOptionPane.INFORMATION_MESSAGE);
			
			}
			
		});
		btnNewButton.setBounds(309, 228, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Result");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
