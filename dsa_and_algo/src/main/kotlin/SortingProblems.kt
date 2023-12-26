import SortingProblems.InsertionSortList.ListNode
import com.sun.source.tree.Tree
import java.util.*


class SortingProblems {
    internal class ReconstructIternary {
        fun findItinerary(tickets: List<List<String>>): List<String> {
            val targets: MutableMap<String, PriorityQueue<String>> = HashMap()
            val route = mutableListOf<String>()
            for (ticket in tickets) {
                // targets.computeIfAbsent(ticket.get(0), k-> new PriorityQueue()).add(ticket.get(1));
                if (!targets.containsKey(ticket[0])) {
                    val sub = PriorityQueue<String>()
                    sub.add(ticket[1])
                    targets[ticket[0]] = sub
                } else {
                    targets[ticket[0]]!!.add(ticket[1])
                }
            }
            visit("JFK", targets, route)
            return route
        }

        fun visit(airport: String, targets: Map<String, PriorityQueue<String>>, route: MutableList<String>) {
            while (targets.containsKey(airport) && !targets[airport]!!.isEmpty()) {
                visit(targets[airport]!!.poll(), targets, route)
            }
            route[0] = airport
        }
    }


    internal class ThreeSum {
        fun threeSum(nums: IntArray): List<List<Int>> {
            val res: MutableList<List<Int>> = ArrayList()
            Arrays.sort(nums)
            var i = 0
            while (i + 2 < nums.size) {
                if (i > 0 && nums[i - 1] == nums[i]) {
                    i++
                    continue
                }
                var j = i + 1
                var k = nums.size - 1
                val target = -nums[i]
                while (j < k) {
                    if (nums[j] + nums[k] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]))
                        j++
                        k--
                        while (j < k && nums[j] == nums[j - 1]) j++ // skip same result
                        while (j < k && nums[k] == nums[k + 1]) k-- // skip same result
                    } else if (nums[j] + nums[k] < target) {
                        j++
                    } else {
                        k--
                    }
                }
                i++
            }
            return res
        }
    }

    internal class GroupAnagram {
        fun groupAnagrams(words: Array<String>): List<List<String?>?> {
            val map: HashMap<String, MutableList<String>> = HashMap()
            var list: List<Int?>
            for (str in words) {
                val ar = str.toCharArray()
                Arrays.sort(ar)
                ar.groupBy {  }
                val word = String(ar)
                if (!map.containsKey(word)) {
                    map[word] = ArrayList()
                }
                map[word]?.add(str)
            }
            return ArrayList<List<String?>?>(map.values)
        }
    }

    internal class MergeSortedArray {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
            var i = m - 1
            var j = n - 1
            var k = m + n - 1
            while (j >= 0) {
                if (i >= 0 && nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--]
                } else {
                    nums1[k--] = nums2[j--]
                }
            }
        }
    }

    internal class InsertionSortList {
        fun insertionSortList(head: ListNode?): ListNode? {
            val helper = ListNode(0)
            var pre: ListNode?
            var current = head
            while (current != null) {
                pre = helper
                while (pre!!.next != null && pre.next!!.`val` < current.`val`) {
                    pre = pre.next
                }
                val next = current.next
                current.next = pre.next
                pre.next = current
                current = next
            }
            return helper.next
        }

        inner class ListNode {
            var `val` = 0
            var next: ListNode? = null

            internal constructor() {}
            internal constructor(`val`: Int) {
                this.`val` = `val`
            }

            internal constructor(`val`: Int, next: ListNode?) {
                this.`val` = `val`
                this.next = next
            }
        }
    }
    class ListNode {
        var `val` = 0
        var next: ListNode? = null

        internal constructor(`val`: Int) {
            this.`val` = `val`
        }

        internal constructor(`val`: Int, next: ListNode?) {
            this.`val` = `val`
            this.next = next
        }
    }
    internal class SortList {
        fun sortList(head: ListNode?): ListNode? {
            if (head == null || head.next == null) return head

            // step 1. cut the list to two halves
            var prev: ListNode? = null
            var slow = head
            var fast = head
            while (fast?.next != null) {
                prev = slow
                slow = slow!!.next
                fast = fast.next!!.next
            }
            prev!!.next = null

            // step 2. sort each half
            val l1 = sortList(head)
            val l2 = sortList(slow)

            // step 3. merge l1 and l2
            return merge(l1, l2)
        }

        fun merge(l1: ListNode?, l2: ListNode?): ListNode? {
            var l1 = l1
            var l2 = l2
            val l = ListNode(0)
            var p: ListNode? = l
            while (l1 != null && l2 != null) {
                if (l1.`val` < l2.`val`) {
                    p!!.next = l1
                    l1 = l1.next
                } else {
                    p!!.next = l2
                    l2 = l2.next
                }
                p = p.next
            }
            if (l1 != null) p!!.next = l1
            if (l2 != null) p!!.next = l2
            return l.next
        }
    }

    internal class LargestNumber {
         class LargerNumberComparator : Comparator<String> {
            override fun compare(a: String, b: String): Int {
                val order1 = a + b
                val order2 = b + a
                return order2.compareTo(order1)
            }
        }

        fun largestNumber(nums: IntArray): String? {
            // Get input integers as strings.
            val asStrs = arrayOfNulls<String>(nums.size)
            for (i in nums.indices) {
                asStrs[i] = nums[i].toString()
            }

            // Sort strings according to custom comparator.
//            Arrays.sort(asStrs, LargerNumberComparator())
            Arrays.sort(asStrs) { a,b ->
                val order1 = a+b
                val order2 =b+a
                return@sort order1.compareTo(order2)
            }

//            asStrs.sort(){ a,b -> return a.compareTo(b)}

            println(Arrays.toString(asStrs))

            // If, after being sorted, the largest number is `0`, the entire number
            // is zero.
            if (asStrs[0] == "0") {
                return "0"
            }

            // Build largest number from sorted array.
            var largestNumberStr: String? = String()
            for (numAsStr in asStrs) {
                largestNumberStr += numAsStr
            }
            return largestNumberStr
        }
    }

    internal class FindKthLargest {
        fun partition(nums: IntArray, lo: Int, hi: Int): Int {
            val len = nums.size
            var i = lo
            val p = nums[hi]
            for (j in lo until hi) {
                if (nums[j] <= p) {
                    val temp = nums[i]
                    nums[i] = nums[j]
                    nums[j] = temp
                    i++
                }
            }
            val temp = nums[i]
            nums[i] = nums[hi]
            nums[hi] = temp
            return i
        }

        fun findKthLargest(nums: IntArray, k: Int): Int {
            var k = k
            k = nums.size - k
            var hi = nums.size - 1
            var lo = 0
            while (lo < hi) {
                val j = partition(nums, lo, hi)
                if (j > k) {
                    hi = j - 1
                } else if (j < k) {
                    lo = j + 1
                } else {
                    break
                }
            }
            return nums[k]
        }
    }

    fun findKthLargest() {
        val f = FindKthLargest()

        f.findKthLargest(intArrayOf(3,1,4,7,8,5),2)
    }

    data class TreeNode(
        var left: TreeNode,
        var right: TreeNode,
        var `val`: Int,
    )

    internal class binaryTreeLevelOrder {
        fun levelOrder(root: TreeNode?): List<MutableList<Int>> {
            // return iterative(root);
            val list: MutableList<MutableList<Int>> = LinkedList()
            recursive(root, 0, list)
            return list
        }

        fun recursive(root: TreeNode?, level: Int, list: MutableList<MutableList<Int>>) {
            if (root == null) return
            if (level >= list.size) {
                list.add(LinkedList())
            }
            list[level].add(root.`val`)
            recursive(root.left, level + 1, list)
            recursive(root.right, level + 1, list)
        }

        fun iterative(root: TreeNode?): List<List<Int>> {
            val q: Queue<TreeNode> = LinkedList<TreeNode>()
            val list: MutableList<List<Int>> = LinkedList()
            if (root == null) return list
            q.offer(root)
            while (!q.isEmpty()) {
                val levelNum = q.size
                val subList: MutableList<Int> = LinkedList()
                for (i in 0 until levelNum) {
                    if (q.peek().left != null) q.offer(q.peek().left)
                    if (q.peek().right != null) q.offer(q.peek().right)
                    subList.add(q.poll().`val`)
                }
                list.add(subList)
            }
            return list
        }
    }
}

fun main() {
    val sol = SortingProblems()

    sol.findKthLargest()
}