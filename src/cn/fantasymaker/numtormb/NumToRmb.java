package cn.fantasymaker.numtormb;

import java.math.BigDecimal;

public class NumToRmb {

	private String[] unitStrArray = { "", "ʰ", "��", "Ǫ" };
	private String[] numStrArray = { "��", "Ҽ", "��", "��", "��", "��", "½", "��",
			"��", "��" };

	private String getRMBIntPart(double dNum) {
		String rMBIntPart = "";
		int i = 0, weiShu;
		int intNum = (int) dNum;
		int yiNum = intNum / 100000000;
		int wanNum = (intNum - (yiNum * 100000000)) / 10000;
		int yuanNum = intNum - (yiNum * 100000000) - (wanNum * 10000);

		// ��3���ִ������֣�ÿ���ּ����ڣ���Ԫ
		// yi
		if (yiNum != 0) {
			rMBIntPart = rMBIntPart + numStrArray[yiNum] + "��";
		}
		
		// wan
		if(wanNum != 0){
			for (i = 3; i >= 0; i--) {
				weiShu = wanNum / (int) Math.pow(10, i);
				if (weiShu == 0) {
					rMBIntPart = rMBIntPart + numStrArray[weiShu];
				} else {
					rMBIntPart = rMBIntPart + numStrArray[weiShu] + unitStrArray[i];
				}
				wanNum = wanNum - (weiShu * (int) Math.pow(10, i));
			}
			// ȥ���ظ�����
			while (rMBIntPart.contains("����")) {
				rMBIntPart = rMBIntPart.replaceAll("����", "��");
			}
			// ȥ����β����
			if (rMBIntPart.endsWith("��")) {
				rMBIntPart = rMBIntPart.substring(0, rMBIntPart.length() - 1);
			}
			rMBIntPart = rMBIntPart + "��";
		}

		// yuan
		if(yuanNum != 0){
			for (i = 3; i >= 0; i--) {
				weiShu = yuanNum / (int) Math.pow(10, i);
				if (weiShu == 0) {
					rMBIntPart = rMBIntPart + numStrArray[weiShu];
				} else {
					rMBIntPart = rMBIntPart + numStrArray[weiShu] + unitStrArray[i];
				}
				yuanNum = yuanNum - (weiShu * (int) Math.pow(10, i));
			}
			// ȥ���ظ�����
			while (rMBIntPart.contains("����")) {
				rMBIntPart = rMBIntPart.replaceAll("����", "��");
			}
			// ȥ����β����
			if (rMBIntPart.endsWith("��")) {
				rMBIntPart = rMBIntPart.substring(0, rMBIntPart.length() - 1);
			}
			rMBIntPart = rMBIntPart + "Ԫ";
		}

		// ȥ�����ַ�����ͷ����
		if (rMBIntPart.startsWith("��")) {
			rMBIntPart = rMBIntPart.substring(1, rMBIntPart.length());
		}

		return rMBIntPart;
	}

	private String getRMBDecPart(double dNum) {
		String rMBDecPart = "";
		double xiaoShu = dNum - Math.floor(dNum);
		// StringתBigDecimal��תDouble����߾���
		String temp = Double.toString(xiaoShu);
		BigDecimal bDecimal = new BigDecimal(temp);
		xiaoShu = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if (xiaoShu == 0) {
			rMBDecPart = "��";
			return rMBDecPart;
		}
		rMBDecPart = numStrArray[(int) ((xiaoShu * 100) / 10)] + "��";
		int fen = (int) ((xiaoShu * 100) - ((int) (xiaoShu * 100) / 10 * 10));
		if (fen != 0) {
			rMBDecPart = rMBDecPart
					+ numStrArray[(int) ((xiaoShu * 100) - ((int) (xiaoShu * 100) / 10 * 10))]
					+ "��";
		}
		return rMBDecPart;
	}

	public String numToRMB(double num) {
		String rMBResult = "�����" + getRMBIntPart(num) + getRMBDecPart(num);
		return rMBResult;
	}
}
