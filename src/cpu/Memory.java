package cpu;

public class Memory {
	
	public static String[] bytes = new String[4096];
	
	public static void clearMemory(){
		
		for(int i = 0; i < 4096; i++)
			bytes[i] = "00000000";
	}
}
