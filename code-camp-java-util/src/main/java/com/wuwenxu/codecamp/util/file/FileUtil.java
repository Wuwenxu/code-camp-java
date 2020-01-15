package net.jerryfu.file;/*
											* Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
											*
											* Licensed under the Apache License, Version 2.0 (the "License");
											* you may not use this file except in compliance with the License.
											* You may obtain a copy of the License at
											*
											*      http://www.apache.org/licenses/LICENSE-2.0
											*
											* Unless required by applicable law or agreed to in writing, software
											* distributed under the License is distributed on an "AS IS" BASIS,
											* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
											* See the License for the specific language governing permissions and
											* limitations under the License.
											*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 文件及文件夹工具类
 * @author jerryfu
 *
 */
public class FileUtil {
	private static final Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * Buffer的大小
	 */
	private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

	public static MessageDigest MD5 = null;

	static {
		try {
			MD5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ne) {
			ne.printStackTrace();
		}
	}

	/**
	 * 获取文件的md5
	 *
	 * @param file
	 * @return
	 */
	public static String fileMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new BigInteger(1, MD5.digest()).toString(16);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取文件的行数
	 *
	 * @param file 统计的文件
	 * @return 文件行数
	 */
	public final static int countLines(File file) {
		try (LineNumberReader rf = new LineNumberReader(new FileReader(file))) {
			long fileLength = file.length();
			rf.skip(fileLength);
			return rf.getLineNumber();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 以列表的方式获取文件的所有行
	 *
	 * @param file 需要出来的文件
	 * @return 包含所有行的list
	 */
	public final static List<String> lines(File file) {
		List<String> list = new ArrayList<>();
		try (
				BufferedReader reader = new BufferedReader(new FileReader(file))
		) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 以列表的方式获取文件的所有行
	 *
	 * @param file     需要处理的文件
	 * @param encoding 指定读取文件的编码
	 * @return 包含所有行的list
	 */
	public final static List<String> lines(File file, String encoding) {
		List<String> list = new ArrayList<>();
		try (
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
		) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 以列表的方式获取文件的指定的行数数据
	 *
	 * @param file  处理的文件
	 * @param lines 需要读取的行数
	 * @return 包含制定行的list
	 */
	public final static List<String> lines(File file, int lines) {
		List<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
				if (list.size() == lines) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 以列表的方式获取文件的指定的行数数据
	 *
	 * @param file     需要处理的函数
	 * @param lines    需要处理的行还俗
	 * @param encoding 指定读取文件的编码
	 * @return 包含制定行的list
	 */
	public final static List<String> lines(File file, int lines, String encoding) {
		List<String> list = new ArrayList<>();
		try (
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
		) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
				if (list.size() == lines) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 在文件末尾追加一行
	 *
	 * @param file 需要处理的函数
	 * @param str  添加的子字符串
	 * @return 是否成功
	 */
	public final static boolean appendLine(File file, String str) {
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(SysHepler.FILE_SEPARATOR + str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 在文件末尾追加一行
	 *
	 * @param file     需要处理的文件
	 * @param str      添加的字符串
	 * @param encoding 指定写入的编码
	 * @return 是否成功
	 */
	public final static boolean appendLine(File file, String str, String encoding) {
		String lineSeparator = System.getProperty("line.separator", "\n");
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write((lineSeparator + str).getBytes(encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将字符串写入到文件中
	 */
	public final static boolean write(File file, String str) {
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			randomFile.writeBytes(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将字符串以追加的方式写入到文件中
	 */
	public final static boolean writeAppend(File file, String str) {
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将字符串以制定的编码写入到文件中
	 */
	public final static boolean write(File file, String str, String encoding) {
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			randomFile.write(str.getBytes(encoding));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将字符串以追加的方式以制定的编码写入到文件中
	 */
	public final static boolean writeAppend(File file, String str, String encoding) {
		try (
				RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
		) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write(str.getBytes(encoding));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 快速清空一个超大的文件
	 *
	 * @param file 需要处理的文件
	 * @return 是否成功
	 */
	public final static boolean cleanFile(File file) {
		try (
				FileWriter fw = new FileWriter(file)
		) {
			fw.write("");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取文件的Mime类型
	 *
	 * @param file 需要处理的文件
	 * @return 返回文件的mime类型
	 * @throws java.io.IOException
	 */
	public final static String mimeType(String file) throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		return fileNameMap.getContentTypeFor(file);
	}

	/**
	 * 获取文件的类型 只利用文件头做判断故不全
	 *
	 * @param file 需要处理的文件
	 * @return 文件类型
	 */
	public final static String fileType(File file) {
		return FileTypeImpl.getFileType(file);
	}

	/**
	 * 获取文件最后的修改时间
	 *
	 * @param file 需要处理的文件
	 * @return 返回文件的修改时间
	 */
	public final static Date modifyTime(File file) {
		return new Date(file.lastModified());
	}


	/**
	 * 复制文件
	 *
	 * @param resourcePath 源文件
	 * @param targetPath   目标文件
	 * @return 是否成功
	 */
	public final static boolean copy(String resourcePath, String targetPath) {
		File file = new File(resourcePath);
		return copy(file, targetPath);
	}

	/**
	 * 复制文件
	 * 通过该方式复制文件文件越大速度越是明显
	 *
	 * @param file       需要处理的文件
	 * @param targetFile 目标文件
	 * @return 是否成功
	 */
	public final static boolean copy(File file, String targetFile) {
		try (
				FileInputStream fin = new FileInputStream(file);
				FileOutputStream fout = new FileOutputStream(new File(targetFile))
		) {
			FileChannel in = fin.getChannel();
			FileChannel out = fout.getChannel();
			//设定缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
			while (in.read(buffer) != -1) {
				//准备写入，防止其他读取，锁住文件
				buffer.flip();
				out.write(buffer);
				//准备读取。将缓冲区清理完毕，移动文件内部指针
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 创建多级目录
	 *
	 * @param paths 需要创建的目录
	 * @return 是否成功
	 */
	public final static boolean createPaths(String paths) {
		File dir = new File(paths);
		return !dir.exists() && dir.mkdir();
	}

	/**
	 * 创建文件支持多级目录
	 *
	 * @param filePath 需要创建的文件
	 * @return 是否成功
	 */
	public final static boolean createFiles(String filePath) {
		File file = new File(filePath);
		if(file.isDirectory()){
			if (!file.exists()) {
				return file.mkdirs();
			}
		}else{
			File dir = file.getParentFile();
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					try {
						return file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 删除一个文件
	 *
	 * @param file 需要处理的文件
	 * @return 是否成功
	 */
	public final static boolean deleteFile(File file) {
		return file.delete();
	}

	/**
	 * 删除一个目录
	 *
	 * @param file 需要处理的文件
	 * @return 是否成功
	 */
	public final static boolean deleteDir(File file) {
		List<File> files = listFileAll(file);
		if (CheckUtil.valid(files)) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteDir(f);
				} else {
					deleteFile(f);
				}
			}
		}
		return file.delete();
	}


	/**
	 * 快速的删除超大的文件
	 *
	 * @param file 需要处理的文件
	 * @return 是否成功
	 */
	public final static boolean deleteBigFile(File file) {
		return cleanFile(file) && file.delete();
	}


	/**
	 * 复制目录
	 *
	 * @param filePath   需要处理的文件
	 * @param targetPath 目标文件
	 */
	public final static void copyDir(String filePath, String targetPath) {
		File file = new File(filePath);
		copyDir(file, targetPath);
	}

	/**
	 * 复制目录
	 *
	 * @param filePath   需要处理的文件
	 * @param targetPath 目标文件
	 */
	public final static void copyDir(File filePath, String targetPath) {
		File targetFile = new File(targetPath);
		if (!targetFile.exists()) {
			createPaths(targetPath);
		}
		File[] files = filePath.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				String path = file.getName();
				if (file.isDirectory()) {
					copyDir(file, targetPath + "/" + path);
				} else {
					copy(file, targetPath + "/" + path);
				}
			}
		}
	}

	/**
	 * 罗列指定路径下的全部文件
	 *
	 * @param path 需要处理的文件
	 * @return 包含所有文件的的list
	 */
	public final static List<File> listFile(String path) {
		File file = new File(path);
		return listFile(file);
	}

	/**
	 * 罗列指定路径下的全部文件
	 *
	 * @param path  需要处理的文件
	 * @param child 是否罗列子文件
	 * @return 包含所有文件的的list
	 */
	public final static List<File> listFile(String path, boolean child) {
		return listFile(new File(path), child);
	}


	/**
	 * 罗列指定路径下的全部文件
	 *
	 * @param path 需要处理的文件
	 * @return 返回文件列表
	 */
	public final static List<File> listFile(File path) {
		List<File> list = new ArrayList<>();
		File[] files = path.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					list.addAll(listFile(file));
				} else {
					list.add(file);
				}
			}
		}
		return list;
	}

	/**
	 * 罗列指定路径下的全部文件
	 *
	 * @param path  指定的路径
	 * @param child 是否罗列子目录
	 * @return
	 */
	public final static List<File> listFile(File path, boolean child) {
		List<File> list = new ArrayList<>();
		File[] files = path.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (child && file.isDirectory()) {
					list.addAll(listFile(file));
				} else {
					list.add(file);
				}
			}
		}
		return list;
	}

	/**
	 * 罗列指定路径下的全部文件包括文件夹
	 *
	 * @param path 需要处理的文件
	 * @return 返回文件列表
	 */
	public final static List<File> listFileAll(File path) {
		List<File> list = new ArrayList<>();
		File[] files = path.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				list.add(file);
				if (file.isDirectory()) {
					list.addAll(listFileAll(file));
				}
			}
		}
		return list;
	}

	/**
	 * 罗列指定路径下的全部文件包括文件夹
	 *
	 * @param path   需要处理的文件
	 * @param filter 处理文件的filter
	 * @return 返回文件列表
	 */
	public final static List<File> listFileFilter(File path, FilenameFilter filter) {
		List<File> list = new ArrayList<>();
		File[] files = path.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					list.addAll(listFileFilter(file, filter));
				} else {
					if (filter.accept(file.getParentFile(), file.getName())) {
						list.add(file);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获取指定目录下的特点文件,通过后缀名过滤
	 *
	 * @param dirPath  需要处理的文件
	 * @param postfixs 文件后缀
	 * @return 返回文件列表
	 */
	public final static List<File> listFileFilter(File dirPath, final String postfixs) {
        /*
        如果在当前目录中使用Filter讲只罗列当前目录下的文件不会罗列孙子目录下的文件
        FilenameFilter filefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(postfixs);
            }
        };
        */
		List<File> list = new ArrayList<File>();
		File[] files = dirPath.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					list.addAll(listFileFilter(file, postfixs));
				} else {
					String fileName = file.getName().toLowerCase();
					if (fileName.endsWith(postfixs.toLowerCase())) {
						list.add(file);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 在指定的目录下搜寻文个文件
	 *
	 * @param dirPath  搜索的目录
	 * @param fileName 搜索的文件名
	 * @return 返回文件列表
	 */
	public final static List<File> searchFile(File dirPath, String fileName) {
		List<File> list = new ArrayList<>();
		File[] files = dirPath.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					list.addAll(searchFile(file, fileName));
				} else {
					String Name = file.getName();
					if (Name.equals(fileName)) {
						list.add(file);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 查找符合正则表达式reg的的文件
	 *
	 * @param dirPath 搜索的目录
	 * @param reg     正则表达式
	 * @return 返回文件列表
	 */
	public final static List<File> searchFileReg(File dirPath, String reg) {
		List<File> list = new ArrayList<>();
		File[] files = dirPath.listFiles();
		if (CheckUtil.valid(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					list.addAll(searchFile(file, reg));
				} else {
					String Name = file.getName();
					if (RegHepler.isMatche(Name, reg)) {
						list.add(file);
					}
				}
			}
		}
		return list;
	}


	/**
	 * 获取文件后缀名
	 *
	 * @param file
	 * @return
	 */
	public final static String suffix(File file) {
		String fileName = file.getName();
		return fileName.substring(fileName.indexOf(".") + 1);
	}
	
	  /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param dir
     *            将要删除的文件目录
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (!dir.exists()) return false;
        if (dir.isDirectory()) {
            String[] childrens = dir.list();
            // 递归删除目录中的子目录下
            for (String child : childrens) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) return false;
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

	/**
	 * 判断是否是目录
	 * 
	 * @param dirPath
	 * @return
	 */
	public static boolean isDir(String dirPath) {
		if (StringUtils.isBlank(dirPath)) {
			return false;
		}
		File file = new File(dirPath);
		if (!file.exists() || !file.isDirectory()) {
			return false;
		}
		return true;
	}

	/**
	 * 创建文件夹,只能创建最底层一级的文件夹,如果上级文件夹不存在,则创建失败
	 * 
	 * @param dirPath
	 */
	public static boolean mkDir(String dirPath) {
		if (StringUtils.isBlank(dirPath)) {
			return false;
		}

		File file = new File(dirPath);
		if (file.exists()) {
			return true;
		} else {
			return file.mkdir();
		}
	}

	/**
	 * 创建文件夹,可以创建多级文件夹,如果最底层一级之上的文件夹也不存在,则同时生成上一级文件夹
	 * 
	 * @param dirPath
	 * @return
	 */
	public static boolean mkDirAndSubDir(String dirPath) {
		if (StringUtils.isBlank(dirPath)) {
			return false;
		}

		File file = new File(dirPath);
		if (file.exists()) {
			return true;
		} else {
			return file.mkdirs();
		}
	}

	// 创建单个文件
	public static boolean createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {// 判断文件是否存在
			System.out.println("目标文件已存在" + filePath);
			return false;
		}
		if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
			System.out.println("目标文件不能为目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
			// 如果目标文件所在的文件夹不存在，则创建父文件夹
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
				System.out.println("创建目标文件所在的目录失败！");
				return false;
			}
		}
		try {
			if (file.createNewFile()) {// 创建目标文件
				System.out.println("创建文件成功:" + filePath);
				return true;
			} else {
				System.out.println("创建文件失败！");
				return false;
			}
		} catch (IOException e) {// 捕获异常
			e.printStackTrace();
			System.out.println("创建文件失败！" + e.getMessage());
			return false;
		}
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + destDirName);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}

	/**
	 * 写文件
	 * 
	 * @param newStr
	 *            新内容
	 * @throws IOException
	 */
	public static boolean writeTxtFile(String fileName, String newStr) throws IOException {
		// 先读取原有文件内容，然后进行写入操作
		boolean flag = false;
		String filein = newStr;
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			// 文件路径
			File file = new File(fileName);
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该文件原有的内容
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
			}
			buf.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		// System.out.println("" +
		// mkDirAndSubDir("/Users/peter/Desktop/redis/test/test/fast/test.txt"));
		String fileName = "F:/aa/bb/1.txt";
		createFile("F:/aa/bb/1.txt");
		try {
			writeTxtFile(fileName, "2017-02-01 22:10:15|payment001|10");
			writeTxtFile(fileName, "2017-02-01 22:10:15|payment001|11");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
