/*
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
package net.jerryfu.string;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String字符串工具类.
 * @author jerryfu
 *
 */
public final class StringUtil {

	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 *
	 * @param str 判断的字符串
	 * @return 是否有效
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串
	 *
	 * @param list   需要处理的列表
	 * @param symbol 链接的符号
	 * @return 处理后的字符串
	 */
	public static String joinString(List list, String symbol) {
		String result = "";
		if (list != null) {
			for (Object o : list) {
				String temp = o.toString();
				if (temp.trim().length() > 0)
					result += (temp + symbol);
			}
			if (result.length() > 1) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * 判定第一个字符串是否等于的第二个字符串中的某一个值
	 *
	 * @param str1 测试的字符串
	 * @param str2 字符串数组(用,分割)
	 * @return 是否包含
	 */
	public static boolean requals(String str1, String str2) {
		if (str1 != null && str2 != null) {
			str2 = str2.replaceAll("\\s*", "");
			String[] arr = str2.split(",");
			for (String t : arr) {
				if (t.equals(str1.trim())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判定第一个字符串是否等于的第二个字符串中的某一个值
	 *
	 * @param str1  测试的字符串
	 * @param str2  字符串数组
	 * @param split str2字符串的分隔符
	 * @return 是否包含
	 */
	public static boolean requals(String str1, String str2, String split) {
		if (str1 != null && str2 != null) {
			str2 = str2.replaceAll("\\s*", "");
			String[] arr = str2.split(split);
			for (String t : arr) {
				if (t.equals(str1.trim())) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 字符串省略截取
	 * 字符串截取到指定长度size+...的形式
	 *
	 * @param subject 需要处理的字符串
	 * @param size    截取的长度
	 * @return 处理后的字符串
	 */
	public static String subStringOmit(String subject, int size) {
		if (subject != null && subject.length() > size) {
			subject = subject.substring(0, size) + "...";
		}
		return subject;
	}


	/**
	 * 截取字符串　超出的字符用symbol代替
	 *
	 * @param str    需要处理的字符串
	 * @param len    字符串长度
	 * @param symbol 最后拼接的字符串
	 * @return 测试后的字符串
	 */
	public static String subStringSymbol(String str, int len, String symbol) {
		String temp = "";
		if (str != null && str.length() > len) {
			temp = str.substring(0, len) + symbol;
		}
		return temp;
	}


	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串
	 *
	 * @param array  需要处理的字符串数组
	 * @param symbol 链接的符号
	 * @return 处理后的字符串
	 */
	public static String joinString(String[] array, String symbol) {
		String result = "";
		if (array != null) {
			for (String temp : array) {
				if (temp != null && temp.trim().length() > 0)
					result += (temp + symbol);
			}
			if (result.length() > 1 && CheckUtil.valid(symbol)) {
				result = result.substring(0, result.length() - symbol.length());
			}
		}
		return result;
	}

	/**
	 * 将一组字符才以指定的字符链接起来
	 *
	 * @param linkStr 链接字符
	 * @param strs    需要连接的动态参数
	 * @return
	 */
	public static String join(String linkStr, String... strs) {
		StringBuffer result = new StringBuffer();
		for (String temp : strs) {
			if (temp != null && temp.trim().length() > 0)
				result.append(temp + linkStr);
		}
		if (result.length() > 1 && CheckUtil.valid(linkStr)) {
			return result.substring(0, result.length() - linkStr.length());
		}
		return result.toString();
	}


	/**
	 * 隐藏邮件地址前缀。
	 *
	 * @param email - EMail邮箱地址 例如: ssss@koubei.com 等等...
	 * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
	 */
	public static String getHideEmailPrefix(String email) {
		if (null != email) {
			int index = email.lastIndexOf('@');
			if (index > 0) {
				email = repeat("*", index).concat(email.substring(index));
			}
		}
		return email;
	}

	/**
	 * repeat - 通过源字符串重复生成N次组成新的字符串。
	 *
	 * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
	 * @param num - 重复生成次数
	 * @return 返回已生成的重复字符串
	 */
	public static String repeat(String src, int num) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < num; i++)
			s.append(src);
		return s.toString();
	}

	/**
	 * 截取字符串左侧的Num位截取到末尾
	 *
	 * @param str1 处理的字符串
	 * @param num  开始位置
	 * @return 截取后的字符串
	 */
	public static String ltrim(String str1, int num) {
		String tt = "";
		if (!isEmpty(str1) && str1.length() >= num) {
			tt = str1.substring(num, str1.length());
		}
		return tt;

	}

	/**
	 * 截取字符串右侧第0位到第Num位
	 *
	 * @param str 处理的字符串
	 * @param num 截取的位置
	 * @return 截取后的字符串
	 */
	public static String rtrim(String str, int num) {
		if (!isEmpty(str) && str.length() > num) {
			str = str.substring(0, str.length() - num);
		}
		return str;
	}

	/**
	 * 根据指定的字符把源字符串分割成一个list
	 *
	 * @param src     处理的字符串
	 * @param pattern 分割字符串
	 * @return 处理后的list
	 */
	public static List<String> parseString2List(String src, String pattern) {
		List<String> list = new ArrayList<>();
		if (src != null) {
			String[] tt = src.split(pattern);
			list.addAll(Arrays.asList(tt));
		}
		return list;
	}

	/**
	 * 格式化一个float
	 *
	 * @param format 要格式化成的格式 such as #.00, #.#
	 * @return 格式化后的字符串
	 */
	public static String formatDouble(double f, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(f);
	}


	/**
	 * 截取字符串左侧指定长度的字符串
	 *
	 * @param input 输入字符串
	 * @param count 截取长度
	 * @return 截取字符串
	 */
	public static String left(String input, int count) {
		if (isEmpty(input)) {
			return "";
		}
		count = (count > input.length()) ? input.length() : count;
		return input.substring(0, count);
	}

	/**
	 * 截取字符串右侧指定长度的字符串
	 *
	 * @param input 输入字符串
	 * @param count 截取长度
	 * @return 截取字符串
	 * Summary 其他编码的有待测试
	 */
	public static String right(String input, int count) {
		if (isEmpty(input)) {
			return "";
		}
		count = (count > input.length()) ? input.length() : count;
		return input.substring(input.length() - count, input.length());
	}


	/**
	 * 全角字符变半角字符
	 *
	 * @param str 需要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String full2Half(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return BCConvert.qj2bj(str);
	}

	/**
	 * 半角字符变全角字符
	 *
	 * @param str 需要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String Half2Full(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return BCConvert.bj2qj(str);
	}


	/**
	 * 页面中去除字符串中的空格、回车、换行符、制表符
	 *
	 * @param str 需要处理的字符串
	 */
	public static String replaceBlank(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}


	/**
	 * 判断字符串数组中是否包含某字符串元素
	 *
	 * @param substring 某字符串
	 * @param source    源字符串数组
	 * @return 包含则返回true，否则返回false
	 */
	public static boolean isIn(String substring, String[] source) {
		if (isEmpty(substring) || !CheckUtil.valid(source)) {
			return false;
		}
		for (String t : source) {
			if (substring.equals(t)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 字符串转换unicode.实现native2ascii.exe类似的功能
	 *
	 * @param string 需要处理的字符串
	 */
	public static String string2Unicode(String string) {
		StringBuilder uni = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			String temp = "\\u" + String.valueOf(Integer.toHexString(string.charAt(i)));
			uni.append(temp);
		}
		return uni.toString();
	}

	/**
	 * 转字符串 实现native2ascii.exe类似的功能
	 *
	 * @param unicode 需要处理的字符串
	 */
	public static String unicode2String(String unicode) {
		StringBuilder str = new StringBuilder();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int data = Integer.parseInt(hex[i], 16);
			str.append((char) data);
		}
		return str.toString();
	}


	/**
	 * 删除所有的标点符号
	 *
	 * @param str 处理的字符串
	 */
	public static String trimPunct(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str.replaceAll("[\\pP\\p{Punct}]", "");
	}

	/**
	 * 字符串相似度比较(速度较快)
	 */
	public static double SimilarityRatio(String str1, String str2) {
		str1 = StringUtil.trimPunct(str1);
		str2 = StringUtil.trimPunct(str2);
		if (str1.length() > str2.length()) {
			return StringImpl.SimilarityRatio(str1, str2);
		} else {
			return StringImpl.SimilarityRatio(str2, str1);
		}

	}

	/**
	 * 字符串相似度比较(速度较快)
	 */
	public static double SimilarDegree(String str1, String str2) {
		str1 = StringUtil.trimPunct(str1);
		str2 = StringUtil.trimPunct(str2);
		if (str1.length() > str2.length()) {
			return StringImpl.SimilarDegree(str1, str2);
		} else {
			return StringImpl.SimilarDegree(str2, str1);
		}
	}


	/**
	 * 获取字符串str在String中出现的次数
	 *
	 * @param string 处理的字符串
	 * @param str    子字符串
	 */
	public static int countSubStr(String string, String str) {
		if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
			return 0;
		}
		int count = 0;
		int index = 0;
		while ((index = string.indexOf(str, index)) != -1) {
			count++;
			index += str.length();
		}
		return count;
	}


	/**
	 * 替换一个出现的子串
	 *
	 * @param s    source string
	 * @param sub  substring to replace
	 * @param with substring to replace with
	 */
	public static String replaceFirst(String s, String sub, String with) {
		int i = s.indexOf(sub);
		if (i == -1) {
			return s;
		}
		return s.substring(0, i) + with + s.substring(i + sub.length());
	}


	/**
	 * 替换最后一次出现的字串
	 * Replaces the very last occurrence of a substring with supplied string.
	 *
	 * @param s    source string
	 * @param sub  substring to replace
	 * @param with substring to replace with
	 */
	public static String replaceLast(String s, String sub, String with) {
		int i = s.lastIndexOf(sub);
		if (i == -1) {
			return s;
		}
		return s.substring(0, i) + with + s.substring(i + sub.length());
	}


	/**
	 * 删除所有的子串
	 * Removes all substring occurrences from the string.
	 *
	 * @param s   source string
	 * @param sub substring to remove
	 */
	public static String remove(String s, String sub) {
		int c = 0;
		int sublen = sub.length();
		if (sublen == 0) {
			return s;
		}
		int i = s.indexOf(sub, c);
		if (i == -1) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length());
		do {
			sb.append(s.substring(c, i));
			c = i + sublen;
		} while ((i = s.indexOf(sub, c)) != -1);
		if (c < s.length()) {
			sb.append(s.substring(c, s.length()));
		}
		return sb.toString();
	}

	/**
	 * 将字符串首字母转大写
	 *
	 * @param str 需要处理的字符串
	 */
	public static String upperFirstChar(String str) {
		if (isEmpty(str)) {
			return "";
		}
		char[] cs = str.toCharArray();
		if ((cs[0] >= 'a') && (cs[0] <= 'z')) {
			cs[0] -= (char) 0x20;
		}
		return String.valueOf(cs);
	}

	/**
	 * 将字符串首字母转小写
	 *
	 * @param str
	 * @return
	 */
	public static String lowerFirstChar(String str) {
		if (isEmpty(str)) {
			return "";
		}
		char[] cs = str.toCharArray();
		if ((cs[0] >= 'A') && (cs[0] <= 'Z')) {
			cs[0] += (char) 0x20;
		}
		return String.valueOf(cs);
	}

	/**
	 * 判读俩个字符串右侧的length个字符串是否一样
	 *
	 * @param str1
	 * @param str2
	 * @param length
	 * @return
	 */
	public static boolean rigthEquals(String str1, String str2, int length) {
		return right(str1, length).equals(right(str2, length));
	}

	/**
	 * 判读俩个字符串左侧的length个字符串是否一样
	 *
	 * @param str1
	 * @param str2
	 * @param length
	 * @return
	 */
	public static boolean leftEquals(String str1, String str2, int length) {
		return left(str1, length).equals(left(str2, length));
	}

	/**
	 * 私有构造方法,将该工具类设为单例模式.
	 */
	private StringUtil() {
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		if (obj != null && obj.toString() != null && !"".equals(obj.toString().trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 函数功能说明 ： 判断字符串是否为空 . 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param str
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str.trim());
	}

	/**
	 * 函数功能说明 ： 判断对象数组是否为空. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param obj
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(Object[] obj) {
		return null == obj || 0 == obj.length;
	}

	/**
	 * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param obj
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			return ((String) obj).trim().isEmpty();
		}
		return !(obj instanceof Number) ? false : false;
	}

	/**
	 * 函数功能说明 ： 判断集合是否为空. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param obj
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(List<?> obj) {
		return null == obj || obj.isEmpty();
	}

	/**
	 * 函数功能说明 ： 判断Map集合是否为空. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param obj
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(Map<?, ?> obj) {
		return null == obj || obj.isEmpty();
	}

	/**
	 * 函数功能说明 ： 获得文件名的后缀名. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param fileName
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 获取去掉横线的长度为32的UUID串.
	 * 
	 * @author WuShuicheng.
	 * @return uuid.
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取带横线的长度为36的UUID串.
	 * 
	 * @author WuShuicheng.
	 * @return uuid.
	 */
	public static String get36UUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
	 * 
	 * @author WuShuicheng .
	 * @param str
	 *            要判断的字符串 .
	 * @return true or false .
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		} else {
			return str.matches("\\d*");
		}
	}

	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				LOG.error("UnsupportedEncodingException:" , e);
			}
		}
		return size;
	}

	/**
	 * 函数功能说明 ： 截取字符串拼接in查询参数. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param ids
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public static List<String> getInParam(String param) {
		boolean flag = param.contains(",");
		List<String> list = new ArrayList<String>();
		if (flag) {
			list = Arrays.asList(param.split(","));
		} else {
			list.add(param);
		}
		return list;
	}

	/**
	 * 将字符串解析成Map , 只适用于键值对拼接的字符串,例如:
	 * ssss=222&bbb=333&ccc=888
	 * @return
	 */
	public static Map<String , Object> parseStringToMap(String sourceString){

		return parseStringToMap(sourceString , "&");
	}

	/**
	 * 将字符串解析成Map , 只适用于键值对拼接的字符串,例如:
	 *
	 * @return
	 */
	public static Map<String , Object> parseStringToMap(String sourceString , String splitChar){
		if (isEmpty(sourceString)){
			return null;
		}

		Map<String , Object> resultMap = new HashMap<String , Object>();
		String[] sourceArr = sourceString.split(splitChar);
		for (String s : sourceArr){
			int firstIndex = s.indexOf("=");
			if (firstIndex > 0){
				String key = s.substring(0 , s.indexOf("="));
				String value = s.substring(s.indexOf("=") + 1);
				resultMap.put(key,value);
			}
		}
		return resultMap;
	}


	/**
	 *	根据传入的实体获得到toString 方法
	 * @param classObj
	 * @return
	 */
	public static String getObjectToStringCode(Class classObj){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("@Override\r");
		stringBuilder.append("\t\tpublic String toString() {").append("\r");
		stringBuilder.append("\t\t\tStringBuilder sb = new StringBuilder();").append("\r");
//		stringBuilder.append("\t\t\tsb.append(getClass().getSimpleName());").append("\r");
		stringBuilder.append("\t\t\tsb.append(\"").append(classObj.getSimpleName()).append("\");\r");
		stringBuilder.append("\t\t\tsb.append(\" [\");").append("\r");
		stringBuilder.append("\t\t\tsb.append(\"Hash = \").append(hashCode());").append("\r");

		Field[] fields = classObj.getDeclaredFields();
		for (Field f : fields){
			f.setAccessible(true);
			stringBuilder.append("\t\t\tsb.append(\", ").append(f.getName()).append("=\").append(").append(f.getName()).append(");").append("\r");
		}

		stringBuilder.append("\t\t\tsb.append(\"]\");").append("\r");
		stringBuilder.append("\t\t\treturn sb.toString();").append("\r");
		stringBuilder.append("\t\t}").append("\r");

		return stringBuilder.toString();
	}

	/**
	 * 生成Form表单字符串
	 * @param url
	 * @param method
	 * @param paramMap
	 * @return
	 */
	public static String generateFormString(String url , String method , Map<String, Object> paramMap ,String codeType){
		String html = "<form action=\"" + url + "\"" + " id=\"toPay\" method=\""+method+"\" accept-charset=\""+codeType+"\">";
		for (String key : paramMap.keySet()) {
			html += "<input name=\"" + key + "\" value=\"" + paramMap.get(key) + "\" type=\"hidden\" />";
		}
		html += "</form><script type=\"text/javascript\">document.getElementById(\"toPay\").submit();</script>";
		return html;
	}


	/**
	 * 编码类型转换
	 * @param sourceString	源字符串
	 * @param sourceCodeType	源编码类型
	 * @param targetCodeType	目标编码类型
	 * @return
	 */
	public static String codeTypeChange(String sourceString , String sourceCodeType , String targetCodeType){

		try {
			byte[] sourceStringBytes = sourceString.getBytes(sourceCodeType);

			return new String(sourceStringBytes, targetCodeType);
		} catch (UnsupportedEncodingException e) {
			LOG.info("原字符串{} 做编码转换{} ===> {} 异常" ,  sourceString ,  sourceCodeType , targetCodeType);
			return null;
		}
	}

}
