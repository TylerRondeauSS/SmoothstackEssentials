package com.ss.basics3.three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        App testRead = new App();
        final String path = "D:\\Downloads\\Personal\\Smoothstack\\SmoothstackEssentials\\Java Basics 3\\testFiles\\jb3Assignment3TestDoc.txt";
        char searchChar = 'e';
        //store the value returned which will either be -1 for failing to find the file or the count of searchChar in the file
        int appeared = testRead.readFile(path, searchChar);
        //If failed to read file, exit
        if(appeared == -1){
            System.exit(0);
        }
        //If file read, print out the char and how many were found
        else{
            System.out.println("The character " + searchChar + " appeared " + appeared + " times!");
        }
    }
    public Integer readFile(String path, Character c){
        try(BufferedReader bfr = new BufferedReader(new FileReader(path))){
            int ch = 0,counter = 0;
            //While the incoming character from the reader (read as an int) is not -1 (would mean end of file) 
            while((ch=bfr.read())!=-1){
                //If the character read (casted to char from int) is the one we're looking for, add to counter
                if((char)ch == c){
                    counter++;
                }
            }
            return counter;
        }
        catch(IOException e){
            System.err.println("Could not find file, please check path.");
            return -1;
        }
    }
}
