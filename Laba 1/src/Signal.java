public class Signal
{
    private int N = 8;

    private double[] argument_values = new double[N];
    private double[] original_signal_values = new double[N];

    private Complex[] dft_values = new Complex[N];
    private double[] idft_values = new double[N];

    private Complex[] fft_values = new Complex[N];
    private double[] ifft_values = new double[N];

    private double[] correlation_values = new double[N];
    private double[] correlation_fft_values = new double[N];

    private double[] convolution_values = new double[N];
    private double[] convolution_fft_values = new double[N];

    public Signal()
    {
        GetOriginalSignal();
        GetSignalAfterDFT();
        GetSignalAfterFFT();
        GetSignalAfterIDFT();
        GetSignalAfterIFFT();
    }

    public Signal(String function, int value)
    {
        GetOriginalSignal(function, value);
        GetSignalAfterDFT();
        GetSignalAfterFFT();
        GetSignalAfterIDFT();
        GetSignalAfterIFFT();
    }

    public double[] GetArgumentValues()
    {
        return this.argument_values;
    }

    public double[] GetDiscreteSignalValues()
    {
        return this.original_signal_values;
    }

    public Complex[] GetDFTSignal()
    {
        return dft_values;
    }

    public Complex[] GetFFTSignal()
    {
        return fft_values;
    }

    public double[] GetIDFTSignal()
    {
        return idft_values;
    }

    public double[] GetIFFTSignal()
    {
        return ifft_values;
    }

    public double[] GetAbsoluteDFT_FFTSignal(int mode)
    {
        double[] result = new double[N];

        for(int i = 0; i < N; i++)
        {
            if(mode == 0)
            {
                result[i] = dft_values[i].Absolute();
            }
            else
            {
                result[i] = fft_values[i].Absolute();
            }

        }

        return result;
    }

    public double[] GetPhaseDFT_FFTSignal(int mode)
    {
        double[] result = new double[N];

        for(int i = 0; i < N; i++)
        {
            if(mode == 0)
            {
                result[i] = dft_values[i].Phase();
            }
            else
            {
                result[i] = fft_values[i].Phase();
            }

        }

        return result;
    }

    public double[] GetCorrelation()
    {
        return correlation_values;
    }

    public double[] GetFFTCorrelation()
    {
        return correlation_fft_values;
    }

    public double[] GetConvolution()
    {
        return convolution_values;
    }

    public double[] GetFFTConvolution()
    {
        return convolution_fft_values;
    }

    public void GetOriginalSignal()
    {
        for(int i = 0; i < N; i++)
        {
            argument_values[i] = (2 * i * Math.PI) / N;
            original_signal_values[i] = Math.cos(argument_values[i]) + Math.sin(argument_values[i]);
        }
    }

    public void GetOriginalSignal(String function, int value)
    {
        for(int i = 0; i < N; i++)
        {
            argument_values[i] = (2 * i * Math.PI) / N;
            if(function.contains("cos"))
            {
                original_signal_values[i] = Math.cos(value * argument_values[i]);
            }
            else if(function.contains("sin"))
            {
                original_signal_values[i] = Math.sin(value * argument_values[i]);
            }
        }
    }

    public void GetSignalAfterDFT()
    {
        dft_values = Transformation.DiscreteFourierTransformation(ConvertDoubleToComplex(this.original_signal_values), 1);
    }

    public void GetSignalAfterFFT()
    {
        fft_values = Transformation.FastFourierTransformation(ConvertDoubleToComplex(this.original_signal_values), 1);
    }

    public void GetSignalAfterIDFT()
    {
        idft_values = ConvertComplexToDouble(Transformation.DiscreteFourierTransformation(dft_values, -1));
    }

    public void GetSignalAfterIFFT()
    {
        ifft_values = ConvertComplexToDouble(Transformation.FastFourierTransformation(fft_values, -1));
    }

    public void GetSignalAfterCorrelation(Signal signal)
    {
        correlation_values = Transformation.Correlation(this.original_signal_values, signal.GetDiscreteSignalValues());
    }

    public void GetSignalAfterFFTCorrelation(Signal signal)
    {
        correlation_fft_values = ConvertComplexToDouble(Transformation.FFTCorrelation(this.fft_values, signal.GetFFTSignal()));
    }

    public void GetSignalAfterConvolution(Signal signal)
    {
        convolution_values = Transformation.Convolution(this.original_signal_values, signal.GetDiscreteSignalValues());
    }

    public void GetSignalAfterFFTConvolution(Signal signal)
    {
        convolution_fft_values = ConvertComplexToDouble(Transformation.FFTConvolution(this.fft_values, signal.GetFFTSignal()));
    }

    private Complex[] ConvertDoubleToComplex(double[] values)
    {
        Complex[] result = new Complex[values.length];

        for(int i = 0; i < values.length; i++)
        {
            result[i] = new Complex(values[i]);
        }

        return result;
    }

    private double[] ConvertComplexToDouble(Complex[] values)
    {
        double[] result = new double[values.length];

        for(int i = 0; i < values.length; i++)
        {
            result[i] = values[i].GetReal();
        }

        return result;
    }
}
