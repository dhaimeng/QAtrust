package MainStep;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tool.Format;
import tool.Print;

public class Iteration {
//	static double[] Confidence={0.4,0.2,0.4,0.7,0.1};
	static double[] Confidence={0.9,0.9,0.9,0.9,0.9};
	static double[][] Trust=new double[3][5];
	static List<ArrayList<int[]>> SourceObject=new ArrayList<ArrayList<int[]>>();	//每个数据源提供值的对象列表
//	double[] Confidence=new double[5];	//测试5个数据源对3个对象值
//	double[][] Trust={
//			{1,1,0,0,0},
//			{1,1,1,0,0},
//			{1,1,0,0,0},
//	};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> lines=Initial();
		
		double[][] PreTrust = new double[3][5];
		double[] PreConfidence=new double[5];
		Print pt=new Print();
		int count=0;
		do{
			ObjectTrust OT=new ObjectTrust(Confidence,Trust);
			SourceConfidence SC =new SourceConfidence(SourceObject);
			for(int i=0;i<Trust.length;i++){
				for(int j=0;j<Trust[0].length;j++){
					PreTrust[i][j]=Trust[i][j];
				}
			}
			for(int i=0;i<5;i++){
				PreConfidence[i]=Confidence[i];
			}
			Trust=OT.SetTrust(lines);
			Confidence=SC.SetConfidence(Trust);
			count++;
			System.out.println("迭代次数"+count);			
			pt.Trust(Trust);
			pt.Confidence(Confidence);
//		}while(!pt.EqualTrust(Trust,PreTrust));		
		}while(!pt.EqualConfidence(PreConfidence,Confidence));		
		
		
	}
	/*读入文件，置数据源提供的对象值位置参数*/
	public static List<String> Initial(){
		String dataPath="D:\\成果\\毕业设计\\实验\\TruthFinderdata2.txt";
		File f=new File(dataPath);
		List<String> lines=new ArrayList<String>();		
		try {
			lines = FileUtils.readLines(f);
			SourceObject=ObjectTrust.SetPara(lines,5);	//求volume，SourceObject)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}	
}
