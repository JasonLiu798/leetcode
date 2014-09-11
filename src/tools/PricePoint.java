package tools;

public class PricePoint {
	public int price;
	//int idx;
	public boolean is_high = true;
	public PricePoint(int price,boolean is_high){//int idx,boolean is_high){
		this.price = price;
		//this.idx = idx;
		this.is_high = is_high;
	}
}
