public class Transformation
{
    public static Complex[] DiscreteFourierTransformation(Complex[] signal, int direction)
    {
        int N = signal.length;

        Complex[] result = new Complex[N];
        for(int i = 0; i < N; i++)
        {
            result[i] = new Complex(0);
        }

        for(int m = 0; m < N; m++)
        {
            for(int n = 0; n < N; n++)
            {
                Complex w_n = new Complex(Math.cos((2 * Math.PI * n * m) / N), (-1) * direction * Math.sin((2 * Math.PI * n * m) / N));
                result[m] = result[m].Add(w_n.Multiply(signal[n]));
            }
            if(direction == -1)
            {
                result[m] = result[m].Divide(N);
            }
        }

        return result;
    }

    public static Complex[] FastFourierTransformation(Complex[] signal, int direction)
    {
        int N = signal.length;
        int base = 2;

        if(N == 1)
        {
            return signal;
        }

        Complex[] a_even = new Complex[N / 2];
        Complex[] a_odd = new Complex[N / 2];

        for(int i = 0; i < N; i++)
        {
            if(i % 2 == 0)
            {
                a_even[i / 2] = signal[i];
            }
            else
            {
                a_odd[i / 2] = signal[i];
            }
        }

        Complex[] b_even = FastFourierTransformation(a_even, direction);
        Complex[] b_odd = FastFourierTransformation(a_odd, direction);

        Complex w_n = new Complex(Math.cos((2 * Math.PI) / N),(-1) * direction * Math.sin((2 * Math.PI) / N));
        Complex w = new Complex(1);

        Complex[] y = new Complex[N];

        for(int i = 0; i < N / 2; i++)
        {
            if(direction == 1)
            {
                y[i] = b_even[i].Add(w.Multiply(b_odd[i]));
                y[i + N / 2] = b_even[i].Subtract(w.Multiply(b_odd[i]));
            }
            if(direction == -1)
            {
                y[i] = b_even[i].Add(w.Multiply(b_odd[i])).Divide(base);
                y[i + N / 2] = b_even[i].Subtract(w.Multiply(b_odd[i])).Divide(base);
            }


            w = w.Multiply(w_n);
        }

        return y;
    }

    public static double[] Correlation(double[] signal_x, double[] signal_y)
    {
       int N = signal_x.length;

       double[] result = new double[N];
       for(int i = 0; i < N; i++)
       {
           result[i] = 0;
       }

       for(int n = 0; n < N; n++)
       {
           for(int k = 0; k < N; k++)
           {
               int index = n + k;
               if(index >= N)
               {
                   index -= N;
               }

               result[n] += signal_x[k] * signal_y[index];
           }
       }

       return result;
    }

    public static Complex[] FFTCorrelation(Complex[] signal_x, Complex[] signal_y)
    {
        int N = signal_x.length;

        Complex[] signal_x_conjugated = new Complex[N];
        for(int i = 0; i < N; i++)
        {
            signal_x_conjugated[i] = signal_x[i].Conjugate();
        }

        Complex[] result_correlation_fft = new Complex[N];
        for(int i = 0; i < N; i++)
        {
            result_correlation_fft[i] = signal_x_conjugated[i].Multiply(signal_y[i]);
        }

        return FastFourierTransformation(result_correlation_fft, -1);
    }

    public static double[] Convolution(double[] signal_x, double[] signal_y)
    {
        int N = signal_x.length;

        double[] result = new double[N];
        for(int i = 0; i < N; i++)
        {
            result[i] = 0;
        }

        for(int n = 0; n < N; n++)
        {
            for(int k = 0; k < N; k++)
            {
                int index = n - k;
                if(index < 0)
                {
                    index += N;
                }

                result[n] += signal_x[k] * signal_y[index];
            }
        }

        return result;
    }

    public static Complex[] FFTConvolution(Complex[] signal_x, Complex[] signal_y)
    {
        int N = signal_x.length;

        Complex[] result_convolution_fft = new Complex[N];
        for(int i = 0; i < N; i++)
        {
            result_convolution_fft[i] = signal_x[i].Multiply(signal_y[i]);
        }

        return FastFourierTransformation(result_convolution_fft, -1);
    }
}
