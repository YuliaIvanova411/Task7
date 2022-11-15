import org.junit.Before

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

import org.junit.Test

class WallServiceTest {
 @Before
 fun clearBeforeTest() {
    WallService.clear()
 }
  @Test
  fun ifIdNotZero () {
     var posts = emptyArray<Post>()
     val post1 = Post(1, comments = Comments(1))


      val (id) = WallService.add(post1)
      val result = if (id > 0) true else false

      assertTrue(result)

  }
    @Test
    fun ifIdExists() {
        var posts = emptyArray<Post>()
        val post1 = Post( 1, comments = Comments(1))

        WallService.add(post1)

        val result = WallService.update(post1)

        assertTrue(result)
    }

    @Test
    fun ifIdDoesntExist() {
        var posts = emptyArray<Post>()
        val post1 = Post(-15, comments = Comments(1))

        WallService.add(post1)

        val result = WallService.update(post1)

        assertFalse(result)
    }
}

