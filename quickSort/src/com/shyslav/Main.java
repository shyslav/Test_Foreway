package com.shyslav;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static int f_count=0;
    public static int s_count=0;
        public static void main(String[] args) throws IOException {
            Main start = new Main();
            Scanner filein = new Scanner(Paths.get(args[0]));
            int N = filein.nextInt();
            int [] Array = new int [N];
            int [] Array2 = new int [N];
            int p=0;
            int r=N;
            for (int i=0;i<N;i++)
            {
                Array[i]=filein.nextInt();
            }
            System.arraycopy(Array, 0, Array2, 0, N);
            start.QuickSort(Array,p,r-1);
            System.out.println("First_Count: "+f_count);
            QuickSort2(Array2, p, r - 1);
            System.out.println("Second_Count: "+s_count);
            PrintWriter f = new PrintWriter("output.txt");
            f.print(f_count + " " + s_count);
            System.out.println("При начале с последнего елемента");
            for (int i: Array)
            {
                System.out.print(" "+i);
            }
            System.out.println("\nПри начале с медианы");
            for (int i: Array2)
            {
                System.out.print(" "+i);
            }
            f.close();
        }
        //FIRST SORT
        public void QuickSort(int[] Arr, int p, int r)
        {
            if ( p > r )
            {
                return;
            }
            int q=Partition(Arr,p,r);
            QuickSort(Arr,p,q-1);
            QuickSort(Arr,q+1,r);
        }
        public int Partition(int [] array, int p, int r)
        {
            int swap_tmp;
            int x = array[r];
            int i = p - 1;
            for (int j = p; j < r; j++)
            {
                f_count++;
                if (array[j] <= x)
                {
                    i++;
                    swap_tmp = array[i];
                    array[i] = array[j];
                    array[j] = swap_tmp;
                }
            }
            swap_tmp = array[i + 1];
            array[i + 1] = array[r];
            array[r] = swap_tmp;
            return i + 1;
        }
        ////----------------SECOND SORT
        private static void QuickSort2(int[]Arr,int p,int r){
            if (p<r)
            {
                int q=Partition2(Arr, p, r);
                QuickSort2(Arr, p, q - 1);
                QuickSort2(Arr, q + 1, r);
            }
        }
        public static int Partition2(int[] array,int p, int r)
        {
            getMedian(array,p, r);
            int swap_tmp;
            int x =  array[r];
            int i = p - 1;
            for (int j = p; j < r; j++)
            {
                s_count++;
                if (array[j] <= x)
                {
                    i++;
                    swap_tmp =  array[i];
                    array[i] =  array[j];
                    array[j] = swap_tmp;
                }
            }
            swap_tmp =  array[i + 1];
            array[i + 1] =  array[r];
            array[r] = swap_tmp;
            return i + 1;
        }
        public static int getMedian(int[]a,int p,int r){
            if (r-p+1<=3)
                return 0;
            int mediana = (r+p)/2;
            if(a[p] > a[mediana])
            {
                int temp = a[p];
                a[p] = a[mediana];
                a[mediana] = temp;
            }
            if(a[p] > a[r])
            {
                int temp = a[p];
                a[p] = a[r];
                a[r] = temp;
            }
            if(a[mediana] > a[r])
            {
                int temp = a[mediana];
                a[mediana] = a[r];
                a[r] = temp;
            }
            int temp = a[mediana];
            a[mediana] = a[r];
            a[r] = temp;
            return a[r];
        }
    }

