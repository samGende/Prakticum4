import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void main(String[] args) {
        int [] test = new int[] {7, 6 ,3 ,4};
        int k = QuickSelectRandom(test, 3);
        System.out.println(k);
        System.out.println(Arrays.toString(test));

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
}
