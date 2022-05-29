package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	// 자주 쓰는 기능은 따로 class 만들어서 모아놓기!!
	// public이라고 써야 다른 class에서도 쓸 수 있음
	// 현재 날짜 시간
	public static String getNowDateStr() {
		// static으로 써야 설계도한테 바로 일 시킬수 있음
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
			
	Date time = new Date();
			
	return format.format(time);
			
	
	}
	
}
