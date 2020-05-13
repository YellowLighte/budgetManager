
package budgetmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


   
public class Chart extends PieChart {

    public Chart() {
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                new PieChart.Data("Food", 1),
                new PieChart.Data("Clothes", 1),
                new PieChart.Data("Entertainment", 1),
                new PieChart.Data("Other", 1)
        );
        setData(pieChart);
        
    } 
}
