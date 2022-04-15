
## 세미나 1 과제

### 1. 구현한 코드 및 설명  
#### 1-1) SignInActivity
```Kotlin
private lateinit var binding : ActivitySignInBinding
binding= ActivitySignInBinding.inflate(layoutInflater)
setContentView(binding.root)
```
  바인딩을 통해 XML안의 id에 접근 가능하게 했습니다.
  
```Kotlin
 if(binding.edittextId.text.isEmpty() || binding.edittextPw.text.isEmpty()){
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
```
아이디와 비밀번호 입력이 하나라도 안되었을 시에 Toast메세지를 띄우게 했습니다.

```Kotlin
activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ it ->

            val intent : Intent? = it.data
            val signup_id=intent?.getStringExtra("id").toString()
            val signup_pw=intent?.getStringExtra("pw").toString()
            if(it.resultCode== RESULT_OK){
                binding.edittextId.setText(signup_id)
                binding.edittextPw.setText(signup_pw)
            }
        }
```
registerForActivityResult를 통해 SignUpActivity에서 넘어온 Intent를 받았습니다.  
resultCode==RESULT_OK인 경우 받은 데이터를 각 아이디, 비밀번호의 edittext의 값으로 설정했습니다.

```Kotlin
 private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
binding.buttonSignup.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
```
회원가입 버튼을 누르면 이동하게 했으며 launcher를 통해 화면을 이동하게 했습니다. 

```Kotlin
        android:hint="비밀번호를 입력해주세요"
        android:inputType="textPassword"
```
힌트를 두었고 비밀번호를 입력하기 때문에 추가적으로 inputType을 설정했습니다.

#### 1-2) SignUpActivity
```Kotlin
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("id",binding.edittextSignupId.text.toString())
            intent.putExtra("pw",binding.edittextSignupPw.text.toString())

            setResult(RESULT_OK, intent)

            finish()

```
회원가입 버튼을 눌렀을 때 SignInActivity로 이동하게 했습니다.
기존에 입력했던 id값과 비밀번호값을 intent.putExtra를 통해 전달했습니다.


#### 1-3) HomeActivity
```Kotlin
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">
```
스크롤 뷰를 통해서 화면에 보일 내용이 길어진 경우 스크롤이 가능하게 했습니다.

```Kotlin

app:layout_constraintDimensionRatio="1:1"
```
constraintDimensionRatio를 통해 이미지 뷰에서 화면 비율을 1:1로 설정하였습니다.  

### 2. 과제를 통해 배운 내용
- registerForActivityResult를 배웠습니다. 
- constraintDimensionRatio를 배웠습니다.
  
### 3. 보완할 부분
- 문자를 그대로 입력하기 보다 string에 넣고 사용하기  
- Intent로 여러 값을 전달할 때 apply()를 사용하기
- 추가 공부 더 하기!!!! 
### 4. 실행화면
<div display="inline-block">
<img src=https://user-images.githubusercontent.com/58364342/162611821-e3bb8824-c2ab-44e7-b540-36d68667a9cc.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/162611839-ff66b1dc-c4ba-450b-8d7a-8b5656fbc52e.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/162611841-8cd5b5e8-9e41-4701-ab9c-036da470f60b.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/162611844-f0663385-6e1f-43f5-8795-cb58bf51fc03.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/162611845-6cf4eda8-3dbc-43ad-aa7f-a25cc6b9d250.jpg width="350dp" height="550dp">
</div>

다음번엔 영상으로 찍겠습니다...
