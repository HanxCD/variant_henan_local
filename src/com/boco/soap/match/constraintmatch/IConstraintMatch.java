package com.boco.soap.match.constraintmatch;

import java.util.List;
import java.util.Map;

import com.boco.soap.common.pojo.INeElement;

public abstract interface IConstraintMatch {
    public abstract List<Map<String, String>> match(INeElement paramINeElement, List<Map<String, String>> paramList, String paramString);
}