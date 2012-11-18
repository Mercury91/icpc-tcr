import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class LongestSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		while(sc.hasNext()){
			String S1,S2;
			S1 = sc.next();
			S2 = sc.next();
			for(String lcs: longestCommonSubstring(S1, S2)){
				System.out.println(lcs);			
				}
			System.out.println();
		}
	}
	
	private static List<String> longestCommonSubstring(String S1, String S2)
	{
		List<String> ret = new ArrayList<String>();
		List<Integer> idx  =new ArrayList<Integer>();
	    int Start = 0;
	    int Max = 0;
	    for (int i = 0; i < S1.length(); i++)
	    {
	        for (int j = 0; j < S2.length(); j++)
	        {
	            int x = 0;
	            while (S1.charAt(i + x) == S2.charAt(j + x))
	            {
	                x++;
	                if (((i + x) >= S1.length()) || ((j + x) >= S2.length())) break;
	            }
	            if (x > Max)
	            {
	                Max = x;
	            	Start = i;
	            	idx.clear();
	            	idx.add(Start);
	            } else if(x==Max){
	            	Start = i;
	            	idx.add(Start);
	            }
	         }
	    }
	    HashSet<String> set = new HashSet<String>(idx.size(),1f);
	    for(Integer start : idx){
	    	String substr = S1.substring(start,start+Max);
	    	if(!set.contains(substr)){
	    		ret.add(substr);
	    		set.add(substr);
	    	}
	    }
	    Collections.sort(ret);
	    //return S1.substring(Start, (Start + Max));
	    return ret;
	}

}
