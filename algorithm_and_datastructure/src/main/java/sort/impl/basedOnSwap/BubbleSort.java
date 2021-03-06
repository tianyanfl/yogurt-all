//package sort.impl.basedOnSwap;
//
//import utils.ArrayUtil;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class BubbleSort {
//
//	private static void checkSortResult(int[] arr){
//		for (int i = 0; i < arr.length - 1; i++){
//			if (arr[i] > arr[i + 1])
//				throw new RuntimeException("排序结果不正确");
//		}
//	}
//
//    //核心实现
//	//默认第一次排序完成后，有序元素的数量为1
//	//第二次排序完成后，有序元素的数量为2
//	//第三次排序完成后，有序元素的数量为3
//	//但是实际情况可能并不是这样，
//    private void bubbleSort(int[] arr){
//        //每次冒泡一个最大的上去
//        for (int i = arr.length - 1; i > 0; i--){
//            for (int j = 0; j < i; j++){
//                if (arr[j] > arr[j+1]){
//	                //swap(arr,j,j+1);
//	                int temp = arr[j];
//	                arr[j] = arr[j+1];
//	                arr[j+1] = temp;
//                }
//            }
//        }
//    }
//
//    //优化版bubbleSort
//	//在整个序列有序后，避免了最后几次无效的冒泡过程
//    private void enhancedBubbleSortV1(int[] arr){
//
//    	for (int i = arr.length - 1; i > 0; i--){
//    		boolean isSorted = true;
//    		for (int j = 0; j < i; j++){
//    			if (arr[j] > arr[j + 1]){
//    				//swap(arr,j,j+1);
//				    int temp = arr[j];
//				    arr[j] = arr[j+1];
//				    arr[j+1] = temp;
//    				isSorted = false;
//			    }
//		    }
//		    //本轮比较没有交换任何元素
//		    //则已有序，直接跳出
//		    if (isSorted)
//		    	break;
//	    }
//    }
//
//    //优化版bubbleSort  V2
//	//记录有序子序列的长度，进行下一次冒泡时，冒泡终点为有序子序列的长度，而不是根据排序躺数来决定
//	//原始的排序过程，第一趟冒泡，最右边1个元素是有序；第二趟冒泡，最后边2个元素是有序；第三趟冒泡，.....
//	//实际情况可能会优于此情况
//    private void enhancedBubbleSortV2(int[] arr){
//    	int sortedSize = 0;
//    	int lastSwapPos = 0;
//    	//注意退出条件
//	    //当执行完最后一趟冒泡后
//	    //lastSwapPos 应该为 1 ，即最后一趟冒泡，交换了下标 0 和 1的元素
//	    //lastSwapPos 此时为1，sortedSize 为 arr.length - 1
//	    //此时排序完成，应当退出
//
//	    //另外，也可能执行到中间某个地方时，整个序列已经有序
//	    //但是记录的lastSwapPos 不为1，而以后的循环，不会再满足 if 块的条件，导致lastSwapPos 无法更新，sortedSize无法更新
//	    //所以还需要加入isSorted 判断条件
//    	while (sortedSize < arr.length - 1){
//    		boolean isSorted = true;
//    		for (int j = 0; j < arr.length - 1 - sortedSize; j++){
//    			if (arr[j] > arr[j + 1]){
//    				//swap(arr,j, j + 1);
//				    int temp = arr[j];
//				    arr[j] = arr[j+1];
//				    arr[j+1] = temp;
//    				lastSwapPos = j + 1;
//    				isSorted = false;
//			    }
//		    }
//		    if (isSorted)
//		    	break;
//		    sortedSize = arr.length - lastSwapPos;
//	    }
//    }
//
//	public static void main(String[] args) {
//
//		List<Long> simpleTime = new ArrayList<>();
//		List<Long> enhancedV1Time = new ArrayList<>();
//		List<Long> enhancedV2Time = new ArrayList<>();
//		List<Long> cockTailTime = new ArrayList<>();
//
//		sort.core.BubbleSort bubbleSort = new sort.core.BubbleSort();
//
//		int size = 300;
//
//		for (int i = 0 ; i < size; i++){
//			int[] test1 = ArrayUtil.createRandomArray(-100,10000,1000);
//			int[] copyArr1 = Arrays.copyOf(test1,test1.length);
//			int[] copyArr3 = Arrays.copyOf(test1,test1.length);
//			int[] copyArr4 = Arrays.copyOf(test1,test1.length);
//
//			long s1 = System.nanoTime();
//			bubbleSort.sort(test1);
//			long s2 = System.nanoTime();
//			checkSortResult(test1);
//			simpleTime.add((s2 - s1) / 1000);
//
//			long s4 = System.nanoTime();
//			bubbleSort.sortV2(copyArr1);
//			long s5 = System.nanoTime();
//			checkSortResult(copyArr1);
//			enhancedV1Time.add((s5 - s4) / 1000);
//
//			long s7 = System.nanoTime();
//			bubbleSort.sortV3(copyArr3);
//			long s8 = System.nanoTime();
//			checkSortResult(copyArr3);
//			enhancedV2Time.add((s8 - s7) / 1000);
//
//			long s9 = System.nanoTime();
//			bubbleSort.cockTailSort(copyArr4);
//			long s10 = System.nanoTime();
//			checkSortResult(copyArr3);
//			cockTailTime.add((s10 - s9) / 1000);
//
//
//		}
//
//		long sumSimple = 0, sumEnhancedV1 = 0,sumEnhancedV2 = 0,sumCockTail = 0;
//		for (long i : simpleTime)
//			sumSimple += i;
//		for (long i : enhancedV1Time)
//			sumEnhancedV1 += i;
//		for (long i : enhancedV2Time)
//			sumEnhancedV2 += i;
//		for (long i : cockTailTime)
//			sumCockTail += i;
//
//		long aveSimple = sumSimple / size;
//		long aveEnhancedV1 = sumEnhancedV1 / size;
//		long aveEnhancedV2 = sumEnhancedV2 / size;
//		long aveCockTail = sumCockTail / size;
//		System.out.println("Simple BubbleSort : " + aveSimple + " ms\nEnhanced V1 : "
//				+ aveEnhancedV1 + " ms\nEnhanced V2 : " + aveEnhancedV2 + " ms\nCockTail Sort : " + aveCockTail + " ms");
//
//	}
//}
