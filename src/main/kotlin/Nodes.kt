abstract class Node<K: Comparable<K>, V, NodeType: Node<K, V, NodeType>>(var key: K, var value: V) {
    var left: NodeType? = null
    var right: NodeType? = null
    var parent: NodeType? = null

    val grandparent: NodeType?
        get() = this.parent?.parent
    val sibling: NodeType?
        get() = if (this.parent?.left == this) this.parent?.right else this.parent?.left
    val uncle: NodeType?
        get() = this.parent?.sibling

    fun isLeaf(): Boolean {
        return (this.left == null && this.right == null)
    }

}




class BinaryNode<K: Comparable<K>, V>(key: K, value: V) :
        Node<K, V, BinaryNode<K, V>>(key, value) {

    override fun equals(other: Any?): Boolean {
        return (other is BinaryNode<*, *>
                && left == other.left
                && right == other.right
                && parent?.key == other.parent?.key
                && key == other.key
                && value == other.value)
    }
}




class RedBlackNode<K: Comparable<K>, V>(key: K, value: V, var color: Char) :
        Node<K, V, RedBlackNode<K, V>>(key, value) {

    // Using Strategy pattern
    private val balancer = NodeBalancer<K, V, RedBlackNode<K, V>>()

    fun leftRotate() {
        balancer.leftRotate(this)
    }

    fun rightRotate() {
        balancer.rightRotate(this)
    }

    override fun equals(other: Any?): Boolean {
        return (other is RedBlackNode<*, *>
                && left == other.left
                && right == other.right
                && parent?.key == other.parent?.key
                && parent?.color == other.parent?.color
                && key == other.key
                && value == other.value
                && color == other.color)
    }
}




class AVLNode<K: Comparable<K>, V>(key: K, value: V) :
        Node<K, V, AVLNode<K, V>>(key, value) {

    var height: Int = 1
        get() = kotlin.math.max((left?.height ?: 0), (right?.height ?: 0)) + 1
    var deltaHeight: Int = 0
        get() = (left?.height ?: 0) - (right?.height ?: 0)

    // Using Strategy pattern
    private val balancer = NodeBalancer<K, V, AVLNode<K, V>>()

    fun leftRotate() {
        balancer.leftRotate(this)
    }

    fun rightRotate() {
        balancer.rightRotate(this)
    }

    fun bigLeftRotate() {
        balancer.bigLeftRotate(this)
    }

    fun bigRightRotate() {
        balancer.bigRightRotate(this)
    }

    override fun equals(other: Any?): Boolean {
        return (other is AVLNode<*, *>
                && left == other.left
                && right == other.right
                && parent?.key == other.parent?.key
                && parent?.height == other.parent?.height
                && key == other.key
                && value == other.value
                && height == other.height)
    }
}