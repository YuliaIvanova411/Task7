import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class WallServiceTest {
 @Before
 fun clearBeforeTest() {
    WallService.clear()
 }
    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val postId = 120
        val comment = Comments()
        WallService.createComment(postId, comment)
    }
    @Test
    fun createComment() {
        val newPost = Post(
            text = "test",
            date = 231122,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )
        WallService.add(newPost)
        val postId = 1
        val comment = Comments()

        val result = WallService.createComment(postId, comment)
        assertEquals(comment, result)
    }

    @Test
    fun add() {
        val testPost = Post(
            text = "test",
            date = 251122,
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
            date = 251122,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        WallService.add(post)

        val post1 = Post(
            id = post.id,
            text = "test",
            date = 251122,
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
            date = 251122,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )


        WallService.add(post)

        val post1 = Post(
            id = 0,
            text = "test",
            date = 251122,
            copyright = null,
            postSource = null,
            geo = null,
            donut = null
        )

        val result = WallService.update(post1)
        assertFalse(result)
    }
}
