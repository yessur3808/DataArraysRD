package Main;

public class ExpandedWindow extends SearchWindow {
	
	
	
	
	public ExpandedWindow(WarpPath lowRes, TimeSeries shrunkX, TimeSeries shrunkY, int radius){
		
		super(shrunkX.size(),shrunkY.size());	
		for(int i = 0; i < lowRes.getSize() ; i++){	
			NPoint Current_point = lowRes.get(i);
			int x = (int) Current_point.getComponent(0);
			int y = (int) Current_point.getComponent(1);
			
			if(super.colomnMax[x] < (y +1)*2 ) super.colomnMax[x] = (y +1)*2 ;
			if(super.colomnMax[x+1] < (y +1)*2 ) super.colomnMax[x] = (y +1)*2 ;

			if(super.colomnMin[x] > y*2) super.colomnMin[x] = y*2;
			if(super.colomnMin[x+1] > y*2) super.colomnMin[x] = y*2;

		
		}
	}
	
}
