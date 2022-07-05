
## 세미나 7 과제  

### 1. 구현한 코드 및 설명  
#### 1-1) GithubSharedPreference
```kotlin
    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(context: Context): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context:Context, value: Boolean) {
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun finishAutoLogin(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear().apply()
    }
```
sharedpreference를 사용하여 자동 로그인 구현, 자동로그인 해제를 구현했습니다.  
기본으로 getAutoLogin()에서 default값을 false를 설정하여 자동로그인이 안되어있도록 설정하였습니다.  
finishAutoLogin()에서는 해제한 코드로 AUTO_LOGIN를 삭제하도록 설정했습니다.  

#### 1-2) SignInActivity
```kotlin
 if(binding.signinCheckbox.isChecked){
            GithubSharedPreference.setAutoLogin(this, binding.signinCheckbox.isChecked)
            Log.d(TAG,"SignInActivity - successSignIn() called isChecked:${binding.signinCheckbox.isChecked}")
        }
```
successSignIn()함수 안에 넣었으며 자동로그인 체크 여부에 따라 setAutoLogin을 설정하였습니다.

#### 1-3) SettingActivity
```kotlin
  private fun initAutoLoginClicked() {
        binding.textviewLoginSetting.setOnClickListener {
            GithubSharedPreference.finishAutoLogin(this)
        }

    }
```
해당 버튼을 클릭하면 finishAutoLogin을 호출하게 했습니다.

#### 1-4) Fragment1, Fragment2, Fragment3
```kotlin
binding.buttonFragment1.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }
```
온보딩 화면으로 StartActivty 안에 있는 fragment입니다. 
다른 fragment로 이동할 때 findNavController.navigate를 이용하여 이동하였습니다.

### 2) 배운 점
- NavigationComponent를 이용하여 fragment 사이에서 이동하는 방법을 배웠습니다.
- 확장함수를 알게 되었고 그에 대한 사용법을 배웠습니다.
- 영속성 데이터를 통해 저장하는 방법을 배웠습니다.

### 3) 구현 영상


https://user-images.githubusercontent.com/58364342/173188955-aaa212ea-c102-4c4e-b684-73bcf6fa52bf.mp4




## 세미나 4 과제  

### 1. 구현한 코드 및 설명  
#### 1-1) SoptService
```kotlin
   @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
```
SignIn과 마찬가지로 signup에 대한 내용을 작성했습니다.
postSignUp함수를 body에 RequestSignUp을 call로 받을 데이터인 RequestSignUp을 설정했습니다.

#### 1-2) RequestSignUp
```kotlin
data class RequestSignUp(
    val name: String,
    val email: String,
    val password: String
)

```
보내는 data class로 이름, 이메일(id), 비밀번호에 대한 클래스입니다.

#### 1-3) ResponseSignUp
```kotlin

data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data
)
{
    data class Data(
        val name:String,
        val email:String
    )
}
```
받는 data class로 이름과 이메일에 대한 클래스입니다.

#### 1-4) SignUpActivity
```kotlin
       private fun signUpNetwork(){
        val requestSignUp = RequestSignUp(
            name=binding.edittextSignupName.text.toString(),
            email=binding.edittextSignupId.text.toString(),
            password = binding.edittextSignupPw.text.toString()
        )
        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignup(requestSignUp)
        call.enqueue(object: Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if(response.isSuccessful){
                    //val data=response.body()?.data
                    Log.d(TAG,"SignUpActivity, response.body: ${response.body()}")
                    Toast.makeText(this@SignUpActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    moveToSignIn()
                    //다음 페이지로 이동
                }else if(response.code()==409){
                    //중복된 경우

https://user-images.githubusercontent.com/58364342/173188815-63a2c900-ff2d-43a1-ad02-35309b54f04f.mp4



https://user-images.githubusercontent.com/58364342/173188859-fa29de68-a11d-4ca0-84fb-3f40c43e7b2a.mp4


                    Toast.makeText(this@SignUpActivity, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                }else

                    Toast.makeText(this@SignUpActivity, "회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.d("NetworkTest","SignUpActivity - onFailure() called, error:$t ")
            }

        })

    }
```
signUpNetwork함수를 설정하였으며 이 함수에서 requestSignUp의 instance를 만들어 입력한 이름,id, pw를 넣었습니다.
call객체의 enqueue를 이용하여 서버 통신을 비동기적으로 요청했습니다.
onResponse함수를 통해 response가 성공적으로 온다면 Toast메세지를 띄운 후에 로그인 화면으로 넘어가게 했습니다.
response의 code가 정상적이지 않은 경우에 대해서 추가적으로 Toast 메세지를 설정하였습니다. 

### 2. 배운 점
- Retrofit2를 이용하여 서버 통신하는 방법을 배웠습니다.

### 3. 실행화면 

![seminar4_gif](https://user-images.githubusercontent.com/58364342/168298029-b295b84c-b4bf-4c49-a446-122523acdc4a.gif)
![image](https://user-images.githubusercontent.com/58364342/168298042-cdf9a1c7-dbb7-4fb1-9b26-959988adbdaf.png)

-----------------------------


## 세미나 3 과제  

### 1. 구현한 코드 및 설명  
#### 1-1) 폰트 적용
```kotlin
android:fontFamily="@font/noto_sans_regular"
```
fontFamily를 이용하여 font패키지에 저장한 noto_sans 폰트를 적용했습니다.

#### 1-2) HomeActivity
```kotlin
   private fun initBottomNavigation(){
        binding.viewPagerHome.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNavHome.menu.getItem(position).isChecked=true
            }
        })
        binding.bottomNavHome.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.viewPagerHome.currentItem= PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.viewPagerHome.currentItem= HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else-> { //카메라인 경우
                    binding.viewPagerHome.currentItem= CAMERA_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
    private fun initAdapter(){
            val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
            homeViewPagerAdpater= ProfileViewPagerAdpater(this)
            homeViewPagerAdpater.setFragments(fragmentList)
            binding.viewPagerHome.adapter=homeViewPagerAdpater
    }
```
initBottomNavigation을 이용하여 BottomNavigation에 있는 각 메뉴를 선택할 때 viewPager에 해당 fragment가 보이도록 설정했습니다. 
initAdapter를 이용해서 viewPager에 보일 수 있는 fragments들을 넣고 연결하였습니다.

#### 1-3) ProfileViewPagerAdpater
```kotlin

class ProfileViewPagerAdpater(fragmentActivity: FragmentActivity)
    :FragmentStateAdapter(fragmentActivity){

    private val fragments= mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun setFragments(list: List<Fragment>){
        fragments.addAll(list)
    }

}
```
ViewPager의 adpater를 설정하여 보여질 fragment를 설정하였습니다.

#### 1-4) HomeFragment
```kotlin
    private fun initAdapter(){
        val fragmentList= listOf(TabHomeFollowerFragment(), TabHomeFollowingFragment())
        homeTabViewPagerAdpater= TabProfileAdpater(this)
        homeTabViewPagerAdpater.setFragments(fragmentList)

        binding.fragmentHomeViewpager.adapter=homeTabViewPagerAdpater
    }
```
HomeFragment에서도 viewPager가 사용되기 때문에 Adapter를 이용하여 연결해주었습니다.

```kotlin
private fun initTabLayout(){
        val tabLabel= listOf("팔로워","팔로잉")
        TabLayoutMediator(binding.fragmentHomeTablayout, binding.fragmentHomeViewpager){ tab , position ->
            tab.text=tabLabel[position]
        }.attach()
    }
```
tablayout의 tabitem들을 설정하였습니다.

#### 1-5) TabProfileAdapter
```kotlin
class TabProfileAdpater(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragments= mutableListOf<Fragment>()

    override fun getItemCount(): Int= fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun setFragments(list: List<Fragment>){
        fragments.addAll(list)
=======
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

TabProfileAdpater도 ViewpagerAdpater와 같이 설정하였습니다.

#### 1-6) NestedScrollHost
```kotlin
class NestedScrollableHost : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var touchSlop = 0
    private var initialX = 0f
    private var initialY = 0f
    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2) {
                v = v.parent as? View
            }
            return v as? ViewPager2
        }

    private val child: View? get() = if (childCount > 0) getChildAt(0) else null

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun canChildScroll(orientation: Int, delta: Float): Boolean {
        val direction = -delta.sign.toInt()
        return when (orientation) {
            0 -> child?.canScrollHorizontally(direction) ?: false
            1 -> child?.canScrollVertically(direction) ?: false
            else -> throw IllegalArgumentException()
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        handleInterceptTouchEvent(e)
        return super.onInterceptTouchEvent(e)
    }

    private fun handleInterceptTouchEvent(e: MotionEvent) {
        val orientation = parentViewPager?.orientation ?: return

        // Early return if child can't scroll in same direction as parent
        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
            return
        }

        if (e.action == MotionEvent.ACTION_DOWN) {
            initialX = e.x
            initialY = e.y
            parent.requestDisallowInterceptTouchEvent(true)
        } else if (e.action == MotionEvent.ACTION_MOVE) {
            val dx = e.x - initialX
            val dy = e.y - initialY
            val isVpHorizontal = orientation == ORIENTATION_HORIZONTAL

            // assuming ViewPager2 touch-slop is 2x touch-slop of child
            val scaledDx = dx.absoluteValue * if (isVpHorizontal) .5f else 1f
            val scaledDy = dy.absoluteValue * if (isVpHorizontal) 1f else .5f

            if (scaledDx > touchSlop || scaledDy > touchSlop) {
                if (isVpHorizontal == (scaledDy > scaledDx)) {
                    // Gesture is perpendicular, allow all parents to intercept
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    // Gesture is parallel, query child if movement in that direction is possible
                    if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)) {
                        // Child can scroll, disallow all parents to intercept
                        parent.requestDisallowInterceptTouchEvent(true)
                    } else {
                        // Child cannot scroll, allow all parents to intercept
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
        }
    }
}
```


구글이 제시한 코드를 참고하였습니다.

```kotlin
    <com.godwpfh.myapplication.util.NestedScrollableHost
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/fragment_home_tablayout">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/fragment_home_viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.godwpfh.myapplication.util.NestedScrollableHost>
```
xml에서 ViewPager를 감싸서 사용했습니다. 

#### 1-7) CameraFragment
```kotlin
 private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

private fun initCameraButton() {
        binding.fragmentCameraButton.setOnClickListener {
            val intent = Intent()
            intent.apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            activityResultLauncher.launch(intent)
        }
    }

```
카메라버튼을 누른 경우 intent를 이용하여 갤러리에서 사진을 선택하게 했습니다.

```kotlin
    private fun setPhotoFromGallery() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                Glide.with(this)
                    .load(result.data?.data)
                    .into(binding.fragmentCameraImageview)

            }
    }
```
갤러리에서 사진을 클릭했을 때 결과를 Glide를 이용하여 해당 imageView에 설정하였습니다.


### 2. 배운 내용
- ActivityResultLauncher를 다시 사용하여 uri에 대해 설정하는 방법을 배웠습니다.
- ViewPager2의 중첩 스크롤 문제에 대해서 알게 되었습니다.
- selector를 통해서 각 상태에 따라 어떻게 구현하는 방법을 알게 되었습니다.
- Tablayout의 tabitem들을 코드에서 구현하는 방법을 알게 되었습니다.
- ViewPager와 ViewPagerAdapter를 설정하는 방법을 알게 되었습니다.

### 3. 실행화면
<div display="inline-block">
<img src=https://user-images.githubusercontent.com/58364342/167086798-fc033d81-03d6-4a0f-ac2b-5de14a796f86.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/167086936-0cfb2a35-611b-4971-bf51-986d4b74eaaa.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/167087034-c8517583-a6ab-42cc-8b2d-8a5ea40e5248.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/167087077-562e9915-8c39-4376-ba44-9d1ad1897dd7.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/167087138-28110158-abd7-447c-a998-3e0dd56884b4.jpg width="350dp" height="550dp">
<img src=https://user-images.githubusercontent.com/58364342/167087171-ad2d530c-fb5d-491e-ad6e-837b2bb64c95.jpg width="350dp" height="550dp">

</div>


=======
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


