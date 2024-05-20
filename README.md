# 민지원

## 기획

- 좋아하는 가수, 연예인이 있다면 그 사람에 대해 얘기하는 게시판을 만들어보자

**와이어프레임**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/e4451b24-79da-4d75-87ec-06a60ce6c890/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/689c8294-6970-4fe6-8c7c-28bb3dda36c8/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/9fdec160-740e-4f5d-a216-96096bdf6804/Untitled.png)

이런 형식으로 구현하고 싶긴 했었다…

**ERD**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/c4e793ab-0166-4e30-b174-bf07b18801c0/Untitled.png)

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

## 시연

- 메인화면
    
    ![스크린샷 2024-01-20 오전 12.37.25.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/f9c338c6-83ca-43c9-ae2a-fed417830724/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-01-20_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.37.25.png)
    
- 게시판 추가
    
    ![스크린샷 2024-01-20 오전 12.36.37.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/08fc788f-2d4f-4111-ba53-f3a8c427aea8/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-01-20_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.36.37.png)
    
- 게시판 메인
    
    ![스크린샷 2024-01-20 오전 12.37.47.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/d6c0298a-38de-490a-a9c0-acedcd43acc1/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-01-20_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.37.47.png)
    
- 게시글
    
    ![스크린샷 2024-01-20 오전 12.38.16.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/c0536f90-f140-4309-9498-7bd3c48f5315/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-01-20_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.38.16.png)
    
- 게시글 작성
    
    ![스크린샷 2024-01-20 오전 12.37.36.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/d85fd78d-de88-4966-9b27-62e3e2e8c2a0/6eb7210c-eba2-4f71-8f9c-b0c4005ddb69/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2024-01-20_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.37.36.png)
    
-
