public class RabinKarp {
    final static int PRIME = 101;

    public static void main(String[] args) {
        String text = "Augusta Byron was the only legitimate child of poet Lord Byron and his wife Lady Byron. All of Byron's other children were born out of wedlock to other women. " +
                "Byron separated from his wife a month after Ada was born and left England forever four months later. " +
                "He commemorated the parting in a poem that begins, \"Is thy face like thy mother's my fair child! ADA! sole daughter of my house and heart?." +
                "He died of disease in the Greek War of Independence when Ada was eight years old. " +
                "Her mother remained bitter and promoted Ada's interest in mathematics and logic in an effort to prevent her from developing her father's perceived insanity. " +
                "Despite this, Ada remained interested in Byron, naming her two sons Byron and Gordon. Upon her eventual death, she was buried next to him at her request." +
                " Although often ill in her childhood, Ada pursued her studies assiduously. She married William King in 1835. King was made Earl of Lovelace in 1838, Ada thereby becoming Countess of Lovelace.";
        String pattern = "Ada";

        rabinKarp(text, pattern);
    }

    private static void rabinKarp(String text, String pattern) {
        int n = pattern.length();
        int m = text.length();
        int hash = hashF(pattern, n);

        String slide = "";

        for (int i = 0; i < n && i < m; i++) {
            slide += text.charAt(i);
        }

        int hashSlide = hashF(slide, slide.length());

        if (hashSlide == hash) {
            scrupulousComparator(text, pattern, n, 0);
        }

        int i = n;

        while (i < m) {
            hashSlide = hashRoll(text.charAt(i - n), text.charAt(i), hashSlide, n - 1);
            if (hash == hashSlide) {
                scrupulousComparator(text, pattern, n, i - n + 1);
            }
            i++;
        }
    }

    static void scrupulousComparator(String text, String pat, int n, int i) {
        int k = 0;
        while (k < n) if (pat.charAt(k) != text.charAt(i + k++)) break;
        if (k == n) System.out.println("Pattern found at " + i);
    }

    private static int hashRoll(char old, char nova, int hash, int n) {
        return (int) (((hash - (old)) / PRIME) + ((nova) * Math.pow(PRIME, n)));
    }

    private static int hashF(String pat, int n) {
        int res = pat.charAt(0);
        for (int i = 1; i < n; i++) {
            res += ((pat.charAt(i)) * Math.pow(PRIME, i));
        }
        return res;
    }
}
