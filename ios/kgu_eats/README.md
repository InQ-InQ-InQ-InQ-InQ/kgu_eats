#  KGU eats!

## Restaurant Infomation View

- 화면 간 데이터 전송
https://jinsangjin.tistory.com/95
https://seungchan.tistory.com/entry/3%EC%A3%BC%EC%B0%A8-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A0%84%EB%8B%AC
    - delegate
    - 세그웨이로 모델을 계속 옮기면 뷰마다 옮겨지는 모델이 달라짐, 그니까 객체처럼 복사 개념인 거 같음
    - -> 값타입이 아닌 참조 타입을 전달해야할 듯????
    - https://blog.naver.com/PostView.naver?blogId=sqlpro&logNo=220513687634&redirect=Dlog&widgetTypeCall=true&directAccess=false
    - cafeteria를 구조체에서 클래스로 바꾸고 cafeteriaManager를 싱글톤 패턴으로 사용함 



- CollectionView로 상단 섬네일 이미지 슬라이드 구현
https://skytitan.tistory.com/304 [-> 자동 슬라이드 참고할 것]

    
- 하단 식당 메뉴, 리뷰, 정보 카테고리 바
두 개의 CollectionView - register할 필요가 없다. (왜 그러지 코드랑 스토리보드랑 중복이라 그런가)

스크롤뷰>컨테이너 뷰 3개 -> 크기 변환으로 해야하나?


스크롤뷰>스택뷰>컨테이너뷰 3개
스크롤뷰는 부모 뷰에 autolayout 상하좌우
스택뷰는 스크롤뷰에 autolayout 상하좌우 + eqaul width + equal height
- 이 때 horizontal의 경우 width의 우선순위 낮추기 
- vertical의 경우 height의 우선순위 낮추기
- distribution -> fill equally

컨테이너뷰 내 collectionView에 delegate 전달
- https://lxxyeon.tistory.com/104
- 도착지에서 prepare로 뷰에 보여지기 전에 delegate 받아야함



이미지 네이밍 룰
- 식당이름_메뉴이름.확장명


###리뷰
- 리뷰 작성 뷰 만들기
    - 리뷰 등록하기
        - 사진 고르기 -> UIImagePickerController 말고 PHPickerViewController를 쓰기로 하자
        - PHPickerViewController는 사진 다중 선택 기능을 기본적으로 제공한다.
        https://gyuios.tistory.com/131
        https://zeddios.tistory.com/1052
        - UICollectionViewCell 내부 버튼을 클릭하여 present()를 발생시켜야 하는데 이는 UIViewController 클래스를 상속하기 때문에 UICollectionViewCell 클래스 내부에서 직접 실행 불가
        - UIViewController 클래스를 delegate하여 present를 실행시키자
        
        
        
- 리뷰 뷰
    - 작성된 리뷰 보기
    
    
    
- 장바구니 버튼 및 장바구니 보기


- 메뉴 클릭 시 수량 선택 뷰

- 수량 선택 후 장바구니 버튼 누르면 장바구니에 추가되기







- response model, request Model

- http 통신 오류 
    - erorr code 
    - xcode since it does not conform to ATS policy
    info.plist에 코드 추가 
    <key>NSAppTransportSecurity</key>
    <dict>
        <key>NSAllowsArbitraryLoads</key>
        <true/>
    </dict>

- AF.request() -> encoding:
json일 경우 JsonEncoding.default로 해야한다.........
https://medium.com/@jakoriaty/alamofire-400-error-%EB%AC%B8%EC%A0%9C-9ca84c498e38

NSHTTPURLResponse decode 하는 방법은 
response.response?.allheaderFields



restaurantInit()에서 CafeteriaManager.shared.cafeterias.count 는 2가 나오는데
dataSource에서 print()하면 0이 나옴
viewDidLoad에서 restaurantInit() 먼저 실행해도 안 됨 
-> escaping closure 사용하면 되는데 정확히 모르겠다 .....

