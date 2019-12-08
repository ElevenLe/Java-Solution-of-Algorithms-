/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class2_sort;

/**
 *
 * @author elevenli
 */
public class Recursion {

    public int fibonacci(int K) {
        // corner case 
        if (K < 0) return 0;
        // base case
        if (K == 0) return 0;
        else if (K == 1) return 1;
        // recursion part
        return fibonacci(K - 1) + fibonacci(K - 2);
    }
    
    public int power(int a, int b){
        // base case
        if(b == 0) return 1;
        
        return a * power(a,b-1);
    }
    
    public int power2(int a, int b){
        // base case
        if(b == 0) return 1;
        else if(b ==1) return a;
        
        int mid = b/2;
        return power2(a,mid) * power2(a,b-mid);
    }
    
    public int power3(int a, int b){
        // base case
        if(b == 0) return 1;
        int halfResult = power3(a,b/2);
        
        return b%2==1 ? halfResult * halfResult * 1 
                        :halfResult * halfResult;    
    }

}
