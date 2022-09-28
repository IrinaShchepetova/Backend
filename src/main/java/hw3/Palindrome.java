package hw3;

public class Palindrome {
    public static void main(String[] args) {
        isPalindrome("мадам");
    }

    public static boolean isPalindrome(String s) {
        if (s==null || s==""){
            throw new IllegalArgumentException("String could not be empty");
       }
        int n = s.length();
        for (int i = 0; i < (n/2); ++i) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
