import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 03.11.2015.
 */
public class BaseIO {
    private String fileName = "D://TestBase.txt";

    public BaseIO() throws IOException {

    }

    public List<ArrayList<String>> readTestBase() throws IOException {
        List<String> questionElement = new ArrayList<String>();
        Scanner scanner = new Scanner(new FileInputStream(fileName),"Windows-1251").useDelimiter("\\s*\\$");
        while (scanner.hasNext()) {
            String i = scanner.next();
            questionElement.add(i);
        }
        List<ArrayList<String>> listQuestions = new ArrayList<ArrayList<String>>();
        ArrayList<String> questionBlock = new ArrayList<String>();

        for (int i = 0; i < questionElement.size(); i++){
            if (!questionElement.get(i).contains("end"))
                questionBlock.add(questionElement.get(i));
            else {
                listQuestions.add(questionBlock);
                questionBlock = new ArrayList<String>();
            }
        }

        return listQuestions;
    }
}

