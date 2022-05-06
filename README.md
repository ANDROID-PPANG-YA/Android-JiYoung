
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


