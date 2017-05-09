package tool;

public class Print {
	public  void Trust(double[][] Trust) {
		int objectN=Trust.length;
		int sourceN=Trust [0].length;
		for (int i = 0; i < objectN; i++) {
			for (int j = 0; j < sourceN; j++) {
				System.out.print(Trust[i][j] + " ");
			}
			System.out.println();
		}
//		System.out.println();
	}

	public void Confidence(double[] Confidence) {
		int sourceN=Confidence.length;
		for (int i = 0; i < sourceN; i++) {
			System.out.print(Confidence[i] + " ");
		}
		System.out.println();
	}
	
	public boolean EqualTrust(double[][] Trust,double[][] Trustnow){
		int objectN=Trust.length;
		int sourceN=Trust [0].length;
		double a,b;
		for(int i=0;i<objectN;i++){
			for(int j=0;j<sourceN;j++){
				a=Trust[i][j];
				b=Trustnow[i][j];
				if(Math.abs(a-b)>0.01){
					return false;
				}
			}			
		}
		return true;
	}
	public boolean EqualConfidence(double[] Confidence,double[] Confidencenow){
		int sourceN=Confidence.length;
		double a,b;
		for(int i=0;i<sourceN;i++){
			
				a=Confidence[i];
				b=Confidencenow[i];
				if(Math.abs(a-b)>0.01){
					return false;
				}
				
		}
		return true;
	}
}
