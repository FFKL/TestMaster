import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 08.11.2015.
 */
public class Shuffle {

    ArrayList<Integer> shuffling(){
        //todo:Начало рандомной выдачи ответов!!! возможно переделать!
        ArrayList<Integer> shuffledAnswers = new ArrayList<Integer>();
        Random rand = new Random();
        while (shuffledAnswers.size() != 4){
            int randNum = rand.nextInt(4) + 1;
            if (!shuffledAnswers.contains(randNum))
                shuffledAnswers.add(randNum);
        }
        return shuffledAnswers;
    }
}
