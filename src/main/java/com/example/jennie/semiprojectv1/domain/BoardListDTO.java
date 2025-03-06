package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardListDTO {
    public BoardListDTO(int cpg, int totalItems, int pageSize, List<?> bdlist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double)totalItems/pageSize);  // h2는 ceil함수 지원 안함 이럴땐 class에서 처리해서 보냄
                                                                                                                               // 정수 / 정수 -> 정수넘어와서  올림 못함, 한쪽을 double로 형변환함.
        this.bdlist = bdlist;
        // 페이지네이션 범위계산
        this.stblk = ( (cpg - 1) /10)*10 +1 ;
        this.edblk = Math.min (stblk + 10 -1, cntpg);
}
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<?> bdlist;

}
