import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

        int[] fruits={1,2,3,2,2};
        System.out.println(totalFruit(fruits));

        String str="aaabbccd";
        int odds=2;
        System.out.println(longSubstringWithDistinctChar(str,odds));

        String s="abcabc";
        System.out.println(numberOfSubstrings(s));
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

    //Fruit Into Baskets
    static int totalFruit(int[] fruits)
    {
        int l=0,r=0,maxlen=0;
        HashMap<Integer,Integer> map= new HashMap<>();
        while(r<fruits.length)
        {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
                while(map.size()>2)
                {
                    map.put(fruits[l], map.get(fruits[l]) - 1);
                    if(map.get(fruits[l])==0) {
                        map.remove(fruits[l]);

                    }
                        l++;

                }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }
        return maxlen;
    }

    //Longest Substring With At Most K Distinct Characters
    static int longSubstringWithDistinctChar(String str, int odds)
    {
        int l=0,r=0,maxlen=0;
        HashMap<Character,Integer> map= new HashMap<>();
        while(r<str.length())
        {
            map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);
            while(map.size()>odds)
            {
                map.put(str.charAt(l), map.get(str.charAt(l)) - 1);
                if(map.get(str.charAt(l))==0) {
                    map.remove(str.charAt(l));
                }
                l++;

            }
            maxlen = Math.max(maxlen, r - l + 1);
            r++;
        }
        return maxlen;
    }

    //Number of Substrings Containing All Three Characters
    static int numberOfSubstrings(String str)
    {
        int[] threes={-1,-1,-1};
        int count=0;
        for(int i=0;i<str.length();i++)
        {
            threes[str.charAt(i)-'a']=i;
            if(threes[0]!=-1 && threes[1]!=-1 && threes[2]!=-1)
            {
                count += 1 + Math.min(threes[0], Math.min(threes[1], threes[2]));
            }
        }
        return count;
    }
}

