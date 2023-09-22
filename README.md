# s2w

[면접 때 명확하게 전달하지 못해서 아쉬운 부분]   
1. 서비스는 Facade pattern 으로 구현하여 각 Controller 는 Service 클래스를 바라보고 Service 클래스는 창구의 역할만 담당합니다.   
그래서 Processor 는 각 요구사항에 맞게 응집도 높은 코드를 작성하고 요구사항이 변경시 Service 의 클래스 변경없이 단일책임을 가진 Processor 클래스만 변경하기 때문에 OCP 도 지킬 수 있습니다.

2. Adapter Pattern 이 있어서 hexagonal architecture 언급을 하신 것 같다고 생각이 듭니다. 그런데 저는 hexagonal architecture 에 대한 깊게 공부를 해본적이 없으며 간단한 개념만 알고 있습니다. Adapter Pattern 은 디자인 패턴을 생각을 하고 만든 것이고 결합도를 낮춰주는 장점이 있기에 사용했습니다. hexagonal architecture 은 Port 를 통해 내부와 외부 메시지를 전달하는 아키텍처로 알고있습니다. 요즘 유행하는 최신기술을 맹목적으로 선호하지 않고 개발에 은탄환은 존재하지 않다는 사실을 인지하고 있습니다.
