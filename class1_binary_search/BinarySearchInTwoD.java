/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class1_binary_search;

/**
 *
 * @author elevenli
 */
public class BinarySearchInTwoD {
    
    // method 1
    public int[] BinarySearchIn2D(int[][] matrix, int target){
        // 初始化返回数据
        int[] targetPosition = new int[]{-1,-1};
        if(matrix == null || matrix.length == 0){
            return targetPosition;
        }
        // 行数由matrix第一个[]有多少元素决定
        int row = matrix.length;
        // 列数由每行有多少个元素来决定
        int colum = matrix[0].length;
        // 将i j 设置成最前元素和最后元素，来将整个2d array看成1d array
        int i = 0 ;
        int j = colum*row-1;
        while(i<=j){
            // 首先获得mid 的数字地址
            int mid = i+(j-i)/2;
            // 再获得 mid 的物理地址
            // 位置%有多少元素一行可以获得在一行中哪个位置
            int mid_colum = mid % colum; 
            // 位置/有多少元素一行可以获得在哪行
            int mid_row = mid / colum;
            if(matrix[mid_row][mid_colum] == target){
                targetPosition[0] = mid_row;
                targetPosition[1] = mid_colum;
                return targetPosition;
            }
            if(matrix[mid_row][mid_colum] < target) i = mid + 1;
            else j = mid - 1;
        }
        return targetPosition;
    }


    // method 2
    public int[] search(int[][] matrix, int target) {
        int[] result = new int[]{-1,-1};
        if(matrix.length == 0 || matrix[0].length == 0) return result;

        int row = findRow(matrix, 0, matrix.length-1, target);
        // corner case
        if(row == -1) return result;

        int col = findCol(matrix[row], 0, matrix[row].length-1,target);
        // corner case
        if(col == -1) return result;

        result[0] = row;
        result[1] = col;
        return result;
    }

    private int findRow(int[][] matrix, int up, int down, int target){
        while(up <= down){
            int mid = up + (down - up) / 2;
            if(matrix[mid][0] > target){
                down = mid -1;
            }
            else{
                up = mid + 1;
            }
        }
        return down;
    }

    private int findCol(int[] array, int left, int right, int target){
        while(left <= right){
            int mid = left + (right-left) / 2;
            if(array[mid] == target) return mid;
            else if(array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }


    
    // method2
    public int[] BinarySearchIn2D2(int[][] matrix, int target){
        int[] targetPosition = new int[2];
        targetPosition[0] = -1;
        targetPosition[1] = -1;
        if(matrix == null || matrix.length == 0) 
            return targetPosition;
        
        int last_row = matrix.length -1;
        int first_row= 0;
        int last_col = matrix[0].length -1;
        int first_col= 0;
        int row_position = -1;
        int col_position = -1;
        // 找到target 在哪一行
        while(first_row <= last_row){
            int mid = (last_row+first_row)/2;
            if(matrix[mid][first_col] < target 
                    && matrix[mid][last_col] > target){
                row_position = mid;
                break;
            }
            else if(matrix[mid][last_col] < target) 
                first_row = mid + 1;
            else last_row = mid -1;
        }
        // 找到在这一行里面的哪一列
        while(first_col <= last_col && row_position != -1){
            int midColum = (first_col+last_col)/2;
            if(matrix[row_position][midColum] == target){
                col_position = midColum;
                targetPosition[0] = row_position;
                targetPosition[1] = col_position;
                return targetPosition;}
            else if(matrix[row_position][midColum] < target)
                first_col = midColum +1;
            else last_col = midColum -1;}
        return targetPosition;
    }  
}
