# Compose By Example
- https://github.com/android/compose-samples -> material design 관련 샘프로 코드 모음


### Material Theme 구성
1. Colors : 색 관련, primary, onPrimary, secondary color등을 지정
2. typography : 텍스트 스타일, 타이틀, 헤드, 바디 등의 글꼴과 크기등 지정
3. shapes : small, medium, large shape을 지정할 수 있다

### custom color지정 방법
1. hold color in an ambient
ambient : param을 통해 내려가는 값들 
2. Provide colors
3. Use colors

### Layout
Column, Row, Stack, Contraint Layout 

padding, background 칼라등을 어떤 순서로 설정하느냐에 따라서 적용 범위가 달라진다. 

ex)
Row(Modifier.padding(16.dp).clickable{...})
=> padding 부분은 클릭이 안된다  

Row(Modifier.clickable{...}.padding(16.dp))
=> padding 부분 포함해서 클릭 가능

MeasureBlock
