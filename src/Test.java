
public class Test {
	public static void main(String[] args) {
		try{
			print();
		}finally {
			System.out.println("-------finally...");
		}
	}
	
	public static void print() {
		throw new RuntimeException("报错了");
	}

}
