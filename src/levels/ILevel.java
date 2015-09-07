package levels;

/* Implementation pattern for individual levels*/
public interface ILevel {
	public void draw();
	public void handleInput(int pressed_key);
	int validateInput(String input);
}
