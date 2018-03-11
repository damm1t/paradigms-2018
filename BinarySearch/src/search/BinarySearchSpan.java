package search;

import java.util.Arrays;

public class BinarySearchSpan {

    // pre: i < j => a[i] >= a[j] ∧ a[-1] > x > a[a.length]
    // post: ℝ = i ∧ (a[i - 1] > x >= a[i] && searchType || a[i - 1] >= x > a[i] && !searchType) ∧ ∀ i: a[i] = a'[i]
    public static int BinarySearch(int x, int[] a, boolean searchType) {
        int l = -1, r = a.length - 1;
        // inv searchType: a[-1] >= a[l] > x >= a[r] > a[a.length]  ∧  l < r < a.length
        // inv !searchType: a[-1] >= a[l] >= x > a[r] > a[a.length]  ∧  l < r < a.length
        while (l + 1 < r) {
            int m = l + ((r - l) >>> 1);
            // inv ∧ l < l + 1 <= r - 1 < r
            // => l < m < r
            if (a[m] > x && searchType || a[m] >= x && !searchType) {
                // inv ∧ l < m < r ∧ ( a[m] > x && searchType || a[m] >= x && !searchType )
                l = m;
                // searchType: a[-1] >= a[m] > x >= a[r] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
                // !searchType: a[-1] >= a[m] >= x > a[r] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
            } else {
                // inv ∧ l < m < r ∧ ( a[m] <= x && searchType || a[m] < x && !searchType )
                r = m;
                // searchType: a[-1] >= a[l] > x >= a[m] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
                // !searchType: a[-1] >= a[l] >= x > a[m] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]

            }
            // inv ∧ l < m < r ∧ ∀ i: a[i] = a'[i]
            // => r' - m < r' - l' ∧ m - l' < r' - l'
            // => r - l < r' - l'
        }
        // post: inv  ∧  l + 1 == r
        // searchType:
        // => a[l] > x >= a[r]  ∧  l + 1 == r
        // => a[r-1] > x >= a[r]
        // => ℝ = r

        // !searchType:
        // => a[l] >= x > a[r]  ∧  l + 1 == r
        // => a[r-1] >= x > a[r]
        // => ℝ = r
        return r;
    }

    // pre: i < j => a[i] >= a[j] ∧ a[-1] > x > a[a.length]
    // post: ℝ = i  ∧ ( a[i - 1] > x >= a[i] && searchType || a[i - 1] >= x > a[i] && !searchType) ∧ ∀ i: a[i] = a'[i]
    public static int recursiveBinarySearch(int x, int a[], boolean searchType) {
        return recursiveBinarySearch(x, a, -1, a.length - 1, searchType);
    }

    // pre: i < j => a[i] >= a[j] ∧ ( searchType: a[-1] >= a[l] > x >= a[r] > a[a.length] ||
    // !searchType: a[-1] >= a[l] >= x > a[r] > a[a.length] ) ∧  l < r < a.length
    // post: (searchType: a[ℝ - 1] > x >= a[ℝ] !searchType: a[ℝ - 1] >= x > a[ℝ]) ∧ ∀ i: a[i] = a'[i]
    private static int recursiveBinarySearch(int x, int a[], int l, int r, boolean searchType) {
        if (l + 1 >= r) {
            //  l + 1 == r
            // searchType:
            // => a[l] > x >= a[r]  ∧  l + 1 == r
            // => a[r-1] > x >= a[r]
            // => ℝ = r

            // !searchType:
            // => a[l] >= x > a[r]  ∧  l + 1 == r
            // => a[r-1] >= x > a[r]
            // => ℝ = r
            return r;
        }
        int m = l + ((r - l) >>> 1);
        // l < l + 1 <= r - 1 < r
        // => l < m < r
        if (a[m] > x && searchType || a[m] >= x && !searchType) {
            // l < m < r ∧ ( a[m] > x && searchType || a[m] >= x && !searchType )
            // searchType: a[-1] >= a[m] > x >= a[r] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
            // !searchType: a[-1] >= a[m] >= x > a[r] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
            return recursiveBinarySearch(x, a, m, r, searchType);
            // l < m < r ∧ ∀ i: a[i] = a'[i]
        } else {
            // l < m < r ∧ ( a[m] <= x && searchType || a[m] < x && !searchType )
            // searchType: a[-1] >= a[l] > x >= a[m] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
            // !searchType: a[-1] >= a[l] >= x > a[m] > a[a.length] ∧ l < m < r < a.length ∧ ∀ i: a[i] = a'[i]
            return recursiveBinarySearch(x, a, l, m, searchType);
            /// l < m < r ∧ ∀ i: a[i] = a'[i]
        }
    }

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Usage: Incorrect input");
            return;
        }
        int[] a = Arrays.stream(args).mapToInt(arg -> Integer.parseInt(arg)).toArray();
        int x = a[0];
        System.arraycopy(a, 1, a, 0, a.length - 1);
        int l = BinarySearch(x, a, true);
        int r = BinarySearch(x, a, false);
        if(a[l] != x){
            System.out.println(String.valueOf(l) + " 0");
            return;
        }
        System.out.println(String.valueOf(l) + ' ' + String.valueOf(r - l));
    }
}
