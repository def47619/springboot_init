## SpringBoot_Init 
- RestAPI 기본 입문  
- 게시판 기능 구현 

### 게시판 주요 기능 
1. 글 쓰기(/board/save)
2. 글 목록(/board/)
3. 글 조회(/board/{id})
4. 글 수정(board/update{id})
5. 글 삭제(board/delete/{id})
6. 페이징 처리(/board/paging)

### Framework 
- SpringBoot(with Initalizer)
- JDK17
- MySQL
- JPA
- Thymeleaf


### 설정 사항 
- PORT = 5432

### 관련 링크 
https://changha-dev.tistory.com/145  
https://www.youtube.com/watch?v=RmSDa0nJd2o&list=PLV9zd3otBRt7jmXvwCkmvJ8dH5tR_20c0&index=2

#### SpringBoot - MySQL(JPA)
- https://pcm9881.tistory.com/130
- https://nyximos.tistory.com/90

#### @RequestMapping 
- https://galid1.tistory.com/769
- 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑해주는 Annotation

#### JPA Entity Mapping 
- https://data-make.tistory.com/610
- https://walkingtechie.blogspot.com/2019/06/difference-between-entity-and-table.html
- @Entity가 붙은 클래스는 JPA가 관리, Entity라고 불림 
- @Table : Entity와 매핑할 테이블을 지정 
- @SuperClass : Entity 간 상속 관련 
  - https://velog.io/@choidongkuen/Spring-JPA-MappedSuperClass-%EB%9E%80-cdl2bl5d

#### MySQL DB - JPA
- https://junior-datalist.tistory.com/305
- 초기 세팅 시, hibernate로 생성하여 DB 생성 안해도 된다. 