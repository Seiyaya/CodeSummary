package cctv.test;

import cctv.code.gen.BeanGenerate;
import cctv.code.gen.GenerateCode;

public class NewMybatisTest {
	
	public static void main(String[] args) {
		GenerateCode generate = new BeanGenerate("C:\\Users\\lenovo\\git\\CodeSummary\\src\\main\\java\\cctv\\bean", "t_shop_user");
		generate.generate();
		
	}
}
