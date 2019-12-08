package Programming_Team;

public class FallW5 {
    public int Delete_from_the_Lef(String s, String t){
        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();

        int i = s.length()-1;
        int j = t.length()-1;
        while(i >= 0 && j >= 0){
            if(s_array[i] == t_array[j]){
                i--;
                j--;
            }
            else {
                return i+j+2;
            }
        }
        return 0;
    }

}

