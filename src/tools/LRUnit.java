package tools;

public class LRUnit {
	public int value;
	public int used_count;
	public LRUnit(int value){
		this.value = value;
		this.used_count = 0;
	}
	
	public String toString(){
		return "["+value+","+used_count+"]";
	}
}
