package platform.game.actor.GUI;

import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Text extends Actor {
	private Vector position;
	private double size;
	private double width;
	private String text;
	
	public Text(Vector position,double size, String text){
		this.setPriority(41);
		this.position = position;
		this.size = size;
		this.width = getTextWidth(size,text);
		this.text = text.toLowerCase();

	}
	
	public Text(int priority ,double size, String text){
		this.setPriority(priority);
		this.size = size;
		this.width = getTextWidth(size,text);
		this.text = text.toLowerCase();

	}
	
	public void setText(String text) {
		this.text = text.toLowerCase();
		this.width = getTextWidth(this.size,this.text);
	}
	
	public String getText() {
		return text;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public Box getBox() { 
		return new Box(position, width, size);
	}

	public void draw(Input input, Output output){
		double posX = position.getX();
		double posY = position.getY();
		char current;
		for(int i = 0; i<text.length();++i){
			current = text.charAt(i);
			if(current!=' '){
				output.drawSprite(getSprite(getCharSprite(current)), 
								  new Box(new Vector(posX-width/2.0+size*getCharSize(current)/2,posY),
										  size*getCharSize(current),size));
			}
			posX+=size*getCharSize(current);
		}
	}
	
	private static double getTextWidth(double size, String s){
		char current;
		double width=0;
		for(int i = 0; i<s.length();++i){
			current = s.charAt(i);
			width+=size*getCharSize(current);
		}
		return width;
	}
	
	private static String getCharSprite(char c){
		switch(c){
		case 'a': return "ui/txt/a";
		case 'b': return "ui/txt/b";
		case 'c': return "ui/txt/c";
		case 'd': return "ui/txt/d";
		case 'e': return "ui/txt/e";
		case 'f': return "ui/txt/f";
		case 'g': return "ui/txt/g";
		case 'h': return "ui/txt/h";
		case 'i': return "ui/txt/i";
		case 'j': return "ui/txt/j";
		case 'k': return "ui/txt/k";
		case 'l': return "ui/txt/l";
		case 'm': return "ui/txt/m";
		case 'n': return "ui/txt/n";
		case 'o': return "ui/txt/o";
		case 'p': return "ui/txt/p";
		case 'q': return "ui/txt/q";
		case 'r': return "ui/txt/r";
		case 's': return "ui/txt/s";
		case 't': return "ui/txt/t";
		case 'u': return "ui/txt/u";
		case 'v': return "ui/txt/v";
		case 'w': return "ui/txt/w";
		case 'x': return "ui/txt/x";
		case 'y': return "ui/txt/y";
		case 'z': return "ui/txt/z";
		case '0': return "ui/num/0";
		case '1': return "ui/num/1";
		case '2': return "ui/num/2";
		case '3': return "ui/num/3";
		case '4': return "ui/num/4";
		case '5': return "ui/num/5";
		case '6': return "ui/num/6";
		case '7': return "ui/num/7";
		case '8': return "ui/num/8";
		case '9': return "ui/num/9";
		case '?': return "ui/txt/int";
		case '!': return "ui/txt/exc";
		case '\'': return "ui/txt/apo";
		case '-' : return "ui/txt/-";
		case '.' : return "ui/txt/dot";
		
		default:
			return  "box.empty";
		}
	}
	
	private static double getCharSize(char c){
		switch(c){
		case 'b': 
		case 'q': return 0.9;
		case 'z': 
		case 'n': 
		case 'e': 
		case 'f': 
		case 'j': 
		case 'k': 
		case 'l': return 0.8;
		case '?': return 0.7;
		case 'i': 
		case '!': 
		case '\'': return 0.5;
		case '.': return 0.3;
		default:
			return 1.0;
		}
	}
}
