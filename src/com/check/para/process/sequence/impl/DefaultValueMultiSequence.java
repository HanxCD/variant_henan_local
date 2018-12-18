package com.check.para.process.sequence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.check.instruction.pojo.checkdata.ICheckData;
import com.check.instruction.pojo.solution.IValueSequence;

public class DefaultValueMultiSequence implements IValueSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultValueMultiSequence.class);
    private Map<String, IValueSequence> sequences = new HashMap();

    private int step = 0;
    private final String formatLength;
    private int maxStep = 0;
    private String groupName;
    private int defaultValue = 0;
    private List sequenceList = new ArrayList();

    public DefaultValueMultiSequence(int step, String formatLength, Map<String, IValueSequence> sequences, int maxStep, int defaultValue) {
        this.step = step;
        this.maxStep = maxStep;

        this.formatLength = formatLength;
        this.sequences = sequences;
        this.defaultValue = defaultValue;
    }

    public DefaultValueMultiSequence(int step, List sequenceList, int defaultValue) {
        // TODO Auto-generated constructor stub
        this.step = step;
        this.sequenceList = sequenceList;
        this.defaultValue = defaultValue;
        this.formatLength = "";
    }

    @Override
    public synchronized String getNextValue(String nameIndex, Map<String, Map<String, IValueSequence>> groupInf, String groupName, Map<String, IValueSequence> sequences, ICheckData checkDataStd) {
        String result = "";
        /*LOGGER.error("sequenceList-----------------------");
        LOGGER.error(this.sequenceList.toString());*/
        if ((this.sequenceList != null) && (this.sequenceList.size() > 0)) {
            int currentValue = Integer.valueOf(String.valueOf(this.sequenceList.get(0)));
            while (this.sequenceList.contains(currentValue + "")) {
                currentValue++;
            }
            result = currentValue + "";
            int preIndex = this.sequenceList.indexOf(String.valueOf(currentValue - 1));
            this.sequenceList.subList(0, preIndex).clear();
            this.sequenceList.set(0, result);
        } else {
            result = ++this.defaultValue + "";
        }
        return result;
    }

    @Override
    public int getMaxStep() {
        return this.maxStep;
    }

    @Override
    public Map<String, IValueSequence> getSequences() {
        return this.sequences;
    }

    @Override
    public int getStep() {
        return this.step;
    }

    @Override
    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    @Override
    public void setSequences(Map<String, IValueSequence> sequences) {
        this.sequences = sequences;
    }

    @Override
    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String getInit() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public void setInit(String init) {
    }

    @Override
    public void setPrefix(String prefix) {
    }

    @Override
    public String getGroupName() {
        return this.groupName;
    }

    @Override
    public void setGroupName(String groupName) {
    }
}
