package platform.game.actor;

//import java.util.Date;
import platform.game.actor.GUI.Text;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Chrono extends Text{
	//private int time = 0;
	private double width = 0;
	private int[] time = new int[3];
	private boolean run = true;

	public Chrono(Vector position, int[] time) {
		super(99999999, 1.0, "00:00:000");
		this.time = time;
	}

	public Chrono(boolean run) {
		super(999999999, 1.0, "00:00:000");
		this.run = run;
	}

	public Chrono() {
		super(999999999, 1.0, "00:00:000");
	}

	@Override
	public void update(Input input) {
		if(time[1]==0 && time[2]==0 && (getWorld().getChrono()[1]!=0 || getWorld().getChrono()[2]!=2)){
			time = getWorld().getChrono();
			super.setText(timeToString(time[2],time[1],time[0]));
		}
		super.setPosition(getWorld().getViewCenter().sub(new Vector(0,getWorld().getViewRadius()-0.6)));
		this.width = getWorld().getWWidth();
		if(run){
			if(time[2]<60){
				time[0] += input.getDeltaTime()*1000.;
				while(time[0]>=1000.){
					time[0]-=1000.;
					++time[1];
				}
				if(time[1]>=60){
					time[1]-=60;
					++time[2];
				}
				super.setText(timeToString(time[2],time[1],time[0]));
			}else{
				run=false;
				time[2] = 99;
				time[1] = 99;
				time[0] = 999;
			}
		}
	}
	public void postUpdate (Input input){
		if(run){
			getWorld().setChrono(time);
		}
	}

	public void draw(Input input, Output output){
		output.drawSprite(getSprite("ui/limits"), new Box(getBox().getCenter(),width,1.4));
		super.draw(input, output);
	}

	private static String timeToString(int m, int s, int ms){
		return numToString(m,2)+":"+numToString(s,2)+":"+numToString(ms,3);
	}

	private static String numToString(int num, int digits){
		int count = digits;
		String out = "";
		while(num<Math.pow(10, count) && count>0){
			--count;
		}
		for(int i=0; i<digits-count-1;++i){
			out+="0";
		}
		return out+num;
	}

	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		return false;		
	}

}
