package com.test;

public class GetDay {
    /*闰年判断
     * 返回值为true：该年为闰年
     * 返回值为false：该年为平年
    */
    public boolean isLeapYear(int y) {
        if((y % 4 == 0 && y % 100 != 0)||y % 400 == 0)
            return true;
        else
            return false;
    }
    
    //下一天判断，返回值为下一天的日期，格式是xxxx年x月x日
    public String nextDate(int m, int d, int y) {
        //判断年份是否超出范围
        if(1912 <= y && y <= 2050) {
            //判断月份是否超出范围
            if(1 <= m && m <= 12) {            
                //大月判断，每个月只有31天
                if(m == 1||m == 3||m == 5||m == 7||m == 8||m ==10||m == 12) {
                    if(1 <= d && d < 31)
                        return y+"年"+m+"月"+(d+1)+"日";
                    else if(d == 31) {    
                        if(m == 12)
                            return (y+1)+"年1月1日";
                        else 
                            return y+"年"+(m+1)+"月1日";
                    }                    
                    else 
                        return "日期超出范围";
                }
                //小月判断，每个月只有30天
                else if(m == 4||m == 6||m == 9||m == 11) {
                    if(1 <= d && d < 30)
                        return y+"年"+m+"月"+(d+1)+"日";
                    if(d == 30)    
                        return y+"年"+(m+1)+"月1日";                    
                    else
                        return "日期超出范围";
                }
                /*二月份在平年和闰年天数不同
                 * 所以需要分开统计
                 * */
                else {
                    if(isLeapYear(y)) {
                        if(1 <= d && d < 29)
                            return y+"年"+m+"月"+(d+1)+"日";
                        if(d == 29)    
                            return y+"年"+(m+1)+"月1日";                    
                        else
                            return "日期超出范围";
                    } else {
                        if(1 <= d && d < 28)
                            return y+"年"+m+"月"+(d+1)+"日";
                        if(d == 28)    
                            return y+"年"+(m+1)+"月1日";                    
                        else
                            return "日期超出范围";
                    }
                }    
            } else {
            	 return "月份超出范围";
            }               
        } else {
            return "年份超出范围";
        }            
    }
    
    //获得大月的前一天
    public String getEvenYesterday(int m, int d, int y) {
    	boolean leapYear = isLeapYear(y);
    	String result = "";
    	if(d < 1 || d > 31) {
    		result = "日期超出范围";
    	} else {
    		if(m != 1 && m != 3){
    			if(d == 1) {
        			result = y + "年" + (m-1) + "月30日";
        		} else {
        			result = y + "年" + m + "月" + (d-1) + "日";
        		}
    		} else {
    			switch(m) {
	    			case 1:
	    				result = (y-1) + "年12月31日";
	    				break;
	    			case 3:
	    				if(leapYear) {
	    					result = y + "年" + (m-1) + "月29日";
	    				} else {
	    					result = y + "年" + (m-1) + "月28日";
	    				}
	    				break;
    			}
    		}
    		
    	} 	
    	return result;
    }
  //获得小月的前一天
    public String getOddYesterday(int m, int d, int y) {
    	String result = "";
    	if(m != 2){
    		if(d < 1 || d > 30) {
        		result = "日期超出范围";
        	} else {   		 
        		if(d == 1) {
            		result = y + "年" + (m-1) + "月31日";
            	} else {
            		result = y + "年" + m + "月" + (d-1) + "日";
            	}   				
        	} 		
    	} else if (m == 2 && isLeapYear(y)){
    		if(d < 1 || d > 29) {
        		result = "日期超出范围";
        	} else {   		 
        		if(d == 1) {
            		result = y + "年" + (m-1) + "月31日";
            	} else {
            		result = y + "年" + m + "月" + (d-1) + "日";
            	}   				
        	} 	
    	} else {
    		if(d < 1 || d > 28) {
        		result = "日期超出范围";
        	} else {   		 
        		if(d == 1) {
            		result = y + "年" + (m-1) + "月31日";
            	} else {
            		result = y + "年" + m + "月" + (d-1) + "日";
            	}   				
        	}   		
    	}
    	
    	return result;   	
    }
    //前一天判断
    public String lastDay(int m, int d, int y) {
    	String result = "";
    	if(y < 1912 || y > 2050) {
    		result = "年份超出范围";
    	} else {
    		switch(m) {
    			case 1:
    			case 3:
    			case 5:
    			case 7:
    			case 8:
    			case 10:
    			case 12:
    				result = getEvenYesterday(m, d, y);
    				break;
    			case 2:
    			case 4:
    			case 6:
    			case 9:
    			case 11:
    				result = getOddYesterday(m, d, y);
    				break;    
    			default:
    				result = "月份超出范围";
    				break;
    		}
    	}
		return result;   	
    }
    
    //使用基姆拉尔森计算公式
    //Week=(Day + 2*Month + 3*(Month+1）/5 + Year + Year/4 - Year/100 + Year/400) % 7
    public int weekDay(int m, int d, int y) {
    	if(m == 1){
    		m = 13;
    		y--;
    	}
    	if(m == 2){
    		m = 14; 
    		y--;
    	}
    	int week=(d + 2*m + 3*(m+1)/5 + y + y/4 - y/100 + y/400)%7;
	
    	return week;
    }
    //将获得的数字转成输出显示的字符串
    public String todayWeek(int m, int d, int y) {
    	int week = weekDay(m, d, y);
    	String w = "";
    	switch(week) {
    	case 0:
    		w = "一";break;
    	case 1:
    		w = "二";break;
    	case 2:
    		w = "三";break;
    	case 3:
    		w = "四";break;
    	case 4:
    		w = "五";break;
    	case 5:
    		w = "六";break;
    	case 6:
    		w = "日";break;
    	}   	
    	return w;
    }
}