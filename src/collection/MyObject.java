package collection;

import java.io.Serializable;

public class MyObject implements Comparable<MyObject>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//不指定的话系统会给你默认指定；如果你自己指定了，你反序列化的时候加个属性也不会报错；如果由系统给出，多个字段肯定报错
	private String name="wang";
	private String code;
    private  int i;
    
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(MyObject o) {
		if (this.getName().compareTo(o.getName()) > 0) {
			return 1;
		} else if (this.getName().compareTo(o.getName()) < 0) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		else
			return true;
	}

}
