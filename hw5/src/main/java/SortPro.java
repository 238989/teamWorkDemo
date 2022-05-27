public class SortPro {
    public static int[] selectionSort(int[] arr) {
        if (arr.length == 0)
            return arr;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[20];
        System.out.println("随机生成的数组如下");
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 100);
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n排序后如下：");
        int minIndex;
        for (int i = 0; i < 20; i++) {
            minIndex = i;
            for (int j = i + 1; j < 20; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        //arr = selectionSort(arr);
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
