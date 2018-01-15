

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.*;

public class GuiMain extends JFrame implements ActionListener,WindowListener
{
    static TextArea input;
    static TextArea output;
    static TextField filelocation;
     Button testButon;
    static boolean testButtonPressed=false;
     GuiMain(){
     getContentPane().setLayout(null);
     setupGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     String buffer="";
   }
   void setupGUI()
   {
     	input = new TextArea();
	input.setLocation(20,20);
	input.setSize(400,300);
	input.setText("");
	input.setRows(5);
	input.setColumns(5);

	getContentPane().add(input);
	
 	filelocation = new TextField();
 	filelocation.setLocation(20,320);
 	filelocation.setSize(300,25);
 	filelocation.setText("E://test.txt");
	filelocation.setEditable(true);
	
	filelocation.setColumns(20);
	getContentPane().add(filelocation);
	output = new TextArea();
	output.setLocation(20,350);
	output.setSize(403,107);
	output.setForeground( new Color(-16711885) );
	output.setBackground( new Color(-13421773) );
	output.setText("welcome to RGameEditor\n");
	output.setRows(5);
	output.setColumns(5);
	
	getContentPane().add(output);

	testButon = new Button();
	testButon.setLocation(321,323);
	testButon.setSize(100,20);
	testButon.setBackground( new Color(-3355393) );
	testButon.setLabel("Test");
	testButon.addActionListener(this);
	getContentPane().add(testButon);

	setTitle("RGameEditor");
	setSize(450,527);
	setVisible(true);
	setResizable(false);
	
	
   }
    public static void main( String args[] )
   {
    	
   new GuiMain();
    
     
   }
    
    //=========================================
    
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==testButon){
			output.setText((output.getText()+"\n-------------------------------------------------------------------------------------"));
		 testButtonPressed=true;
		
		 
		 SwingUtilities.invokeLater(
					new Runnable(){
						public void run(){
							
		//==================WORKSPACE======================================	
							
							Lexer lex=new Lexer(input.getText());
							 lex.checkIfIdentfier();
							 lex.checkIfValidNumeric();
							 lex.printTable();
							 Grammer grm;
							try {
								grm = new Grammer(lex,filelocation.getText());
								output.append(lex.output+grm.output);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    
						   
						    }
						
		//================================================================				
							 }
					
					
					);
		}
	}
	
}
