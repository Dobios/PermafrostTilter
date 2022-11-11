package platform.game.actor;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import platform.game.World;
import platform.game.actor.interactors.Keys;
import platform.game.actor.projectiles.Fireball;
import platform.game.level.GameOver;
import platform.game.level.Menu;
import platform.game.actor.projectiles.Snowball;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Player extends Actor {
	private Vector position;
	private Vector velocity = Vector.ZERO;
	private Sprite skin;
	private double fat = 0.8;
	private boolean grounding = false;
	private boolean climbing = false;
	private double friction = 0.001;
	private final static double MAXHEALTH = 5;
	private double health;
	private final static double MAXVIGOR = 5;
	private double vigor;
	private double cooldown;
	private Actor interactor = null;
	private double interactorDist = 10.0;
	private ArrayList<Keys> keychain = new ArrayList<Keys>();
	private Vector fvelocity = Vector.ZERO;
	private boolean stuck = false;
	private double stamina;
	private boolean canSprint;

	public Player(Vector position, Vector velocity){
		this.setPriority(42);
		this.position = position;
		this.velocity = velocity;
		this.health = this.MAXHEALTH;
		this.vigor = this.MAXVIGOR;
		this.stamina = 3.0;
	}

	public Player(Vector position, boolean stuck){
		this.setPriority(42);
		this.position = position;
		this.stuck  = stuck;
		this.health = this.MAXHEALTH;
		this.vigor = this.MAXVIGOR;
		this.stamina = 3.0;
	}

	public Difficulty getDifficulty() {
		return getWorld().getDifficulty(); 
	}

	public Sprite getSprite() {
		if (health > (MAXHEALTH/2) && vigor > 0.0) {
			skin = getSprite("creature/player/happy");
		} else if (health <= MAXHEALTH/2 && health > 0 && vigor > 0.0) {
			skin = getSprite("creature/player/sad");
		} else if (health <= 0 && vigor > 0.0) {
			skin = getSprite("creature/player/dead");
		} else if (vigor <= 0.0 && health > 2.5) {
			skin = getSprite("creature/player/PlayerCold");
		} else if ((vigor<=0.0 && health<2.5)) {
			skin = getSprite("creature/player/PlayerCold2");
		}
		return skin;
	}

	public void draw(Input input, Output output){
		output.drawSprite(getSprite(), getBox());
	}

	public void preUpdate(Input input){
		grounding = false;
		climbing = false;
		interactor = null;
		interactorDist = 10.0;
		fvelocity = Vector.ZERO;
		friction = 0.001;
	}

	public void update(Input input) {
		double delta = input.getDeltaTime();
		if (getDifficulty() == Difficulty.HARDCORE)
			cooldown -= delta/2;
		else 
			cooldown -= delta;
		double maxSpeed = 4.0;

		//SPRINT
		if(stamina > 0.0 && canSprint && grounding && input.getKeyboardButton(KeyEvent.VK_SHIFT).isDown()){
			maxSpeed = 7.0;
			stamina -= delta;
		} else {
			if (stamina <= 0.0 && canSprint) 
				canSprint = false;
			if (!canSprint)  
				stamina += delta/2;
			else {
				stamina += delta;
			}
		}
		if (stamina > 3.0 && !input.getKeyboardButton(KeyEvent.VK_SHIFT).isDown()) {
			stamina = 3.0;
			canSprint = true;
		}

		//GO RIGHT
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) {
			if (velocity.getX() < maxSpeed) {
				double speed = 60.0 * delta;
				if (speed > maxSpeed)
					speed = maxSpeed;
				velocity = velocity.add(new Vector(speed*(1.0-friction), 0));
			}
		}
		//GO LEFT
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) {
			if (velocity.getX() > -maxSpeed) {
				double speed = 60.0 * delta;
				if (speed < -maxSpeed) {
					speed = -maxSpeed;
				}
				velocity = velocity.sub(new Vector(speed*(1.0-friction), 0));
			}
		}
		//BLOW
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()) {
			if (cooldown <= 0) {
				getWorld().hurt(getBox(), this, Damage.AIR, 0.0, getPosition());
				cooldown = 1.5;
			} 
		}
		//INTERACT WITH INTERACTOR
		if (grounding && input.getKeyboardButton(KeyEvent.VK_E).isPressed() && (interactor!=null)) {
			interactor.hurt(this, Damage.ACTIVATION, 0.0, getPosition());
		}

		//MENU
		if (input.getKeyboardButton(KeyEvent.VK_ESCAPE).isPressed()) {
			getWorld().setNextLevel(new Menu());
			getWorld().nextLevel();
		}

		//CLIMBING MOVEMENT
		if (climbing) { 
			if (input.getKeyboardButton(KeyEvent.VK_UP).isDown()) {
				velocity = new Vector(velocity.getX(), 5);
			} else if (input.getKeyboardButton(KeyEvent.VK_DOWN).isDown()) {
				velocity = new Vector(velocity.getX(), -5);
			}

		} else {
			//JUMP
			if (grounding && (velocity.getY() == 0.0 ) && input.getKeyboardButton(KeyEvent.VK_UP).isDown()) {
				velocity = new Vector(velocity.getX(), 11);
			}
			//FIREBALL and SNOWBALL
			if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()) {
				if((vigor<=0.0 && health<2.5)){
					this.getWorld().register(new Snowball(position, new Vector(velocity.getX()*3,velocity.getY()+3), this));
				}else{
					if(vigor<0.0){
						vigor=0.0;
						if (getDifficulty() == Difficulty.HARDCORE)
							health -= 2.0;
						else 
							health -= 1.0;
					}
					if (getDifficulty() == Difficulty.HARDCORE)
						vigor -= 2.5;
					else 
						vigor -= 1.0;
					this.getWorld().register(new Fireball(position, new Vector(velocity.getX()*3,velocity.getY()+3), this));
				}
			}
			//GRAVITY
			Vector acceleration = World.getGravity();
			velocity = velocity.add(acceleration.mul(delta));
		}
		//FRICTION
		if ((grounding || climbing)) {
			double scale = Math.pow(friction, delta); 
			velocity = velocity.mul(scale);
		}
		position = position.add(velocity.mul(delta)).add(fvelocity.mul(delta));
	}

	public void postUpdate(Input input) {
		if(!stuck)
			getWorld().setView(position, 8);
		if((health <= 0.0) || (input.getKeyboardButton(KeyEvent.VK_Q).isPressed())){
			getWorld().register(new PlayerDead(position, fat));
			if (getWorld().getRespawn()) {
				getWorld().setNextLevel(getWorld().getCurrentLevel());
			} else {
				getWorld().setNextLevel(new GameOver());
			}

			getWorld().unregister(this);
		}
	}

	@Override
	public Box getBox() { 
		return new Box(position, fat, fat);
	}

	public Vector getPosition() { 
		return position;
	}

	public double getHealth(){
		return health;
	}

	public double getMaxHealth(){
		return MAXHEALTH;
	}

	public double getVigor(){
		return vigor;
	}

	public double getMaxVigor(){
		return MAXVIGOR;
	}

	public Keys[] getKeys(){
		Keys output[] = new Keys[keychain.size()];
		int i=0;
		for(Keys key : keychain){
			output[i]=key;
			++i;
		}
		return output;
	}
	public void addKey(Keys key){
		keychain.add(key);
	}

	public boolean hasKey(Keys key){
		return(keychain.indexOf(key)>=0);
	}

	public void removeKey(Keys key){
		keychain.remove(keychain.indexOf(key));
	}

	public boolean isPlayer() { 
		return true;
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox()); 
		if(delta!=null){
			if (other.isSolid()) {
				friction = other.getFriction();
				position = position.add(delta); 
				if (delta.getX() != 0.0)
					velocity = new Vector(0.0, velocity.getY()); 
				if (delta.getY() != 0.0){
					if (getDifficulty() == Difficulty.HARDCORE) {
						if(velocity.getY() < -13 ) 
							health -= 2;
					} else {
						if(velocity.getY() <-17 )
							health -= 0.5;
					}
					velocity = new Vector(velocity.getX(), 0.0);
				}
				Vector offset = other.getBox().getCenter().sub(getBox().getCenter());
				if(Math.abs(offset.getX())+0.05<(other.getBox().getWidth()+fat)/2.0 && offset.getY()<=other.getBox().getHeight()/2.0){
					grounding = true;
				}

				if (other.hurt(this, Damage.STICK, 1.0, Vector.ZERO)) {
					this.fvelocity = other.getSpeed();
				}
			}

			if(other.isClimbable()) {
				climbing = true;
			}
			if(other.isInteract()){
				Double newInteractDist = other.getBox().getCenter().sub(this.getBox().getCenter()).getLength();
				if(interactorDist > newInteractDist){
					interactor = other;
				}
			}
		}
	}

	public Actor getInteractor(){
		return interactor;
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		case FIRE:
			return true;
		case AIR: 
			velocity = velocity.add(location.mul(amount));
			if(velocity.getLength() > amount)
				velocity = velocity.resized(location.mul(amount).getLength());
			return true;
		case VOID:
			health = -1.;
			return true;
		case HEAL:
			if (health < MAXHEALTH) {
				if (getDifficulty() == Difficulty.HARDCORE)
					health += amount/2;
				else 
					health += amount;
				return true;
			}
			return false;
		case PHYSICAL:
			if (getDifficulty() == Difficulty.HARDCORE) 
				health -= amount * 2;
			health -= amount;
			return true;

		case COLD:
			if (getDifficulty() == Difficulty.HARDCORE) 
				amount = amount *2 ;
			if(vigor > 0.0) {
				this.vigor -= amount;
			}else{
				this.vigor -= amount;
				if(vigor < 0.0) {
					vigor = 0.0;
					if (getDifficulty() == Difficulty.HARDCORE)
						health -= 0.75;
					else 
						health -= 0.5;
				}
			}
			return true;

		case HEAT:
			if (getDifficulty() == Difficulty.HARDCORE) {
				this.vigor += amount/2;
			} else {
				this.vigor += amount;
			}
			if(vigor > MAXVIGOR) {
				vigor = MAXVIGOR;
			}
			return true;

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}
}
