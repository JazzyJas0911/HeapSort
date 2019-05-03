/*
 * Create three functions named build_MaxHeap(a), max_heapify(a,i), heap_sort(a).
 * 1. Request the user to enter a positive integer, and call it n.
 * 2. Generate n random integers between -10000 to 10000 and save them in array a.
 * 3. Call heap_sort(a) function to sort the array. In order to sort the array using heapsort, you need to follow the below steps:
 	* 3.1 Build a max-heap (call the function build_heap(a)).
 	* 3.2 Remove the root (remove the first element in a):
 * 4. Determine the average-running time of heap_sort function for n = 10000, and 100 repetitions.
 * 5. Compare your answer with the average-running time of quicksort and selection sort.
 */

import java.util.Random;
import java.util.Scanner;

public class main{

	public static void main(String[] args){
		
		Scanner keyboard = new Scanner(System.in);
		Random randomGenerator = new Random();
		
		int repetitions = 100;
		
		System.out.println("Enter a positive integer");
		int n = keyboard.nextInt();
		int a[] = new int[n];
		int temp[] = new int[n];
		for(int i = 0; i < n; i++){
			int num = -10000 + randomGenerator.nextInt(20000);
			a[i] = num;
		}
		
		System.out.println("\nOriginal Array:");
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + ", ");
		}
		
		long heapSortTotalTime = 0;
		for(int i = 0; i < repetitions; i++){
			temp = a;
			long heapSortStartTime = System.nanoTime();
			heap_sort(temp);
			long heapSortEndTime = System.nanoTime();
			heapSortTotalTime += (heapSortEndTime - heapSortStartTime);
		}
		long heapSortAverageTime = heapSortTotalTime / repetitions;
		
		System.out.println("\n\nSorted Array:");
		for(int i = 0; i < temp.length; i++){
			System.out.print(temp[i] + ", ");
		}
		
		System.out.println("\n\nAverage Running Time of Heap Sort Function: " + heapSortAverageTime + " ns");
	}
	
	public static void swap(int a[], int index1, int index2){
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
	
	// O(nlogn)
	public static void heap_sort(int a[]){
		build_MaxHeap(a);
		for(int i = a.length - 1; i > 0; i--){
			swap(a, 0, i);
			max_heapify(a, 0, i);
			
		}
	}
	
	// O(n)
	public static void build_MaxHeap(int a[]){
		for(int i = (a.length - 2) / 2; i >= 0; i--){
			max_heapify(a, i, a.length - 1);
		}
	}
	
	//O(h)
	public static void max_heapify(int a[], int parent, int length){
		int largest = parent;
		int lChild = 2 * parent + 1;
		int rChild = 2 * parent + 2;
		if(lChild < length && a[parent] < a[lChild])
			largest = lChild;
		if(rChild < length && a[largest] < a[rChild])
			largest = rChild;
		if(largest != parent){
			swap(a, parent, largest);
			max_heapify(a, largest, length);
		}
	}
}
