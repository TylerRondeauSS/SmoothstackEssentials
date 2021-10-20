package com.ss.basics1.two;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int guesses = 4;
        int answer = (int) (Math.random()*100 + 1);
        //print out the number for debugging
        System.out.println(answer);
        System.out.println("Hello, please guess a number between 1 and 100!");
        int userAnswer = myScanner.nextInt();
        while(guesses!=0){
            //Check if guess is within parameters
            if (userAnswer <=0 || userAnswer >100) {
                System.out.println("Guess is not between 1 and 100. Please guess again!");
                userAnswer = myScanner.nextInt();
            }
            //Check if guess is correct
            else if (userAnswer == answer ){
                System.out.println("You got the number perfectly correct! It was : " + answer);
                myScanner.close();
                System.exit(0);
            }
            //Check if guess is within 10 of correct
            else if ((answer - 10) <= userAnswer && (answer + 10) >= userAnswer) {
                System.out.println("You were within 10 of the number! The number was " + answer);
                myScanner.close();
                System.exit(0);
            }
            //Guess was incorrect, guess again
            else {
                System.out.println("Your guess was incorrect, Please guess again! You have " + guesses + " guesses remaining.");
                userAnswer = myScanner.nextInt();
                guesses--;
            }
        }
        myScanner.close();
        System.out.println("You ran out of guesses! Sorry!");
    }
}
