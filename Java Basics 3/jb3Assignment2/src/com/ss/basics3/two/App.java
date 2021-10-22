package com.ss.basics3.two;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        //path to our test file
        String testPath = "D:\\Downloads\\Personal\\Smoothstack\\SmoothstackEssentials\\Java Basics 3\\testFiles\\jb3Assignment2TestDoc";
        File appendTest = new File(testPath);
        //Make sure the file exists and is a file not a directory and we can write to it
        if(appendTest.exists() && appendTest.isFile() && appendTest.canWrite()){
            
        }
    }
}
