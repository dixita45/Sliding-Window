import java.util.HashSet;
public class Main {
    public static void main(String[] args) {
        int[] cards ={6,2,3,4,7,2,1,7,1};
        int k=4;
        System.out.println(maxPointsFromCards(cards,k));

        String st="pwwkew";
        System.out.println(lengthOfLongestSubstring(st));

        int[] nums ={0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k0=3;
        System.out.println(longestOnes(nums,k0));
    }

    //Max Points From Cards
    static int maxPointsFromCards(int[] cards, int k)
    {
        int n = cards.length;
        int totalSum = 0;

        for (int num : cards) totalSum += num;
        if (k == n) return totalSum;
        int windowSize = n - k;
        int currWindowSum = 0;

        for (int i = 0; i < windowSize; i++) {
            currWindowSum += cards[i];
        }
        int minSubarraySum = currWindowSum;
        for (int i = windowSize; i < n; i++) {
            currWindowSum += cards[i] - cards[i - windowSize];
            minSubarraySum = Math.min(minSubarraySum, currWindowSum);
        }

        return totalSum - minSubarraySum;
    }

    //Length Of Longest Substring
    static int lengthOfLongestSubstring(String s)
    {
        int left=0,right=0,max=0;
        HashSet<Character> set =new HashSet<>();
        while(right < s.length())
        {
            if(!set.contains(s.charAt(right)))
            {
                set.add(s.charAt(right));
                right++;
                max=Math.max(set.size(),max);
            }
            else{
                set.remove(s.charAt(left));
                left++;
            }
        }
       return max;
    }

    //Max Consecutive Ones III
    static int longestOnes(int[] nums, int k0)
    {
        int left=0,right=0,maxlen=0,zeros=0,len=0;
        while(right<nums.length)
        {
            if(nums[right]==0)
            {
                zeros++;
            }
            while(zeros>k0)
            {
                if(nums[left]==0)
                {
                    zeros--;

                }
                    left++;
            }
            if(zeros<=k0)
            {
                len=right-left+1;
                maxlen=Math.max(len,maxlen);
                right++;
            }

        }
        return maxlen;
    }
}
