# leaning_test
학습 테스트 관련

## JSON
### GSON
#### json을 표현하는 기본 요소 : JsonElement(하위 클래스 : JsonArray, JsonNull, JsonObject, JsonPrimitive)

> JsonObject, JsonArray에 JsonElement를 추가할 수 있다.
> JsonObject에 포함시킬 때에는 약칭이 필요하다. jsonObject.add("inner", JsonElement);
> JsonObject에 Wrapper형 객체를 추가할 때는 addProperty 메소드를 사용한다(add 메소드와 같이 약칭과 객체를 인자로 가진다).
> getAs~ 메소드를 이용해서 Element를 구성하는 값을 읽어올 수 있다.

### Jackson
### JSON.Simple

>> JSON 파싱 시 사용하는 대표적인 라이브러리 GSON, Jackson, JSON.Simple 파싱 비교(왼쪽이 가장 좋음)
>>> 참조사이트 : https://blog.overops.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/
>>>> JSON 크기 클 때 : Jackson > GSON
>>>> JSON 크기 작을 때 : GSON > Jackson 
>>>>> 해당 사이트에서는 세 개의 라이브러리를 가지고, 검사를 하였으나, JSON.simple의 경우 업데이트가 잘 안 되고 있어서 여기에서는 뺐음.
>>> 다른 참조사이트 : https://www.developer.com/lang/jscript/top-7-open-source-json-binding-providers-available-today.html (2014년 자료)
>>> 번역 사이트 : https://djkeh.github.io/articles/The-fastest-way-to-parse-json-data-to-java-kor/
>>>> 작은 용량 TOP 3(500 KB 이하) : GSON, genson, FlexJson(직렬화 시간 + 역직렬화 시간 => GSON과 genson의 경우 직렬화, 역직렬화 모두 3위 안에 듦, FlexJson의 경우 직렬화는 가장 빠르지만, 역직렬화가 느림)
>>>> 큰 용량 TOP  3(100 MB 이상) : JackSon, JSON-lib, genson(직렬화 시간 + 역직렬화 시간 => Jackson은 직렬화, 역직렬화 모두 빠른 편, JSON-lib는 직렬화는 그렇게 빠른 편이 아니지만, 역직렬화가 상당히 빠름)

<hr />

## java.lang.reflect

## XML
### Parser



#### DOM : 문서의 완전한 내용을 로드하고, 메모리에 완전한 계층 트리를 작성하여, XML 문서를 파싱함
> 언제 사용할까?
>> 문서의 구조에 대해 많이 알고 싶을 때, XML 문서의 내용을 이동시키고자 할 때(특정 요소 정렬), XML 문서의 정보를 두 번 이상 사용하고자 할 때.

> 사용 순서
1. XML 관련 패키지 임포트
2. DocumentBuilder 생성
3. 파일 또는 스트림에서 Document 생성
4. 루트 요소 추출
5. 속성 검사
6. 하위 요소 검사

> 파일을 XML로 내보낼 때 Element를 계속 해서 생성해줘야 함. 하나의 레퍼런스로 create~ 호출 안 통함

#### SAX : 이벤트 기반 트리거에서 문서를 파싱한다. 완전한 문서를 메모리에 로드하지 않는다. 
#### JDOM : DOM 파서와 비슷한 방식으로 XML 문서를 파싱하지만, 보다 쉬운 방법이다.
#### StAX : SAX 파서와 비슷한 방식이지만, 보다 효율적인 방법으로 파싱한다.
#### XPath : 표현식을 기반으로 XML 문서를 파싱하고, XSLT와 함께 광범위하게 사용된다.
#### DOM4J : 자바 컬렉션 프레임워크를 사용하여 XML, XPath, XSLT를 파싱하는 자바 라이브러리. DOM, SAX, JAXP에 대한 지원을 제공한다.

### Marshaller(Unmarshaller)

#### JAXB
#### Castor

### HTTP(Apache HTTP 사용)