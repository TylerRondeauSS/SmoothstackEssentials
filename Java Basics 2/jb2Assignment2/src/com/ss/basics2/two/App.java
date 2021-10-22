package com.ss.basics2.two;

public class App {
    public static void main(String[] args) throws Exception {
        //Basic filled array for debugging
        Integer[][] twoDArray = {{1,2,3}, {4,5,6}, {7,8,9}};
        /*code to make array random
        for(int row = 0; row < twoDArray.length;row++){
            for(int col = 0; col < twoDArray[row].length;col++){
                twoDArray[row][col] = (int) (Math.random()*100 + 1);
            }
        }*/
        Integer maxValue = twoDArray[0][0], maxRow=0, maxCol = 0;
        //traverse array from top left to bottom right by row then column
        for(int row = 0; row < twoDArray.length;row++){
            for(int col = 0; col < twoDArray[row].length;col++){
                if(twoDArray[row][col] > maxValue) maxValue = twoDArray[row][col]; maxRow = row; maxCol = col;
            }
        }
        System.out.println("The highest value in the 2D Array is : " + maxValue + " at position : [" + maxRow + "][" + maxCol + "]");
    }
}