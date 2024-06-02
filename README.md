# 민지원

## 기획

- 좋아하는 가수, 연예인이 있다면 그 사람에 대해 얘기하는 게시판을 만들어보자

**ERD**

<img width="763" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/af0ec1a9-6d8b-4387-b73d-53ff674c01cf">

## 시연

- 메인화면

<img width="697" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/55db5afe-52e9-444f-aa8b-0e11f1c2b184">

    
- 게시판 추가

<img width="732" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/48233e82-dd88-49e6-9641-2012aef61eb8">

    
- 게시판 메인
    
<img width="709" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/7f54f85a-fd49-4a3a-b3c8-6180eac94366">

    
- 게시글
    
<img width="725" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/5a89ae8b-8f0c-45d7-b536-27a5826b44ce">


- 게시글 작성
    
<img width="712" alt="image" src="https://github.com/MinZW0n/Board/assets/87708490/ac32f0a4-c2b8-4d6a-b054-638f66fe07aa">


## 트러블슈팅

- 문제상황: 게시물에 있는 댓글을 지우면 댓글이 모두 사라지는 현상
- 원인: 댓글과 게시물이 연결이 되어있어 그런 것 같다
- 해결방법:

foundComment.setPost(null);
commentRepository.delete(foundComment);

딜리트 실행 전에 post를 null로 바꾸어 주니 같이 삭제가 되지 않았다.

- 문제상황: 사진을 업로드 하면 그 사진을 띄우게 하고 싶었는데 그 사진이 뜨지 않는 현상
- 원인: Spring Boot의 정적 리소스 핸들링 설정: Spring Boot는 기본적으로 "/static", "/public", "/resources", "/META-INF/resources" 이렇게 4개의 위치에 있는 정적 리소스를 자동으로 제공하기 때문에 추가로 config를 구현해주어야 됐다.
- 해결방법: `WebMvcConfigurer` 구현

## 회고

- 이미지를 넣는 부분에서 계속 오류가 떠 며칠동안 계속 검색하며 방법을 찾아보았다.
- 게시판을 직접 생성하는 형식이다 보니까 게시판의 특성에 맞는 사진도 같이 있었으면 좋겠다고 생각해 이미지를 추가할 수 있는 기능을 넣었다.
- 간단하게 게시판, 게시글, 댓글의 CRUD를 만들어 보면서 html과 스프링간에 통신 부분이 좀 어려웠고 에러도 많이 생겨 이것저것 많이 고쳐봤다. 아직 완벽하게 스프링을 할 수 있다고는 못하겠지만 어느정도 흐름은 알게 되어 좋은 경험이 되었다.

