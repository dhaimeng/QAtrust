package test;
//测试Yahoo Answer发现确实如小样本是没有用户信息的。
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class OpenBigDom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String rpath = "D:\\dataset\\Yahoo\\Webscope_L6-2\\FullOct2007.xml.part2";
		String line = null;
		int n = 50;//从第三行开始读取
	    BufferedReader br = new BufferedReader(new FileReader(rpath));
	    while (n -- > 1 ) {
	    	System.out.println(br.readLine());
	    }
	    br.close();
	    
	}
	
}
