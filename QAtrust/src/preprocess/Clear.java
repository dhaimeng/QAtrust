package preprocess;

/**
 * 去html标签，去停用词和标点
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Clear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text="<p>Let's start off with (what the function of the tamp) is.</p><p><img src=\"http://i.stack.imgur.com/xt7gz.jpg\" alt=\"enter image description here\"></p><h2>Function</h2><p>The tamp is cylindrical in shape and is ideally just snug enough to fit into the portafilter smoothly. What the tamp does, is prime the coffee bed to be met with water. What we know about: the water is that in general, in commercial machines, water is applied with 9 bars of pressure. Physics tells us that pressured water flow will take the path of least resistance. By this logic, the tamp functions by making the coffee bed smooth, even, and robust enough to be hit with 9 bars of pressure and extract evenly. </p><h2>Barista Health</h2><p><em>Note: In no way am I a qualified physician. This is not to be considered medical advice.</em></p><p>If the surface one tamps on is on the proper level, the barista should be able to tamp with their elbo at a 90 degree(ish) angle, and their wrists in a semi-straight path to prevent damage. The barista can then use their body weight, and not muscle, to perform the tamp. By leaning into the tamp with the correct path, one can achieve about 30lbs of pressure. </p><h2>Experiments with varying pressure</h2><p>Personally I have experimented with varying pressured tamps and have found that applying a greater pressure than 30 lbs produced little effect, at most, a longer extraction. There are other variables that should be adjusted instead, such as the grind size, to achieve such an effect.</p>";
		
		text=RemoveHTML(text);
		text=RemoveStopwords(text);
		System.out.print(text);
	}
	public static String Clear(String text) {
		// TODO Auto-generated method stub
		String t=RemoveHTML(text);
		t=RemoveStopwords(t);
		return t;
	}
	public static String RemoveHTML(String htmlStr){
//		String htmlStr="<p>Let's start off with what the function of the tamp is.</p><p><img src=\"http://i.stack.imgur.com/xt7gz.jpg\" alt=\"enter image description here\"></p><h2>Function</h2><p>The tamp is cylindrical in shape and is ideally just snug enough to fit into the portafilter smoothly. What the tamp does, is prime the coffee bed to be met with water. What we know about the water is that in general, in commercial machines, water is applied with 9 bars of pressure. Physics tells us that pressured water flow will take the path of least resistance. By this logic, the tamp functions by making the coffee bed smooth, even, and robust enough to be hit with 9 bars of pressure and extract evenly. </p><h2>Barista Health</h2><p><em>Note: In no way am I a qualified physician. This is not to be considered medical advice.</em></p><p>If the surface one tamps on is on the proper level, the barista should be able to tamp with their elbo at a 90 degree(ish) angle, and their wrists in a semi-straight path to prevent damage. The barista can then use their body weight, and not muscle, to perform the tamp. By leaning into the tamp with the correct path, one can achieve about 30lbs of pressure. </p><h2>Experiments with varying pressure</h2><p>Personally I have experimented with varying pressured tamps and have found that applying a greater pressure than 30 lbs produced little effect, at most, a longer extraction. There are other variables that should be adjusted instead, such as the grind size, to achieve such an effect.</p>";
		Document doc = Jsoup.parse(htmlStr);
		String text = doc.text();
		StringBuilder builder = new StringBuilder(text);
		int index = 0;
		while(builder.length()>index){
			char tmp = builder.charAt(index);
			if(Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)){
				builder.setCharAt(index, ' ');
			}
			index++;
		}
		text = builder.toString().replaceAll(" +", " ").trim();
//		System.out.println(text);
		return text;
	}
	public static String RemoveStopwords(String text){
		String t="";
		Analyzer analyzer = new StandardAnalyzer();
//		String text="Let's start off with what the function of the tamp is";
		TokenStream ts = analyzer.tokenStream(null, text); 
		OffsetAttribute offsetAttribute = ts.addAttribute(OffsetAttribute.class);  
		CharTermAttribute charTermAttribute = ts.addAttribute(CharTermAttribute.class);  
		try {
			ts.reset();
			while( ts.incrementToken() ){  
				int startOffset = offsetAttribute.startOffset();  
				int endOffset = offsetAttribute.endOffset();  
				String term = charTermAttribute.toString();  				
//				System.out.print(term+" ");  
				t+=term+" ";
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//必须的  
		return t.substring(0, t.length()-1);
		
	}

}
