/**
 * 
 */
package artemisLite;

/**
 * 
 * @author emmadeane
 *
 */
public class ReadScript implements Runnable {

	
	@Override
	public void run() {
		readLineByLine();
		
	}
	
	/**
	 * 
	 */
	private void readLineByLine() {
		for(String line : Script.text) {
			System.out.println(line);
			try {
				Thread.currentThread();
				Thread.sleep(1300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Script.text.clear();
		
	}


}
