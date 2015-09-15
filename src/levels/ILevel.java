package levels;

/* Implementation pattern for individual levels*/
public interface ILevel {
	public boolean isFinished();
	public void draw();
	public void handleInput(int pressed_key);
	public ILevel getNextLevel();
}
