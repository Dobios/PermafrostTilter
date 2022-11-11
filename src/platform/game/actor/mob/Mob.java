package platform.game.actor.mob;



import platform.game.World;
import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.actor.Difficulty;
import platform.game.actor.particle.PoufEffect;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public abstract class Mob extends Actor implements Signal{
	private Vector position;
	private boolean dead = false;
	private double width=0.6;
	private double height=0.5;
	private double damageCd = 0.5;
	private double friction = 0.001;
	private double health;
	private double maxSpeed = 3.0;
	private Vector velocity = Vector.ZERO;
	private boolean grounding = false;

	private boolean direction = true;//left = true , right = false

	public Mob(Vector position, double health, double width, double height) {
		setPriority(43);
		this.position = position;
		this.health = health;
		this.width = width;
		this.height = height;

	}

	public void preUpdate(Input input) {
		grounding = false;
	}

	public void update(Input input) {
		double delta = input.getDeltaTime();
		damageCd -= delta;
		//MOVE
		if (direction) {
			if (velocity.getX() < maxSpeed) {
				double speed = 60.0 * delta;
				if (speed > maxSpeed)
					speed = maxSpeed;
				velocity = velocity.add(new Vector(speed*(1.0-friction), 0));
			}
		}
		else if(!direction){
			if (velocity.getX() > -maxSpeed) {
				double speed = 60.0 * delta;
				if (speed < -maxSpeed)
					speed = -maxSpeed;
				velocity = velocity.sub(new Vector(speed*(1.0-friction), 0));
			}
		}

		//GRAVITY
		Vector acceleration = World.getGravity();
		velocity = velocity.add(acceleration.mul(delta));

		//FRICTION
		if (grounding) {
			double scale = Math.pow(friction, delta); 
			velocity = velocity.mul(scale);
		}

		position = position.add(velocity.mul(delta));
	}

	public void postUpdate(Input input) {
		if(health <= 0.0){
			getWorld().register(new PoufEffect(getBox(), 0.5));
			dead = true;
		}
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
					velocity = new Vector(velocity.getX(), 0.0);
				}
				Vector offset = other.getBox().getCenter().sub(getBox().getCenter());
				if(Math.abs(offset.getX())<(other.getBox().getWidth()+width-0.001)/2.0 && offset.getY()<=other.getBox().getHeight()/2.0){
					grounding = true;
				}
				if(Math.abs(offset.getX())>=(other.getBox().getWidth()+width-0.001)/2.0)
					direction=!direction;

			}
			if(other.isPlayer() && damageCd<0.0){
				other.hurt(this, Damage.COLD, 0.5, getPosition());
				damageCd=0.5;
			}
		}
	}

	@Override
	public Box getBox() { 
		if(!dead)
			return new Box(position, width, height);
		return Box.EMPTY;
	}

	public Vector getPosition() { 
		return position;
	}

	public Vector getVelocity(){
		return velocity;
	}

	@Override
	public boolean isActive() {
		return dead;
	}

	public boolean getDirection() {
		return direction;
	}
	
	public double getHealth(){
		return health;
	}

	public void setSize(double width, double height){
		this.width = width;
		this.height = height;
	}

	public void setSpeed(double speed){
		this.maxSpeed = speed;
	}


	@Override 
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) { 
		
		if (getWorld().getDifficulty() == Difficulty.HARDCORE) 
			amount /= 2;
		
		switch (type) { 
		case FIRE: 
			health -=amount; 
			return true; 
		case AIR:  
			//velocity = velocity.add(location.mul(amount)); 
			return false; 
		case VOID: 
			health = -1.; 
			return true; 
		case HEAL: 
			return false; 
		case PHYSICAL: 
			health -= amount; 
			return true; 
		case COLD: 
			return false; 
		case HEAT: 
			this.health -= amount; 
			return true; 
		default: 
			return super.hurt(instigator, type, amount, location); 
		} 

	}
}
