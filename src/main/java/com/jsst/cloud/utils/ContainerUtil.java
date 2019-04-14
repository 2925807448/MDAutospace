package com.jsst.cloud.utils;

import java.util.List;

public class ContainerUtil {
	public static boolean listIsEmpty(List list) {
		if ((list != null) && (!list.isEmpty())) {
			return false;
		}
		return true;
	}

	public static boolean listIsNotEmpty(List list) {
		return !listIsEmpty(list);
	}
}
