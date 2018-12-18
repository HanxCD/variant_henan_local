/*
 * Copyright 2014 BOCO Inter-Telecom(xi'an)
 * All rights reserved. 
 * project name: fileParse
 * version V1.0
 */
package com.boco.soap.fileparse.operate;

import java.util.List;
import java.util.Map;

/**
 * @author lijixin
 *
 */
public interface ISpliteOperate {
	
	public List<Map<String,String>> operate(List<Map<String,String>> oldList);
}
