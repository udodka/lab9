import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static int get_priority(char oper){
        switch (oper){
            case '-':
            case '+':
                return 3;
            case '*':
            case ':':
                return 2;
        }
        return 0;
    }
    public static Fraction do_operation(Fraction f1, Fraction f2, char op){
        switch (op){
            case '+':
                return f1.add(f2);
            case '-':
                return f1.dif(f2);
            case '*':
                return f1.multiply(f2);
            case ':':
                return f1.division(f2);
        }
        return f1;
    }
    public static Fraction calculate(String input){
        int haserrors = 0;
        Fraction res = new Fraction(0,0);
        try{
            // создаем два стека и выставляем верхушки на 0
            Fraction[] fracs = new Fraction[100];
            char[] operations = new char[100];
            int ftop = 0;
            int optop = 0;
            Fraction f1 = new Fraction(0,0);
            Fraction f2 = new Fraction(0,0);
            int c = 0;
            char operation = ' ';
            // начинаем перебирать строку
            int i = 0;
            while(i < input.length()){
                switch (input.charAt(i)){
                    // если нам встретилась операция
                    case '+':
                    case '-':
                    case ':':
                    case '*':
                        // выясняем ее приоритет
                        int pr = get_priority(input.charAt(i));
                        for (int j = optop; j >= 0; j--){
                            // выталкиваем все с приоритетом не ниже
                            if (optop != 0 && get_priority(operations[optop-1]) <= pr && operations[optop-1] != '('){
                                ftop--;
                                fracs[ftop-1] = do_operation(fracs[ftop-1], fracs[ftop], operations[optop-1]);
                                optop--;
                            }else{
                                break;
                            }
                        }
                        // и пихаем в стек
                        operations[optop++] = input.charAt(i);
                        i++;
                        break;
                    case '(':
                        // добавляем скобку в стек, если встретилась
                        operations[optop++] = '(';
                        i++;
                        break;
                    case ')':
                        // если встретилась закрывающая
                        for (int j = optop; j >= 0; j--){
                            // выносим всё, что до открывающей
                            if (operations[optop-1] != '('){
                                ftop--;
                                fracs[ftop-1] = do_operation(fracs[ftop-1], fracs[ftop], operations[optop-1]);
                                optop--;
                            }else{
                                optop--;
                                break;
                            }
                        }
                        i++;
                        break;
                    default:
                        // если это не операция
                        if (input.charAt(i) < '9' && input.charAt(i) > '0'){
                            // если циферка
                            Fraction ft = new Fraction(0,0);
                            int f = 0;
                            for (int j = 0; j < 10; j++){
                                // если доходим до знака, выходим
                                if (i > input.length()-1 || input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == ':' || input.charAt(i) == '*' || input.charAt(i) == '(' || input.charAt(i) == ')'){
                                    break;
                                }
                                // если дробь, переходим к знаменателю
                                if (input.charAt(i)=='/'){
                                    i++;
                                    f++;
                                    continue;
                                }
                                // в зависимости от флага пишем в числитель либо в знаменатель
                                if (f == 0){
                                    ft.numerator = ft.numerator*10 + input.charAt(i)-'0';
                                    i++;
                                }else {
                                    ft.denominator = ft.denominator*10 + input.charAt(i)-'0';
                                    i++;
                                }
                            }
                            fracs[ftop++] = ft;
                        }else {
                            haserrors = 1;
                        }
                }
                if (haserrors == 1){
                    break;
                }
            }
            // стек добиваем
            if (haserrors != 1){
                for (int j = optop; j > 0; j--){
                    ftop--;
                    fracs[ftop-1] = do_operation(fracs[ftop-1], fracs[ftop], operations[optop-1]);
                    optop--;
                }
                res = fracs[0];
            }
        } catch (Exception e){
            // если ввод не очень(
            haserrors = 1;
        }
        if (haserrors == 1){
            System.out.println("Some errors in input");
            return null;
        }
        return res;
    }
    public static <e> void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(true){
            System.out.print("Enter expression:");
            String input = in.nextLine();
            if (Objects.equals(input, "quit")){
                in.close();
                System.out.println("The end");
                break;
            }
            Fraction res = calculate(input);
            if (res != null){
                System.out.println("Result: " + res.numerator + "/" + res.denominator);
            }else{
                System.out.println("Result: ");
            }
        }
    }
}
