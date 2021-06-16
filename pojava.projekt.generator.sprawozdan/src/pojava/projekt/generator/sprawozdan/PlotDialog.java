package pojava.projekt.generator.sprawozdan;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PlotDialog extends TableDialog {

		private JTextField xTitletextField;
		private JTextField xColumntextField;
		private JTextField yTitletextField;
		private JTextField yColumntextField;
		private JRadioButton titleRadio;

		public PlotDialog() {
			super();
			setBounds(100, 100, 393, 386);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			SpringLayout sl_contentPane = new SpringLayout();
			contentPane.setLayout(sl_contentPane);
			
			//--------cancel Button (from CustomDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.WEST, cancelButton, -81, SpringLayout.EAST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, contentPane);
			contentPane.add(cancelButton);
			
			//--------ok Button (from CustomDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.SOUTH, okButton, 0, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, okButton, -87, SpringLayout.EAST, contentPane);
			okButton.removeActionListener(okListener);
			okButton.addActionListener(new OkListener());
			contentPane.add(okButton);
			
			//--------title Label (from CustomDialog)--------------------------------
			titleLabel.setText("Podaj tytu\u0142 wykresu:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, titleLabel, 13, SpringLayout.NORTH, contentPane);
			contentPane.add(titleLabel);
			
			//--------title Text Field(from CustomDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.EAST, titleLabel, -42, SpringLayout.WEST, titleTextField);
			sl_contentPane.putConstraint(SpringLayout.NORTH, titleTextField, 10, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, titleTextField, 173, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, titleTextField, 0, SpringLayout.EAST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, titleLabel, -163, SpringLayout.WEST, titleTextField);
			contentPane.add(titleTextField);
			titleTextField.setColumns(21);
			
			//--------file chooser Label (from TableDialog)--------------------------------
			fileChooserLabel.setText("Wybierz plik z danymi:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, fileChooserLabel, 25, SpringLayout.SOUTH, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, fileChooserLabel, 0, SpringLayout.WEST, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, fileChooserLabel, 125, SpringLayout.WEST, titleLabel);
			contentPane.add(fileChooserLabel);
			
			//--------file chooser Button (from TableDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.NORTH, searchButton, -4, SpringLayout.NORTH, fileChooserLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, contentPane);
			contentPane.add(searchButton);
			
			//--------X axis title Label--------------------------------
			JLabel xTitleLabel = new JLabel("Podaj tytu\u0142 osi X:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, xTitleLabel, 25, SpringLayout.SOUTH, fileChooserLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, xTitleLabel, 0, SpringLayout.WEST, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, xTitleLabel, 0, SpringLayout.EAST, titleLabel);
			contentPane.add(xTitleLabel);
			
			//--------X axis column number Label--------------------------------
			JLabel xColumnLabel = new JLabel("Podaj numer kolumny z warto\u015Bciami X:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, xColumnLabel, 30, SpringLayout.SOUTH, xTitleLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, xColumnLabel, 0, SpringLayout.WEST, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, xColumnLabel, 220, SpringLayout.WEST, titleLabel);
			contentPane.add(xColumnLabel);
			
			//--------Y axis title Label--------------------------------
			JLabel yTitleLabel = new JLabel("Podaj tytu\u0142 osi Y:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, yTitleLabel, 24, SpringLayout.SOUTH, xColumnLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, yTitleLabel, 0, SpringLayout.WEST, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, yTitleLabel, 0, SpringLayout.EAST, fileChooserLabel);
			contentPane.add(yTitleLabel);
			
			//--------Y axis column number Label--------------------------------
			JLabel yColumnLabel = new JLabel("Podaj numer kolumny z warto\u015Bciami Y:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, yColumnLabel, 21, SpringLayout.SOUTH, yTitleLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, yColumnLabel, 0, SpringLayout.WEST, titleLabel);
			contentPane.add(yColumnLabel);
			
			//--------X axis title Text Field--------------------------------
			xTitletextField = new JTextField();
			sl_contentPane.putConstraint(SpringLayout.NORTH, xTitletextField, -3, SpringLayout.NORTH, xTitleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, xTitletextField, 0, SpringLayout.EAST, cancelButton);
			xTitletextField.setColumns(21);
			contentPane.add(xTitletextField);
			
			//--------Y axis title number Text Field--------------------------------
			yTitletextField = new JTextField();
			sl_contentPane.putConstraint(SpringLayout.SOUTH, yTitletextField, 0, SpringLayout.SOUTH, yTitleLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, yTitletextField, 0, SpringLayout.EAST, cancelButton);
			yTitletextField.setColumns(21);
			contentPane.add(yTitletextField);
			
			//--------Y axis column number Text Field--------------------------------
			yColumntextField = new JTextField();
			sl_contentPane.putConstraint(SpringLayout.NORTH, yColumntextField, -3, SpringLayout.NORTH, yColumnLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, yColumntextField, 0, SpringLayout.EAST, cancelButton);
			yColumntextField.setColumns(5);
			contentPane.add(yColumntextField);
			
			//--------X axis column number Text Field--------------------------------
			xColumntextField = new JTextField();
			sl_contentPane.putConstraint(SpringLayout.SOUTH, xColumntextField, 0, SpringLayout.SOUTH, xColumnLabel);
			sl_contentPane.putConstraint(SpringLayout.EAST, xColumntextField, 0, SpringLayout.EAST, cancelButton);
			xColumntextField.setColumns(5);
			contentPane.add(xColumntextField);
			
			titleRadio = new JRadioButton("Poka\u017C tytu\u0142 na wykresie");
			sl_contentPane.putConstraint(SpringLayout.NORTH, titleRadio, 33, SpringLayout.SOUTH, yColumnLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, titleRadio, 10, SpringLayout.WEST, contentPane);
			contentPane.add(titleRadio);
			//-----------------------------------------------------------------------------------------------------------------
		}
		public class OkListener implements ActionListener{
			//Creates a Graph's instance and calls methods necessary for creating TeX code (only pasteToTextField, temporarily)
			@Override
			public void actionPerformed(ActionEvent e) {
				/*Graph graph = new Graph();
				graph.pasteToTextFrame(MainInterface.codeField, graph.makeElementCode());*/
				title = titleTextField.getText();
				boolean isSelected = titleRadio.isSelected();
				try {
		            
				Graph graph = new Graph(file, title, Integer.parseInt(xColumntextField.getText()) -1, xTitletextField.getText(),
						Integer.parseInt(yColumntextField.getText())-1, yTitletextField.getText(), isSelected);
				graph.pasteToTextFrame(MainInterface.codeField, graph.makeElementCode());}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null,
						    "Numery kolumn musz¹ byæ liczbami ca³kowitymi", "B³¹d",JOptionPane.ERROR_MESSAGE);
				}
		}
		}
	}
