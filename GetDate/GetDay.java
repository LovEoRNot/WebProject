package com.test;

public class GetDay {
    /*�����ж�
     * ����ֵΪtrue������Ϊ����
     * ����ֵΪfalse������Ϊƽ��
    */
    public boolean isLeapYear(int y) {
        if((y % 4 == 0 && y % 100 != 0)||y % 400 == 0)
            return true;
        else
            return false;
    }
    
    //��һ���жϣ�����ֵΪ��һ������ڣ���ʽ��xxxx��x��x��
    public String nextDate(int m, int d, int y) {
        //�ж�����Ƿ񳬳���Χ
        if(1912 <= y && y <= 2050) {
            //�ж��·��Ƿ񳬳���Χ
            if(1 <= m && m <= 12) {            
                //�����жϣ�ÿ����ֻ��31��
                if(m == 1||m == 3||m == 5||m == 7||m == 8||m ==10||m == 12) {
                    if(1 <= d && d < 31)
                        return y+"��"+m+"��"+(d+1)+"��";
                    else if(d == 31) {    
                        if(m == 12)
                            return (y+1)+"��1��1��";
                        else 
                            return y+"��"+(m+1)+"��1��";
                    }                    
                    else 
                        return "���ڳ�����Χ";
                }
                //С���жϣ�ÿ����ֻ��30��
                else if(m == 4||m == 6||m == 9||m == 11) {
                    if(1 <= d && d < 30)
                        return y+"��"+m+"��"+(d+1)+"��";
                    if(d == 30)    
                        return y+"��"+(m+1)+"��1��";                    
                    else
                        return "���ڳ�����Χ";
                }
                /*���·���ƽ�������������ͬ
                 * ������Ҫ�ֿ�ͳ��
                 * */
                else {
                    if(isLeapYear(y)) {
                        if(1 <= d && d < 29)
                            return y+"��"+m+"��"+(d+1)+"��";
                        if(d == 29)    
                            return y+"��"+(m+1)+"��1��";                    
                        else
                            return "���ڳ�����Χ";
                    } else {
                        if(1 <= d && d < 28)
                            return y+"��"+m+"��"+(d+1)+"��";
                        if(d == 28)    
                            return y+"��"+(m+1)+"��1��";                    
                        else
                            return "���ڳ�����Χ";
                    }
                }    
            } else {
            	 return "�·ݳ�����Χ";
            }               
        } else {
            return "��ݳ�����Χ";
        }            
    }
    
    //��ô��µ�ǰһ��
    public String getEvenYesterday(int m, int d, int y) {
    	boolean leapYear = isLeapYear(y);
    	String result = "";
    	if(d < 1 || d > 31) {
    		result = "���ڳ�����Χ";
    	} else {
    		if(m != 1 && m != 3){
    			if(d == 1) {
        			result = y + "��" + (m-1) + "��30��";
        		} else {
        			result = y + "��" + m + "��" + (d-1) + "��";
        		}
    		} else {
    			switch(m) {
	    			case 1:
	    				result = (y-1) + "��12��31��";
	    				break;
	    			case 3:
	    				if(leapYear) {
	    					result = y + "��" + (m-1) + "��29��";
	    				} else {
	    					result = y + "��" + (m-1) + "��28��";
	    				}
	    				break;
    			}
    		}
    		
    	} 	
    	return result;
    }
  //���С�µ�ǰһ��
    public String getOddYesterday(int m, int d, int y) {
    	String result = "";
    	if(m != 2){
    		if(d < 1 || d > 30) {
        		result = "���ڳ�����Χ";
        	} else {   		 
        		if(d == 1) {
            		result = y + "��" + (m-1) + "��31��";
            	} else {
            		result = y + "��" + m + "��" + (d-1) + "��";
            	}   				
        	} 		
    	} else if (m == 2 && isLeapYear(y)){
    		if(d < 1 || d > 29) {
        		result = "���ڳ�����Χ";
        	} else {   		 
        		if(d == 1) {
            		result = y + "��" + (m-1) + "��31��";
            	} else {
            		result = y + "��" + m + "��" + (d-1) + "��";
            	}   				
        	} 	
    	} else {
    		if(d < 1 || d > 28) {
        		result = "���ڳ�����Χ";
        	} else {   		 
        		if(d == 1) {
            		result = y + "��" + (m-1) + "��31��";
            	} else {
            		result = y + "��" + m + "��" + (d-1) + "��";
            	}   				
        	}   		
    	}
    	
    	return result;   	
    }
    //ǰһ���ж�
    public String lastDay(int m, int d, int y) {
    	String result = "";
    	if(y < 1912 || y > 2050) {
    		result = "��ݳ�����Χ";
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
    				result = "�·ݳ�����Χ";
    				break;
    		}
    	}
		return result;   	
    }
    
    //ʹ�û�ķ����ɭ���㹫ʽ
    //Week=(Day + 2*Month + 3*(Month+1��/5 + Year + Year/4 - Year/100 + Year/400) % 7
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
    //����õ�����ת�������ʾ���ַ���
    public String todayWeek(int m, int d, int y) {
    	int week = weekDay(m, d, y);
    	String w = "";
    	switch(week) {
    	case 0:
    		w = "һ";break;
    	case 1:
    		w = "��";break;
    	case 2:
    		w = "��";break;
    	case 3:
    		w = "��";break;
    	case 4:
    		w = "��";break;
    	case 5:
    		w = "��";break;
    	case 6:
    		w = "��";break;
    	}   	
    	return w;
    }
}