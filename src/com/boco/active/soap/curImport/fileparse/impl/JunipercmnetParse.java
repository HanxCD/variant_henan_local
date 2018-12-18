package com.boco.active.soap.curImport.fileparse.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.active.soap.curImport.fileparse.AbstructFileParse;
import com.boco.active.soap.curImport.fileparse.IFileParse;
import com.boco.active.soap.curImport.fileparse.exception.FileParseException;
import com.boco.active.soap.curImport.fileparse.pojo.AbstractParseConfig;
import com.boco.active.soap.curImport.fileparse.pojo.SingleTableSingleLineConfig;
import com.boco.active.soap.curImport.fileparse.pojo.StrategyContext;

public class JunipercmnetParse extends AbstructFileParse implements IFileParse {
    private static final Logger log = LoggerFactory.getLogger(JunipercmnetParse.class);
    private final SingleTableSingleLineConfig parseConfig;

    public JunipercmnetParse(AbstractParseConfig config) {
        this.parseConfig = (SingleTableSingleLineConfig) config;
    }

    @Override
    public List<Map<String, String>> parse(String filePath) {
        StrategyContext contexts = this.parseConfig.getStrategyContext();
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        FileInputStream in = null;
        InputStreamReader reader = null;
        try {
            in = new FileInputStream(filePath);
            int index = in.available();
            byte[] abyte0 = new byte[index];

            in.read(abyte0);
            String localDataStr = new String(abyte0, "UTF-8");
            Perl5Matcher matcher = new Perl5Matcher();
            Pattern pattern = new Perl5Compiler().compile(contexts.getPatternStr());
            PatternMatcherInput matcherInput = new PatternMatcherInput(localDataStr);
            while (matcher.contains(matcherInput, pattern)) {
                MatchResult matchResult = matcher.getMatch();
                resultList.add(this.getSingleTableValue(matchResult, contexts.getParseItems().getParseItems()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new FileParseException(e.getMessage());
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resultList = this.splitData(resultList, this.parseConfig.getSplites());
        return resultList;
    }

    public static void main(String[] args) throws MalformedPatternException {
        Perl5Matcher matcher = new Perl5Matcher();
        Perl5Compiler compiler = new Perl5Compiler();
        PatternMatcherInput matcherInput = new PatternMatcherInput("1.85.5.74/32");
        Pattern pattern = compiler.compile("\\s*(.*?)");
        if (matcher.contains(matcherInput, pattern)) {
            System.out.println("ok");
        }
    }
}
