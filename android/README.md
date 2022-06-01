# airbnb
그룹 프로젝트 #4


#### 🌱 [team 규칙](https://github.com/hwicode/airbnb/wiki/backend-QuestionContent)

<details>
<summary>📑 ERD 설계</summary>
<div markdown="1">

<img width="1240" alt="스크린샷 2022-05-25 오후 2 28 25" src="https://user-images.githubusercontent.com/95541996/170209916-6df18330-fb13-4d06-976c-78d260d58ff6.png">

</div>
</details>

<details>
<summary>❓ 질문사항</summary>
<div markdown="1">

<br>
  
위시리스트를 어떻게 설계해야 할지 조언을 얻고 싶습니다.
  1. `accommondation` 테이블과 `wish` 테이블을 일대다의 관계로 매핑하고, `wish` 테이블과 `member` 테이블을 다대일의 관계로 매핑합니다. 
  ![다대다](https://user-images.githubusercontent.com/95541996/170213362-0e7ad48d-1766-4a2c-ab24-7eb4d298939d.PNG)

  
  2. `member` 테이블이 일대일의 관계로 `wish_list` 테이블을 가지고 `wish_list` 테이블과 `accommondation` 테이블을 일대다의 관계로 매핑합니다.
  ![일대다](https://user-images.githubusercontent.com/95541996/170213372-1ede5df8-359b-4f02-b8e0-18282cd50f48.PNG)

</div>
</details>

