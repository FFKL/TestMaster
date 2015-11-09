import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 08.11.2015.
 */
public class TestStartThread implements Runnable {

    ArrayList<String> currentQuestion = null;
    private boolean flag;
    @Override
    public void run() {
        try {
            BaseIO base = new BaseIO();
            List<ArrayList<String>> questionsBase = base.readTestBase();
            mysuspend();
            for (int i = 0; i < questionsBase.size(); i++) {
                synchronized (this){
                    while (flag){
                        currentQuestion = questionsBase.get(i);
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public synchronized void myresume() {
        flag = false;
        notify();
    }

    public synchronized void mysuspend() {
        flag = true;
    }
}

