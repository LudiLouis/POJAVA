package pojava.projekt.generator.sprawozdan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.filechooser.FileSystemView;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;


// Used FlatLaf LaF - https://www.formdev.com/flatlaf/
public class MainInterface extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String path;
	private JFileChooser fileCh;
	private static int returnValue = 0;
	private JPanel  panelLeft;
	private JMenuItem save, saveAs, open, newDocument;
	private JMenuBar menuBar;
	private JMenu file;
	private JRadioButtonMenuItem dark, light;
	private JButton bGraph, bTable, bSection, bJPG;
	public static JTextArea codeField; //codeField is necessary in TexElement's pasteToTextFrame method, it should be public static
	public File cf, dirfile, folder;
	String startText = "\\documentclass[12pt, a4paper]{article}\n"
			+ "\\usepackage{graphicx}\n"
			+ "\\usepackage{float}\r\n"
			+ "\\usepackage{polski}\n"
			+ "\\begin{document}\n"
			+ "\\end{document}\n";
	
	public MainInterface() throws HeadlessException {
		
		//--------MEGAFRAME parameters -----------------------------
		
		this.setSize(800,600);
		this.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
					  int n = confirmDialog();
					  if (n == 2) MainInterface.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				    else MainInterface.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				  }
				});
		this.setLayout(new BorderLayout());
		
		
		//----------------Menu---------------------------------------
		menuBar = new JMenuBar();
		file = new JMenu ("Plik");
		
		fileCh = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileCh.setDialogTitle("Wybierz plik.");
		fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		newDocument = new JMenuItem("Nowy");
		newDocument.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirmDialog() == 1) {
				codeField.setText(startText);
				//+ TODO: remove all files created by previous document
				codeField.setCaretPosition(codeField.getDocument().getLength() - 16 ); // Setting default caret position between beginning 
				//and ending of the document
			}}
		});
		
		save = new JMenuItem("Zapisz");
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		}
	);
		saveAs = new JMenuItem("Zapisz jako");
		saveAs.addActionListener(new Action1Listener());
		open= new JMenuItem("Wczytaj");
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String in = "";
				returnValue = fileCh.showOpenDialog(null);
    	          if (returnValue == JFileChooser.APPROVE_OPTION) 
    	          {
    	          try{
    	        	  cf = new File(fileCh.getSelectedFile().getAbsolutePath());
    	              FileReader read = new FileReader(cf);
    	              Scanner scan = new Scanner(read);
    	              while(scan.hasNextLine()){
    	                  String line = scan.nextLine() + "\n";
    	                  in = in + line;
    	          }
    	              scan.close();
    	              codeField.setText(in);
    	          }
    	          catch (FileNotFoundException except) {
      	              Component f = null;
      	              JOptionPane.showMessageDialog(f,"Nie znaleziono pliku.");
      	          }
    	  }
			
			}
		}
	);
		file.add(newDocument);
		file.add(save);
		file.add(saveAs);
		file.add(open);
		
		JMenu view = new JMenu("Widok");
		dark = new JRadioButtonMenuItem("Ciemny");
		dark.addActionListener(new ViewListener());
		dark.setSelected(true);
		
		light = new JRadioButtonMenuItem("Jasny");
		light.addActionListener(new ViewListener());
		
		ButtonGroup group = new ButtonGroup();
		group.add(light);
		group.add(dark);
		
		view.add(dark);
		view.add(light);
		menuBar.add(file);
		menuBar.add(view);
		this.setJMenuBar(menuBar);
		
		//--------left panel buttons --------------------------------
		
		panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(5,1));
		
		bTable  = new JButton("Tabela");
		bTable.addActionListener(new ActionListener()
		{
			// Open Table Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
				/*every GUI's element must be located in EventDispatchThread. In actionPerformed, EventDispatchThread
				 *   is setted by default, isn't necessary to place code in EventQueue try-catch block*/
							TableDialog frame = new TableDialog();
							frame.setVisible(true);
			}
		}
	);
		bJPG = new JButton("JPG/PNG");
		bJPG.addActionListener(new ActionListener()
		{
			// Open Graphics Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							GraphicsDialog frame = new GraphicsDialog();
							frame.setVisible(true);
			}
		}
	);
		bSection = new JButton("Sekcja");
		bSection.addActionListener(new ActionListener()
		{
			// Open Section Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							SectionDialog frame = new SectionDialog();
							frame.setVisible(true);
			}
		}
	);
		bGraph = new JButton("Wykres");
		bGraph.addActionListener(new ActionListener()
		{
			// Open Plot Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							PlotDialog frame = new PlotDialog();
							frame.setVisible(true);
			}
		}
	);
		
		
		panelLeft.add(bTable);
		panelLeft.add(bJPG);
		panelLeft.add(bSection);
		panelLeft.add(bGraph);
		//panelLeft.add(bFolder);
		
		this.add(panelLeft, BorderLayout.LINE_START);
		
		//---------Text panel Center---------------------------------------
			/* JTextArea is located in JScrollPane (setViewportView method provides this), so scrolls show automatically */
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setPreferredSize(new Dimension(680, 535));
		codeField = new JTextArea(startText);
		codeField.setFont(new Font("Consolas", Font.PLAIN, 14));
		codeField.setCaretPosition(codeField.getDocument().getLength() - 16 ); // Setting default caret position between beginning 
		//and ending of the document
		//panelCode = new JPanel();
		//panelCode.add(scrollPane);
		TextLineNumber tln = new TextLineNumber(codeField);
		scrollPane.setRowHeaderView( tln );
		scrollPane.setViewportView(codeField);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		//-----------------------------------------------------------
	}

	public MainInterface(GraphicsConfiguration gc) {
		super(gc);
	}

	public MainInterface(String title) throws HeadlessException {
		super(title);
	}

	public MainInterface(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public class Action1Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			firstSaveFile();
		}
	}
	public class ViewListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == dark)
				try {
					UIManager.setLookAndFeel( new FlatDarkLaf() );
                    SwingUtilities.updateComponentTreeUI(MainInterface.this);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			else
				try {
					UIManager.setLookAndFeel( new FlatLightLaf() );
                    SwingUtilities.updateComponentTreeUI(MainInterface.this);
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		}
	 public int confirmDialog() {
		 Object[] options = {"Tak",
                 "Nie",
                 "Anuluj"};
		int n = JOptionPane.showOptionDialog(null, "Czy chcesz zapisaæ plik?", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                 null,
                 options,
                 options[0]); //default selected option
		 if (n == JOptionPane.YES_OPTION && fileCh.getSelectedFile() == null) {
			firstSaveFile();
			return 1;
		 }
		 else if (n == JOptionPane.YES_OPTION && fileCh.getSelectedFile()!= null) {
			 saveFile();
			 return 1;
		 }
		 else if(n == JOptionPane.NO_OPTION) return 0;
		 else return 2;
	 }
	 public void firstSaveFile() {
		
			returnValue = fileCh.showSaveDialog(null);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
	          try {
	              cf = new File ( fileCh.getSelectedFile().getAbsolutePath()+".tex");
	              FileWriter out = new FileWriter(cf);
	              out.write(codeField.getText());
	              out.close();
	          } catch (FileNotFoundException except) {
	              Component f = null;
	              JOptionPane.showMessageDialog(f,"Nie znaleziono pliku.");
	          } catch (IOException except) {
	              Component f = null;
	              JOptionPane.showMessageDialog(f,"B³¹d.");
	          }
			
			}
	 }
	 public void saveFile() {
		 try {
	              File f = cf;
	              FileWriter out = new FileWriter(f);
	              out.write(codeField.getText());
	              out.close();
	          } 
	           catch (IOException except) {
	              JOptionPane.showMessageDialog(null,"B³¹d zapisu.");
	          }
		catch (NullPointerException except)
		{
			JOptionPane.showMessageDialog(null,"Wska¿ lokalizacjê pliku.");
			firstSaveFile();
		}	
	 }
	 
	public static String get1Path() {
		return path;
		
	}

	public static void setPath(String path) {
		MainInterface.path = path;
	}

	public int tworzenieFolderu() {
		fileCh = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileCh.setDialogTitle("Wybierz plik.");
		fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		returnValue = fileCh.showOpenDialog(null);
		if ( returnValue == JFileChooser.APPROVE_OPTION)
		{
			folder = new File (fileCh.getSelectedFile().getAbsolutePath());
			
			
			if(folder.exists()&& folder.isDirectory())
			{
				
				path = folder.getAbsolutePath();
				dirfile = new File(path);
				fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				return 1;
				
			}else {
			path = folder.getAbsolutePath();
			dirfile = new File(path);
		      //Creating the directory
		      boolean bool = dirfile.mkdirs();
		      if(bool){
		         System.out.println("Directory created successfully");
		         System.out.println(path);
		 		fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		         return 1;
		      }else{
		         System.out.println("Sorry couldnt create specified directory");
		         return 0;
		      }
			}
		}
			
		
		return 0;
	}
	public static void main(String[] args) {
		
		FlatDarkLaf.install();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null,"Witaj w Generatorze Sprawozdañ. Aby utworzyæ sprawozdanie w systemie LaTeX"
							+ ", stwórz lub wybierz folder, w którym je umieœcisz. Wybierz OK, aby przejœæ dalej...");
					MainInterface mi = new MainInterface();
					if(mi.tworzenieFolderu() == 1)	mi.setVisible(true);
					else System.exit(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
