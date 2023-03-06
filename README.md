# Simple Rest

_A simple interface for retrofit_

The aim of this project is to make interfacing with retrofit just a tad easier.
The library abstracts the actual retrofit interface away from the caller so the call site just
becomes a simple request task.

## Usage

#### Add the dependency

Kotlin DSL:
```kotlin
repositories {
    maven {
        maven {
            url = uri("https://maven.pkg.github.com/Lyxnx/simplerest-kt")
            credentials {
                username = project.property("gpr.username") as? String ?: System.getenv("USERNAME")
                password = project.property("gpr.token") as? String ?: System.getenv("TOKEN")
            }
        }
    }
}
```
where `gpr.username` is your GitHub username and `gpr.token` is a personal access token.

The idea is to be simple to use but a few things need to be set up first.

_A full but simple example can be found in
the [example package](src/main/kotlin/net/lyxnx/simplerest/example)_

You'll need the following:

- An implementation of an [ApiInterface](src/main/kotlin/net/lyxnx/simplerest/ApiInterface.kt)
- An implementation of an [ApiProvider](src/main/kotlin/net/lyxnx/simplerest/ApiProvider.kt)

#### Create your models

```kotlin
data class User(val id: Int, val name: String, val age: Int)
```

#### Create your retrofit interface as usual, but make it implement [ApiInterface](src/main/kotlin/net/lyxnx/simplerest/ApiInterface.kt)

```kotlin
interface ExampleApiInterface : ApiInterface {
    @GET("/user")
    suspend fun getUser(): User

    @POST("/user")
    suspend fun postUser(@Body user: User)
}
```

#### Create the requests. In this case, a base request has been created to reduce the number of generic
parameters the requests need to accept

```kotlin
abstract class ExampleBaseRequest<T : Any> : ApiRequestTask<T, ExampleApiInterface>()

class GetUserRequest : ExampleBaseRequest<User>() {
    override suspend fun execute(api: ExampleApiInterface): User {
        return api.getUser()
    }
}
```

#### Create a provider that provides the created ApiInterface

```kotlin
object ExampleApiProvider : ApiProvider<ExampleApiInterface> {
    override fun build(): ExampleApiInterface {
        return buildApi {
            baseUrl("https://test.com")
            client {
                addInterceptor {
                    val original = it.request()

                    it.proceed(
                        original.newBuilder()
                            .method(original.method(), original.body())
                            .header("User-Agent", "Some user agent")
                            .build()
                    )
                }
            }
        }
    }
}
```

#### Finally, initialize the provider
within [SimpleRest](src/main/kotlin/net/lyxnx/simplerest/SimpleRest.kt)

```kotlin
class SomeClass {
    init {
        SimpleRest.init(ExampleApiProvider)
    }
}
```

That's all, to use just call your request within a coroutine:

```kotlin
scope.launch(Dispatchers.IO) {
    val response: User = GetUserRequest()
    // Do something with the response
}
```

Alternatively:

```kotlin
val user = ExampleApiProvider.sendRequest {
    getUser()
}
```

can be used over a full request class. This can be useful when you don't want to create a full class
for a simple request and would rather interface with the ApiInterface directly at the call site