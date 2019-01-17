package searchEngines;

public class VariableRef {

	/**
	 * 绝对地址引用xml规则文件
	 */
	public static final String RULE_XML_PATH="F:\\All_Workspace\\eclipse-workspace\\conf\\Rule.xml";
	/**
	 * 相对地址引用项目配置文件
	 */
//	public static final String RULE_XML_PATH="..\\exclusive\\siteList\\Rule.xml";
	/**
	 * 存放索引的绝对地址
	 */
	public static final String INDEX_PATH="F:/TestFolder/searchEngines/index";
	/**
	 * 最大尝试次数
	 */
	public static final int MAX_TRY_TIMES = 3;
	/**
	 * 数据库驱动
	 */
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * 数据库URL地址
	 */
	public static final String DB_URL = "jdbc:mysql://localhost:3306/db_novel?characterEncoding=utf-8";
	
	/**
	 * 数据库用户名
	 */
	public static final String USER = "root";
	
	/**
	 * 数据库密码
	 */
	public static final String PASSWORD = "root";
	
	
}
