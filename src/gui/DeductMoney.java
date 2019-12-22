package gui;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.nio.charset.Charset;

public class DeductMoney extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		DeductMoney deductMoney=new DeductMoney();
		Panel panel=new Panel();
		Label label=new Label();
		label.setVisible(true);
		Charset charset=Charset.forName("utf-8");
		label.setText(new String("数据1".getBytes(),charset));
		panel.add(label);
		TextArea area=new TextArea();
		panel.add(area);
		deductMoney.add(panel);
		deductMoney.setSize(700, 500);
		deductMoney.setVisible(true);
		
	}

}
