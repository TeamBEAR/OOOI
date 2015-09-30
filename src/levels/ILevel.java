package levels;

/* Implementation pattern for individual levels*/
public interface ILevel {
	public boolean isFinished();
	public void draw();
	
	public ILevel getNextLevel();
	
	// TODO: merge
	public void handleInput(int pressed_key);
	public void handleCodedInput(int pressed_key);
}
