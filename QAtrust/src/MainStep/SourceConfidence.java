package MainStep;

import java.util.ArrayList;
import java.util.List;

public class SourceConfidence {

	List<ArrayList<int[]>> SourceObject=new ArrayList<ArrayList<int[]>>();	//每个数据源提供值的对象列表
	int[] volume=new int[5];

	public SourceConfidence(List<ArrayList<int[]>> sourceObject) {
		// TODO Auto-generated constructor stub
		SourceObject = sourceObject;
		for(int i=0;i<SourceObject.size();i++){
			volume[i]=SourceObject.get(i).size();
		}
	}

	public double[] SetConfidence(double[][] Trust){
		
		double[] Confidence=new double[5];
		int sourceN=Trust[0].length;
		for(int i=0;i<sourceN;i++){
			double b=0;
			int SofferOnum=SourceObject.get(i).size();
			int Object,CountObject;
			for(int j=0;j<SofferOnum;j++){
				Object=SourceObject.get(i).get(j)[0];
				CountObject=SourceObject.get(i).get(j)[1];
				b+=Trust[Object][CountObject];
			}
			Confidence[i]=b/volume[i];
		}	
		return Confidence;
	}
}
