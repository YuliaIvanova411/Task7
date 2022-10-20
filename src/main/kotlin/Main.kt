class Comments (
    val count: Int = 0,
    val canPost: Boolean = true,
    val groopsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)
val comment = Comments (1)

data class Post (
    val id: Int = 1,
    val fromId: Int = 2,
    val text: String = "Something written",
    val friendsOnly: Boolean = false,
    val comments: Comments,
    val postType: String = "Post",
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val isFavourite: Boolean = false,
    val markedAsAds: Boolean = false
)


object WallService {
    private var posts = emptyArray<Post>()
    fun add(post: Post): Post {
        posts += post.copy(id = post.id + 1)
        return posts.last()
    }
    fun print() {
        println(posts)
    }

    fun update(id: Int): Boolean {
        var result = false
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy()
                result = true
            }

        }
        return result
    }
}


fun main() {

    WallService.add(Post(2, comments = comment))
    WallService.print()
}
