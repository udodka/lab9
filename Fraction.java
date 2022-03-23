
public class Fraction { //создание класса дробей
    public int denominator;//создаем свойства класса(знаменатель)
    public int numerator;//создаем свойства класса(числитель)

    public Fraction(int numerator, int denominator) {//создаем конструктор, который принимает два параметра
        this.denominator = denominator;
        this.numerator = numerator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    private static int nod(int a, int b) {
        return b == 0 ? a : nod(b, a % b);
    }

    private static int nok(int a, int b) {
        return a / nod(a, b) * b;
    }

    private static Fraction sokr(Fraction f){
        int n = nod(f.denominator, f.numerator);
        f.denominator = f.denominator/n;
        f.numerator = f.numerator/n;
        return f;
    }

    public Fraction add(Fraction other) {
        Fraction result = new Fraction(1, 1);
        if (this.denominator == other.denominator) { //если знаменатели одинаковые
            result.denominator = this.denominator;
            result.numerator = this.numerator + other.numerator;
        } else {
            int nok = nok(this.denominator, other.denominator);
            result.denominator = nok;
            result.numerator = this.numerator * (nok / this.denominator) + other.numerator * (nok / other.denominator);
        }
        sokr(result);
        return result;
    }

    public static Fraction add(Fraction a, Fraction b) {
        Fraction result = new Fraction(1, 1);
        if (a.denominator == b.denominator) { //если знаменатели одинаковые
            result.denominator = a.denominator;
            result.numerator = a.numerator + b.numerator;
        } else {
            int nok = nok(a.denominator, b.denominator);
            result.denominator = nok;
            result.numerator = a.numerator * (nok / a.denominator) + b.numerator * (nok / b.denominator);
        }
        sokr(result);
        return result;
    }

    public Fraction dif(Fraction other) {
        Fraction result = new Fraction(1, 1);
        if (this.denominator == other.denominator) { //если знаменатели одинаковые
            result.denominator = this.denominator;
            result.numerator = this.numerator - other.numerator;
        } else {
            int nok = nok(this.denominator, other.denominator);
            result.denominator = nok;
            result.numerator = this.numerator * (nok / this.denominator) - other.numerator * (nok / other.denominator);
        }
        sokr(result);
        return result;
    }

    public static Fraction dif(Fraction a, Fraction b) {
        Fraction result = new Fraction(1, 1);
        if (a.denominator == b.denominator) { //если знаменатели одинаковые
            result.denominator = a.denominator;
            result.numerator = a.numerator - b.numerator;
        } else {
            int nok = nok(a.denominator, b.denominator);
            result.denominator = nok;
            result.numerator = a.numerator * (nok / a.denominator) - b.numerator * (nok / b.denominator);
        }
        sokr(result);
        return result;
    }

    public Fraction multiply(Fraction other) {
        Fraction result = new Fraction(1, 1);
        result.denominator = this.denominator * other.denominator;
        result.numerator = this.numerator * other.numerator;
        sokr(result);
        return result;
    }
    public static Fraction multiply(Fraction a, Fraction b) {
        Fraction result = new Fraction(1, 1);
        result.denominator = a.denominator * b.denominator;
        result.numerator = a.numerator * b.numerator;
        sokr(result);
        return result;
    }

    public Fraction division(Fraction other) {
        Fraction result = new Fraction(1, 1);
        result.denominator = this.denominator * other.numerator;
        result.numerator = this.numerator * other.denominator;
        sokr(result);
        return result;
    }
    public static Fraction division(Fraction a, Fraction b) {
        Fraction result = new Fraction(1, 1);
        result.denominator = a.denominator * b.numerator;
        result.numerator = a.numerator * b.denominator;
        sokr(result);
        return result;
    }
}

