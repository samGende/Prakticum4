import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
class MinHeap {
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
            System.out.println("Das " +k+"-kleinste Element ist: "+heapSelect(arr, k));
        }
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

