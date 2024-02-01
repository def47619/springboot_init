package com.leew.springboot_init.repository;

import com.leew.springboot_init.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // 조회 수 증가 SQL : "update board_table set board_hits = (board_hits) + 1 where id = ?"
    /* query라는 Annotation
        SQL에서는 테이블 이름이 올 자리에 Entity를 기준으로 update하기 때문에, b라는 약칭을 쓴다.

    */
    @Modifying // update, delete 등의 query를 사용할 때는 Modifying Annotation을 추가로 넣어 줘야 한다.
    @Query(value="update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
