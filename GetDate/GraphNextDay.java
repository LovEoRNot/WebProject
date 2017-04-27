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
	
	//绘制界面
	public void drawPicture() {
		JFrame frame = new JFrame("日期计算程序");
		frame.setLayout(null);
		/*文字标签组*/
		JLabel title = new JLabel("请输入需要计算的年月日(1912-2050之间）");
		JLabel year = new JLabel("年：");
		JLabel month = new JLabel("月：");
		JLabel day = new JLabel("日：");
		JLabel thisWeek = new JLabel("这一天是星期");
		JLabel nextDay = new JLabel("下一天是：");
		JLabel prevDay = new JLabel("上一天是：");	
		/*文字输入区域组*/
		JTextField t_year = new JTextField();
		JTextField t_month = new JTextField();
		JTextField t_day = new JTextField();
		JTextField t_thisWeek = new JTextField();
		JTextField t_nextDay = new JTextField();
		JTextField t_prevDay = new JTextField();		
		/*按钮组*/
		JButton submit = new JButton("确定");
		JButton cancel = new JButton("清空");
		
		//使用匿名匿名内部类为submit按钮添加监听事件
		submit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//判断触发源是否为submit按钮
				if(e.getSource() == submit) {
					//得到输入的年，月，日，返回的是String 字符串类型
					String y = t_year.getText();
					String m = t_month.getText();
					String d = t_day.getText();
					//把获得的字符串转成数字
					int i_y = Integer.parseInt(y);
					int i_m = Integer.parseInt(m);
					int i_d = Integer.parseInt(d);
					//输出后一天
					String nextD = gd.nextDate(i_m, i_d, i_y);
					t_nextDay.setText(nextD);
					//输出前一天
					String prevD = gd.lastDay(i_m, i_d, i_y);
					t_prevDay.setText(prevD);
					//输入出当前是星期几
					String week = gd.todayWeek(i_m, i_d, i_y);
					t_thisWeek.setText(week);
				}	
				
			}
		});
		//使用匿名匿名内部类为cancel按钮添加监听事件
		cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//清空输入的数据
				if(e.getSource() == cancel) {
					t_year.setText("");
					t_month.setText("");
					t_day.setText("");
				}
				
			}
		});
			
		/*设置标签位置及大小*/
		Font ft = new Font("黑体",Font.BOLD,24);//设置显示字体
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
		/*设置文字位置及大小*/
		t_year.setBounds(130, 124, 50, 25);
		t_month.setBounds(280, 124, 50, 25);
		t_day.setBounds(430, 124, 50, 25);
		t_thisWeek.setBounds(230, 248, 50, 25);
		t_thisWeek.setFont(ft);
		t_thisWeek.setEnabled(false);//设置为无法输入
		t_nextDay.setBounds(180, 310, 200, 25);
		t_nextDay.setFont(ft);
		t_nextDay.setEnabled(false);//设置为无法输入
		t_prevDay.setBounds(180, 372, 200, 25);
		t_prevDay.setFont(ft);
		t_prevDay.setEnabled(false);//设置为无法输入	
		/*设置按钮位置及大小*/
		submit.setBounds(180, 187, 62, 31);
		cancel.setBounds(320, 187, 62, 31);		
		//把所有组件添加到frame中	
		frame.add(title);frame.add(year);frame.add(month);frame.add(day);
		frame.add(thisWeek);frame.add(nextDay);frame.add(prevDay);
		frame.add(t_year);frame.add(t_month);frame.add(t_day);
		frame.add(t_thisWeek);frame.add(t_nextDay);frame.add(t_prevDay);
		frame.add(submit);frame.add(cancel);
		/*设置显示画布大小及显示的位置*/
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
