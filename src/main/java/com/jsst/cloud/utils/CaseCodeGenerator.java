package com.jsst.cloud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CaseCodeGenerator {
	private static final String CDP_PREFIX = "BK";
	private static final String DK_PREFIX = "DK";
	private static final String YD_PREFIX = "YD";
	private static final String CDZ_PREFIX = "CDZ";
	private static final String WY_PREFIX = "WY";
	private static final String SR_PREFIX = "SR";
	private static final String SDP_PREFIX = "SDP";
	private static final String CFTC_PREFIX = "CFTC";
	private static final String CS_PREFIX = "CS";

	public static String generateCdpOrderNo() {
		return generate("BK");
	}

	public static String generateDkOrderNo() {
		return generate("DK");
	}

	public static String generateYdOrderNo() {
		return generate("YD");
	}

	public static String generateCdzOrderNo() {
		return generate("CDZ");
	}

	public static String generateWyOrderNo() {
		return generate("WY");
	}

	public static String generateSrOrderNo() {
		return generate("SR");
	}

	public static String generateSdpOrderNo() {
		return generate("SDP");
	}

	public static String generateCFTCOrderNo() {
		return generate("CFTC");
	}

	private static String generate(String prefix) {
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			String dateStr = format.format(date);

			Random r = new Random();
			UUID uuid = UUID.randomUUID();
			return prefix + dateStr + Math.abs(uuid.hashCode())
					+ String.valueOf(100000 - r.nextInt(10000));
		} catch (Exception localException) {
		}
		return "";
	}
	public static String generateCaseNo() {
		return generate("CS");
	}
	public static void main(String[] args) {
		/**
		for (int i = 0; i <= 1000; i++) {
			String s = generate("CS");
			System.out.println(s + "     " + s.length());
		}*/
		System.out.println(generate("CS"));
	}
}
