package preprocess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Bean.QApair;

/**
 * 生成问题对应的答案序号列表。
 * 
 * @author dhm
 *
 */
public class QAGenerate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String domain="coffee";
		String wpath="D:\\dataset\\OtherBenchMark\\StackOverflow\\实验分析\\coffeeText.txt";
		GetQA(domain,1);
	}
	public static QApair GetQA(String domain,int QId){
		String rpath="D:\\dataset\\OtherBenchMark\\StackOverflow\\"+domain+"\\Posts.xml";		
		QApair qa=new QApair();
		int count=0;
		ArrayList<Integer> Aid=new ArrayList<Integer>();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("PostTypeId"));
				int Id=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("Id"));				
				if(Id==QId&&PostTypeId==1){		//问题													
					int AnswerCount=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("AnswerCount"));								
					qa.setQid(QId);
					qa.setAnsNum(AnswerCount);
				}else if(PostTypeId==2){		//答案	
					int ParentId=Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("ParentId"));				
					if(ParentId==QId){
						count++;	
						Aid.add(Id);					
					}
					
				}else{
					continue;
				}
			}
			qa.setAid(Aid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return qa;
//		System.out.println(qa.getAnsNum()+" "+count);
//		for(int i:qa.getAid()){
//			System.out.println(i);
//		}
//		if(qa.getAnsNum()==count){
//			System.out.println("ture");
//		}else{
//			System.out.println("false");
//		}
	}
	
}
