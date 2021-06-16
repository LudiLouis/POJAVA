package pojava.projekt.generator.sprawozdan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


public class TableDialog extends CustomDialog{
	
	JLabel fileChooserLabel;
	JButton searchButton;
	File file;
	TableListener okListener;

	public TableDialog(){
		super();
		setBounds(100, 100, 393, 178);
		
		//--------cancel Button (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.WEST, cancelButton, -81, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, contentPane);
		contentPane.add(cancelButton);
		
		//--------ok Button (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.SOUTH, okButton, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, okButton, -87, SpringLayout.EAST, contentPane);
		okListener = new TableListener();
		okButton.addActionListener(okListener);
		contentPane.add(okButton);
		
		//--------title Label (from CustomDialog)--------------------------------
		titleLabel.setText("Podaj tytu\u0142 tabeli:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, titleLabel, 13, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titleLabel, -62, SpringLayout.WEST, titleTextField);
		sl_contentPane.putConstraint(SpringLayout.WEST, titleLabel, -163, SpringLayout.WEST, titleTextField);
		contentPane.add(titleLabel);
		
		//--------title Text Field (from CustomDialog)--------------------------------
		sl_contentPane.putConstraint(SpringLayout.NORTH, titleTextField, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, titleTextField, 173, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, titleTextField, 0, SpringLayout.EAST, contentPane);
		
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		//--------file chooser Label--------------------------------
		fileChooserLabel = new JLabel("Wybierz plik z tabel¹:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, fileChooserLabel, 25, SpringLayout.SOUTH, titleLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, fileChooserLabel, 0, SpringLayout.WEST, titleLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, fileChooserLabel, 130, SpringLayout.WEST, titleLabel);
		contentPane.add(fileChooserLabel);
		
		//--------file chooser Button--------------------------------
		searchButton = new JButton("Przegl\u0105daj...");
		sl_contentPane.putConstraint(SpringLayout.NORTH, searchButton, -4, SpringLayout.NORTH, fileChooserLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, contentPane);
		searchButton.addActionListener(new SearchListener());
		contentPane.add(searchButton);
		//-----------------------------------------------------------------------------------------------------------------------
		}
	/*public class OkListener implements ActionListener{
		//Creates a Table's instance and calls methods necessary for creating TeX code (only pasteToTextField, temporarily)
		@Override
		public void actionPerformed(ActionEvent e) {
			Table tab = new Table();
			tab.pasteToTextFrame(MainInterface.codeField, tab.makeElementCode());
		
		}*/
	public class TableListener  implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		title = titleTextField.getText();
		
		if(file != null)
		{
			Table tab = new Table(title, file);
			tab.pasteToTextFrame(MainInterface.codeField, tab.makeElementCode());
		}
		else JOptionPane.showMessageDialog(null,"Wybierz lokalizacjê pliku.");
	}}
	
	public class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileCh = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileCh.setDialogTitle("Wybierz plik.");
			fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int returnValue = fileCh.showOpenDialog(null);
	          if (returnValue == JFileChooser.APPROVE_OPTION) 
	          {
	        	  
	          file = new File(fileCh.getSelectedFile().getAbsolutePath());
	          }
		}
		
	}
}
