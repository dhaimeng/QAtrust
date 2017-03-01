package preprocess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class UserCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] domain = { "coffee" };
		String[] domain={"coffee","gaming","movie","music","sports"};
		String wpath="D:\\dataset\\OtherBenchMark\\StackOverflow\\实验分析\\4.txt";
		for (int i = 0; i < domain.length; i++) {
			String rpath = "D:\\dataset\\OtherBenchMark\\StackOverflow\\" + domain[i] + "\\Users.xml";
			String postpath = "D:\\dataset\\OtherBenchMark\\StackOverflow\\" + domain[i] + "\\Posts.xml";
			HashMap<Integer, Integer> UserhasAns=new HashMap<Integer, Integer> (); 
			UserhasAns=QACount(domain[i],rpath,UserhasAns);
			UserhasAns=hit(postpath,UserhasAns);
			File f=new File(wpath);
			Printhm(f,domain[i],UserhasAns);
		}
	}

	public static HashMap<Integer, Integer> QACount(String domain,String rpath,HashMap<Integer, Integer> UserhasAns) {
		
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			int QueCount = 0;
			int AnsCount = 0;
			for (int i = 0; i < allChildren.size(); i++) {
				int Id = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("Id"));
				UserhasAns.put(Id, 0);				
			}			
//			System.out.println(domain+" "+allChildren.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserhasAns;	
	}
	public static HashMap<Integer, Integer> hit(String rpath,HashMap<Integer, Integer> hm) {
        int count = 0;
        try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("PostTypeId"));				
				if(PostTypeId==2){		//答案
					if((((Element) allChildren.get(i)).getAttributeValue("OwnerUserId")!=null)){
						int OwnerUserId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("OwnerUserId"));		
						int now=hm.get(OwnerUserId);
						now++;
						hm.put(OwnerUserId, now);
					}
					
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}        
        return hm;
    }
	public static void Printhm(File f,String domain,HashMap<Integer, Integer> map){
		int sum=0;
		int userSum=0;
		Object[] key =  map.keySet().toArray();    
		Arrays.sort(key);  
		for(int i = 0; i<key.length; i++)
		{  
		    if(map.get(key[i])>1&&map.get(key[i])<50){
		    	String userAns=key[i]+" "+map.get(key[i]);  
		    	try {
					FileUtils.write(f, userAns+"\n",true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	sum+=map.get(key[i]);
		    	userSum++;
		    }
			
		}
//		System.out.println("共计: "+sum);
//		System.out.println(domain+"有效用户数： "+userSum);
//		try {
//			FileUtils.write(f, domain+"有效用户数： "+userSum+"\n",true);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
