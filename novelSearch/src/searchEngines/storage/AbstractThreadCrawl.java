package searchEngines.storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import searchEngines.entitys.Novel;
import searchEngines.exclusive.NovelListFactory;
import searchEngines.interfaces.INoveGetlList;
import searchEngines.interfaces.IProcess;
import searchEngines.utils.DetailProcess;

/**
 * 多线程抓取数据insert到数据库
 * @author SFJ
 *
 */
public abstract class AbstractThreadCrawl implements IProcess{

	//线程池链接
	protected Map<String, String> tasks = new TreeMap<>();
	
	/**
	 * 开始多线程抓取并存入本地
	 */
	@Override
	public void metadataCrawl() {
		//设置任务线程池
		ExecutorService service = Executors.newFixedThreadPool(tasks.size());
		//任务完成结果链表
		List<Future<String>> futures = new ArrayList<>(tasks.size());
		//任务迭代开始
		for (Entry<String, String> entry : tasks.entrySet()) {
			//每一个entry中保存一个小说首字母系列链接开始，key是首字母，value是链接
			final String key = entry.getKey();
			final String value = entry.getValue();
			futures.add(service.submit(new Callable<String> () {
				@Override
				public String call() throws Exception {
					INoveGetlList getList = NovelListFactory.getNovelList(value);
					//尝试10次
					Iterator<List<Novel>> iterator = getList.iterator(value, 10);
					while (iterator.hasNext()) {
						System.out.println("开始抓取[" + key + "] 的 URL:" + getList.next());
						List<Novel> novels = iterator.next();
						for (Novel novel : novels) {
							novel.setFirstLetter(key);	//设置小说的名字的首字母
							if(novel.getAddTime() == null) {
								novel.setAddTime(DetailProcess.getDateEtc(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"yyyy-MM-dd").toString());
							}
						}
						//执行插入方法
						Connector cor = new Connector();
						cor.insert(novels);
						Thread.sleep(1_000);
					}
					return key;
				}
				
			}));
		}
		service.shutdown();
		for (Future<String> future : futures) {
			try {
				System.err.println("抓取[" + future.get() + "]结束！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
