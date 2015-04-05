package cn.fantasymaker.numtormb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class Gui {

	private static Gui gui = null;
	private String inputString;
	private BigDecimal bDecimal;
	private double num;
	private NumToRmb ntb = new NumToRmb();
	private String resultTextString;

	private Gui() {

	}

	public static Gui getInstance() {
		if (gui == null) {
			gui = new Gui();
		}
		return gui;
	}

	public void init() {
		// frame����
		Frame frame = new Frame("��д����ҽ��ת����");
		frame.setBounds(0, 0, 350, 103);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		// ��ʾ��ǩ����
		Label tipLabel = new Label("�����ı��������밢�������ֽ���ߵ��ڣ����2λС����", Label.LEFT);

		// �����ǩ����
		Label resultlabel = new Label();
		resultlabel.setName("showLabel");
		resultlabel.setBackground(Color.LIGHT_GRAY);

		// �ı�������
		TextField textField = new TextField(100);
		textField.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				inputString = textField.getText();
				if (!inputString.isEmpty()) {
					try {
						bDecimal = new BigDecimal(inputString);
						num = bDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();

						resultTextString = ntb.numToRMB(num);
						resultlabel.setText(resultTextString);
					} catch (ArrayIndexOutOfBoundsException aioobe) {
						resultlabel.setText("���ֹ������������롣");
					} catch (NumberFormatException nfe) {
						resultlabel.setText("������������������������롣");
					}
				} else {
					resultlabel.setText("");
				}
			}
		});

		// ����������ʾ
		frame.add(tipLabel, BorderLayout.NORTH);
		frame.add(textField, BorderLayout.CENTER);
		frame.add(resultlabel, BorderLayout.SOUTH);
		frame.setVisible(true);

	}
}
