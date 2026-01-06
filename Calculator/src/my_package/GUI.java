package my_package;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame {
    JPanel buttonPanel = new JPanel();
    JTextField result = new JTextField("0");
    
    JButton plusButton, subButton, mulButton, divButton, resultButton, ACButton, deleteButton;
    RoundButton Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button0;

    String expression = "";

    public GUI() {
        plusButton = createOpButton("+");
        subButton = createOpButton("-");
        mulButton = createOpButton("*");
        divButton = createOpButton("/");
        resultButton = createOpButton("=");
        ACButton = createOpButton("AC");
        deleteButton = createOpButton("<");

        Button1 = createNumButton("1"); Button2 = createNumButton("2"); Button3 = createNumButton("3");
        Button4 = createNumButton("4"); Button5 = createNumButton("5"); Button6 = createNumButton("6");
        Button7 = createNumButton("7"); Button8 = createNumButton("8"); Button9 = createNumButton("9");
        Button0 = createNumButton("0");

        result.setFont(new Font("Arial", Font.BOLD, 40));
        result.setHorizontalAlignment(JTextField.RIGHT);
        result.setEditable(false);
        result.setBackground(Color.BLACK);
        result.setForeground(Color.WHITE);
        result.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(ACButton); buttonPanel.add(deleteButton); buttonPanel.add(new JLabel("")); buttonPanel.add(divButton);
        buttonPanel.add(Button7); buttonPanel.add(Button8); buttonPanel.add(Button9); buttonPanel.add(mulButton);
        buttonPanel.add(Button4); buttonPanel.add(Button5); buttonPanel.add(Button6); buttonPanel.add(subButton);
        buttonPanel.add(Button1); buttonPanel.add(Button2); buttonPanel.add(Button3); buttonPanel.add(plusButton);
        buttonPanel.add(new JLabel("")); buttonPanel.add(Button0); buttonPanel.add(new JLabel("")); buttonPanel.add(resultButton);

        this.setLayout(new BorderLayout());
        this.add(result, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.BLACK);

        this.setSize(350, 500);
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ButtonListener listener = new ButtonListener();
        JButton[] allButtons = {
            Button0, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9,
            plusButton, subButton, mulButton, divButton, resultButton, ACButton, deleteButton
        };

        for (JButton btn : allButtons) {
            if (btn != null) btn.addActionListener(listener);
        }

        this.setVisible(true);
    }

    private JButton createOpButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        return btn;
    }

    private RoundButton createNumButton(String text) {
        RoundButton btn = new RoundButton(text);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        return btn;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("AC")) {
                expression = "";
                result.setText("0");
            } 
            else if (command.equals("<")) {
                if (expression.length() > 0) {
                    expression = expression.substring(0, expression.length() - 1);
                    result.setText(expression.isEmpty() ? "0" : expression);
                }
            } 
            else if (command.equals("=")) {
                try {
                    double finalResult = evaluateExpression(expression);
                    if (finalResult == (long) finalResult) {
                        result.setText(String.valueOf((long) finalResult));
                    } else {
                        result.setText(String.valueOf(finalResult));
                    }
                    expression = result.getText(); // Για να συνεχίσεις την πράξη
                } catch (Exception ex) {
                    result.setText("Error");
                    expression = "";
                }
            } 
            else {
                expression += command;
                result.setText(expression);
            }
        }
    }

    private double evaluateExpression(String expr) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        String tempNum = "";
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                tempNum += c;
            } else {
                numbers.add(Double.parseDouble(tempNum));
                operators.add(c);
                tempNum = "";
            }
        }
        numbers.add(Double.parseDouble(tempNum));

        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                double res = 0;
                if (op == '*') res = numbers.get(i) * numbers.get(i+1);
                else res = numbers.get(i) / numbers.get(i+1);
                
                numbers.set(i, res);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; 
            }
        }

        double total = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == '+') total += numbers.get(i + 1);
            else if (op == '-') total -= numbers.get(i + 1);
        }
        
        return total;
    }
}