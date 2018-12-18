package com.boco.soap.variant.henan.local.ldra.hw.DMLNK;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

public class LKSNAME1 extends VariantValueInvoke {

	/**
	 * @author 
	 * @time 2015-11-18 10:23:28 
	 * 链路集名称 
	 * 执行命令时，不区分大小写。
	 */
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
        //ZZSAEGW09BER-c0-34_LKS    ZZPCRF07BNK
		//ZZSAEGW09BER-c1-34_LKS
		Object o = data.get("DN");
		String ip=data.get("PIP1").toString().split("\\.")[3];
		String result = "";
		String name="";
		if (null == o) {
			result = null;
		} else {
			for(int i=0;i<15;i++){
				name+=","+ o.toString().trim()+"-c"+i+ "-"+ip+"_LKS";
			}
			if (name.length()>1) {
               return name.substring(1).split(",");
			//	return name.split(",");
			}
		}
		//ADD DMLNK: LNKNAME=@Value#{LNKNAME}, LKSNAME=@Value#{LKSNAME},MID=@Value#{MID}, PTYPE=@Value#{PTYPE}, WMODE=@Value#{WMODE}, IPTP=@Value#{IPTP}, ADDRID1=@Value#{ADDRID1}, <nullable #{ADDRID2}>ADDRID2=@Value#{ADDRID2}, </nullable>LPORT=@Value#{LPORT}, <nullable #{REGPORTFLAG}>REGPORTFLAG=@Value#{REGPORTFLAG}, </nullable><nullable #{REGPORT}>REGPORT=@Value#{REGPORT}, </nullable>@Value#{PIP1}, <nullable #{PIP2}>@Value#{PIP2}, </nullable>PPORT=@Value#{PPORT}, SCTPPARANAME="Default", MOG="PUBLIC";
		//ADD DMLNK: LNKNAME="ZZSAEGW09BER-c0-35_LNK01",LKSNAME="ZZSAEGW09BER-c0-35_LKS",SHARELNK=NO,MID=6,PTYPE=TCP,WMODE=SERVER,IPTP=IPV4,ADDRID1="0-1-Signal-IP1",LPORT=9002,REGPORTFLAG=YES,REGPORT=9000,PIP41="10.91.108.35",PPORT=0,LCON=80,LCOFF=60,MOG="PUBLIC"; 
		//ADD DMLNK: LNKNAME="ZZPCRF04BNK-c0-99_LNK01", LKSNAME="ZZPCRF04BNK-c0-99_LKS",MID=, PTYPE=SCTP, WMODE=CLIENT, IPTP=IPv4, ADDRID1="0-1-Signal-IP1", ADDRID2="0-3-Signal-IP1", LPORT=NULL, PIP41="NULL", PPORT=NULL, SCTPPARANAME="Default", MOG="PUBLIC";
		
		return new String[] { name};
	}
}
