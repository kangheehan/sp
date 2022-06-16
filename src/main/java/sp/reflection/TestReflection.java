package sp.reflection;

import java.util.List;

public class TestReflection {

	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test() throws Exception {
		TestAPIWrapper wrapper = new TestAPIWrapper();
		List<String> ret = wrapper.getWordList("Hello World");
		System.out.println("ret:" + ret);
	}
}
