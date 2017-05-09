package tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public class Hashtool {
	public static void sortbyValue(Map<String,Integer> map){
		ArrayList<Map.Entry<String,Integer>> ID=new ArrayList<Map.Entry<String, Integer>>(map.entrySet());		
		Collections.sort(ID, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		    	if ((o2.getValue() - o1.getValue())>0)  
		            return 1;  
		          else if((o2.getValue() - o1.getValue())==0)  
		            return 0;  
		          else   
		            return -1;  
		    }
		});
		for (Iterator iter = ID.iterator(); iter.hasNext();) 
		{
		   Map.Entry entry = (Map.Entry)iter.next();
		   String  key = (String)entry.getKey();
		   System.out.println(key.toString()+" "+map.get(key));  
		}
		String a=ID.get(0).toString();
		System.out.println(a);
	}
}
