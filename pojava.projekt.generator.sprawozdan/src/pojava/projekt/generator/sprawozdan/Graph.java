package pojava.projekt.generator.sprawozdan;

import java.awt.image.BufferedImage;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends CsvElement {

	protected String picName;
	static BufferedImage image;
	static int chartNumber = 1;
	JFreeChart chart;
	/*TO DO: 
	 * -Communication between Graph, parent classes (TexElement, CsvElement) and GraphDialog. 
	 * -Chart library handling, probably JFreeChart */
	public Graph(File file, String t, int numberX, String titleX, int numberY, String titleY, boolean selected) {
		super(file);
		title = t;
		setData();

		XYSeries series = new XYSeries("Seria 1");
		for(int i = 0; i<data.size(); i++) {
			series.add(data.get(i)[numberX], data.get(i)[numberY]);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		if(selected == true) {
		 chart = ChartFactory.createXYLineChart(
				title,//Tytul
				titleX, // opisy osi
				titleY, 
				dataset, // Dane 
				PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
				false, // legenda
				true, // tooltips
				false
			);}
		else {
			chart = ChartFactory.createXYLineChart(
					null,//Tytul
					titleX, // opisy osi
					titleY, 
					dataset, // Dane 
					PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
					false, // legenda
					true, // tooltips
					false
				);}
		try {
			picName = "wykres" + chartNumber++ + ".png";
			while(new File(MainInterface.get1Path(), picName).exists()) picName = "wykres" + chartNumber++ + ".png";;
	        ChartUtilities.saveChartAsPNG(new File (MainInterface.get1Path()+"\\" + picName), chart, 500, 400);
	        } catch (Exception e1) {
	        System.out.println("Problem z zapisem wykresu do pliku");
	        }
            
	}

	@Override
	String makeElementCode() {
		String graphCode = "\n\\begin{figure}[H]\n" +
						"\\includegraphics[scale=1]{" + picName+ "}\n" +
						"\\caption{" +
						title +
						"}" +
						"\n\\end{figure}\n";
		return graphCode;
	}
}
