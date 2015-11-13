import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 03.11.2015.
 */
public class WindowBuilder {
    JPanel mainWindow;
    JPanel testPanel;
    JPanel buttonPanel;
    JButton getQuestions;
    JButton checkAnswer;
    JTextField question;
    JTextField answerA;
    JTextField answerB;
    JTextField answerC;
    JTextField answerD;
    JRadioButton a;
    JRadioButton b;
    JRadioButton c;
    JRadioButton d;

    char id;
    public static int counter = 0;
    public static int rights = 0;

    public WindowBuilder() {
        createWindow();
    }

    public void createWindow(){
        BorderLayout mainBorder = new BorderLayout();
        mainWindow = new JPanel();
        mainWindow.setLayout(mainBorder);
        testPanel = new JPanel();
        buttonPanel = new JPanel();
        getQuestions = new JButton("Старт/Дальше");
        checkAnswer = new JButton("Проверить");
        GridLayout layoutQuestion = new GridLayout(4, 2);
        GridLayout layoutButtons = new GridLayout(1, 2);
        testPanel.setLayout(layoutQuestion);
        buttonPanel.setLayout(layoutButtons);

        question = new JTextField(30);
        answerA = new JTextField(30);
        answerB = new JTextField(30);
        answerC = new JTextField(30);
        answerD = new JTextField(30);
        ButtonGroup groupRadioButtons = new ButtonGroup();
        a = new JRadioButton("А");
        b = new JRadioButton("B");
        c = new JRadioButton("C");
        d = new JRadioButton("D");
        groupRadioButtons.add(a);
        groupRadioButtons.add(b);
        groupRadioButtons.add(c);
        groupRadioButtons.add(d);

        /*a.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //AbstractButton aButton = (AbstractButton)e.getSource();
                ButtonModel aModel = a.getModel();
                boolean selected = aModel.isSelected();
                if (selected == true)
                    id = 'A';
            }
        });*/
        testPanel.add(a);
        testPanel.add(answerA);
        testPanel.add(b);
        testPanel.add(answerB);
        testPanel.add(c);
        testPanel.add(answerC);
        testPanel.add(d);
        testPanel.add(answerD);

        buttonPanel.add(getQuestions);
        buttonPanel.add(checkAnswer);

        mainWindow.add("North", question);
        mainWindow.add("Center", testPanel);
        mainWindow.add("South", buttonPanel);

        question.setEditable(false);
        answerA.setEditable(false);
        answerB.setEditable(false);
        answerC.setEditable(false);
        answerD.setEditable(false);

        getQuestions.addActionListener(new StartQuestion());
        checkAnswer.addActionListener(new CheckAnswer());

        JFrame frame = new JFrame("TestMaster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainWindow);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
    }

    class StartQuestion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            BaseIO base = null;
            try {
                base = new BaseIO();
                ArrayList<String> currentQuestion = base.readTestBase().get(counter);
                System.out.println(counter);
                if (counter >= (base.readTestBase().size()) - 1){
                    ResultWindow result = new ResultWindow();
                    result.setMark(rights, base.readTestBase().size());
                }
                else {
                    question.setText(currentQuestion.get(0));
                    ArrayList<Integer> shuffledAnswers = new Shuffle().shuffling();

                    String afterRandomA = currentQuestion.get(shuffledAnswers.get(0));
                    String afterRandomB = currentQuestion.get(shuffledAnswers.get(1));
                    String afterRandomC = currentQuestion.get(shuffledAnswers.get(2));
                    String afterRandomD = currentQuestion.get(shuffledAnswers.get(3));

                    if (afterRandomA.contains("&")){
                        id = 'A';
                        afterRandomA = afterRandomA.substring(1);
                    }
                    else if (afterRandomB.contains("&")) {
                        id = 'B';
                        afterRandomB = afterRandomB.substring(1);
                    }
                    else if (afterRandomC.contains("&")){
                        id = 'C';
                        afterRandomC = afterRandomC.substring(1);
                    }
                    else if (afterRandomD.contains("&")){
                        id = 'D';
                        afterRandomD = afterRandomD.substring(1);
                    }

                    answerA.setText(afterRandomA);
                    answerB.setText(afterRandomB);
                    answerC.setText(afterRandomC);
                    answerD.setText(afterRandomD);
                    counter++;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    class CheckAnswer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            char id2 = ' ';
            if (a.getModel().isSelected() == true)
                id2 = 'A';
            else if (b.getModel().isSelected() == true)
                id2 = 'B';
            else if (c.getModel().isSelected() == true)
                id2 = 'C';
            else if (d.getModel().isSelected() == true)
                id2 = 'D';

            if (id2 == id) {
                System.out.println("Ответ верный!!! Ура");
                rights++;
                System.out.println(rights);
            }
            else
                System.out.println("Ответ неверный! Подумайте еще!");

        }
    }
}
