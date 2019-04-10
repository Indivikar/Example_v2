package app.example.Interfaces.Countdown_with_Interface;

public interface ICountdownListener {
	public void onTick(int current);
	public void onStart();
	public void onStop();
	public void onPause();
	public void onUnPause();
	public void onMaximum(int maximum);
}
