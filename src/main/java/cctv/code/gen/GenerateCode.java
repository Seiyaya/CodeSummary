package cctv.code.gen;

/**
 * 代码生成
 * @author 王佳
 * @created 2018年2月1日 上午9:40:44
 */
public interface GenerateCode {
	/**
	 * 模版路径
	 */
	public static String TEMPLATE_DIR = "target\\classes\\Theme";
	/**
	 * @author 王佳
	 * @created 2018年2月1日 上午9:45:47tag
	 * @param path	文件路径
	 * @param tableName	表名,以表名_后的部分作为类名，后续可重构为接口做类名和表名的映射关系
	 */
	void generate();
}
