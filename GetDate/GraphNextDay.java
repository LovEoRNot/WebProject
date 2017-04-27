package com.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GraphicInterFace {
	private GetDay gd = new GetDay();
	
	public  GraphicInterFace() {
		drawPicture();
	}
	
	//���ƽ���
	public void drawPicture() {
		JFrame frame = new JFrame("���ڼ������");
		frame.setLayout(null);
		/*���ֱ�ǩ��*/
		JLabel title = new JLabel("��������Ҫ�����������(1912-2050֮�䣩");
		JLabel year = new JLabel("�꣺");
		JLabel month = new JLabel("�£�");
		JLabel day = new JLabel("�գ�");
		JLabel thisWeek = new JLabel("��һ��������");
		JLabel nextDay = new JLabel("��һ���ǣ�");
		JLabel prevDay = new JLabel("��һ���ǣ�");	
		/*��������������*/
		JTextField t_year = new JTextField();
		JTextField t_month = new JTextField();
		JTextField t_day = new JTextField();
		JTextField t_thisWeek = new JTextField();
		JTextField t_nextDay = new JTextField();
		JTextField t_prevDay = new JTextField();		
		/*��ť��*/
		JButton submit = new JButton("ȷ��");
		JButton cancel = new JButton("���");
		
		//ʹ�����������ڲ���Ϊsubmit��ť��Ӽ����¼�
		submit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//�жϴ���Դ�Ƿ�Ϊsubmit��ť
				if(e.getSource() == submit) {
					//�õ�������꣬�£��գ����ص���String �ַ�������
					String y = t_year.getText();
					String m = t_month.getText();
					String d = t_day.getText();
					//�ѻ�õ��ַ���ת������
					int i_y = Integer.parseInt(y);
					int i_m = Integer.parseInt(m);
					int i_d = Integer.parseInt(d);
					//�����һ��
					String nextD = gd.nextDate(i_m, i_d, i_y);
					t_nextDay.setText(nextD);
					//���ǰһ��
					String prevD = gd.lastDay(i_m, i_d, i_y);
					t_prevDay.setText(prevD);
					//�������ǰ�����ڼ�
					String week = gd.todayWeek(i_m, i_d, i_y);
					t_thisWeek.setText(week);
				}	
				
			}
		});
		//ʹ�����������ڲ���Ϊcancel��ť��Ӽ����¼�
		cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//������������
				if(e.getSource() == cancel) {
					t_year.setText("");
					t_month.setText("");
					t_day.setText("");
				}
				
			}
		});
			
		/*���ñ�ǩλ�ü���С*/
		Font ft = new Font("����",Font.BOLD,24);//������ʾ����
		title.setFont(ft);
		title.setBounds(62, 62, 500, 25);
		year.setFont(ft);
		year.setBounds(80, 124, 50, 25);
		month.setFont(ft);
		month.setBounds(230, 124, 50, 25);
		day.setFont(ft);
		day.setBounds(380, 124, 50, 25);
		thisWeek.setFont(ft);
		thisWeek.setBounds(62, 248, 200, 25);
		prevDay.setFont(ft);
		prevDay.setBounds(62, 372, 150, 25);
		nextDay.setFont(ft);
		nextDay.setBounds(62, 310, 150, 25);
		/*��������λ�ü���С*/
		t_year.setBounds(130, 124, 50, 25);
		t_month.setBounds(280, 124, 50, 25);
		t_day.setBounds(430, 124, 50, 25);
		t_thisWeek.setBounds(230, 248, 50, 25);
		t_thisWeek.setFont(ft);
		t_thisWeek.setEnabled(false);//����Ϊ�޷�����
		t_nextDay.setBounds(180, 310, 200, 25);
		t_nextDay.setFont(ft);
		t_nextDay.setEnabled(false);//����Ϊ�޷�����
		t_prevDay.setBounds(180, 372, 200, 25);
		t_prevDay.setFont(ft);
		t_prevDay.setEnabled(false);//����Ϊ�޷�����	
		/*���ð�ťλ�ü���С*/
		submit.setBounds(180, 187, 62, 31);
		cancel.setBounds(320, 187, 62, 31);		
		//�����������ӵ�frame��	
		frame.add(title);frame.add(year);frame.add(month);frame.add(day);
		frame.add(thisWeek);frame.add(nextDay);frame.add(prevDay);
		frame.add(t_year);frame.add(t_month);frame.add(t_day);
		frame.add(t_thisWeek);frame.add(t_nextDay);frame.add(t_prevDay);
		frame.add(submit);frame.add(cancel);
		/*������ʾ������С����ʾ��λ��*/
		frame.setSize(600, 490);
		frame.setLocation(300,300);
		frame.setVisible(true);	
	}

}
public class GraphNextDay {

	public static void main(String[] args) {
		new GraphicInterFace();

	}

}
