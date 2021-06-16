package pojava.projekt.generator.sprawozdan;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SectionDialog extends CustomDialog {

	String sectionText;
	JTextArea textArea;
	public SectionDialog() {
		super();
		setBounds(100, 100, 541, 566);
		
		//--------cancel Button (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.WEST, cancelButton, -81, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, contentPane);
		contentPane.add(cancelButton);
		
		//--------ok Button (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.SOUTH, okButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, okButton, -87, SpringLayout.EAST, contentPane);
		okButton.addActionListener(new OkListener());
		contentPane.add(okButton);
		
		//--------title Label (from CustomDialog)--------------------------------
		titleLabel .setText("Podaj tytu\u0142 sekcji:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, titleLabel, 13, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titleLabel, -50, SpringLayout.WEST, titleTextField);
		contentPane.add(titleLabel);
		
		//--------title Text Field (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.NORTH, titleTextField, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, titleTextField, 173, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titleTextField, 0, SpringLayout.EAST, contentPane);
		contentPane.add(titleTextField);
		titleTextField.setColumns(21);
		
		//--------Text of the section Label--------------------------------
		JLabel sectionTextLabel = new JLabel("Wpisz tekst do umieszczenia w sekcji:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, sectionTextLabel, 17, SpringLayout.SOUTH, titleTextField);
		sl_contentPane.putConstraint(SpringLayout.WEST, sectionTextLabel, 0, SpringLayout.WEST, titleLabel);
		contentPane.add(sectionTextLabel);
		
		//--------section Text Area (in scrollPane)--------------------------------
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 11, SpringLayout.SOUTH, sectionTextLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, titleLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -23, SpringLayout.NORTH, cancelButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, cancelButton);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//-----------------------------------------------------------------------------------------------------------------
	}
	public class OkListener implements ActionListener{
		//Creates a Section's instance and calls methods necessary for creating TeX code
		@Override
		public void actionPerformed(ActionEvent e) {
			sectionText = textArea.getText();
			title = titleTextField.getText();
			Section sec = new Section(title, sectionText);
			sec.pasteToTextFrame(MainInterface.codeField, sec.makeElementCode());
	}
}
}
