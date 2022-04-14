package report;

import java.util.*;


/**
 * @author 성한
 * @date 2022. 4. 14. - 오후 4:01:50
 * @subject
 * @content
 */
class Solution2 {

	public static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		int[][] reportList = new int[id_list.length][id_list.length]; // 행-유저, 열-신고당한 유저
		String[] splitReport = new String[2]; // 신고한 유저 0 , 신고당한 유저 1
		int idIdx = 0, rIdx = 0, cnt = 0;

		for (int i = 0; i < report.length; i++) {
			splitReport = report[i].split("\\s");
			for (int j = 0; j < id_list.length; j++) {
				if (id_list[j].equals(splitReport[0]))
					idIdx = j; // 신고한 유저 인덱스
				else if (id_list[j].equals(splitReport[1]))
					rIdx = j; // 신고당한 유저 인덱스
			} // for j
			reportList[idIdx][rIdx]++;
		} // for i

		for (int i = 0; i < reportList[0].length; i++) { // 열
			cnt = 0;
			for (int j = 0; j < reportList.length; j++) { // 행
				if (reportList[j][i] >= 1)
					cnt++;
			} // for j
			if (cnt >= k) {
				for (int j = 0; j < reportList.length; j++) {
					if (reportList[j][i] >= 1)
						answer[j]++;
				} // for j
			} // if
		} // for i
		return answer;
	} // solution
}