package com.ss.basics3.one;
//Author : Tyler Rondeau
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        //Create an instance to run the non-static method
        App runPrintDir = new App();
        Integer depth = 0;
        //Path to the folder we want to print the contents of
        final String dirPath = "D:\\Downloads\\Personal\\Smoothstack\\SmoothstackEssentials\\Java Basics 3\\testFiles";
        //Open the directory indicated in the path above
        File dir = new File(dirPath);
        //make sure the location exists and is a directory then make an array of all files inside it
        if(dir.exists() && dir.isDirectory()){
            File dirs[] = dir.listFiles();
            System.out.println("Following is a list of all Directories / Files from path : " + dir);
            System.out.println();
            runPrintDir.printDir(dirs,depth);
        }
    }
    public void printDir(File[] dir, Integer depth){
        for(File step: dir){
            //For loop to add tabs for subdirectories using a variable depth to indicate how deep we are
            for(int i = 0; i < depth; i++) System.out.print("\t");
            //If it's a file, print it's name
            if(step.isFile()) System.out.println(step.getName());
            //If it's a directory, print it's name then call the method inside it
            else if (step.isDirectory()){
                System.out.println("[" + step.getName() + "]");
                printDir(step.listFiles(), depth+1);
            }
        }
    }
}
