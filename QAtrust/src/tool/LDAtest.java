package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Bean.QApair;

/**
 * 生成问题答案语料库
 * 
 * @author dhm
 *
 */
public class LDAtest {
	static String config_path="D:\\dataset\\OtherBenchMark\\StackOverflow\\";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String domain = "coffee";
		String wpath = config_path+"实验分析\\coffee\\coffeeQAText.txt";
		File Text = new File(wpath);
		if (Text.exists()) {
			Text.delete();
		}
		ArrayList<Integer> Qlist = GetQlist(domain);
		int a=0;
		for (int i = 0; i < Qlist.size(); i++) {
			String result=GetQAtext(domain, Qlist.get(i), Text);
			if(result.equals("false")){
				a++;
				
			}
		}
		System.out.println(a);
	}
	//按照问题序号获取相应的答案文本
	public static String GetQAtext(String domain, int QId, File f) {
		String rpath = config_path + domain + "\\Posts.xml";
		int count = 0;
		ArrayList<String> QAtext=new ArrayList<String>();
		int AnswerCount=0;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();		
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("PostTypeId"));
				int Id = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("Id"));
				String Body = ((Element) allChildren.get(i)).getAttributeValue("Body").replaceAll("\n", "");
				if (Id == QId && PostTypeId == 1) { // 问题
					AnswerCount = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("AnswerCount"));
					
					try{
						int AcceptedAnswerId = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("AcceptedAnswerId"));
						String Title = ((Element) allChildren.get(i)).getAttributeValue("Title").replaceAll("\n", "");
						Body = Body + Title;
//						FileUtils.write(f, Body + "\n", true);
//						System.out.println(QId+" "+AcceptedAnswerId);
						QAtext.add(Body);
					}catch(java.lang.NumberFormatException e){
//						System.out.println(QId+" null");
					}				
					
				} else if (PostTypeId == 2) { // 答案
					int ParentId = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("ParentId"));
					if (ParentId == QId) {
						count++;
//						FileUtils.write(f, Body + "\n", true);
//						System.out.println(Id);
						QAtext.add(Body);
					} else {
						continue;
					}

				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count==AnswerCount){
			return "true";
		}else{
			return "false";
		}
	}
	//获取某领域下返回答案数量大于1的问题ID序列列表
	public static ArrayList<Integer> GetQlist(String domain) {
		ArrayList<Integer> Qlist = new ArrayList<Integer>();
		String rpath = config_path + domain + "\\Posts.xml";
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("PostTypeId"));
				int Id = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("Id"));
				if (PostTypeId == 1) { // 问题
					int AnswerCount = Integer.parseInt(((Element) allChildren.get(i)).getAttributeValue("AnswerCount"));
					if (AnswerCount > 0) {
						Qlist.add(Id);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Qlist;
	}
}
