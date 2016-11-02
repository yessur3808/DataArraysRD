package Main;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class WarpPath {

	private ArrayList<Integer> indexI;
	private ArrayList<Integer> indexJ;
	private ArrayList<Double> values;
	private int size;
	
	public WarpPath(int capacity){
		this();
		indexI.ensureCapacity(capacity);
		indexJ.ensureCapacity(capacity);
		this.values.ensureCapacity(capacity);
	}

	public WarpPath(){
		this.indexI = new ArrayList<Integer>();
		this.indexJ = new ArrayList<Integer>();
		this.values = new ArrayList<Double>();
		this.size = 0;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void addPoint(int i, int j, double value){
		this.indexI.add(size, i);
		this.indexJ.add(size, j);
		this.values.add(size, value);
		this.size++;
	}
	
	
	
	  public NPoint get(int index)
	   {
	      if ( (index>this.getSize()) || (index<0) )
	         throw new NoSuchElementException();
	      else
	         return new NPoint(new double[]{((Integer)indexI.get(index)).intValue(),
	                                 ((Integer)indexJ.get(index)).intValue()});
	   }
	
	
	public int getindexI(int index){
		return this.indexI.get(index).intValue();
	}
	
	public int getIndexJ(int index){
		return this.indexJ.get(index).intValue();
	}
	
	public double getValue(int index){
		return this.values.get(index).doubleValue();
	}

	public void reverse() {
		int center = size/2;
		for(int i = this.size - 1, j = 0; i >= center; i --, j++){
			swapInt(i, j, this.indexI);
			swapInt(i, j, this.indexJ);
			swapDouble(i, j, this.values);
		}
	}

	private void swapDouble(int i, int j, ArrayList<Double> array) {
		double first = array.get(i).intValue();
		double second = array.get(j).intValue();
		array.add(j, first);
		array.add(i, second);		
	}

	private void swapInt(int i, int j, ArrayList<Integer> array) {
		int first = array.get(i).intValue();
		int second = array.get(j).intValue();
		array.add(j, first);
		array.add(i, second);
	}

	@Override
	public String toString(){
		String result = "";
		for(int i = 0; i < this.size; i ++){
			result += "point " + i + " ( " +this.getindexI(i) + " , " + this.getIndexJ(i) + " ) ";
			result += "\n";
		}
		return result;
	}
}
