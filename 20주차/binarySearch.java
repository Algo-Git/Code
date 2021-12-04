import java.io.IOException;
import java.util.Arrays;

public class binarySearch {

    public static void main(String[] args) throws IOException {

        int[] arr = new int[] {10,9,1,2,3,4,5,6,7,8,15};

        // 1. 배열을 정렬한다.
        Arrays.sort(arr);

        // 2. 찾을 값을 설정한다.
        int searchNum = 7;
        int result = -1;

        //left rigth 크기를 설정한다.
        int left = 0;
        int right = arr.length-1;

        while (left <= right){
            int mid = (left + right)/2;
            if (arr[mid] > searchNum)
                right = mid-1;
            else if(arr[mid] < searchNum)
                left = mid + 1;
            else{
                result = mid;
                break;
            }
        }

        System.out.println(upperBound(arr, searchNum) + " " + lowerBound(arr,searchNum));

        System.out.println(String.format("찾고자하는 수는 %d 찾고자하는 위치는 %d 값은 %d",searchNum, result, arr[result]));

    }

    private static int lowerBound(int[] arr, int val){

        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            if (val <= arr[mid]){
                high = mid;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }

    private static int upperBound(int[] arr, int val){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high){
            mid = (low + high) / 2;

            if (val >= arr[mid]){
                low =  mid + 1;
            }else
                high = mid;
        }

        return low;
    }

    // input file is not

}
