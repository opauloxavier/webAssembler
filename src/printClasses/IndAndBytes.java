package printClasses;

public class IndAndBytes {
	
	private String index;
	private String byte0;
	private String byte1;
	private String byte2;
	private String byte3;
	
	public IndAndBytes(String i, String a, String b, String c, String d){
		this.index = i;
		this.byte0 = a;
		this.byte1 = b;
		this.byte2 = c;
		this.byte3 = d;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getByte0() {
		return byte0;
	}

	public void setByte0(String byte0) {
		this.byte0 = byte0;
	}

	public String getByte1() {
		return byte1;
	}

	public void setByte1(String byte1) {
		this.byte1 = byte1;
	}

	public String getByte2() {
		return byte2;
	}

	public void setByte2(String byte2) {
		this.byte2 = byte2;
	}

	public String getByte3() {
		return byte3;
	}

	public void setByte3(String byte3) {
		this.byte3 = byte3;
	}
}
