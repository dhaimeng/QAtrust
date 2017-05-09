package relation;
/**
 * 
 */
import java.util.HashMap;

import org.jdom2.Element;

import tool.Hashtool;

public class TFsimi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text="<p>I just got a pound of microroasted, local coffee and am curious what the optimal way to store it is (what temperature, humidity, etc)</p>";
		System.out.println(text);
		TFstatistic(text);
		
	}
	public static HashMap<String,Integer> TFstatistic(String text){
		HashMap<String,Integer> tfnum=new HashMap<String,Integer>();
		String[] words=text.split(" ");
		for(String s:words){
			if(tfnum.containsKey(s)){
				int value=tfnum.get(s)+1;
				tfnum.put(s, value);
			}else{
				tfnum.put(s, 1);
			}
		}
		
		Hashtool.sortbyValue(tfnum);
		return tfnum;
	}


}
