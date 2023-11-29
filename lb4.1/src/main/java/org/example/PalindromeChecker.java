public class PalindromeChecker {


    public static boolean isPalindrome(String word) {

        String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();


        int left = 0;
        int right = cleanedWord.length() - 1;
        while (left < right) {
            if (cleanedWord.charAt(left) != cleanedWord.charAt(right)) {
                return false; //
            }
            left++;
            right--;
        }
        return true; //
    }

    public static void main(String[] args) {

        String[] testWords = {"radar", "level", "Java", "palindrome", "A man, a plan, a canal, Panama!"};

        for (String word : testWords) {
            if (isPalindrome(word)) {
                System.out.println(word + " - є паліндромом");
            } else {
                System.out.println(word + " - не є паліндромом");
            }
        }
    }
}
