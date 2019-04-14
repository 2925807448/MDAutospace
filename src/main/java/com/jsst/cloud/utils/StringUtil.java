package com.jsst.cloud.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	private static final String seqPrefix = getStringUUID() + "_";
	private static final AtomicLong atomicLong = new AtomicLong();

	public static String getSeqId() {
		return getStringUUID()
				+ Long.toString(atomicLong.incrementAndGet(), 36);
	}

	public static String getStringUUID() {
		UUID localUUID = UUID.randomUUID();
		int i = localUUID.toString().hashCode();
		return Integer.toString(i, 36);
	}

	public static String objToStringWithDef(Object o, String str) {
		if (isEmpty(o)) {
			return str;
		}
		return o.toString();
	}

	public static String objToString(Object o) {
		return o == null ? "" : o.toString();
	}

	public static String idsToString(List<String> ids) {
		StringBuilder buff = new StringBuilder("");
		if ((ids != null) && (ids.size() > 0)) {
			for (int i = 0; i < ids.size(); i++) {
				buff.append("'").append((String) ids.get(i)).append("'");
				if (i != ids.size() - 1) {
					buff.append(",");
				}
			}
		}
		return buff.toString();
	}

	public static String mapToString(Map<String, Object> map) {
		Set<String> set = map.keySet();

		Iterator<String> iter = set.iterator();

		StringBuilder builder = new StringBuilder();
		String tempKey = "";
		boolean isFirst = true;
		while (iter.hasNext()) {
			if (!isFirst) {
				builder.append(";");
			} else {
				isFirst = false;
			}
			tempKey = (String) iter.next();
			builder.append(tempKey).append(":").append(map.get(tempKey));
		}
		return builder.toString();
	}

	public static boolean isEmpty(Object str) {
		return (str == null) || ("".equals(String.valueOf(str).trim()));
	}

	public static boolean isNotEmpty(Object str) {
		return !isEmpty(str);
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if ((cs == null) || ((strLen = cs.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static int stringToInt(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	/**
	 * 
	 * @param 优惠劵签名方法
	 * @param md5Key
	 * @return
	 */
	public static String signByMD5(String meta, String md5Key) {
		logger.info("排序前的签名参数：{}", meta);
		if ((null == meta) || (meta.length() == 0)) {
			return null;
		}
		String item = null;
		String key = null;
		String val = null;
		int pos = -1;
		List<String> keys = new ArrayList();
		Map<String, String> vals = new HashMap(30);
		StringTokenizer token = new StringTokenizer(meta, "&");
		while (token.hasMoreTokens()) {
			item = token.nextToken();
			pos = item.indexOf('=');
			if (pos != -1) {
				key = item.substring(0, pos);
				val = item.substring(pos + 1);
				if ((null != val) && (val.length() != 0)) {
					keys.add(key);
					vals.put(key, val);
					key = null;
					val = null;
				}
			}
		}
		if (keys.size() == 0) {
			logger.info("待签名字符串不合法[{}].", meta);
			return null;
		}
		Collections.sort(keys);
		logger.info("排序后的签名参数：{}", keys);
		StringBuffer rs = new StringBuffer();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String ky = (String) it.next();
			rs.append("&").append(ky).append("=").append((String) vals.get(ky));
		}
		String plain = "";
		try {
			plain = rs.append("&").append(md5Hex(md5Key.getBytes("UTF-8")))
					.substring(1);
			return md5Hex(plain.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("签名失败.{}", meta);
		}
		return null;
	}

	public static String md5Hex(byte[] data) {
		return toHexString(md5(data));
	}

	public static String md5Hex(String data) {
		return toHexString(md5(data));
	}

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append("0123456789abcdef".charAt(b[i] >>> 4 & 0xF));
			sb.append("0123456789abcdef".charAt(b[i] & 0xF));
		}
		return sb.toString();
	}

	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	public static byte[] md5(String data) {
		return md5(data.getBytes());
	}

	public static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5算法不存在", e);
		}
	}

	public static void main(String[] args) {
		List<String> ids = new ArrayList();

		ids.add("def");

		System.out.println(idsToString(ids));

		Map<String, Object> map = new HashMap();

		map.put("first", "dddddddda");
		map.put("second", ids);
		map.put("ddees", "这是一个测试");
		System.out.println(mapToString(map));
		System.out.println(StringUtil.getUUID());
	}

	public static String replaceFirst(String sourceStr, String matchStr,
			String replaceStr) {
		int index = sourceStr.indexOf(matchStr);
		int matLength = matchStr.length();
		int sourLength = sourceStr.length();
		String beginStr = sourceStr.substring(0, index);
		String endStr = sourceStr.substring(index + matLength, sourLength);
		sourceStr = beginStr + replaceStr + endStr;
		return sourceStr;
	}
}
