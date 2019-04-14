package com.jsst.cloud.common.redis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.jsst.cloud.utils.StringUtil;
import com.jsst.cloud.utils.ValueUtil;
import org.springframework.util.Assert;
public class RedisKeyFormat
{
  public static String getKey(String cons, Object... args)
  {
    List<Object> argList = new ArrayList();
    if (args != null) {
      for (Object arg : args) {
        if ((arg == null) || ("".equals(arg))) {
          argList.add("");
        } else {
          argList.add(arg);
        }
      }
    }
    String keyPrefix = ValueUtil.get("case.redis.key");
    return MessageFormat.format(keyPrefix + cons, argList.toArray());
  }
  
  public static String format(String key, Object... args)
  {
    Assert.hasLength(key, "key不能为空");
    if ((null == args) || (args.length == 0)) {
      return key;
    }
    return MessageFormat.format(key, args);
  }
}
