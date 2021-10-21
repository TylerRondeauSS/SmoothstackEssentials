package com.ss.basics2.one;

public class App {
    public static void main(String[] args) throws Exception {
        int[] argsInts;
        int addedTotal = 0;
        argsInts = new int[args.length];
        try{
            for(int i =0; i<args.length;i++){
                argsInts[i] = Integer.parseInt(args[i]);
            }
        }
        catch (NumberFormatException nfe){
            System.err.println("Must enter only integers");
            System.exit(1);
        }
        for(int i = 0; i < argsInts.length;i++){
            addedTotal += argsInts[i];
        }
        System.out.println("The total of all values passed is : " + addedTotal);
    }
}
