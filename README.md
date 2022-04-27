
## 세미나 2 과제

### 1. 구현한 코드 및 설명  
#### 1-1) HomeActivity

```kotlin
  private fun initTransactionEvent(){

         val followFragment = FollowFragment()
        val reposFragment = ReposFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followFragment).commit()

        binding.buttonFollow.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, followFragment).commit()
          
        }
        binding.buttonRepos.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, reposFragment).commit()
        }

    }
```
initTransactionEvent함수를 통해서 각 버튼을 눌렀을 때 그에 맞는 fragment를 보여주었습니다.
기본적으로 followFragment를 보여주며 버튼이 클릭되었을 때 transaction.replace를 통하여 해당 fragment를 보여주었습니다.

```kotlin
 <Button
        android:id="@+id/button_follow"
        android:layout_width="150dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="@string/list_follow"
        app:layout_constraintEnd_toStartOf="@id/button_repos"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/textview_home_mbti" />

    <Button
        android:id="@+id/button_repos"
        android:layout_width="150dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="@string/list_repos"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/button_follow"
        app:layout_constraintTop_toBottomOf="@id/textview_home_mbti" />

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment_home"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/button_repos" />
```
XML에서는 FragmentContainerView를 두 개의 버튼 밑에 두었습니다. 

#### 1-2) FollowData, ReposData
```kotlin
data class FollowData(
    val follow_image : Int,
    val follow_name : String,
    val follow_des : String
)
```
리사이클러뷰에 들어갈 FollowData를 정의하였습니다.

```kotlin
data class ReposData(
    val repos_name : String,
    val repos_des : String
)
```
리사이클러뷰에 들어갈 ReposData를 정의하였습니다.

#### 1-3) FollowAdpater
```kotlin
class FollowAdapter : RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    val followUserList = mutableListOf<FollowData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder{
         val binding=ItemFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.onBind(followUserList[position])
    }

    override fun getItemCount(): Int {
        return followUserList.size
    }

    class FollowViewHolder(
        private val binding : ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowData){
            binding.imageviewFollowUser.setImageResource(data.follow_image)
            binding.textviewFollowName.text=data.follow_name
            binding.textviewFollowDes.text=data.follow_des
        }

    }

}
```
 - onCreateViewHolder를 통해서 binding 객체를 만들고 FollowViewHolder로 넘겨줍니다.   
 - onBindViewHolder를 통해서 followUserList[position]을 ViewHolder안의 onBind함수를 호출합니다. onBind함수를 통해서 각 데이터를 연결시킵니다.  
 - getItemCount를 통해서 follwerUSerList에 들어간 데이터의 크기를 반환합니다.

+ ReposAdapter도 이와 같습니다.

#### 1-4) FollowFragment

```kotlin
 private fun initFollowAdapter(){

        followAdatper = FollowAdapter()

        binding.fragmentFollowRecylcerview.adapter=followAdatper

        followAdatper.followUserList.addAll(
            listOf(
                FollowData(R.drawable.hyebin_image,"이혜빈", "T가 되고 싶은 F"),
                FollowData(R.drawable.jimin_image,"유지민", "안드는 하나야 둘이 될 수 없어"),
                FollowData(R.drawable.jiyeon_image,"정지연", "새벽 코딩 with 새벽 감성"),
                FollowData(R.drawable.jehun_image,"조재훈", "Android is my everything..."),
                FollowData(R.drawable.android_image,"JungEun Park", "JungEun Park"),
                FollowData(R.drawable.android_image,"백지연", "100Gyeon"),
                FollowData(R.drawable.android_image,"최시언", "noino0819")

                )
        )
        followAdatper.notifyDataSetChanged()
    }
```
initFollowAdapter함수를 통해서 FollowAdpater 객체를 만들고 현재 follow fragment의 xml에서의 recyclerview의 adapter를 해당 객체로 설정합니다.  
adapter에 원하는 정보를 addAll을 통해서 넣었으며 listOf를 통하여 여러 객체를 넣었습니다.  
현재 followData에는 그림, 이름, 설명이 필요하기 때문에 각 정보를 넣었습니다. 
notifyDataSetChanged()를 통하여 adapter를 통해 데이터를 갱신합니다.


### 2. 배운 내용
- RecyclerView를 더 자세하게 배웠습니다.
- Fragment를 배웠으며 Fragment에서의 view binding 방법을 배웠습니다.


### 3. 실행화면
<div display="inline-block">
<img src=https://user-images.githubusercontent.com/58364342/164737578-1951f386-4c58-41f6-a696-22b05e8651a7.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/164737659-89ddad69-8435-4f8c-8e19-699fa9dc359c.jpg width="350dp" height="550dp">
</div>


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
