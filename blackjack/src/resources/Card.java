package resources;

public class Card {
	
	private int val=0;
	private int index=0;
	private int set=0;
	private int setSize=13;
	private int faceVal=0;
	
	
	
	public Card(int index){
		this.index=index;
		set=this.index/setSize; //
		val=this.index%setSize;
		//11-Jack, 12-Queen, 13-King
		faceVal=val+1;
		val++;
		if(this.val==11 || this.val==12 || this.val==13){
			this.val=10;
		}
	}
	
	public int getValue(){
		return val;
	}
	
	public int getFaceValue(){
		return faceVal;
	}
	
	public String toString(){
		return ""+getFaceValue();
	}
	
}
