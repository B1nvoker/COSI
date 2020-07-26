import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Chart
{
    private final int N = 8;

    private LineChart<Number, Number> chart;
    private ObservableList<XYChart.Data> data;

    public Chart(double[] data_x, double[] data_y, String title)
    {
        chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        chart.setTitle(title);
        chart.setLegendVisible(false);
        data = FXCollections.observableArrayList();
        XYChart.Series series = new XYChart.Series();

        for(int i = 0; i < N; i++)
        {
            data.add(new XYChart.Data(data_x[i], data_y[i]));
        }

        series.setData(data);

        chart.getData().add(series);
        chart.setCreateSymbols(true);
    }

    public LineChart<Number, Number> GetChart()
    {
        return this.chart;
    }
}
