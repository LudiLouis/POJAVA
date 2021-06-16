package pojava.projekt.generator.sprawozdan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class CsvElement extends TexElement {

	
	int numberOfLines;
	int numberOfColumns;
	ArrayList<Double[]> data = new ArrayList<Double[]>();
	ArrayList<String[]> sData = new ArrayList<String[]>();
	
	public CsvElement(File file) {
		setsData(file);
		//setData();
		setNumberOfColumns();
		setNumberOfLines();
	}
	
	
	public int getNumberOfLines() {
		return numberOfLines;
	}


	public void setNumberOfLines() {
		numberOfLines = sData.size();
	}


	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns() {
		numberOfColumns = sData.get(0).length;
	}


	public ArrayList<Double[]> getData() {
		return data;
	}


	public void setData() {
		for(int i = 1; i<sData.size(); i++) {
			data.add(new Double[sData.get(i).length]);
			for(int j = 0; j<data.get(i-1).length; j++) {
				if(sData.get(i)[j] != null && sData.get(i)[j].isEmpty() == false) data.get(i-1)[j] = Double.parseDouble(sData.get(i)[j]);
			}
		}
		 }


	public ArrayList<String[]> getsData() {
		return sData;
	}


	public void setsData(File file) {
		String line;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				sData.add(line.split(","));
			}
			br.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	@Override
	String makeElementCode() {
		return null;
	}

}
