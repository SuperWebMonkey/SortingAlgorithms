
package sortcompare;

import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *Project Description: test out the time efficiency of the algorithms
 **/

public class SortCompare 
{    
    public static long sum = 0;
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        DecimalFormat format = new DecimalFormat("0.0000");
        int ranNum2;
        char option;
        int j = 1;
        long startTime = 0;
        long endTime = 0;
        long totalTime = 0;
        long average = 0;
        int count = 0;
        
        //Sample Ary
        int ary[] = new int[5000];
        int ary2[] = new int[1024]; //random array of 2^1 to 2^16
        
        for(int i = 0; i < ary.length; i++){
            ary[i] = i;
        }
        
        do//Start of Menu
        {
            for(int i = 0; i < ary2.length; i++)//Array of random integers
            {
                ranNum2 = rand.nextInt(ary2.length) + 1;
                ary2[i] = ranNum2;
            }
            
            System.out.println("Unsorted Array: ");
            
            for(int i = 0; i < 32; i++){
                System.out.print(ary2[i] + " ");
            }
            
            int start = ary2[0]; //partition start
            int end = ary2[ary2.length - 1]; //partition end
            
            System.out.println("\nPlease select one of the following options"
                    + "\na)Insertion Sort \nb)Selection Sort \nc)QuickSort 1"
                    + "\nd)QuickSort 2 \ne)QuickSort 3\nq)To quit");
            option = keyboard.next().charAt(0);
            
            if(option == 'a' || option == 'A')
            {
                for(int i = 0; i < 10; i++){ //cycle of 10
                    startTime = System.nanoTime();
                    ary2 = insertionSort(ary2);
                    endTime = System.nanoTime();
                    totalTime += (endTime - startTime)/1000;
                    count++;

                }
               
                print(ary2);
            }
            else if(option == 'b' || option == 'B')
            { 
                for(int i = 0; i < 10; i++){ //10 cycles to sort n elements
                    startTime = System.nanoTime();
                    ary2 = selectionSort(ary2);
                    endTime = System.nanoTime();
                    totalTime += (endTime - startTime)/1000;
                    count++;
                }
                print(ary2);
            }
            else if(option == 'c' || option == 'C')
            {    //for(int i = 0; i < 5; i++){
                 startTime = System.nanoTime();
                 ary2 = quickSort1(ary2, 0, ary2.length - 1);
                 endTime = System.nanoTime();
                 totalTime += (endTime - startTime)/1000;
                 count++;
                 //}
                 print(ary2);
                
            }
            else if(option == 'd' || option == 'D')
            {
                //for(int i = 0; i < 5; i++){
                    startTime = System.nanoTime();
                    ary2 = quickSort2(ary2, 0, ary2.length - 1);
                    endTime = System.nanoTime();
                    totalTime += (endTime - startTime)/1000;
                    count++;
                //}
                
                print(ary2);
            }
            else if (option ==  'e' || option == 'E')
            {
                //for(int i = 0; i < 10; i++){
                    startTime = System.nanoTime();
                    ary2 = quickSort3(ary2, 0, ary2.length - 1);
                    endTime = System.nanoTime();
                    totalTime += (endTime - startTime)/1000;
                    count++;
                //}
                print(ary2);
            }
            else if(option == 'q' || option == 'Q')
            {
                System.out.println("You have quit the program");
            }
            else
            {
                System.out.println("Wrong Option. Please choose again");
            }
            
            average = totalTime/count;
          
            System.out.println("\nThe average time is " + format.format(average) + "\n");
              
            
        }while(option != 'q' && option != 'Q');
        
                        
    }
    
    /**
     * Insertion sort
     * @param ary
     * @return 
     */
    public static int[] insertionSort(int []ary)
    {
       for(int i = 1; i < ary.length; ++i)
       {
           for(int j = i; j > 0; --j)
           {
               if(ary[j] < ary[j - 1])
               {    
                   swap(ary, j, j - 1);
               }
               else
               {
                   break;
               }
           }
           
       }
        
       return ary;
    }
    
    /**
     * Selection sort
     * @param ary
     * @return ary: array of sorted values
     */
    public static int[] selectionSort(int[] ary)
    {
        for(int i = 0; i < ary.length - 1; i++)
        {
            int min = i;
            for(int j = i + 1; j < ary.length; j++)
            {
                if(ary[j] < ary[min])
                    min = j;
            }
            
            swap(ary, min, i);
        }
        
        return ary;
    }
    
    /**
     * Ordinary Quick Sort
     * @param ary: sorted or unsorted array
     * @param low: start of array
     * @param high: end of array
     * @return array: sorted array
     */
    public static int[] quickSort1(int []ary, int low, int high)
    {
        if(low < high){
            int pivot = partition(ary, low, high);
        
            if(low < pivot - 1)
            {
                quickSort1(ary, low, pivot - 1);
            }
        
            if(high > pivot)
            {
                quickSort1(ary, pivot, high);
            }
        }
        
        return ary;
    }
    
    /**
     * Quick Sort with insertion sort
     * @param ary: sorted or unsorted array
     * @param low: left side of ary
     * @param high: right side of ary
     * @return ary: sorted ary
     */
    public static int[] quickSort2(int []ary, int low, int high)
    {
       if(low < high){
        //Sort 2 quicksort with insertion sort
            if((high - low) <= 16 || ary.length <= 16)
            {
                //System.out.println("Insertion sort works.");
                insertSort2(ary, low, high + 1);
                //insertionSort(ary);
            }
            else
            {
                 int pivot = partition(ary, low, high);
        
                 if(low < pivot - 1)
                 {
                    quickSort2(ary, low, pivot - 1);
                 }
        
                if(high > pivot)
                {
                    quickSort2(ary, pivot, high);
                }
            }
        }
        
        return ary;
    }
    
    public static void insertSort2(int[] ary, int start, int end)
    {
        for(int i = start + 1; i < end; i++)
        {
            int key = ary[i];
            int j = i - 1;
            while(j >= 0 && key < ary[j]){
                ary[j + 1] = ary[j];
                j--;
            }
            ary[j + 1] = key;
        }
    }
    
    /**
     * Quick Sort with Randomizer
     * @param ary: sorted or unsorted ary
     * @param low: left side of ary
     * @param high: right side of ary
     * @return ary: sorted ary
     */
    public static int[] quickSort3(int []ary, int low, int high)
    {
        if(low < high)
        {
            if((high - low + 1) >= 16)
            {
                // System.out.println("It works." + (high - low + 1));
                int ranNum = randomizer(ary, ary.length - 1);
                int tmp = ary[low];
                ary[low] = ary[low + (ranNum%(high - low + 1))];
                ary[low + (ranNum%(high - low + 1))] = tmp;
                //swap(ary, ary[low], ary[low + (ranNum%(high - low + 1))]);
                //System.out.println("ary i is " + ary[low + ranNum%(high - low + 1)]);
           
            }
        
            int pivot = partition(ary, low, high);
       
            if(low < pivot - 1)
            {
                quickSort3(ary, low, pivot - 1);
            }
        
            if(high > pivot)
            { 
                  quickSort3(ary, pivot, high);
            }
        }
        
        return ary;
    }
    
    /**
     * Ary is divided into two: right and left to help with sorting
     * @param ary: sorted or unsorted ary
     * @param start: starting index
     * @param end: ending inded
     * @return index
     */
     public static int partition(int[] ary, int start, int end)
    {
        int pivot = ary[start];
        
        while(start <= end)
        {
            while(ary[start] < pivot)
                start++;
            
            while(ary[end] > pivot)
                end--;
            
            if(start <= end)
            {
                int temp = ary[start];
                ary[start] = ary[end];
                ary[end] = temp;
                
                start++;
                end--;
            }
        }
        return start;
    }
    
     /**
      * 
      * @param ary: sorted or nonsorted array
      * @param i: swapping value
      * @param j: swapping value
      */
    public static void swap(int ary[], int i, int j)
    {
        int temp = ary[i];
        ary[i] = ary[j];
        ary[j] = temp;        
    }
    
    public static long startTime(long time){
        time = System.nanoTime();
        return time;
    }
    
    /**
     * Create a random start value of values in the range of the size of the
     * array
     * @param ary: contents of ary 
     * @param length: size of the ary
     * @return 
     */
    public static int randomizer(int ary[], int length)
    {
        Random rand = new Random();
        int ranNum = ary[rand.nextInt(length) + 1];
        return ranNum;
    }
    
    public static long endTime(long time)
    {
        time = System.nanoTime();
        return time;
    }
    
    public static long timePerformance(long time1, long time2){
        long totalTime = (time2 - time1);
        System.out.println("The time performance is " + totalTime);
        return totalTime;
    }
    
    public static void sum(long time)
    {
        sum += time;
    }
    
    public static void average(long sum, int count){
        long average = sum/count;
        System.out.println("The average time is " + average);
    }
    
    /**
     * Prints the values of the sorted array
     * @param ary: sorted array
     */
    public static void print(int[] ary){
        System.out.println("Sorted Array:");
        for(int i = 0; i < 32; i++){
            System.out.print(ary[i] + " ");
                     
        }
    }
}
