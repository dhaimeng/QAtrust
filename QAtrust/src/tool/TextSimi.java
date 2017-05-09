package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import Jama.Matrix;

/**
 * 相似矩阵生成，第一列为与问题的相似度，后面为与答案
 * @author dhm
 *
 */
public class TextSimi {
	static String config_path="D:\\dataset\\OtherBenchMark\\StackOverflow\\";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String domain = "coffee";
		ArrayList<Integer> Qlist = LDAtest.GetQlist(domain);		
		for (int i = 0; i < Qlist.size(); i++) {

//			culSimilarMetrix();
			
		}
	}
	public static Matrix culSimilarMetrix(int n){
		Matrix A = new Matrix(n, n);
		Vector v=new Vector();
//		FileUtils.readLines(f);
		return A;
	}

}
