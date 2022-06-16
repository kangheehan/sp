package sp.reflection;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class TestAPIWrapper {
	
	// 패키지 시작되는 상위 경로로 지정
	private static final String CLASSPATH = "target/classes";
	private URLClassLoader ucl;
	private Class<?> classTestAPI;
	private Class<?> classResultHandler;
	private List<String> list;
	
	public TestAPIWrapper() throws Exception {
		URL[] urls = new URL[] { new File(CLASSPATH).toURI().toURL() };
		ucl = new URLClassLoader(urls);
		
		classTestAPI = ucl.loadClass("sp.reflection.TestAPI");
		classResultHandler = ucl.loadClass("sp.reflection.TestAPI$ResultHandler");
	}
	
	public List<String> getWordList(String str) throws Exception {
		
		// 아래 인터페이스의 매서드가 호출 될 때 작동되는 핸들러임 
		// public interface ResultHandler {
		// 	public void handler(List<String> result);
		// }
		InvocationHandler invocationHandlerResultHandler = new InvocationHandler() {
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String methodName = method.getName();
				switch(methodName) {
				// public void handler(List<String> result);
				case "handler": {
					System.out.println("## " + methodName + "");
					Object parameter = args[0];
					list = (List<String>) parameter;
					
					// return 타입이 void형이기 때문에 null 리턴
					return null;
				}
				default:
					return null;
				}
			}
		};
		
		// public interface ResultHandler에 대한 인스턴스 생성
		Object instanceResultHandler = Proxy.newProxyInstance(
				TestReflection.class.getClassLoader(),
				new Class[] { classResultHandler },
				invocationHandlerResultHandler);
		
		// TestAPI에 대한 인스턴스 생성
		Object instanceTestAPI = classTestAPI.newInstance();
		Method methodGetWordList = classTestAPI.getMethod("getWordList", String.class, classResultHandler);
		
		// TestApi.getWordList(str, resultHandler) 호출
		methodGetWordList.invoke(instanceTestAPI, str, instanceResultHandler);
		
		return list;
	}
}
