import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends JFrame
{
    private JTextField input;
    private String num = "";
    private ArrayList<String> equation = new ArrayList<String>();

    boolean isResult;

    // GUI
    public Calculator()
    {
        isResult = false;

        setLayout(null);
        input = new JTextField();
        input.setEditable(false);
        input.setBackground(Color.WHITE);
        input.setBounds(8, 10, 270, 30);

        JPanel buttonPanel = new JPanel();
        //GridLayout(4, 4, 10, 10) -> 가로 칸수, 세로 칸수, 좌우 간격, 상하 간격
        buttonPanel.setLayout(new GridLayout(5, 4, 0, 0));
        buttonPanel.setBounds(8, 40, 270, 235);

        String button_names[] = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "0", "00", "/", "(", ")", "C", "=" };
        JButton buttons[] = new JButton[button_names.length];

        for (int i = 0; i < button_names.length; i++)
        {
            buttons[i] = new JButton(button_names[i]);
            buttons[i].addActionListener(new Action());
            buttonPanel.add(buttons[i]);
        }

        add(input);
        add(buttonPanel);

        setTitle("계산기");
        setVisible(true);
        setSize(300,320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 버튼 리스너
    class Action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String operation = e.getActionCommand();

            //C가 눌렸을 경우
            if (operation.equals("C"))
            {
                input.setText("");

            } else if (operation.equals("="))
            { //=이 눌렸을 경우
                isResult = true;

//                double result = calc(input.getText());
//                if((result % 1) == 0)
//                {
//                    input.setText("" + Math.round(result));
//                }
//                else
//                {
//                    input.setText("" + result);
//                }
                input.setText(getCalculate(input.getText()));
                num = "";
            }
            else { // 나머지
                if(isResult == true)
                {
                    isResult = false;
                    input.setText("");
                }
                input.setText(input.getText() + e.getActionCommand());
            }
        }
    }

    static boolean checkCorrectBracket(String str) {
        Stack<Character> check = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {
                check.push('(');
            }else if(str.charAt(i) == ')') {
                if(!check.isEmpty()) {
                    check.pop();
                }else {
                    return false;
                }
            }
        }

        if(check.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    // ��� ���ϱ�
    static String getCalculate(String str) {
        char operation[] = {'+', '-', 'X', '/', '(', ')', '%'};

        ArrayList<String> postfix = new ArrayList<>();
        Stack<Character> opStack = new Stack<>();
        Stack<String> calStack = new Stack<>();
        String num = "";

        if(str.charAt(0) == '-') {
            str = "0" + str;
        }

        if(!checkCorrectBracket(str)) {
            return "Bracket Error";
        }

        // ���� �����ڷ� ����
        for(int i = 0; i < str.length(); i++) {
            boolean checkOp = false;
            for(int j = 0; j < operation.length; j++) {
                if(str.charAt(i) == operation[j]) {

                    checkOp = true;

                    if(!num.equals("")) {
                        postfix.add(num);
                        num = "";
                    }

                    if(operation[j] == '(') { // '(' �̸� ������ push
                        opStack.push(operation[j]);
                    }else if(operation[j] == ')') {
                        // '(' ������ ������
                        while(opStack.peek() != '(' && !opStack.isEmpty()) {
                            postfix.add(opStack.pop().toString());
                        }
                        // '(' pop
                        opStack.pop();
                    }else {
                        if(opStack.isEmpty()) {
                            opStack.push(operation[j]);
                        }else {
                            if(opOrder(opStack.peek()) < opOrder(operation[j])) {
                                opStack.push(operation[j]);
                            }else {
                                postfix.add(opStack.pop().toString());
                                opStack.push(operation[j]);
                            }
                        }
                    }
                }
            }

            if(!checkOp) {
                num += str.charAt(i);
            }

        }

        if(!num.equals("")) {
            postfix.add(num);
        }

        while(!opStack.isEmpty()) {
            postfix.add(opStack.pop().toString());
        }

        for(int i = 0; i < postfix.size(); i++) {
            calStack.push(postfix.get(i));
            for(int j = 0; j < operation.length; j++) {
                if(postfix.get(i).charAt(0) == operation[j]) {
                    calStack.pop();
                    Double n2 = Double.parseDouble(calStack.pop());
                    String re = "";

                    if(operation[j] == '%') {
                        re = Double.toString(n2 * 0.01);
                    }else {
                        Double n1 = Double.parseDouble(calStack.pop());
                        if(operation[j] == '+') {
                            re = Double.toString(n1 + n2);
                        }else if(operation[j] == '-') {
                            re = Double.toString(n1 - n2);
                        }else if(operation[j] == 'X') {
                            re = Double.toString(n1 * n2);
                        }else if(operation[j] == '/') {
                            re = Double.toString(n1 / n2);
                        }
                    }

                    calStack.push(re);
                }
            }
        }

        Double result = Double.parseDouble(calStack.pop());
        String calResult[] = Double.toString(result).split("\\.");

        if(Double.parseDouble(calResult[1]) == 0) {
            if(3 <= calResult[1].length() && calResult[1].substring(0, 2).equals("0E")) {
                return Double.toString(result);
            }else { // ���� �϶�,
                return calResult[0];
            }
        }else { //�Ǽ��϶�
            return String.format("%.10f", result);
        }
    }

    static int opOrder(char op) {
        switch(op) {
            case '+':
            case '-':
                return 1;
            case 'X':
            case '/':
                return 2;
            case '%':
                return 3;
            default:
                return -1;
        }
    }

    public static double calc(String input)
    {
        int idx;
        idx = input.indexOf('+');

        if (idx != -1)
        {
            return calc(input.substring(0, idx)) + calc(input.substring(idx + 1));
        }
        else
        {
            idx = input.indexOf('-');
            if (idx != -1)
            {
                return calc(input.substring(0, idx)) - calc(input.substring(idx + 1));
            }
            else
            {
                idx = input.indexOf('*');
                if (idx != -1)
                {
                    return calc(input.substring(0, idx)) * calc(input.substring(idx + 1));
                }
                else
                {
                    idx = input.indexOf('/');
                    if (idx != -1)
                    {
                        return calc(input.substring(0, idx)) / calc(input.substring(idx + 1));
                    }
                }
            }
        }

        String data = input.trim();
        if (data == null || data.isEmpty()) {
            return 0;
        }

        return Double.parseDouble(data);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}