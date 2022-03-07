import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MemoryGame
{
    private static String[] generate(int length) {
        String l = "abcdefghijklmnopqrstuvwxyz";
        String[] prod = new String[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int next = r.nextInt(26);
            prod[i] = Character.toString(l.charAt(next));
        }
        return prod;
    }
    public static void main(String[] args) {

        // Create the "memory strings" - an array of single character strings to
        // show in the buttons, one element at a time. This is the sequence
        // the player will have to remember.

        MemoryGameGUI Game = new MemoryGameGUI();
        int num = 1;
        while (true) {
            JFrame d = new JFrame();
            int b =  - 1;
            String l = JOptionPane.showInputDialog(d, "How long do you want the sequence to be?");
            try {
                b = Integer.parseInt(l);
            }
            catch (NumberFormatException e) {
                JFrame t = new JFrame();
                JOptionPane.showMessageDialog(t, "Enter a number", "Oops", JOptionPane.PLAIN_MESSAGE);
            }
            if (b > 0) {
                num = b;
                break;
            }
        }
        

        Game.createBoard(num, false);
        int score = 0;
        int round = 0;
        while(true) {
            String[] sequence = generate(num);
            String guess = Game.playSequence(sequence, .5);
            String ans = String.join("", sequence);
            if(ans.equals(guess)) {
                Game.matched();
                score++;
            }
            round++;
            if (!Game.playAgain()) break;
        }
        Game.showScore(score, round);

 
     

    }
}