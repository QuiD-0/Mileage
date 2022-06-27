# TRIPLE_BE
이재웅_트리플 서비스 백엔드 엔지니어 과제전형 리포지토리입니다.   

- java 15   
- spring-boot 2.7.0   
- mySQL 8.0.21   
- jpa

## jar파일로 실행

현재 설정된 DB의 id와 pw는 triple 입니다.   
DB에 사용자를 생성하여주세요.    
DDL\triple_BE.sql 파일을 실행하여 DB를 생성합니다.   

cmd창에서 해당 폴더로 이동후   
java -jar TripleMileageBE.jar 을 입력 해 주세요.   

## 소스 코드로 실행

현재 설정된 DB의 id와 pw는 triple 입니다.   
DB에 사용자를 생성하여주세요.    
혹은 mileage_BE\src\main\resources\application.properties 에서    
spring.datasource.username={id}
spring.datasource.password={pw}
해당 부분을 로컬 DB의 id,pw에 맞게 수정 해 주세요.   
DDL\triple_BE.sql 파일을 실행하여 DB를 생성합니다.  

mileage_BE 폴더를 프로젝트로 열어 dependency가 다운로드될때까지 기다려 주세요
jdk를 15버전 이상으로 설정 해 주세요.
mileage_BE/src/main/java/mileage/mileage_be/MileageBeApplication.java를 실행 해 주세요.

## 서비스

과제 코드는 mileage_BE\src\main에 위치해 있으며   
크게 user, review, history 3가지 서비스로 분리하여 작성하였습니다.   

POST /events를 통해 리뷰 추가, 수정, 삭제를 할수 있습니다.   
GET /history를 통해 모든 포인트 부여, 회수 이력을 볼 수 있습니다.   
GET /history/{userID}를 통해 해당 사용자의 포인트 부여, 회수 이력을 볼 수 있습니다.
GET /point를 통해 모든 사용자의 포인트를 확인할 수 있습니다.   
GET /point/{userID}를 통해 해당 사용자의 현재 포인트를 확인 할 수 있습니다.   

테스트 코드는 mileage_BE\src\test에 위치해 있습니다.   
reviewService에 대한 테스트 코드입니다.
