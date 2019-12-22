package designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * clone 
 * 1、clone是底层二进制的复制，不是通过构造器创建而成的，不用数据准备和权限校验，比普通的new 对象快
 * @author wwn
 * @date 2019年9月17日
 *
 */
public class PrototypePattern {
	public static void main(String[] args) throws CloneNotSupportedException {
		ArrayList<String> list=new ArrayList<>();
		list.add("123");
		Entity entity=new Entity();
		entity.setName("测试1");
		entity.setAge(11);
		entity.setArrayList(list);
		System.out.println("entity 初始化："+entity);
		Entity entity2=(Entity) entity.clone();
		System.out.println("entity2 clone后："+entity2);
		
		entity2.setAge(10);
		entity2.setName("测试2");
		
		List<String> newList=entity2.getArrayList();
		newList.remove(0);
		newList.add("234");
		entity2.setArrayList(newList);
		//entity2.setArrayList(new ArrayList<String>(Arrays.asList(new String[] {"234"})) );//指向新的对象不会影响entity的list值
		//object的clone方法是浅克隆，基本类型是值赋值，引用类型是地址复制，所以entity2指向的list对象发生改变，entity也会发生改变
		//这里的String虽然是引用类型，但是他也是final，所以setName是指向另一个String 对象，而不是原来的对象发生改变，所以不会使entity相应发生变化，同理如果list也指向新的对象，那么也不会影响entity。
		System.out.println("对entity2赋值后：entity"+entity+";entity2"+entity2);
		
		//PrototypePattern prototypePattern=new PrototypePattern();
		//因为object是所有类的父类，所以PrototypePattern也有clone 方法，如果close 没有实现Cloneable接口
		//就会报 java.lang.CloneNotSupportedException
		//prototypePattern.clone();
		
		
		Student student =new Student();
		student.setSex("1");
		Student newStudent=(Student) student.clone();//调用的是父类的clone
		System.out.print(newStudent);
		
	}
	

}

class  Entity implements Cloneable{
	private String  name;
	private int age;
	
	private List<String> arrayList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getArrayList() {
		return arrayList;
	}
	public void setArrayList(List<String> arrayList) {
		this.arrayList = arrayList;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + ", arrayList=" + arrayList.get(0) + "]";
	}

	
}

class  Student extends Entity {
	
	private String  sex;
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [sex=" + sex + "]";
	}
	
}