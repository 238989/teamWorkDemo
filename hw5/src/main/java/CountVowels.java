public class CountVowels {
    private static boolean isVowel(int c){
        return c=='a' ||c=='e'||c=='i'||c=='o'||c=='u';
    }

    public static int countVowel(String s){
        return (int) s.toLowerCase().chars().
                filter(CountVowels::isVowel).count();
    }

    public static int countConstants(String s){
        return s.length()-CountVowels.countVowel(s);
    }
}
