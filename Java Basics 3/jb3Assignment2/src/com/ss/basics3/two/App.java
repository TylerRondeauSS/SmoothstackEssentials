package com.ss.basics3.two;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.*;

public class App {
    public static void main(String[] args) throws Exception {
        App runTest = new App();
        //path to our test file
        final String testPath = "D:\\Downloads\\Personal\\Smoothstack\\SmoothstackEssentials\\Java Basics 3\\testFiles\\jb3Assignment2TestDoc.txt";
        final String testText = "\nI added text to the file again!";
        runTest.writeFile(testPath, testText);
    }
    public void writeFile(String path, String text){
        try(BufferedReader read = new BufferedReader(new FileReader(path))){
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            System.err.println("File not found, please check that the file exists and the path is correct");
        }
    }
}
