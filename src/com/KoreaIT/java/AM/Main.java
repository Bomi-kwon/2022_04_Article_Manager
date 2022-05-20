package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int 마지막_번호;
		int number = 0;

		while (true) {
			System.out.println("명령어 ) ");
			String command = sc.nextLine();

			if (command.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			} 
			
			else if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			else if (command.equals("article write")) {

				System.out.println("제목 : ");
				String x = sc.nextLine();
				System.out.println("내용 : ");
				String y = sc.nextLine();
				마지막_번호 = number + 1;
				System.out.printf("%d번글이 생성되었습니다.\n", 마지막_번호);
				number = 마지막_번호;

			}

			else if (command.equals("System exit")) {
				break;
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("==프로그램 끝==");
	}
}
