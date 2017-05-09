package Bean;

import java.util.ArrayList;

public class QApair {
	int ID;			//序号
	int Qid;	//问题id
	int AnsNum;
	ArrayList<Integer> Aid=new ArrayList<Integer>();
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getQid() {
		return Qid;
	}
	public void setQid(int qid) {
		Qid = qid;
	}
	public ArrayList<Integer> getAid() {
		return Aid;
	}
	public void setAid(ArrayList<Integer> aid) {
		Aid = aid;
	}
	public int getAnsNum() {
		return AnsNum;
	}
	public void setAnsNum(int ansNum) {
		AnsNum = ansNum;
	}
	
}
