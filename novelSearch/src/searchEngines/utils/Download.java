package searchEngines.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import searchEngines.entitys.Chapter;
import searchEngines.entitys.ChapterContent;
import searchEngines.entitys.Thread;
import searchEngines.exclusive.ChapterContentFactory;
import searchEngines.exclusive.ChapterListFactory;
import searchEngines.exclusive.NovelSiteEnum;
import searchEngines.interfaces.IChapterGetContent;
import searchEngines.interfaces.IChapterGetList;
import searchEngines.interfaces.INovelDownload;

public class Download implements INovelDownload {

	@Override
	public String download(String url, Thread thread) {
		IChapterGetList getList = ChapterListFactory.getChapterList(url);
		List<Chapter> chapterList = getList.getChapterList(url);
		
		//某个线程下载完毕之后，你得告诉主线程：我下载好了
		//所有的线程都下载好了，合并！！！
		int size = thread.getSize();
		//根据章节的数量设置最大线程数量
		int maxThreadSize = (int)Math.ceil(chapterList.size() * 1.0 / size);
		Map<String, List<Chapter>> downloadTask = new HashMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			//起始章节
			int fromIndex = i * thread.getSize();
			//结束章节
			int toIndex = i == maxThreadSize - 1 ? chapterList.size() : i * thread.getSize() + thread.getSize();
			downloadTask.put(fromIndex + "-" + toIndex, chapterList.subList(fromIndex, toIndex));
		}
		//设置线程池
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet = downloadTask.keySet();
		List<Future<String>> tasks = new ArrayList<>();
		
		//通过这两段代码就可以创建缺失的路径
		String savePath = thread.getPath() + "/" + NovelSiteEnum.getEnumByUrl(url).getUrl();
		new File(savePath).mkdirs();
		
		//开始下载任务
		for (String key : keySet) {
			tasks.add(service.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTask.get(key), thread.getTryTimes())));
		}
		
		//下载结束，关闭线程池
		service.shutdown();
		
		//每一个线程任务完成后响应
		for (Future<String> future : tasks) {
			try {
				System.out.println(future.get() + ",下载完成！");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		//是否合并每一个线程下载后的文件
		if(thread.isIsmerge()) {
			FileOperation.multiFileMerge(savePath, null, true);
			return savePath + "/merge.txt";
		}else {
			return savePath;
		}
	}
	
}

/**
 * 具体下载任务内容
 * @author SFJ
 */
class DownloadCallable implements Callable<String> {
	private List<Chapter> chapters;
	private String path;
	private int tryTimes;
	public DownloadCallable(String path, List<Chapter> chapters, int tryTimes) {
		this.path = path;
		this.chapters = chapters;
		this.tryTimes = tryTimes;
	}
	@Override
	public String call() throws Exception {
		try (PrintWriter out = new PrintWriter(new File(path), "UTF-8")) {
			for (Chapter chapter : chapters) {
				IChapterGetContent contentSpider = ChapterContentFactory.getChapterContent(chapter.getUrl());
				
				ChapterContent content = null;
				for (int i = 0; i < tryTimes; i++) {
					try {
						content = contentSpider.getChapterContent(chapter.getUrl());
						out.println(content.getTitle());
						out.println(content.getContent());
						break;
					} catch (RuntimeException e) {
						System.err.println("尝试第[" + (i + 1) + "/" + tryTimes + "]次下载失败了！");
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return path;
	}
	
}

