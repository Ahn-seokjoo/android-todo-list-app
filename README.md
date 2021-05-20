## TODO List 어플리케이션 - Android
---
### **설명**
* 기본적인 TODO List이며 기능은 추가, 삭제, 변경이 있으며 추가, 변경한 시간이 기록된다.
  리사이클러뷰를 통해 구현했으며 Room Database를 통해 db를 구현하였고, MVVM 구조를 사용하였으며 LiveData를 Coroutine을 통해 추가, 삭제, 수정동작을 비동기로 처리해주었다. 
---
### **실행 영상**  
#
  - ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/67602108/117580143-789cfc00-b131-11eb-8a5f-7800f619679e.gif)
  - 먼저 실행시에 room db를 이용한 데이터가 저장되어 있는 모습으로 시작, 우하단의      + 버튼을 통해 항목을 추가, 롱클릭시에는 항목을 삭제, 일반 클릭시에는 수정 기능을 구현하였고, 가장 마지막으로 수정 또는 추가한 데이터는 맨 위의 위치로 올라갈 수 있도록 시간 순으로 정렬하였다.

### **사용 외부 라이브러리**
---
1. Layout
- [recyclerView]("https://developer.android.com/guide/topics/ui/layout/recyclerview")
2. Jetpack
- Room
- LiveData
- ViewModel
- Fragment

### **개발 환경**
---
- 언어 - **Kotlin**
- minSdkVersion - 29
- targetSdkVersion - 30
- target - Android 11.0(Google APIs) 
- Test device - Nexus 5x API 30(VM)
