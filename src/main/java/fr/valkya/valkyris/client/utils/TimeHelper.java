package fr.valkya.valkyris.client.utils;

public class TimeHelper {

	private long lastMS = 0L;
	
	public long getCurrentMS(){
		return System.nanoTime() / 1000000L;
	}
	
	public long getOffset() {
		return getCurrentMS() - lastMS;
	}
	
	public boolean hasReached(long milliseconds){
		return getOffset() >= milliseconds;
	}
	
	public void reset(){
		lastMS = getCurrentMS();
	}
	
	public void reset(long offset) {
		lastMS = getCurrentMS() - offset;
	}
	
}
