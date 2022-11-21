import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class WallServiceTest {
 @Before
 fun clearBeforeTest() {
    WallService.clear()
 }
    @Test
    fun add() {
        val testPost = Post(
            text = "test",
            date = 101010,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        WallService.add(testPost)
        val result = testPost.id
        assertEquals(0, result)
    }

    @Test
    fun updateIdIsFound() {
        val post = Post(
            id = 1,
            date = 101022,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        WallService.add(post)

        val post1 = Post(
            id = post.id,
            text = "test",
            date = 111010,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        val result = WallService.update(post1)
        assertTrue( result)
    }

    @Test
    fun updateIdNotFound() {
        val post = Post(
            text = "test",
            date = 101010,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )


        WallService.add(post)

        val post1 = Post(
            id = 0,
            text = "test",
            date = 111010,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        val result = WallService.update(post1)
        assertFalse(result)
    }
}
