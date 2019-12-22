package annotation;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptClass {
	public static void main(String[] args) throws Exception {
		
		ScriptEngineManager manager=new ScriptEngineManager();
	    ScriptEngine engine=manager.getEngineByName("JavaScript");
	    //读取js文件
	    Reader reader=new InputStreamReader(ScriptClass.class.getClassLoader().getResourceAsStream("annotation/mytest.js"));
	    engine.eval(reader);//绑定js脚本
	    if(engine instanceof Invocable) {
	    	//实现Invocable的脚本，调用该脚本的函数
	    	//((Invocable)engine).invokeFunction("alert", "测试");不能调用浏览器中定义的js函数
	    	System.out.print(((Invocable)engine).invokeFunction("add", 1,2));
	    }
//	    CompiledScript script=((Compilable)engine).compile(reader);
//	    System.out.print(script.eval());
//	    
	}

}
