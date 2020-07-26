import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;



public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("COSI_lab1&2");
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 400);
        TabPane tab_pane = new TabPane();
        BorderPane borderPane = new BorderPane();

        Signal signal = new Signal();
        Signal signal_y = new Signal("cos(x)", 1);
        Signal signal_z = new Signal("sin(x)", 1);

        Chart original_signal_y_convolution = new Chart(signal_y.GetArgumentValues(), signal_y.GetDiscreteSignalValues(), "Original signal cos(x)");
        Chart original_signal_z_convolution = new Chart(signal_z.GetArgumentValues(), signal_z.GetDiscreteSignalValues(), "Original signal sin(x)");
        Chart original_signal_y_correlation = new Chart(signal_y.GetArgumentValues(), signal_y.GetDiscreteSignalValues(), "Original signal cos(x)");
        Chart original_signal_z_correlation = new Chart(signal_z.GetArgumentValues(), signal_z.GetDiscreteSignalValues(), "Original signal sin(x)");

        signal_y.GetSignalAfterCorrelation(signal_z);
        signal_y.GetSignalAfterFFTCorrelation(signal_z);
        signal_y.GetSignalAfterConvolution(signal_z);
        signal_y.GetSignalAfterFFTConvolution(signal_z);

        Chart correlation = new Chart(signal_y.GetArgumentValues(), signal_y.GetCorrelation(), "Correlation");
        Chart fft_correlation = new Chart(signal_y.GetArgumentValues(), signal_y.GetFFTCorrelation(), "FFT correlation");
        Chart convolution = new Chart(signal_y.GetArgumentValues(), signal_y.GetConvolution(), "Convolution");
        Chart fft_convolution = new Chart(signal_y.GetArgumentValues(), signal_y.GetFFTConvolution(), " FFT Convolution");

        Chart original_signal_dft = new Chart(signal.GetArgumentValues(), signal.GetDiscreteSignalValues(), "Original signal cos(x) + sin(x)");
        Chart original_signal_fft = new Chart(signal.GetArgumentValues(), signal.GetDiscreteSignalValues(), "Original signal cos(x) + sin(x)");
        Chart absolute_dft_signal = new Chart(signal.GetArgumentValues(), signal.GetAbsoluteDFT_FFTSignal(0), "DFT absolute signal");
        Chart absolute_fft_signal = new Chart(signal.GetArgumentValues(), signal.GetAbsoluteDFT_FFTSignal(1), "FFT absolute signal");
        Chart phase_dft_signal = new Chart(signal.GetArgumentValues(), signal.GetPhaseDFT_FFTSignal(0), "DFT phase signal");
        Chart phase_fft_signal = new Chart(signal.GetArgumentValues(), signal.GetPhaseDFT_FFTSignal(1), "FFT phase signal");
        Chart idft_signal = new Chart(signal.GetArgumentValues(), signal.GetIDFTSignal(), "IDFT signal");
        Chart ifft_signal = new Chart(signal.GetArgumentValues(), signal.GetIFFTSignal(), "IFFT signal");

        Tab dft_tab = CreateTab("DFT", original_signal_dft, absolute_dft_signal, phase_dft_signal, idft_signal);
        tab_pane.getTabs().add(dft_tab);

        Tab fft_tab = CreateTab("FFT", original_signal_fft, absolute_fft_signal, phase_fft_signal, ifft_signal);
        tab_pane.getTabs().add(fft_tab);

        Tab convolution_tab = CreateTab("Convolution", original_signal_y_convolution, original_signal_z_convolution, convolution, fft_convolution);
        tab_pane.getTabs().add(convolution_tab);

        Tab correlation_tab = CreateTab("Correlation", original_signal_y_correlation, original_signal_z_correlation, correlation, fft_correlation);
        tab_pane.getTabs().add(correlation_tab);

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tab_pane);
        root.getChildren().add(borderPane);

        stage.setScene(scene);
        stage.show();
    }

    private Tab CreateTab(String name, Chart first_chart, Chart second_chart, Chart third_chart, Chart fourth_chart)
    {
        Tab tab = new Tab();
        tab.setText(name);
        HBox hbox = new HBox();
        hbox.getChildren().add(first_chart.GetChart());
        hbox.getChildren().add(second_chart.GetChart());
        hbox.getChildren().add(third_chart.GetChart());
        hbox.getChildren().add(fourth_chart.GetChart());
        tab.setContent(hbox);

        return tab;
    }
}