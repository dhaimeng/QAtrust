package preprocess;

import java.io.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.jdom2.*;
import org.jdom2.input.*;


public class QAcount {
	public static void main(String arge[]) {
		String[] domain={"coffee","gaming","movie","music","sports"};
//		String[] domain={"coffee"};
		String wpath="D:\\dataset\\OtherBenchMark\\StackOverflow\\实验分析\\2.txt";
		File flongtail=new File(wpath);
		if(flongtail.exists()){
			flongtail.delete();
		}
		for(int i=0;i<domain.length;i++){
//			try {
//				FileUtils.writeStringToFile(flongtail, domain[i]+"\n",true);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			QACount(domain[i],flongtail);
		}
	}
	public static void QACount(String domain,File f){
		String rpath="D:\\dataset\\OtherBenchMark\\StackOverflow\\"+domain+"\\Posts.xml";
		
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			int QueCount=0;
			int AnsCount=0;
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("PostTypeId"));
				int Id=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("Id"));				
				if(PostTypeId==1){		//问题					
					int AnswerCount=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("AnswerCount"));
					if(AnswerCount>0){
						String QApair=Id+" "+AnswerCount;
						QueCount++;
						FileUtils.write(f, QApair+"\n",true);
					}
					
				}	
				else if(PostTypeId==2){		//答案
					AnsCount++;
//					int ParentId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("ParentId"));
//					System.out.println(AnsCount+"  "+Id+"  "+ParentId);
				}	
				else{
//					System.out.println(Id+" "+PostTypeId);
					continue;
				}
			}
			System.out.println(domain+" "+allChildren.size()+" "+QueCount+" "+AnsCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

