import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class SpecPieChart extends JFrame {

	public SpecPieChart(String title) {
		super(title);

		PieDataset dataset = createDataset();

    	JFreeChart chart = ChartFactory.createPieChart(
    			"Info",
    			dataset,
    			true, 
    			true,
    			false);

    	PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
    		"Marks {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
    	((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
    
    	ChartPanel panel = new ChartPanel(chart);
    	setContentPane(panel);
	}

  private PieDataset createDataset() {
    DefaultPieDataset dataset=new DefaultPieDataset();
    InfList list=Utils.readFromJSON("input.json");
    for(Information i:list.list) {
    	dataset.setValue(i.name, i.numberOf);
    }
    return dataset;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		  SpecPieChart example = new SpecPieChart("Pie Chart");
		  example.setSize(800, 400);
		  example.setLocationRelativeTo(null);
		  example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  example.setVisible(true);
		}
	});
  }
}