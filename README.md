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

