public class Complex
{
    private final double real;
    private final double imaginary;

    public Complex(double real)
    {
        this.real = real;
        this.imaginary = 0;
    }

    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public String toString()
    {
        if (this.imaginary == 0) return this.real + "";
        if (this.real == 0) return this.imaginary + "i";
        if (this.imaginary <  0) return this.real + " - " + (-this.imaginary) + "i";
        return this.real + " + " + this.imaginary + "i";
    }

    public double GetReal()
    {
        return this.real;
    }

    public double GetImaginary()
    {
        return this.imaginary;
    }

    public Complex Conjugate()
    {
        return new Complex(this.real, -this.imaginary);
    }

    public double Absolute()
    {
        return Math.sqrt(this.SquareLength());
    }

    public double Phase()
    {
        return Math.atan2(this.imaginary, this.real);
    }

    public double SquareLength()
    {
        return this.real * this.real + this.imaginary * this.imaginary;
    }

    public Complex Add(Complex number)
    {
        return new Complex(this.real + number.real, this.imaginary + number.imaginary);
    }

    public Complex Subtract(Complex number)
    {
        return new Complex(this.real - number.real, this.imaginary - number.imaginary);
    }

    public Complex Multiply(Complex number)
    {
        double real = this.real * number.real - this.imaginary * number.imaginary;
        double imaginary = this.real * number.imaginary + this.imaginary * number.real;

        return new Complex(real, imaginary);
    }

    public Complex Multiply(double number)
    {
        return new Complex(this.real * number, this.imaginary * number);
    }

    public Complex Divide(Complex number)
    {
        double divider = number.SquareLength();

        double real = (this.real * number.real + this.imaginary * number.imaginary) / divider;
        double imaginary = (this.imaginary * number.real - this.real * number.imaginary) / divider;

        return new Complex(real, imaginary);
    }

    public Complex Divide(int number)
    {
        return new Complex(this.real / number, this.imaginary / number);
    }
}
