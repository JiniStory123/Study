import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/*
스택 자료구조를 이용해 식을 계산한다.
수식을 읽어들이고 수가 입력되면 그대로 출력, 연산자가 입력되면 스택에 push.
연산자가 스택에 저장된 상태에서 연산자가 입력된다면 둘의 우선순위를 비교해 결정한다.

새로 입력된 연산자가 괄호 열기 "("인 경우 pop 없이 push
새로 입력된 연산자가 괄호 닫기 ")"인 경우 괄호 열기 "("가 pop될 때까지 pop 수행
새로 입력된 연산자 우선순위 > 스택 속 연산자의 우선순위 pop 없이 push
그 외의 경우 스택 속 연산자를 pop
*/

public class Calculator extends JFrame
{
    private JTextField tf_input;
    private JPanel p_button;

    public static void main(String[] args)
    {
        new Calculator();
    }

    public Calculator()
    {
        tf_input = new JTextField();
        tf_input.setEditable(false);
        tf_input.setBackground(Color.WHITE);
        tf_input.setBounds(8, 10, 270, 30);

        p_button = new JPanel(); // 버튼 패널
        p_button.setLayout(new GridLayout(5, 4, 0, 0));
        p_button.setBounds(8, 40, 270, 235);
        String button_names[] = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "0", "+/-", "/", "(", ")", "C", "=" };
        JButton buttons[] = new JButton[button_names.length];
        for(int i=0; i< button_names.length; i++)
        {
            buttons[i] = new JButton(button_names[i]);
            buttons[i].addActionListener(new Action());
            p_button.add(buttons[i]);
        }

        add(tf_input);
        add(p_button);

        setTitle("계산기");
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    class Action implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String operation = e.getActionCommand();

            if(operation.equals("C"))
                tf_input.setText("");
            else if(operation.equals("="))
            {
//                System.out.println(tf_input.getText());
                String[] input = divideInput(tf_input.getText());
                for (String ee : input)
                {
                    System.out.print(ee + " ");
                }
                double result = calc(input);

                if(result % 1 == 0)
                    tf_input.setText(Math.round(result) + "");
                else
                    tf_input.setText(result + "");
            }
            else
            {
                switch (operation)
                {
                    case "+" :
                    case "-" :
                    case "*" :
                    case "/" :
                        tf_input.setText(tf_input.getText() + " " + e.getActionCommand() + " ");
                        break;
                    case "(" :
                        tf_input.setText(tf_input.getText() + e.getActionCommand() + " ");
                        break;
                    case ")" :
                        tf_input.setText(tf_input.getText() + " " + e.getActionCommand());
                        break;
                    case "+/-" :
                        tf_input.setText(tf_input.getText() + "-");
                        break;
                    default :
                        tf_input.setText(tf_input.getText() + e.getActionCommand());
                }
            }
        }
    }

    private static String[] divideInput(String input)
    {
        String[] inputArr = input.split(" ");
        String inputStr = "";
        Stack<String> stack = new Stack<String>();

        for(String exp : inputArr)
        {
            try
            {
                double num = Double.parseDouble(exp);
                inputStr += num + " ";
            }
            catch (NumberFormatException e)
            {
                if(exp.equals("("))
                    stack.push("(");
                else if(exp.equals(")"))
                {
                    while(!stack.peek().equals("("))
                        inputStr += stack.pop() + " ";
                    stack.pop();
                }
                else
                {
                    OperatorPriorityWithParentheses priority = OperatorPriorityWithParentheses.findPriority(exp);
                    while(!stack.isEmpty())
                    {
                        String expInStack = stack.peek();
                        if(priority.getPriority() <= OperatorPriorityWithParentheses.findPriority(expInStack).getPriority())
                            inputStr += stack.pop() + " ";
                        else
                            break;
                    }
                    stack.push(exp);
                }
            }
        }

        while(!stack.isEmpty())
            inputStr += stack.pop() + " ";

        return inputStr.trim().split(" ");
    }

    private static double calc(String[] input)
    {
        Stack<Double> stack = new Stack<Double>();
        for(String exp : input)
        {
            try
            {
                double num = Double.parseDouble(exp);
                stack.push(num);
            }
            catch (NumberFormatException e)
            {
                double num1 = stack.pop();
                double num2 = stack.pop();

                switch (exp)
                {
                    case "+":
                        stack.push(num2 + num1);
                        break;
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "/":
                        stack.push(num2 / num1);
                        break;
                }
            }
        }

        return stack.pop();
    }
}