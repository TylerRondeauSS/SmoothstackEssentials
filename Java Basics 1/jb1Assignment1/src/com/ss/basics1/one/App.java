package com.ss.basics1.one;

public class App {
    public static void main(String[] args) throws Exception {
        //First pattern
        System.out.print("1)");
        int row = 4;
        for(int i=0; i <= row; i++){
            for(int j =0; j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println(".........");
        System.out.println();

        //second pattern
        System.out.println("2)");
        System.out.println("..........");
        for(int i=0; i <= row; i++){
            for(int j=4; j>i;j--){
                System.out.print("*");
            }
            if(i==row){
                break;
            }
            System.out.println();
        }
        System.out.println();
        
        //Third pattern
        System.out.println("3)");
        int space = row;
        for(int i=0; i <= row-1; i++){
            for(int j=0; j<=space;j++){
                System.out.print(" ");
            }
            space--;
            for(int j = 1; j<=2*i + 1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("...........");
        System.out.println();

        //Fourth pattern
        System.out.println("4)");
        System.out.println("............");
        space = 2;
        for(int i=0; i <= row-1; i++){
            for(int j=0; j<space;j++){
                System.out.print(" ");
            }
            space++;
            for(int j = 7; j>=2*i + 1; j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
