package class_10_Bit_Operation;

public class BitOperation {
    public boolean isPowerOfTwo(int number) {
        return number > 0 && countOnes(number) == 1;
    }

    private int countOnes(int number){
        int count = 0;
        while (number > 0){
            count += (number & 1);
            number >>= 1;
        }
        return count;
    }

    public boolean isPowerOfTwo2(int number){
        return (number > 0 && (number & (number-1)) == 0);
    }

    public int differentBit(int a, int b){
        int different = a ^ b;
        int count = 0;
        // here different could be a negative number
        // if both number msb is 0 or 1.
        // then cannot set different as bigger than 0
        while (different != 0){
            count += (different & 1);
            // logic shift so than left all get 0
            different >>>= 1;
        }
        return count;
    }

    public boolean allUnique(String word) {
        boolean[] occurredChar = new boolean[26];
        for (int i =0; i < word.length(); i ++){
            int charPosition = word.charAt(i) - 'a';
            if (occurredChar[charPosition]){
                return false;
            }
            occurredChar[charPosition] = true;
        }
        return true;
    }

    public boolean allUnique2(String word){
        int occurredChar = 0;
        for(int i = 0; i < word.length(); i++){
            int charPosition = word.charAt(i) - 'a';
            if(((occurredChar >> charPosition) & 1 ) == 1) {
                return false;
            }
            occurredChar |= (1<< charPosition);
        }
        return true;
    }

    public boolean allUniqueASCII(String word){
        int[] occurredChar = new int[8];
        for(int i = 0; i < word.length(); i++){
            int charPosition = word.charAt(i);
            int row = charPosition / 32;
            int col = charPosition % 32;
            if(((occurredChar[row] >> col) & 1) == 1){
                return false;
            }
            occurredChar[row] |= (1 << col);
        }
        return true;
    }

    public long reverse(long n){
        for(int offset = 0; offset < 16; ++offset){
            long right = (n >> offset) & 1;
            long left = (n >> (31 - offset)) & 1;
            if(right != left) {
                n ^= (1 << offset) | (1 << (31 - offset)) ;
            }
        }
        return n;
    }
    public String hex(int number) {
        if(number == 0) return "0x0";
        char[] base = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        while (number !=0){
            int curr = number % 16;
            sb.append(base[curr]);
            number = number / 16;
        }
        sb.append("x0");
        sb.reverse();
        return sb.toString();
    }

    public String hex2(int number){
        char[] base = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder("0x");
        boolean isLeading = true;
        for(int offset = 28; offset >= 0;  offset -= 4){
            char curr = base[(number >> offset) & 0xF];
            if(curr != '0' || !isLeading){
                sb.append(curr);
                isLeading = false;
            }
        }
        return sb.toString();
    }
}
