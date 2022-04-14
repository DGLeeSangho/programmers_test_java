package report;

import java.util.*;
/**
 * @author sangho
 * @date 2022. 4. 14. - 오후 4:01:46
 * @subject
 * @content
 */
public class Solution1 {

		public int[] solution(String[] id_list, String[] report, int k) {
			String[] splitReport = new String[report.length * 2];	//신고 내용 분할하여 저장하기 위한 배열					
			
			int[] answer = new int[id_list.length];							//리턴용 정답 배열

			splitReport = getReport(report, splitReport);
			
			int report_time = 0; // 신고당한 횟수
			
			//정지 대상 아이디 찾기 위함
			int[] report_list = new int[id_list.length];	//아이디별 신고당한 횟수 저장한 배열
			for (int i = 0; i < id_list.length; i++) {
				report_time = 0;
				for (int j = 1; j < splitReport.length; j += 2) {
					if (id_list[i].equals(splitReport[j]))
						report_time++;
				}
				
				report_list[i] = report_time;
			}
			
			String[] suspended_Id = new String[id_list.length];			//정지당한 아이디 배열
			for (int i = 0; i < report_list.length; i++) {
				if (report_list[i] >= k) {	//k : 정지 당할 신고 당한 횟수 이상 일 시 정지당한 아이디 배열에 아이디 저장
					suspended_Id[i] = id_list[i];
				}
			}

			// 신고당한 사람은 홀수 항에 존재
			
			List<String> mail_list = new ArrayList<>(); // 메일 받을 사람 명단

			for (int j = 0; j < suspended_Id.length ; j++) {
				for (int i = 1; i < splitReport.length; i += 2) {	// [1], [3], [5] ... 에 신고당한 사람 이름 저장되어있음
					if (splitReport[i].equals(suspended_Id[j])) {	// 신고당한 사람이 정지당했다면
						mail_list.add(splitReport[i - 1]);	//신고 한 사람을 메일 받을 사람 List에 저장
					}
				}
			}

			int mail;

			for (int i = 0; i < id_list.length; i++) {
				mail = Collections.frequency(mail_list, id_list[i]); // 동일한 아이디 갯수 출력, 중복 갯수 만큼 메일 받음

				answer[i] = mail;
				}
			
			return answer;
		}
		
		public String[] getReport(String[] report, String[] splitReport) {	//신고 내용 배열 중복 제거, 자기자신 신고 제거 하여 리턴
			HashSet<String> hashReport_list = new HashSet<>();	//중복제거 신고 내용 저장위한 Hash
			String[] temp = new String[2];	
			
			for (int i = 0; i < report.length; i++) {
				hashReport_list.add(report[i]);
			}		
			
			report = hashReport_list.toArray(new String[0]);
			
			
			for (int i = 0; i < report.length; i++) {
				temp = report[i].split(" ");
				if(temp[0].equals(temp[1])) {	//자기자신 신고 불가
					temp[0] = null;
					temp[1] = null;
				}
					
				for (int j = 0; j <= 1; j++) {
					
					splitReport[j + 2 * i] = temp[j];
				}
			}
			// null 값 없애기 위해 리턴받을 신고 내용 배열 크기 재정의
			int length=0;
			for (int i = 0; i < splitReport.length; i++) {
				if(splitReport[i]!=null)
					length++;
			}
			
			String [] changed_splitReport = new String[length];
			for (int i = 0; i < splitReport.length; i++) {
				if(splitReport[i]!=null)
				changed_splitReport[i] = splitReport[i];
			}
			
			return changed_splitReport;
		
	    }
    }