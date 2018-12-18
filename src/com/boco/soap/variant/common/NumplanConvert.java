package com.boco.soap.variant.common;

//2017-11-22 wanghao添加移动新增大号段198的转214、212的规则
//2018-03-07 wanghao添加移动新增大号段物联网不含放音功能的14400、14401、14402转214、212的规则
//2018-04-23 wanghao添加移动新增物联网14403转214、212的规则和大号段148的转214、212的规则
//2018-04-24 wanghao添加移动物联网号段10647,10648的转214\212规则

public class NumplanConvert {
    public static String E164ToE214(String number) {
        String strMgt = "";
        if (number.equals("")) {
            return strMgt;
        }

        if (!number.startsWith("861")) {
            return String.format("不支持转换的E164号码{0}，请检查标准数据", new Object[] { number });
        }

        if (number.substring(3, 5).equals("78")) {
            strMgt = "861575" + number.substring(5);
        } else if (number.substring(3, 5).equals("72")) {
            strMgt = "861572" + number.substring(5);
        } else if (number.substring(3, 5).equals("70")) {
            if (number.substring(5, 6).equals("5")) {
                strMgt = "8615705" + number.substring(6);
            } else {
                strMgt = "861570" + number.substring(5);
            }

        } else if (number.substring(3, 5).equals("84")) {
            strMgt = "861384" + number.substring(5);
        } else if (number.substring(3, 5).equals("83")) {
            strMgt = "861385" + number.substring(5);
        } else if (number.substring(3, 5).equals("82")) {
            strMgt = "861386" + number.substring(5);
        } else if (number.substring(3, 5).equals("50")) {
            strMgt = "861383" + number.substring(5);
        } else if ((number.substring(3, 5).equals("57")) || (number.substring(3, 5).equals("88"))) {
            strMgt = "86157" + number.substring(4);
        } else if (number.substring(3, 5).equals("47")) {
            strMgt = "861579" + number.substring(5);
        } else if (number.substring(3, 5).equals("98")) {
            strMgt = "861571" + number.substring(5);
        } else if(number.substring(3, 7).equals("4400")){
        	strMgt = "861372" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4401")){
        	strMgt = "861373" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4402")){
        	strMgt = "861374" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4403")){
        	strMgt = "861375" + number.substring(7);  	
        } else if(number.substring(3, 5).equals("48")){
        	strMgt = "861368" + number.substring(5);  	
        } else if(number.substring(3, 7).equals("0648")){
        	strMgt = "861370" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("0647")){
        	strMgt = "861371" + number.substring(7);  	
        } else if ((number.substring(3, 5).equals("51")) || (number.substring(3, 5).equals("52")) || (number.substring(3, 5).equals("58")) || (number.substring(3, 5).equals("59")) || (number.substring(3, 5).equals("87"))) {
            strMgt = "86138" + number.substring(4);
        } else if (number.substring(3, 5).equals("34")) {
            strMgt = "861380" + number.substring(5);
        } else if ((number.substring(3, 5).equals("35")) || (number.substring(3, 5).equals("36")) || (number.substring(3, 5).equals("37")) || (number.substring(3, 5).equals("38")) || (number.substring(3, 5).equals("39"))) {
            if (number.substring(5, 6).equals("0")) {
                if (number.length() > 9) {
                    strMgt = "86139" + number.substring(6, 9) + number.substring(4, 5) + number.substring(9);
                } else {
                    strMgt = "86139" + number.substring(6, 9) + number.substring(4, 5);
                }
            } else if (number.length() > 9) {
                strMgt = "86139" + number.substring(6, 9) + (Integer.parseInt(number.substring(4, 5)) - 5) + number.substring(5, 6) + number.substring(9);
            } else {
                strMgt = "86139" + number.substring(6, 9) + (Integer.parseInt(number.substring(4, 5)) - 5) + number.substring(5, 6);
            }

        } else if (number.substring(3, 5).equals("30")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "0";
        } else if (number.substring(3, 5).equals("31")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "9";
        } else if (number.substring(3, 5).equals("32")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "2";
        } else if (number.substring(3, 5).equals("45")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "7";
        } else if (number.substring(3, 5).equals("55")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "4";
        } else if (number.substring(3, 5).equals("56")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "3";
        } else if (number.substring(3, 5).equals("76")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "1";
        } else if (number.substring(3, 5).equals("85")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "5";
        } else if (number.substring(3, 5).equals("86")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "6";
        } else {
            strMgt = "E164:" + number + "无法转换为相应的E214号段";
        }
        return strMgt;
    }

    public static String E214ToE164(String number) {
        String strHgt = "";

        if (number.substring(3, 6).equals("575")) {
            strHgt = "86178" + number.substring(6);
        } else if (number.substring(3, 6).equals("570")) {
            if (number.substring(6, 7).equals("5")) {
                strHgt = "861705" + number.substring(7);
            }

        } else if (number.substring(3, 6).equals("384")) {
            strHgt = "86184" + number.substring(6);
        } else if (number.substring(3, 6).equals("385")) {
            strHgt = "86183" + number.substring(6);
        } else if (number.substring(3, 6).equals("386")) {
            strHgt = "86182" + number.substring(6);
        } else if (number.substring(3, 6).equals("383")) {
            strHgt = "86150" + number.substring(6);
        } else if ((number.substring(3, 6).equals("381")) || (number.substring(3, 6).equals("382")) || (number.substring(3, 6).equals("388")) || (number.substring(3, 6).equals("389"))) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(3, 6).equals("387")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(3, 6).equals("577")) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(3, 6).equals("578")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(3, 6).equals("579")) {
            strHgt = "86147" + number.substring(6);
        } else if (number.substring(3, 6).equals("380")) {
            strHgt = "86134" + number.substring(6);
        } else if (number.substring(3, 6).equals("571")) {
            strHgt = "86198" + number.substring(6);
        } else if (number.substring(3, 6).equals("372")) {
            strHgt = "8614400" + number.substring(6);
        } else if (number.substring(3, 6).equals("373")) {
            strHgt = "8614401" + number.substring(6);
        } else if (number.substring(3, 6).equals("374")) {
            strHgt = "8614402" + number.substring(6);
        } else if (number.substring(3, 6).equals("375")) {
            strHgt = "8614403" + number.substring(6);
        } else if (number.substring(3, 6).equals("370")) {
            strHgt = "8610648" + number.substring(6);
        } else if (number.substring(3, 6).equals("371")) {
            strHgt = "8610647" + number.substring(6);
        } else if (number.substring(3, 6).equals("368")) {
            strHgt = "86148" + number.substring(6);
        }

        else if (number.substring(3, 5).equals("39")) {
            if (number.substring(8, 9).equals("0")) {
                if (number.length() > 10) {
                    strHgt = "86135" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86135" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("1")) {
                if (number.length() > 10) {
                    strHgt = "86136" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86136" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("2")) {
                if (number.length() > 10) {
                    strHgt = "86137" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86137" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("3")) {
                if (number.length() > 10) {
                    strHgt = "86138" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86138" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("4")) {
                if (number.length() > 10) {
                    strHgt = "86139" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86139" + number.substring(9, 10) + number.substring(5, 8);
                }
            } else if (number.length() > 9) {
                strHgt = "8613" + number.substring(8, 9) + "0" + number.substring(5, 8) + number.substring(9);
            } else {
                strHgt = "8613" + number.substring(8, 9) + "0" + number.substring(5, 8);
            }
        } else if (number.substring(3, 5).equals("30")) {
            if (number.substring(9, 10).equals("0")) {
                strHgt = "86130" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("1")) {
                strHgt = "86176" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("2")) {
                strHgt = "86132" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("3")) {
                strHgt = "86156" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("4")) {
                strHgt = "86155" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("5")) {
                strHgt = "86185" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("6")) {
                strHgt = "86186" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("7")) {
                strHgt = "86145" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("9")) {
                strHgt = "86131" + number.substring(8, 9) + number.substring(5, 8);
            } else {
                strHgt = "E214:" + number + "无法转换为相应的E164号段";
            }
        }

        return strHgt;
    }

    public static String E164ToE212(String number) {
        String strE212 = "";

        if (!number.startsWith("86")) {
            number = "86" + number;
        }

        if (number.substring(3, 5).equals("78")) {
            strE212 = "460075" + number.substring(5);
        } else if (number.substring(3, 5).equals("70")) {
            if (number.substring(5, 6).equals("5")) {
                strE212 = "4600705" + number.substring(6);
            } else if (number.substring(5, 6).equals("6")) {
                strE212 = "4600706" + number.substring(6);
            } else {
                strE212 = "460070" + number.substring(5);
            }

        } else if (number.substring(3, 5).equals("72")) {
            strE212 = "460072" + number.substring(5);
        } else if (number.substring(3, 5).equals("84")) {
            strE212 = "460024" + number.substring(5);
        } else if (number.substring(3, 5).equals("83")) {
            strE212 = "460025" + number.substring(5);
        } else if (number.substring(3, 5).equals("82")) {
            strE212 = "460026" + number.substring(5);
        } else if (number.substring(3, 5).equals("50")) {
            strE212 = "460023" + number.substring(5);
        } else if (number.substring(3, 5).equals("98")) {
            strE212 = "460071" + number.substring(5);
        } else if (number.substring(3, 7).equals("4400")) {
            strE212 = "460042" + number.substring(7);
        } else if (number.substring(3, 7).equals("4401")) {
            strE212 = "460043" + number.substring(7);
        } else if (number.substring(3, 7).equals("4402")) {
            strE212 = "460044" + number.substring(7);
        } else if (number.substring(3, 7).equals("4403")) {
            strE212 = "460045" + number.substring(7);
        } else if (number.substring(3, 7).equals("0648")) {
            strE212 = "460040" + number.substring(7);
        } else if (number.substring(3, 7).equals("0647")) {
            strE212 = "460041" + number.substring(7);
        } else if (number.substring(3, 5).equals("48")) {
            strE212 = "460138" + number.substring(5);
        } else if ((number.substring(3, 5).equals("51")) || (number.substring(3, 5).equals("52")) || (number.substring(3, 5).equals("58")) || (number.substring(3, 5).equals("59")) || (number.substring(3, 5).equals("87"))) {
            strE212 = "46002" + number.substring(4);
        } else if ((number.substring(3, 5).equals("57")) || (number.substring(3, 5).equals("88"))) {
            strE212 = "46007" + number.substring(4);
        } else if (number.substring(3, 5).equals("47")) {
            strE212 = "460079" + number.substring(5);
        } else if (number.substring(3, 5).equals("34")) {
            strE212 = "460020" + number.substring(5);
        } else if ((number.substring(3, 5).equals("35")) || (number.substring(3, 5).equals("36")) || (number.substring(3, 5).equals("37")) || (number.substring(3, 5).equals("38")) || (number.substring(3, 5).equals("39"))) {
            if (number.substring(5, 6).equals("0")) {
                if (number.length() > 9) {
                    strE212 = "46000" + number.substring(6, 9) + number.substring(4, 5) + number.substring(9);
                } else {
                    strE212 = "46000" + number.substring(6, 9) + number.substring(4, 5);
                }
            } else if (number.length() > 9) {
                strE212 = "46000" + number.substring(6, 9) + (Integer.parseInt(number.substring(4, 5)) - 5) + number.substring(5, 6) + number.substring(9);
            } else {
                strE212 = "46000" + number.substring(6, 9) + (Integer.parseInt(number.substring(4, 5)) - 5) + number.substring(5, 6);
            }

        } else if (number.substring(3, 5).equals("30")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "0";
        } else if (number.substring(3, 5).equals("31")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "9";
        } else if (number.substring(3, 5).equals("32")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "2";
        } else if (number.substring(3, 5).equals("45")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "7";
        } else if (number.substring(3, 5).equals("55")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "4";
        } else if (number.substring(3, 5).equals("56")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "3";
        } else if (number.substring(3, 5).equals("76")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "1";
        } else if (number.substring(3, 5).equals("85")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "5";
        } else if (number.substring(3, 5).equals("86")) {
            strE212 = "46001" + number.substring(6, 9) + number.substring(5, 6) + "6";
        } else {
            strE212 = "E164:" + number + "无法转换为相应的E212号段";
        }

        return strE212;
    }

    public static String E212ToE164(String number) {
        String strHgt = "";

        if (number.substring(4, 6).equals("75")) {
            strHgt = "86178" + number.substring(6);
        } else if (number.substring(4, 6).equals("70")) {
            if (number.substring(6, 7).equals("5")) {
                strHgt = "861705" + number.substring(7);
            }

        } else if (number.substring(4, 6).equals("24")) {
            strHgt = "86184" + number.substring(6);
        } else if (number.substring(4, 6).equals("25")) {
            strHgt = "86183" + number.substring(6);
        } else if (number.substring(4, 6).equals("26")) {
            strHgt = "86182" + number.substring(6);
        } else if (number.substring(4, 6).equals("23")) {
            strHgt = "86150" + number.substring(6);
        } else if (number.substring(3, 6).equals("571")) {
            strHgt = "86198" + number.substring(6);
        } else if (number.substring(3, 6).equals("372")) {
            strHgt = "8614400" + number.substring(6);
        } else if (number.substring(3, 6).equals("373")) {
            strHgt = "8614401" + number.substring(6);
        } else if (number.substring(3, 6).equals("374")) {
            strHgt = "8614402" + number.substring(6);
        } else if (number.substring(3, 6).equals("375")) {
            strHgt = "8614403" + number.substring(6);
        } else if (number.substring(3, 6).equals("370")) {
            strHgt = "8610648" + number.substring(6);
        } else if (number.substring(3, 6).equals("371")) {
            strHgt = "8610647" + number.substring(6);
        } else if (number.substring(3, 6).equals("368")) {
            strHgt = "86148" + number.substring(6);
        } else if ((number.substring(4, 6).equals("21")) || (number.substring(4, 6).equals("22")) || (number.substring(4, 6).equals("28")) || (number.substring(4, 6).equals("29"))) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(4, 6).equals("27")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(4, 6).equals("77")) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(4, 6).equals("78")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(4, 6).equals("79")) {
            strHgt = "86147" + number.substring(6);
        } else if (number.substring(4, 6).equals("20")) {
            strHgt = "86134" + number.substring(6);
        } else if (number.substring(4, 5).equals("0")) {
            if (number.substring(8, 9).equals("0")) {
                if (number.length() > 10) {
                    strHgt = "86135" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86135" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("1")) {
                if (number.length() > 10) {
                    strHgt = "86136" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86136" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("2")) {
                if (number.length() > 10) {
                    strHgt = "86137" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86137" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("3")) {
                if (number.length() > 10) {
                    strHgt = "86138" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86138" + number.substring(9, 10) + number.substring(5, 8);
                }

            } else if (number.substring(8, 9).equals("4")) {
                if (number.length() > 10) {
                    strHgt = "86139" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                } else {
                    strHgt = "86139" + number.substring(9, 10) + number.substring(5, 8);
                }
            } else if (number.length() > 9) {
                strHgt = "8613" + number.substring(8, 9) + "0" + number.substring(5, 8) + number.substring(9);
            } else {
                strHgt = "8613" + number.substring(8, 9) + "0" + number.substring(5, 8);
            }
        } else if (number.substring(4, 5).equals("1")) {
            if (number.substring(9, 10).equals("0")) {
                strHgt = "86130" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("1")) {
                strHgt = "86176" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("2")) {
                strHgt = "86132" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("3")) {
                strHgt = "86156" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("4")) {
                strHgt = "86155" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("5")) {
                strHgt = "86185" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("6")) {
                strHgt = "86186" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("7")) {
                strHgt = "86145" + number.substring(8, 9) + number.substring(5, 8);
            } else if (number.substring(9, 10).equals("9")) {
                strHgt = "86131" + number.substring(8, 9) + number.substring(5, 8);
            }

        }

        return strHgt;
    }

    public static String HwE164ToE214(String number) {
        String strMgt = "";

        String Np1Gt = number;

        if ((number.length() < 9) && (!number.substring(3, 5).equals("34")) && (!number.substring(3, 5).equals("50")) && (!number.substring(3, 5).equals("51")) && (!number.substring(3, 5).equals("52")) && (!number.substring(3, 5).equals("57")) && (!number.substring(3, 5).equals("58")) && (!number.substring(3, 5).equals("59")) && (!number.substring(3, 5).equals("87")) && (!number.substring(3, 5).equals("88")) && (!number.substring(3, 5).equals("47")) && (!number.substring(3, 5).equals("83"))) {
            int count = 9 - number.length();
            for (int i = 0; i < count; i++) {
                Np1Gt = Np1Gt + "E";
            }

        }

        if (number.substring(3, 5).equals("78")) {
            strMgt = "861575" + number.substring(5);
        } else if (number.substring(3, 5).equals("84")) {
            strMgt = "861384" + number.substring(5);
        } else if (number.substring(3, 5).equals("83")) {
            strMgt = "861385" + number.substring(5);
        } else if (number.substring(3, 5).equals("82")) {
            strMgt = "861386" + number.substring(5);
        } else if (number.substring(3, 5).equals("50")) {
            strMgt = "861383" + number.substring(5);
        } else if (number.substring(3, 5).equals("98")) {
            strMgt = "861571" + number.substring(5);
        } else if(number.substring(3, 7).equals("4400")){
        	strMgt = "861372" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4401")){
        	strMgt = "861373" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4402")){
        	strMgt = "861374" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("4403")){
        	strMgt = "861375" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("0648")){
        	strMgt = "861370" + number.substring(7);  	
        } else if(number.substring(3, 7).equals("0647")){
        	strMgt = "861371" + number.substring(7);  	
        } else if(number.substring(3, 5).equals("48")){
        	strMgt = "861368" + number.substring(5);  	
        } else if ((number.substring(3, 5).equals("51")) || (number.substring(3, 5).equals("52")) || (number.substring(3, 5).equals("58")) || (number.substring(3, 5).equals("59")) || (number.substring(3, 5).equals("87"))) {
            strMgt = "86138" + number.substring(4);
        } else if ((number.substring(3, 5).equals("57")) || (number.substring(3, 5).equals("88"))) {
            strMgt = "86157" + number.substring(4);
        } else if (number.substring(3, 5).equals("47")) {
            strMgt = "861579" + number.substring(5);
        } else if (number.substring(3, 5).equals("34")) {
            strMgt = "861380" + number.substring(5);
        } else if ((number.substring(3, 5).equals("35")) || (number.substring(3, 5).equals("36")) || (number.substring(3, 5).equals("37")) || (number.substring(3, 5).equals("38")) || (number.substring(3, 5).equals("39"))) {
            if (number.substring(5, 6).equals("0")) {
                if (number.length() > 9) {
                    strMgt = "86139" + Np1Gt.substring(6, 9) + Np1Gt.substring(4, 5) + Np1Gt.substring(9);
                } else {
                    strMgt = "86139" + Np1Gt.substring(6, 9) + Np1Gt.substring(4, 5);
                }
            } else if (number.length() > 9) {
                strMgt = "86139" + Np1Gt.substring(6, 9) + (Integer.parseInt(Np1Gt.substring(4, 5)) - 5) + Np1Gt.substring(5, 6) + Np1Gt.substring(9);
            } else {
                strMgt = "86139" + Np1Gt.substring(6, 9) + (Integer.parseInt(Np1Gt.substring(4, 5)) - 5) + Np1Gt.substring(5, 6);
            }

        } else if (number.substring(3, 5).equals("30")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "0";
        } else if (number.substring(3, 5).equals("31")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "9";
        } else if (number.substring(3, 5).equals("32")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "2";
        } else if (number.substring(3, 5).equals("45")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "7";
        } else if (number.substring(3, 5).equals("55")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "4";
        } else if (number.substring(3, 5).equals("56")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "3";
        } else if (number.substring(3, 5).equals("76")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "1";
        } else if (number.substring(3, 5).equals("85")) {
            strMgt = "86130" + Np1Gt.substring(6, 9) + Np1Gt.substring(5, 6) + "5";
        } else if (number.substring(3, 5).equals("86")) {
            strMgt = "86130" + number.substring(6, 9) + number.substring(5, 6) + "6";
        }

        return strMgt;
    }

    public static String HwE214ToE164(String number) {
        String strHgt = "";

        if (number.substring(3, 6).equals("575")) {
            strHgt = "86178" + number.substring(6);
        } else if (number.substring(3, 6).equals("384")) {
            strHgt = "86184" + number.substring(6);
        } else if (number.substring(3, 6).equals("385")) {
            strHgt = "86183" + number.substring(6);
        } else if (number.substring(3, 6).equals("386")) {
            strHgt = "86182" + number.substring(6);
        } else if (number.substring(3, 6).equals("383")) {
            strHgt = "86150" + number.substring(6);
        } else if ((number.substring(3, 6).equals("381")) || (number.substring(3, 6).equals("382")) || (number.substring(3, 6).equals("388")) || (number.substring(3, 6).equals("389"))) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(3, 6).equals("387")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(3, 6).equals("577")) {
            strHgt = "8615" + number.substring(5);
        } else if (number.substring(3, 6).equals("578")) {
            strHgt = "8618" + number.substring(5);
        } else if (number.substring(3, 6).equals("579")) {
            strHgt = "86147" + number.substring(6);
        } else if (number.substring(3, 6).equals("571")) {
            strHgt = "86198" + number.substring(6);
        } else if (number.substring(3, 6).equals("372")) {
            strHgt = "8614400" + number.substring(6);
        } else if (number.substring(3, 6).equals("373")) {
            strHgt = "8614401" + number.substring(6);
        } else if (number.substring(3, 6).equals("374")) {
            strHgt = "8614402" + number.substring(6);
        } else if (number.substring(3, 6).equals("375")) {
            strHgt = "8614403" + number.substring(6);
        } else if (number.substring(3, 6).equals("370")) {
            strHgt = "8610648" + number.substring(6);
        } else if (number.substring(3, 6).equals("371")) {
            strHgt = "8610647" + number.substring(6);
        } else if (number.substring(3, 6).equals("368")) {
            strHgt = "86148" + number.substring(6);
        } else if (number.substring(3, 6).equals("380")) {
            strHgt = "86134" + number.substring(6);
        } else {
            if (number.substring(3, 5).equals("39")) {
                if (number.substring(8, 9).equals("0")) {
                    if (number.length() > 10) {
                        strHgt = "86135" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                    } else {
                        strHgt = ("86135" + number.substring(9, 10) + number.substring(5, 8)).replace("E", "");
                    }

                } else if (number.substring(8, 9).equals("1")) {
                    if (number.length() > 10) {
                        strHgt = "86136" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                    } else {
                        strHgt = ("86136" + number.substring(9, 10) + number.substring(5, 8)).replace("E", "");
                    }

                } else if (number.substring(8, 9).equals("2")) {
                    if (number.length() > 10) {
                        strHgt = "86137" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                    } else {
                        strHgt = ("86137" + number.substring(9, 10) + number.substring(5, 8)).replace("E", "");
                    }

                } else if (number.substring(8, 9).equals("3")) {
                    if (number.length() > 10) {
                        strHgt = "86138" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                    } else {
                        strHgt = ("86138" + number.substring(9, 10) + number.substring(5, 8)).replace("E", "");
                    }

                } else if (number.substring(8, 9).equals("4")) {
                    if (number.length() > 10) {
                        strHgt = "86139" + number.substring(9, 10) + number.substring(5, 8) + number.substring(10);
                    } else {
                        strHgt = ("86139" + number.substring(9, 10) + number.substring(5, 8)).replace("E", "");
                    }
                } else if (number.length() > 9) {
                    strHgt = "8613" + number.substring(8, 9) + "0" + number.substring(5, 8) + number.substring(9);
                } else {
                    strHgt = ("8613" + number.substring(8, 9) + "0" + number.substring(5, 8)).replace("E", "");
                }
            }

            if (number.substring(3, 5).equals("30")) {
                number = number.replace("e", "E");

                if (number.substring(9, 10).equals("0")) {
                    strHgt = "86130" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("1")) {
                    strHgt = "86176" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("2")) {
                    strHgt = "86132" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("3")) {
                    strHgt = "86156" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("4")) {
                    strHgt = "86155" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("5")) {
                    strHgt = "86185" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("6")) {
                    strHgt = "86186" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("7")) {
                    strHgt = "86145" + number.substring(8, 9) + number.substring(5, 8);
                } else if (number.substring(9, 10).equals("9")) {
                    strHgt = "86131" + number.substring(8, 9) + number.substring(5, 8);
                }

                strHgt = strHgt.replace("E", "");
            }
        }
        return strHgt;
    }

    public static String E212ToE214(String number) {
        return number.replace("46001", "86130");
    }
}