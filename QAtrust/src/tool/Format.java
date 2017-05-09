package tool;

import java.math.BigDecimal;

public class Format {
	 
    public double[][] MertrixFormat(double[][] Trust) {
    	Print pt=new Print();
    	int objectN=Trust.length;
		int sourceN=Trust [0].length;
		double[][] FormatTrust=new double[objectN][sourceN];
    	for(int i=0;i<objectN;i++){
			for(int j=0;j<sourceN;j++){
				BigDecimal bg = new BigDecimal(Trust[i][j]);  
		        double f1 = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		        FormatTrust[i][j]=f1;
			}
    	}
//    	pt.Trust(FormatTrust);
    	return FormatTrust;
    }
    public double[] arrayFormat(double[] Confidence) {
    	
    	return Confidence;
    }
}
