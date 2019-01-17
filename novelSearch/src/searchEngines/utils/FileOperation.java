package searchEngines.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 合并文件
 * @author SFJ
 *
 */
public class FileOperation {

	/**
	 * 多个文件合并为一个文件，合并规则：按文件名分割排序
	 * @param path 基础目录，该根目录下的所有文本文件都会被合并到 mergeToFile
	 * @param mergeToFile 被合并的文本文件，这个参数可以为null,合并后的文件保存在path/merge.txt
	 */
	public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1Index > o2Index) {
					return 1;
				} else if (o1Index == o2Index){
					return 0;
				} else {
					return -1;
				}
			}
		});
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(mergeToFile), "UTF-8");
			for (File file : files) {
				BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));	
				String line = null;
				while ((line = bufr.readLine()) != null) {
					out.println(line);
				}
				bufr.close();
				
				if (deleteThisFile) {
					file.delete();
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		} finally {
			out.close();
		}
	}
}
