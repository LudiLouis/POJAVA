package pojava.projekt.generator.sprawozdan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class CustomDialog extends JFrame /*implements ActionListener*/{

	//--------FIELDS --------------------------------
	JPanel contentPane;
	JTextField titleTextField;
	
	JButton okButton;
	JButton cancelButton;
	
	JLabel titleLabel;
	
	String title;
	
	SpringLayout sl_contentPane; // making an SpringLayout field, because methods working on SpringLayout instances will be needed
	

	//--------CONSTRUCTORS --------------------------------
	public CustomDialog() {
		setWindow();
		
		//--------cancel Button --------------------------------
		cancelButton = new JButton("Anuluj");
		cancelButton.addActionListener(new ActionListener() { // cancel button closes the dialog
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		//--------ok Button --------------------------------
		okButton = new JButton("OK");
		getRootPane().setDefaultButton(okButton); // make a okbutton as default button to be clicked when enter key is pressed
		//okButton.addActionListener(this); 
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/* every CustomDialog subclass inherits this ActionListener, but still have to add exclusive version of okListener
				 *  (because every Dialog does something different when Event appears, however all of them should dispose after ok was clicked)*/
					dispose();
				}
		}); 
		//---------------------------------------------------
		
		titleLabel = new JLabel("Podaj tytu³:");
		titleTextField = new JTextField();
	}
	
	//--------METHODS --------------------------------
	
	//setWindow defines default features of every sub-dialog
	void setWindow(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //creates non-visible border on the edges of the contentPane panel
		setContentPane(contentPane);
		sl_contentPane = new SpringLayout(); 
		contentPane.setLayout(sl_contentPane);
	}
	//public void actionPerformed(ActionEvent e) {
	//	dispose();
	//}
	
}
