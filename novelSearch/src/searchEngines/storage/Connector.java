package searchEngines.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import searchEngines.VariableRef;
import searchEngines.entitys.Novel;

public class Connector {

	public static Connection getconnect() {
		Connection conn = null;
		try {
			// 注册 JDBC 驱动
			Class.forName(VariableRef.JDBC_DRIVER);
			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(VariableRef.DB_URL, VariableRef.USER, VariableRef.PASSWORD);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return conn;
	}
	
	/**
	 * 批量插入小说信息
	 * @param novelList
	 * @throws Exception
	 */
	public void insert(List<Novel> novelList) throws Exception {
		Connection conn = getconnect();
		System.out.println("开始批量插入.....");
		String sql ="INSERT INTO tbl_novel_1 VALUES";
		for(Novel n:novelList) {
			sql += "('"+n.getId()+"','" + n.getName() + "','" + n.getAuthor()+"','" + n.getUrl()+"','" + n.getType() + "','" + n.getLastUpdateChapter() + "','"
					+ n.getLastUpdateChapterUrl() +  "','" + n.getLastUpdateTime() + "','" + n.getStatus() + "','" + n.getFirstLetter() + "','"
					+ n.getPlatformId() + "','" + n.getAddTime()+"'),";
		}
		sql = sql.substring(0,sql.length()-1);
		//编译
		PreparedStatement ps = conn.prepareStatement(sql);
		//执行
		ps.execute();
		//关闭
        ps.close();
        conn.close();
        System.out.println("批量插入完毕！");
		
	}
	
	/**
	 * 单挑插入小说信息
	 * @param novelList
	 * @throws Exception
	 */
	public void insert(Novel novel) throws Exception {
		Connection conn = getconnect();
		System.out.println("开始插入一条小说信息.....");
		String sql ="INSERT INTO tbl_novel_1 VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		//预编译
		PreparedStatement ps = conn.prepareStatement(sql);
		//替换问号
		ps.setString(1, novel.getId());
		ps.setString(2, novel.getName());
		ps.setString(3, novel.getUrl());
		ps.setString(4, novel.getType());
		ps.setString(5, novel.getLastUpdateChapter());
		ps.setString(6, novel.getLastUpdateChapterUrl());
		ps.setString(7, novel.getLastUpdateChapterUrl());
		ps.setString(8, novel.getLastUpdateTime());
		ps.setString(9, novel.getStatus());
		ps.setString(10, novel.getFirstLetter());
		ps.setString(11, novel.getPlatformId());
		ps.setString(12, novel.getAddTime());
		//执行
		ps.execute();
		//关闭流
		ps.close();
		conn.close();
		System.out.println("插入完毕！");
	}
	
}
