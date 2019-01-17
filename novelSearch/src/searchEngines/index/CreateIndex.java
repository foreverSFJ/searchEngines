package searchEngines.index;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import searchEngines.VariableRef;
import searchEngines.storage.Connector;

public class CreateIndex {
	
	public void createIndex() {
		String[] args = null;
		main(args);
	}
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			IndexWriter writer = new IndexWriter(VariableRef.INDEX_PATH, new StandardAnalyzer(), true);
			conn = Connector.getconnect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from tbl_novel_1 order by id");
			while (rs.next()) {
				System.out.println(rs.getRow());
				Document doc = new Document();
				
				//处理空值和其他字符
				String id = rs.getString("id");
				String name = rs.getString("Name");
				String author = rs.getString("Author");
				String url = rs.getString("URL");
				String type = rs.getString("Type");
				if(type ==null) {
					type = "此处是空值！";
				}
				String lastUpdateChapter = rs.getString("Last_Update_Chapter");
				String lastUpdateChapterUrl = rs.getString("Last_Update_Chapter_Url");
				String lastUpdateTime = rs.getString("Last_Update_Time");
				String status = rs.getString("Status");
				String firstLetter = rs.getString("First_Letter");
				String platformId = rs.getString("Platform_Id");
				String addTime = rs.getString("AddTime");
				if(addTime == null) {
					addTime = "此处是空值！";
				}
				
				
				/**
				 * 开始创建索引
				 * 
				 * id, url, type, lastUpdateChapterUrl, lastUpdateTime, firstLetter, addTime不需要被索引
				 */
				Field idFld = new Field("id", id, Field.Store.YES, Field.Index.NO);
				Field urlFld = new Field("url", url, Field.Store.YES, Field.Index.NO);
				Field typeFld = new Field("type", type, Field.Store.YES, Field.Index.NO);
				Field lastUpdateChapterUrlld = new Field("lastUpdateChapterUrl", lastUpdateChapterUrl, Field.Store.YES, Field.Index.NO);
				Field lastUpdateTimeFld = new Field("lastUpdateTime", lastUpdateTime, Field.Store.YES, Field.Index.NO);
				Field firstLetterFld = new Field("firstLetter", firstLetter, Field.Store.YES, Field.Index.NO);
				Field addTimeFld = new Field("addTime", addTime, Field.Store.YES, Field.Index.NO);
				/**
				 * title, author, 先分析再索引
				 */
				Field titleFld = new Field("name", name, Field.Store.YES, Field.Index.TOKENIZED);
				Field authorFld = new Field("author", author, Field.Store.YES, Field.Index.TOKENIZED);
				/**
				 * 对lastUpdateChapter， status， platformId进行索引，但是不分析
				 */
				Field lastUpdateChapterFld = new Field("lastUpdateChapter", lastUpdateChapter, Field.Store.YES, Field.Index.NO_NORMS);
				Field statusFld = new Field("status", status, Field.Store.YES, Field.Index.NO_NORMS);
				Field platformIdFld = new Field("platformId", platformId, Field.Store.YES, Field.Index.NO_NORMS);
			
				/**
				 * 保存索引
				 */
				doc.add(idFld);
				doc.add(urlFld);
				doc.add(typeFld);
				doc.add(lastUpdateChapterUrlld);
				doc.add(lastUpdateTimeFld);
				doc.add(firstLetterFld);
				doc.add(addTimeFld);
				doc.add(titleFld);
				doc.add(authorFld);
				doc.add(lastUpdateChapterFld);
				doc.add(statusFld);
				doc.add(platformIdFld);
				writer.addDocument(doc);
			}
			// 对索引进行优化
			writer.optimize();
			// 关闭写入流对象并将索引写入目录
			writer.close();
			System.err.println("数据库索引已经创建完毕，请在目录："+ VariableRef.INDEX_PATH +"下查看！！");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
