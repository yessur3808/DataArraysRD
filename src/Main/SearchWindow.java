package Main;

public class SearchWindow {
	
	
	protected int[] colomnMax;
	protected int[] colomnMin;
	protected int rows, columns;
	
	/**
	 * Full search Window by default
	 * @param columns
	 * @param rows
	 */
	   
	   
	public SearchWindow(int columns, int rows){
		this.rows = rows;
		this.columns = columns;
		this.colomnMax = new int[columns];
		this.colomnMin = new int[columns];
		for(int i = 0; i < rows; i++){
			colomnMin[i] = rows;
			colomnMax[i] = columns;
		}
	}
	

	public boolean contains(int i, int j){
		return (i > 0 && i < columns) && (j >= colomnMin[i] && j < colomnMax[i]);
	}

	public int height() {
		return rows;
	}

	public int width() {
		return columns;
	}
	
	@Override
	public String toString(){
		String result = "";
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				if(this.contains(i, j)) result += "X";
				else result += "_";
			}
			result +="\n";
		}
		return result;
	}
}
