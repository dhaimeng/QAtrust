package MainStep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ObjectTrust {
	double[] Confidence;
	double[][] Trust;

	public ObjectTrust(double[] confidence, double[][] trust) {
		// TODO Auto-generated constructor stub
		this.Confidence=confidence;
		this.Trust=trust;
	}
	public double[][] SetTrust(List<String> lines){ 	//valueN=lines.size();		
		int Object;		
		String Value;		
		String[] SourceListStr;
		int Source;
		String[] content=new String[3];
		HashSet<Integer> ObjectList=new HashSet<Integer> ();
		int CountObject=0;
		for(int i=0;i<lines.size();i++){
			content=lines.get(i).split(" ",3);
			Object=Integer.parseInt(content[0]);	
			if(!ObjectList.contains(Object)){
				ObjectList.add(Object);
				CountObject=0;
			}else{
				CountObject++;
			}
			Value=content[1];			
			SourceListStr=content[2].split(" ");			
			double a=1;			//记录不正确的概率
			for(int j=0;j<SourceListStr.length;j++){
				Source=Integer.parseInt(SourceListStr[j]);
				a*=(1-Confidence[Source]);	
			}
			Trust[Object][CountObject]=1-a;
		}
		return Trust;
	}
	public static List<ArrayList<int[]>> SetPara(List<String> lines,int sourceN){ 	//valueN=lines.size();	
		List<ArrayList<int[]>> SourceObject=new ArrayList<ArrayList<int[]>>();	//每个数据源提供值的对象列表			
		int Object;		
		String Value;		
		String[] SourceListStr;
		int Source;
		String[] content=new String[3];
		HashSet<Integer> ObjectList=new HashSet<Integer> ();
		int CountObject=0;
		for(int i=0;i<sourceN;i++){
			ArrayList<int[]> a=new ArrayList<int[]>();
			SourceObject.add(a);
		}
		for(int i=0;i<lines.size();i++){
			content=lines.get(i).split(" ",3);
			Object=Integer.parseInt(content[0]);	
			if(!ObjectList.contains(Object)){
				ObjectList.add(Object);
				CountObject=0;
			}else{
				CountObject++;
			}
			Value=content[1];			
			SourceListStr=content[2].split(" ");			
			for(int j=0;j<SourceListStr.length;j++){
				Source=Integer.parseInt(SourceListStr[j]);
				SourceObject.get(Source).add(new int[]{Object,CountObject});				
			}
	
		}
		return SourceObject;
//		for(int i=0;i<sourceN;i++){
//			ArrayList<int[]> a=SourceObject.get(i);
//			System.out.print(i+" "+a.size()+" ");
//			for(int j=0;j<a.size();j++){
//				int[] b=a.get(j);
//				for(int k=0;k<b.length;k++){
//					System.out.print("["+b[k]+"]");
//				}
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
	}
	
}
