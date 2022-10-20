import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun ifAdd() {

        val post = Post (id = 2, comments = comment)

        val result = WallService.add(post)
        //добавить проверку на больше нуля

        assertEquals (true, result)

    }
}