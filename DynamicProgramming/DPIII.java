package DynamicProgramming;

import com.sun.media.sound.RIFFInvalidDataException;
import org.omg.CORBA.MARSHAL;

public class DPIII {
    public int longest(int[] array) {
        if(array.length == 0) return 0;
        int curr = 0;
        int globalMax = 0;
        for(int i =0; i< array.length; i ++){
            if(array[i] ==0) curr = 0;
            else curr ++;

            globalMax = Math.max(globalMax, curr);
        }
        return globalMax;
    }


    // find the largest cross which contains only 1s,
    // with the same arm lengths and the four arms
    // joining at the central point.
    public int largest(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        int result = mergeFour(
                LtoR(matrix,N,M),
                RtoL(matrix,N,M),
                UtoD(matrix,N,M),
                DtoU(matrix,N,M),
                N,M);

        return result;

    }
    private int[][] LtoR(int[][] matrix, int N, int M){
        int[][] DPtable = new int[N][M];
        for(int i = 0; i < N; i ++){
            for(int j =0; j < M; j ++){
                if(j == 0) DPtable[i][j] = matrix[i][j];
                else if(matrix[i][j] == 0) DPtable[i][j] = 0;
                else DPtable[i][j] = DPtable[i][j-1] + 1;
            }
        }
        return DPtable;
    }
    private int[][] RtoL(int[][] matrix, int N, int M){
        int[][] DPtable = new int[N][M];
        for(int i = 0; i < N; i ++){
            for(int j = M-1; j >=0 ; j --){
                if(j == M-1) DPtable[i][j] = matrix[i][j];
                else if(matrix[i][j] == 0) DPtable[i][j] =0;
                else DPtable[i][j] = DPtable[i][j+1] + 1;
            }
        }
        return DPtable;
    }
    private int[][] UtoD(int[][] matrix, int N, int M){
        int[][] DPtable = new int[N][M];
        for(int i = 0; i < N; i ++){
            for(int j =0; j < M; j ++){
                if(i == 0) DPtable[i][j] = matrix[i][j];
                else if(matrix[i][j] == 0) DPtable[i][j] =0;
                else DPtable[i][j] = DPtable[i-1][j] + 1;
            }
        }
        return DPtable;
    }
    private int[][] DtoU(int[][] matrix, int N, int M){
        int[][] DPtable = new int[N][M];
        for(int i = N-1; i >=0; i--){
            for(int j = 0; j < M ; j ++){
                if(i == N-1) DPtable[i][j] = matrix[i][j];
                else if(matrix[i][j] == 0) DPtable[i][j] =0;
                else DPtable[i][j] = DPtable[i+1][j] + 1;
            }
        }
        return DPtable;
    }
    private int mergeFour(int[][] LtoR,int[][] RtoL,int[][] UtoD,int[][] DtoU,
                              int N, int M){
        int globalMAX = 0;
        int[][] resultDP = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j =0; j < M; j++){
                resultDP[i][j] = minFour(LtoR[i][j], RtoL[i][j], UtoD[i][j],DtoU[i][j]);
                globalMAX = Math.max(globalMAX, resultDP[i][j]);
            }
        }
        return globalMAX;
    }
    private int minFour(int one, int two, int three, int four){
        int min = Math.min(one, two);
        min = Math.min(min, three);
        min = Math.min(min,four);
        return min;
    }

    // same question but with shorter code
    public int largest2(int[][] matrix){
        int N = matrix.length;
        if(N == 0) return N;
        int M = matrix[0].length;
        if(M == 0) return M;

        int[][] leftUp = leftUp(matrix, N,M);
        int[][] rightDown = rightDown(matrix, N,M);

        return merge(leftUp, rightDown, N, M);
    }

    private int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
        int result = 0;
        for(int i =0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                leftUp[i][j] = Math.min(leftUp[i][j],rightDown[i][j]);
                result = Math.max(result, leftUp[i][j]);
            }
        }
        return result;
    }

    private int[][] leftUp(int[][] matrix,int N,int M){
        int[][] left = new int[N][M];
        int[][] up = new int[N][M];
        for(int i = 0; i < M; i ++){
            for(int j = 0; j < N; j++ ){
                if(matrix[i][j] == 1){
                    if(i == 0 && j == 0){
                        left[i][j] = 1;
                        up[i][j] = 1;
                    }
                    else if(i == 0){
                        left[i][j] = left[i][j-1] + 1;
                        up[i][j] = 1;
                    }else if(j == 0){
                        left[i][j] = 1;
                        up[i][j] = up[i-1][j] + 1;
                    }else{
                        left[i][j] = left[i][j-1] + 1;
                        up[i][j] = up[i-1][j] + 1;
                    }
                }
            }
        }
        merge(left,up,N,M);
        return left;
    }

    private int[][] rightDown(int[][] matrix,int N,int M){
        int[][] right= new int[N][M];
        int[][] down = new int[N][M];
        for(int i = N-1; i >= 0 ; i--){
            for(int j = M-1; j >= 0; j--){
                if(matrix[i][j] == 1){
                    if (i==0&& j==0){
                        right[i][j] = 1;
                        down[i][j] = 1;
                    }else if(i == 0){
                        right[i][j] = right[i][j+1] + 1;
                        down[i][j] = 1;
                    }else if(j == 0){
                        right[i][j] = 1;
                        down[i][j] = down[i+1][j] + 1;
                    }else{
                        right[i][j] = right[i][j+1] + 1;
                        down[i][j] = down[i+1][j] + 1;
                    }
                }
            }
        }
        merge(right,down,N,M);
        return right;
    }

    //Determine the largest square surrounded by 1s in a binary matrix
    // (a binary matrix only contains 0 and 1),
    // return the length of the largest square
    public int largestSquareSurroundedByOne(int[][] matrix) {
        int N = matrix.length;
        if(N == 0) return N;
        int M = matrix[0].length;
        if(M == 0) return M;

        boolean allZero = true;

        int[][] dicrection1 = RtoL(matrix, N, M);
        int[][] dicrection2 = DtoU(matrix, N, M);

        for(int i = 0; i < N; i++){
            if(dicrection1[i][M-1] != 0){
                allZero = false;
            }
        }

        for(int i =0; i < M; i++){
            if(dicrection2[N-1][i] != 0){
                allZero = false;
            }
        }

        int gMax = allZero ? 0 : 1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(matrix[i][j] == 1){
                    int minDis = Math.min(dicrection1[i][j], dicrection2[i][j]);
                    for(int k = minDis-1; k > 0; k--){
                        if(dicrection1[i+k][j] > k && dicrection2[i][j+k] > k){
                            gMax = Math.max(gMax, k+1);
                        }
                    }
                }
            }
        }
        return gMax;
    }

    public int largestSquareOfMatches(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        int result = 0;
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] right = new int[N+1][M+1];
        int[][] down = new int[N+1][M+1];

        for(int i = N-1; i >=0 ; i--){
            for(int j = M-1; j >= 0; j--){
                if(hasRight(matrix[i][j])) right[i][j] = right[i][j+1]+1;
                if(hasDown(matrix[i][j])) down[i][j] = down[i+1][j] + 1;
                if(hasBoth(matrix[i][j])){
                    for (int maxLength = Math.min(right[i][j], down[i][j]); maxLength >= 1; maxLength--){
                        if(right[i+maxLength][j] >= maxLength && down[i][j+maxLength] >= maxLength){
                            result = Math.max(result, maxLength);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean hasRight(int value){
        return (value & 0b1) != 0;
    }
    private boolean hasDown(int value){
        return (value & 0b10) != 0;
    }
    private boolean hasBoth(int value){
        return value == 0b11;
    }

    public int largestSubMatrixSum(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        int result = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            int[] curr = new int[M];
            for(int j =i;j < N; j ++){
                addTwoRow(curr,matrix[j]);
                result = Math.max(result, max(curr));
            }
        }

        return result;
    }
    private void addTwoRow(int[] curr, int[] add){
        for(int i = 0; i < curr.length; i ++) curr[i] += add[i];
    }

    private int max(int[] curr){
        int result = curr[0];
        int temp = curr[0];
        for(int i = 1; i<curr.length; i ++){
            temp = Math.max(temp + curr[i],curr[i]);
            result = Math.max(result, temp);
        }
        return result;
    }
}
