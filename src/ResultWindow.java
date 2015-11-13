import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 09.11.2015.
 */
public class ResultWindow {
    JPanel resultWindow;
    JPanel areasPanel;
    JLabel percentRightLabel;
    JLabel markLabel;
    JLabel percentRight;
    JLabel mark;
    JButton restartButton;

    public ResultWindow(){
        createResultWindow();
    }

    public void createResultWindow(){
        BorderLayout crBorder = new BorderLayout();
        GridLayout areasBorder = new GridLayout(2, 1);
        resultWindow = new JPanel();
        resultWindow.setLayout(crBorder);
        areasPanel = new JPanel();
        areasPanel.setLayout(areasBorder);


        Font font = new Font(null, Font.BOLD, 30);
        percentRightLabel = new JLabel("Процент правильных ответов:");
        percentRight = new JLabel();
        markLabel = new JLabel("Ваша оценка:");
        mark = new JLabel();
        percentRightLabel.setHorizontalAlignment(JLabel.CENTER);
        percentRightLabel.setVerticalAlignment(JLabel.CENTER);
        markLabel.setHorizontalAlignment(JLabel.CENTER);
        markLabel.setVerticalAlignment(JLabel.CENTER);
        percentRight.setHorizontalAlignment(JLabel.CENTER);
        percentRight.setVerticalAlignment(JLabel.CENTER);
        percentRight.setFont(font);
        percentRight.setForeground(Color.GREEN);
        mark.setHorizontalAlignment(JLabel.CENTER);
        mark.setVerticalAlignment(JLabel.CENTER);
        mark.setFont(font);
        mark.setForeground(Color.RED);
        areasPanel.add(percentRightLabel);
        areasPanel.add(markLabel);
        areasPanel.add(percentRight);
        areasPanel.add(mark);

        restartButton = new JButton("Пройти заново");

        resultWindow.add("Center", areasPanel);
        resultWindow.add("South", restartButton);

        JFrame frame = new JFrame("Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(resultWindow);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
    }

    public void setMark(int right, int all) {
        int percent = (right / all) * 100;
        percentRight.setText(percent + "%");

        if (percent < 30)
            mark.setText("2");
        else if (percent < 60 && percent >= 30)
            mark.setText("3");
        else if (percent < 90 && percent >= 60)
            mark.setText("4");
        else
            mark.setText("5");
    }
}
