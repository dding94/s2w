# s2w

[면접 때 명확하게 전달하지 못해서 아쉬운 부분]   
서비스는 Facade pattern 으로 구현하여 각 Controller 는 Service 클래스를 바라보고 Service 클래스는 창구의 역할만 담당합니다.   
그래서 Processor 는 각 요구사항에 맞게 응집도 높은 코드를 작성하고 요구사항이 변경시 Service 의 클래스 변경없이 단일책임을 가진 Processor 클래스만 변경하기 때문에 OCP 도 지킬 수 있습니다.
