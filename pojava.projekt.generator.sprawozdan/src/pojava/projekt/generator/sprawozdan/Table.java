package pojava.projekt.generator.sprawozdan;

import java.io.File;

public class Table extends CsvElement {

	String cLines;
	String lines = "";
	/*TO DO: communication between Table, parent classes (TexElement, CsvElement) and TableDialog */
	public Table(String t, File file){
		super(file);
		title = t;
		setCLines();
		setLines();
	}

	void setCLines() { 
		cLines = "|c|"; 
		for(int i = 1; i< numberOfColumns; i++)
			cLines = cLines + "c|";
	}
	void setLines() {
		for(int i = 0; i<sData.size(); i++) {
			lines += "\\hline ";
			for(int j = 0; j<sData.get(i).length; j++){
				if(j == sData.get(i).length - 1) lines += sData.get(i)[j];
				else lines += sData.get(i)[j] + " & ";
			}
			lines += "\\\\ \n";
		}
	}
	@Override
	public String makeElementCode() {
		String tableCode = "\n\\begin{table}[h] \n" +
						"\\begin{tabular}{" +
						cLines +
						"} \n" +
						lines +
						"\\hline \n" +
						"\\end{tabular} \n" +
						"\\caption{" +
						title +
						"}\n\\end{table} \n";
		return tableCode;
	}
}
