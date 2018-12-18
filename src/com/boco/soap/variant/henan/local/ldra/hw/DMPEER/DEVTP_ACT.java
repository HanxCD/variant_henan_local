package com.boco.soap.variant.henan.local.ldra.hw.DMPEER;

import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;

/**
 * DEVTP 对应现网取值
 */
public class DEVTP_ACT extends VariantValueInvoke {

	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1, Map<String, ?> data, String arg3) {
		/**
		 * @author Sha Jianwen
		 * @time:2015-11-17 09:47:24 
		 * DMPEER:设备接口能力集 根据设备类型枚举
		 * 用于指定对端设备的设备类型，按照实际情况选择。 
		 * 与PCRF对接时，选择Gx和Rx
		 * 与P-CSCF对接时，选择Rx 
		 * 与PGW对接时，选择Gx 
		 * 与I/S-CSCF对接时，选择Cx
		 * 与TAS对接时，选择Sh 
		 * 与LSP对接时，选择SLg和SLh 
		 * 与BSF对接时，选择Zh
		 * 与MME对接时，选择S6a和SLg 
		 * 与HSS对接时，选择S6a、Cx、Sh、Zh、SLh
		 * 与DRA对接时，选择全部 --《中国移动通信局数据制作规范-JSJ－SB－DRA－HW－V1.0》
		 * 指令参数取值：Gx-1&Rx-1 现网参数：PCEF-PCRF, PCRF-AF/PCRF-CSCF
		 * 现网数据入库时，DEVTP最后带有一个空格，应加入。解析正则(\S+.*)\s+匹配的该参数。
		 */
		Object o = data.get("DEVTYPE");
		String result = null;
		if (null == o) {
			result = "NULL";
		} else {

			String typeString = o.toString().toUpperCase().trim();

			if (typeString.equals("PCRF")) {
				// ZZPCRF01BER
				result = "PCEF-PCRF, PCRF-AF/PCRF-CSCF ";// Gx-1&Rx-1

			} else if (typeString.equals("P-CSCF")) {// ZZPSBC01BHW
				result = "PCRF-AF/PCRF-CSCF ";// Rx-1
			} else if (typeString.equals("PGW")) {// ZZSAEGW01BHW
				result = "PCEF-PCRF ";// Gx-1
			} else if (typeString.equals("I/S-CSCF")) {// LUYICSCF3BZX
				result = "CSCF-HSS/CSCF-SLF ";// Cx/Dx-1
			} else if (typeString.equals("TAS")) {// LUYVOLTETAS3BZX
				result = "AS-HSS/AS-SLF ";// Sh/Dh-1
			} else if (typeString.equals("LSP")) {// ZZLSP01BNK
				result = "HSS-GMLC, MME-GMLC ";// SLg-1&SLh-1
			} else if (typeString.equals("BSF")) {// ZZUIM101BHW
				result = "BSF-HSS ";// Zh-1
			} else if (typeString.equals("MME")) {// ZZVOLTEHSS101FE01BHW
				// ZZMME01BAL-AL:阿尔卡特
				result = "MME-HSS/SGSN-HSS, MME-GMLC ";// S6a/S6d-1&SLg-1
			} else if (typeString.equals("HSS")) {// ZZVOLTEHSS101FE01BHW
				// ZZVOLTEHSS101FE01BHW
				result = "MME-HSS/SGSN-HSS, CSCF-HSS/CSCF-SLF, AS-HSS/AS-SLF, HSS-GMLC, BSF-HSS ";// S6a/S6d-1&Cx/Dx-1&Sh/Dh-1&SLh-1&Zh-1
			} else if (typeString.equals("DRA")) {// ZZVOLTEHSS101FE01BHW
				// ZZDRA01AHW-A
				result = "MME-HSS/SGSN-HSS, PCEF-PCRF, PGW-OCS/CSCF-OCF/AF-OCF, Diameter Agent, PCRF-AF/PCRF-CSCF, PGW-OFCF/CSCF-OFCF/AF-OFCF, CSCF-HSS/CSCF-SLF, AS-HSS/AS-SLF, MME-EIR, vPCRF-hPCRF, PCRF-OCS, MME-GMLC, HSS-GMLC, non-3GPP IP access network-3GPP AAA Server/non-3GPP IP access network-3GPP AAA Proxy, BBERF-PCRF, PDN-3GPP AAA, 3GPP AAA Server-HSS, BSF-HSS, 保留Bit19, 保留Bit20, 保留Bit21, 保留Bit22, 保留Bit23, 保留Bit24, 保留Bit25, 保留Bit26, 保留Bit27, 保留Bit28, 保留Bit29, 保留Bit30, 保留Bit31, 保留Bit32 ";
				// S6a/S6d-1&Gx-1&Gz/Rf-1&Gy/Ro-1&Cx/Dx-1&Sh/Dh-1&Rx-1&S13/S13Bis-1&S9-1&Sy-1&SLg-1&SLh-1&STa-1&Gxx-1&S6b-1&SWx-1&Zh-1&Relay-1&RSVBIT19-1&RSVBIT20-1&RSVBIT21-1&RSVBIT22-1&RSVBIT23-1&RSVBIT24-1&RSVBIT25-1&RSVBIT26-1&RSVBIT27-1&RSVBIT28-1&RSVBIT29-1&RSVBIT30-1&RSVBIT31-1&RSVBIT32-1
			} else {
				result = "NULL";
			}

		}
		// 参数核查时，对比逻辑 选择 “集合”，返回带逗号分隔字符串，不应返回字符串集合(适用动态)。
		// 对比逻辑为“集合”，拆分“项”时，分隔符固定为“,”半角英文逗号。
		return new String[] { result };

	}
}
