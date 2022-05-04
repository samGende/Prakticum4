import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list= new ArrayList<Integer>();
        while (input.hasNext()) {
            try {
                list.add(input.nextInt());
            } catch (NumberFormatException e) {
                System.err.println("Input list contains a non-number.");
                return;
            }
        }
        int[]arr= new int[list.size()];
        for (int i=0; i<list.size(); i++){
            arr[i]=list.get(i);
        }
        int k = Integer.parseInt(args[0]);
        if(k>arr.length){
            System.out.println(" k muss kleiner als die Anzahl der Elemente im Array sein.");
        }else if (k==0){
            System.out.println("k kann nicht gleich NUll sein");
        }else{
            int kleinsteZahl = 0;
            if(args[1].equals("quickr")) {
                kleinsteZahl = QuickSelectRandom(arr, k);
            } else if(args[1].equals("quickf")) {
                kleinsteZahl = QuickSelectFirst(arr, k);
            } else if(args[1].equals("heap")){
                kleinsteZahl = heapSelect(arr, k);
            } else {
                System.out.println("fuck");
            }
            System.out.println("Das " +k+"-kleinste Element ist: "+ kleinsteZahl);
        }
    }


    public static int Partition(int[] data, int l, int p, int r){
        int temp;
      for (int i = l; i <= r; i++){
          if(data[i] < data[p] && i > p){
              temp = data[i];
              data[i] = data[p];
              data[p] = temp;
              p = i;
          } else if(data[i] > data[p] && i < p){
              temp = data[i];
              data[i] = data[p];
              data[p] = temp;
              p = i;
          }
        }
      return p;
    }

    public static int QuickSelectFirst(int[] data, int k, int l, int r){
       int m  = Partition(data, l, l, r);
       if(m == k){
           return m;
       } else if(m > k){
           return QuickSelectFirst(data, k, l, m-1);
       } else if(m < k){
           return QuickSelectFirst(data, k, m+1, r);
       }
       return -1;

    }

    public static int QuickSelectFirst(int[] data, int k){
        int kIndex = QuickSelectFirst(data, k-1, 0, data.length -1 );
        return data[kIndex];
    }

    public static int QuickSelectRandom(int[] data, int k, int r, int l){
        Random rand = new Random();
        int pivot = r + rand.nextInt(l-r+1);
        int m  = Partition(data, l, pivot, r);
        if(m == k){
            return m;
        } else if(m > k){
            return QuickSelectFirst(data, k, l, m-1);
        } else if(m < k){
            return QuickSelectFirst(data, k, m+1, r);
        }
        return -1;
    }

    public static int QuickSelectRandom(int[] data, int k){
        int kIndex = QuickSelectFirst(data, k-1, 0, data.length -1 );
        return data[kIndex];
    }
    public static void minHeapify(int [] data, int i, int n){
        int swap;
        int j=i+1;
        if (2*j>=n){
            return;
        }else if(2*j+1>=n){
            if(data[2*i]<data[i]){
                swap=data[2*i];
                data[2*i]=data[i];
                data[i]=swap;
                return;
            }
        }else{
            if(data[2*i]<data[i]||data[2*i+1]<data[i]){
                if(data[2*i]>data[2*i+1]){
                    swap=data[i];
                    data[i]=data[2*i+1];
                    data[2*i+1]=swap;
                    minHeapify(data, 2*i+1, n);
                }else{
                    swap=data[2*i];
                    data[2*i]=data[i];
                    data[i]=swap;
                    minHeapify(data,2*i, n);
                }
            }
            return;
        }
    }
    public static void buildMinHeap(int [] data){
        for(int i=data.length/2; i>=0; i--){
            minHeapify(data, i, data.length);
        }
    }
    public static int extractMin(int [] data, int n){
        if(n==1){
            return data[0];
        }else{
            int swap;
            swap=data[0];
            data[0]=data[n-1];
            data[n-1]=swap;
            minHeapify(data, 0, n-1);
            return data[n-1];
        }
    }
    public static int heapSelect(int [] data, int k){
        buildMinHeap(data);
        for(int i=1; i<=k-1; i++){
            extractMin(data, data.length-i+1);
        }
        //return data[data.length-k];
        return extractMin(data, data.length-k);
    }

}
