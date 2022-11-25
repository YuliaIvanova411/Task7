

data class Comments (
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)

data class Copyright (
    val id: Int = 0,
    val link: String,
    val name: String,
    val type: String
        )

class Likes (
    count:Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = false
        ) {
    var count : Int = count
        set(value) {
            if (value < 0) {
                return
            }
            field = value
        }
}

data class Reposts (
    val count: Int = 0,
    val userReposted: Boolean = false
        )

data class Views (
    val count: Int = 0
        )

data class PostSource(
    val type: String,
    val platform: String,
    val data: String,
    val url: String
)
data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place = Place()
)

class Place(
    val id: Int = 0,
    val title: String = "Name",
    val latitude: Int = 0,
    val longitude: Int = 0,
    val created: Int = 0,
    val icon: String = "pic",
    val checkins: Int = 0,
    val updated: Int = 0,
    val type: Int = 0,
    val country: Int = 0,
    val city: Int = 0,
    val address: String = "where"
)

data class Donut(
    val isDonat: Boolean,
    val paidDuration:Int,
    val placeHolder: String,
    val canPublishFreeCopy: Boolean,
    val editMode: String
)

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text: String = "photo",
    val date: Int = 0,
    val sizes: Array<Sizes> = emptyArray(),
    val width: Int = 0,
    val height: Int = 0
)

data class Sizes(
    val type: String = "size",
    val url: String = "url",
    val width: Int = 0,
    val height: Int = 0
)

data class Audio(
    val id: Int,
    val ownerId: Int = 0,
    val artist: String = "name",
    val title: String = "song",
    val duration: Int = 0,
    val url: String = "url",
    val lyricsId: Int = 0,
    val albumId: Int = 0,
    val genreId: Int = 0,
    val date: Int = 0,
    val noSearch: Boolean = false,
    val isHq: Boolean = false
)

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String,
    val description: String = "description",
    val duration: Int = 0,
    val image: Array<Image> = emptyArray(),
    val firstFrame: Array<Firstframe>,
    val date: Int = 0,
    val addingDate: Int = 0,
    val views: Int = 0,
    val localViews: Int = 0,
    val comments: Int = 0,
    val player: String = "play",
    val platform: String = "platform",
    val canAdd: Boolean = false,
    val isPrivate: Boolean = false,
    val accessKey: String = "key",
    val processing: Int = 0,
    val isFavorite: Boolean = false,
    val canComment: Boolean = false,
    val canEdit: Boolean = false,
    val canLike: Boolean = false,
    val canRepost: Boolean = false,
    val canSubscribe: Boolean = false
)

data class Image(
    val height: Int = 0,
    val url: String = "url",
    val width: Int = 0,
    val withPadding: Boolean = false
)

data class Firstframe(
    val height: Int = 0,
    val url: String = "url",
    val width: Int = 0
)

data class Link(
    val url: String,
    val title: String = "name",
    val caption: String = "caption",
    val description: String = "description",
    val photo: Photo = Photo(),
    val product: Product = Product(),
    val previewPage: String = "page",
    val previewUrl: String = "url"
)

data class Product(
    val price: Int = 0
)


data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val date: Int,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val text: String = "Something written",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: Copyright?,
    val postType: String = "Post",
    val signerId: Int = 0,
    val copyHystory: Array<Reposts> = emptyArray(),
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val isFavourite: Boolean = false,
    val markedAsAds: Boolean = false,
    val postponedId: Int = 0,
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postSource: PostSource?,
    val geo: Geo?,
    val donut: Donut?,
    val attachment : Array<Attachment> = emptyArray<Attachment>()

)
interface Attachment {
    val type: String
}

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type: String = "photo"
}

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
}

data class VideoAttachment(val audio: Video) : Attachment {
    override val type: String = "video"
}

data class LinkAttachment(val link: Link) : Attachment {
    override val type: String = "link"
}

class PostNotFoundException(message: String) : RuntimeException(message)

object WallService {
    var posts = emptyArray<Post>()
    fun clear() {
        posts = emptyArray()
    }
    fun add(post: Post): Post {
        val newId: Int = if (posts.isEmpty()) 1
        else (posts.last().id + 1)
        posts += post.copy(id = newId)
        return posts.last()
    }
    fun print() {
        for (post in posts){
            println(post)
        }
        println()
    }

    fun update(newPost: Post): Boolean {
        val (id) = newPost
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(
                    fromId = newPost.fromId,
                    createdBy = newPost.createdBy,
                    text = newPost.text,
                    replyOwnerId = newPost.replyOwnerId,
                    replyPostId = newPost.replyPostId,
                    friendsOnly = newPost.friendsOnly,
                    comments = newPost.comments,
                    copyright = newPost.copyright,
                    likes = newPost.likes,
                    reposts = newPost.reposts,
                    views = newPost.views,
                    postType = newPost.postType,
                    postSource = newPost.postSource,
                    geo = newPost.geo,
                    signerId = newPost.signerId,
                    canPin = newPost.canPin,
                    canDelete = newPost.canDelete,
                    canEdit = newPost.canEdit,
                    isPinned = newPost.isPinned,
                    markedAsAds = newPost.markedAsAds,
                    donut = newPost.donut,
                    postponedId = newPost.postponedId,
                    attachment = newPost.attachment
                )
                return true
            }
        }
        return false
    }
    private var comments = emptyArray<Comments>()

    fun createComment(postId: Int, comment: Comments): Comments {
        val newComment = findPostById(postId) ?: throw PostNotFoundException("Post with $postId not found")
        comments += comment
        return comments.last()
    }
    private fun findPostById(postId: Int): Post? {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                return post
            }
        }
        return null
    }
    }




fun main() {

    val post1 = Post(
        ownerId = 1,
        date = 211122,
        text = "one",
        friendsOnly = true,
        likes = Likes(count = 1),
        copyright = null,
        postSource = null,
        geo = Geo("Somewhere", "28.134.85"),
        donut = null
    )

    val post2 = Post(
        text = "two",
        date = 201122,
        likes = Likes(count = 51),
        copyright = null,
        postSource = PostSource("vk", "android", "Photo", "url"),
        geo = null,
        donut = null
    )
    WallService.add(post1)
    WallService.add(post2)

    val newPost2 = Post(
        id = post2.id,
        text = "three",
        date = 111122,
        copyright = null,
        postSource = PostSource("vk", "android", "Photo", "url"),
        geo = null,
        donut = null,
        attachment = arrayOf(
            PhotoAttachment(Photo(0, width = 150, height = 200)),
            AudioAttachment(Audio(1, title = "favouriteSong")),
        )
    )

    WallService.update(newPost2)

    WallService.print()

}
