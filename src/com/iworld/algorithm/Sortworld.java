package com.iworld.algorithm;

/**
 * 排序
 * 使用大小排序数组，
 * @Autor iworld
 * @Date 2020-05-09 14:32
 */
public class Sortworld {

    public static void main(String[] args) {
        Sortworld sortworld = new Sortworld();
        int[] result = sortworld.guestSortArray(new int[]{60, 35, 81, 98, 14, 47});
        String maxHexString = Integer.toBinaryString(Integer.MAX_VALUE);
        System.out.println("maxHexString==" + maxHexString + "==maxValue==" + Integer.MAX_VALUE);
        String minHexString = Integer.toBinaryString(Integer.MIN_VALUE);
        System.out.println("minHexString==" + minHexString + "==minValue==" + Integer.MIN_VALUE);
        String hexString = Integer.toBinaryString(-16);
        System.out.println("hexString==" + hexString + "==hexString==" + -16);

        System.out.println("maxfloat==" + Float.MAX_VALUE);
        System.out.println("minfloat==" + Float.MIN_VALUE);
    }

    /***
     * 使用一个数组记录当前位置的和其他位置对比结果，如果大于其他值则加一,反之在大的数字位置增加一。
     * 数组中每个数字间想回比较，数值大的在数值大的当前位置加一
     * @param array
     * @return
     */
    public int[] guestSortArray(int[] array){
        int[] counter = new int[array.length];
        for (int i = 0; i <= array.length - 2; i++){
            for(int j = i + 1; j <= array.length -1; j++){
                int ii = array[i];
                int jj = array[j];
                if(ii < jj){
                    counter[j] = counter[j] + 1;
                }else{
                    counter[i] = counter[i] + 1;
                }
            }
        }
        int[] result = new int[array.length];
        for(int i = 0; i <= array.length -1; i++){
            if(i == 0){
                System.out.print("array[");
            }
            System.out.print(array[i]);
            if(i == array.length - 1){
                System.out.print("]");
            }else{
                System.out.print(",");
            }
        }
        System.out.println();
        for(int i = 0; i <= counter.length -1; i++){
            if(i == 0){
                System.out.print("counter[");
            }
            System.out.print(counter[i]);
            if(i == counter.length - 1){
                System.out.print("]");
            }else{
                System.out.print(",");
            }
        }
        System.out.println();
        for(int i = 0; i <= array.length -1; i++){
            System.out.println("couter[" + i + "]====" + counter[i]);
            result[counter[i]] = array[i];
        }
        for(int i = 0; i <= array.length -1; i++){
            System.out.println("result[" + i + "]====" + result[i]);
        }
        return result;
    }
}
