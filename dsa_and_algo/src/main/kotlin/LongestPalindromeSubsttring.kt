import java.util.*
import kotlin.math.max


class LongestPalindromeSubsttring {
}

class Solution {
    var start = 0
    var end = 0
    fun longestPalindrome(s: String): String {


        fun check(q: Int, p: Int) {
            var i = q
            var j = p
            while (i >= 0 && j < s.length && s[i] == s[j]) {
                i--
                j++
            }
            if (j - i > end - start) {
                end = j
                start = i + 1
            }
        }

        for (i in s.indices) {
            if (i != 0) check(i - 1, i)
            check(i, i)
        }
        return s.substring(start, end)
    }

    fun check(start: Int, end: Int, s: String) {
        val dp = Array(s.length + 1) {Array(s.length + 1) {0}}
        var i = start
        var j = end
        while (i >= 0 && j < s.length && s[i] == s[j]) {
            i--
            j++
        }
        if (j - i > this.end - this.start) {
            this.end = j
            this.start = i + 1
        }
    }



    var combination: MutableList<String>? = null
    fun generateParenthesis(n: Int): List<String>? {
        combination = ArrayList()
        helper(CharArray(2 * n), 0)
        return combination
    }

    private fun helper(comb: CharArray, index: Int) {
        if (index >= comb.size) {
            if (validate(comb)) combination!!.add(String(comb))
        } else {
            comb[index] = '('
            helper(comb, index + 1)
            comb[index] = ')'
            helper(comb, index + 1)
        }
    }

    private fun validate(comb: CharArray): Boolean {
        var balance = 0
        for (c in comb) {
            if (c == '(') balance++
            if (c == ')') balance--
            if (balance < 0) return false
        }
        return balance == 0
    }



    fun trap(height: IntArray?): Int {

        val n = height!!.size
        if (height == null || n <= 1) return 0
        val lb = IntArray(n)
        val rb = IntArray(n)
        var trappedWater = 0
        var maxLeftBlock = 0
        var maxRightBlock = 0
        for (i in 0 until n) {
            if (height[i] > maxLeftBlock) maxLeftBlock = height[i]
            if (height[n - 1 - i] > maxRightBlock) maxRightBlock = height[n - 1 - i]
            lb[i] = maxLeftBlock
            rb[n - 1 - i] = maxRightBlock
        }
        for (i in 1 until n - 1) {
            trappedWater = if (Math.min(lb[i], rb[i]) - height[i] > 0) trappedWater + Math.min(
                lb[i],
                rb[i]
            ) - height[i] else trappedWater
        }
        return trappedWater
    }

    internal class WildcardMatching
    {
        fun isMatch(str: String, pattern: String): Boolean {
            var s = 0
            var p = 0
            var match = 0
            var starIdx = -1
            while (s < str.length) {
                // advancing both pointers
                if (p < pattern.length && (pattern[p] == '?' || str[s] == pattern[p])) {
                    s++
                    p++
                } else if (p < pattern.length && pattern[p] == '*') {
                    starIdx = p
                    match = s
                    p++
                } else if (starIdx != -1) {
                    p = starIdx + 1
                    match++
                    s = match
                } else return false
            }

            //check for remaining characters in pattern
            while (p < pattern.length && pattern[p] == '*') p++
            return p == pattern.length
        }
    }

    fun maxSubArray(nums: IntArray): Int {
        var maxSubArray = nums[0]
        var currentSubArray = nums[0]
        for (i in 1 until nums.size) {
            currentSubArray = Math.max(nums[i], currentSubArray + nums[i])
            maxSubArray = Math.max(maxSubArray, currentSubArray)
        }
        return maxSubArray
    }

    fun uniquePaths(m: Int, n: Int): Int {
        val d = Array(m) { IntArray(n){1} }
//        for (arr in d) {
//            Arrays.fill(arr, 1)
//        }
        for (col in 1 until m) {
            for (row in 1 until n) {
                d[col][row] = d[col - 1][row] + d[col][row - 1]
            }
        }
        return d[m - 1][n - 1]
    }

    fun minPathSum(grid: Array<IntArray>): Int {
        for (i in grid.indices.reversed()) {
            for (j in grid[0].indices.reversed()) {
                if (i == grid.size - 1 && j != grid[0].size - 1) grid[i][j] =
                    grid[i][j] + grid[i][j + 1] else if (j == grid[0].size - 1 && i != grid.size - 1) grid[i][j] =
                    grid[i][j] + grid[i + 1][j] else if (j != grid[0].size - 1 && i != grid.size - 1) grid[i][j] =
                    grid[i][j] + Math.min(
                        grid[i + 1][j], grid[i][j + 1]
                    )
            }
        }
        return grid[0][0]
    }

    fun numDecodings(s: String): Int {
        if (s[0] == '0') {
            return 0
        }
        val n = s.length
        var twoBack = 1
        var oneBack = 1
        for (i in 1 until n) {
            var current = 0
            if (s[i] != '0') {
                current = oneBack
            }
            val twoDigit = s.substring(i - 1, i + 1).toInt()
            if (twoDigit in 10..26) {
                current += twoBack
            }
            twoBack = oneBack
            oneBack = current
        }
        return oneBack
    }


    internal class decodeWays {
        var memo: MutableMap<Int, Int> = HashMap()
        fun numDecodings(s: String): Int {
            return recursiveWithMemo(0, s)
        }

        private fun recursiveWithMemo(index: Int, str: String): Int {
            // Have we already seen this substring?
            if (memo.containsKey(index)) {
                return memo[index]!!
            }

            // If you reach the end of the string
            // Return 1 for success.
            if (index == str.length) {
                return 1
            }

            // If the string starts with a zero, it can't be decoded
            if (str[index] == '0') {
                return 0
            }
            if (index == str.length - 1) {
                return 1
            }
            var ans = recursiveWithMemo(index + 1, str)
            if (str.substring(index, index + 2).toInt() <= 26) {
                ans += recursiveWithMemo(index + 2, str)
            }

            // Save for memoization
            memo[index] = ans
            return ans
        }
    }

    fun bst(start: Int, end: Int): List<TreeNode?> {
        val list: MutableList<TreeNode?> = ArrayList()
        if (start > end) {
            list.add(null)
        }
        for (i in start..end) {
            val leftlist = bst(start, i - 1)
            val rightlist = bst(i + 1, end)
            for (left in leftlist) {
                for (right in rightlist) {
                    val root: TreeNode = TreeNode(i)
                    root.left = left
                    root.right = right
                    list.add(root)
                }
            }
        }
        return list
    }
    class TreeNode {
        var `val` = 0
        var left: TreeNode? = null
        var right: TreeNode? = null

        constructor() {}
        constructor(`val`: Int) {
            this.`val` = `val`
        }

        constructor(`val`: Int, left: TreeNode?, right: TreeNode?) {
            this.`val` = `val`
            this.left = left
            this.right = right
        }
    }

    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        val memo = Array(s1.length) { IntArray(s2.length) }
        for (i in 0 until s1.length) {
            for (j in 0 until s2.length) {
                memo[i][j] = -1
            }
        }
        return findInter(s1, 0, s2, 0, s3, 0, memo)
    }

    fun findInter(s1: String, i: Int, s2: String, j: Int, s3: String, k: Int, memo: Array<IntArray>): Boolean {
        if (i == s1.length) {
            return s2.substring(j) == s3.substring(k)
        }
        if (j == s2.length) {
            return s1.substring(i) == s3.substring(k)
        }
        if (memo[i][j] >= 0) {
            return if (memo[i][j] == 1) true else false
        }
        var ans = false
        if (s1[i] == s3[k] && findInter(s1, i + 1, s2, j, s3, k + 1, memo) || s2[j] == s3[k] &&
            findInter(
                s1,
                i,
                s2,
                j + 1,
                s3,
                k + 1,
                memo
            )
        ) {
            ans = true
        }
        memo[i][j] = if (ans) 1 else 0
        return ans
    }

    fun generate(numRows: Int): List<MutableList<Int>> {
        val triangle: MutableList<MutableList<Int>> = ArrayList()

        // Base case; first row is always [1].
        triangle.add(ArrayList())
        triangle[0].add(1)
        for (rowNum in 1 until numRows) {
            val row: MutableList<Int> = ArrayList()
            val prevRow: List<Int> = triangle[rowNum - 1]

            // The first row element is always 1.
            row.add(1)

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (j in 1 until rowNum) {
                row.add(prevRow[j - 1] + prevRow[j])
            }

            // The last row element is always 1.
            row.add(1)
            triangle.add(row)
        }
        return triangle
    }

    fun getRow(rowIndex: Int): MutableList<Array<Int>> {
        val arr = Array<Int>(rowIndex + 1) { 0}
        arr[0] = 1
        for (i in 1..rowIndex) {
            for (j in i downTo 1) {
                arr[j] = arr[j]!! + arr[j - 1]!!
            }
        }
        return mutableListOf(arr)
    }

    fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        for (i in prices.indices) {
            if (minPrice > prices[i]) {
                minPrice = prices[i]
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice)
            }
        }
        return maxProfit
    }

    internal class TreeNode2 {
        var `val` = 0
        var left: TreeNode2? = null
        var right: TreeNode2? = null

        constructor() {}
        constructor(`val`: Int) {
            this.`val` = `val`
        }

        constructor(`val`: Int, left: TreeNode2?, right: TreeNode2?) {
            this.`val` = `val`
            this.left = left
            this.right = right
        }
    }

    internal class Solution {
        var max_sum = Int.MIN_VALUE
        fun max_gain(node: TreeNode2?): Int {
            if (node == null) return 0

            // max sum on the left and right sub-trees of node
            val left_gain = Math.max(max_gain(node.left), 0)
            val right_gain = Math.max(max_gain(node.right), 0)

            // the price to start a new path where `node` is a highest node
            val price_newpath = node.`val` + left_gain + right_gain

            // update max_sum if it's better to start a new path
            max_sum = Math.max(max_sum, price_newpath)

            // for recursion :
            // return the max gain if continue the same path
            return node.`val` + Math.max(left_gain, right_gain)
        }

        fun maxPathSum(root: TreeNode2?): Int {
            max_gain(root)
            return max_sum
        }
    }

    fun partition(s: String): List<List<String>>? {
        val len = s.length
        val res: MutableList<List<String>> = ArrayList()
        val dp = Array(len) { BooleanArray(len) }
        helper(res, s, 0, ArrayList(), dp)
        return res
    }

    private fun helper(
        res: MutableList<List<String>>,
        s: String,
        start: Int,
        currList: MutableList<String>,
        dp: Array<BooleanArray>
    ) {
        if (start >= s.length) {
            res.add(ArrayList(currList))
            // return;
        }
        for (end in start until s.length) {
            if (s[start] == s[end] && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true
                currList.add(s.substring(start, end + 1))
                helper(res, s, end + 1, currList, dp)
                currList.removeAt(currList.size - 1)
            }
        }
    }
    internal class WordBreak {
        fun wordBreak(s: String, wordList: List<String>): Boolean {
            return searchFrom(s, 0, Trie(wordList), BooleanArray(s.length))
        }

        private fun searchFrom(s: String, start: Int, root: Trie, searched: BooleanArray): Boolean {
            var curr: Trie? = root
            var found = false
            for (pos in start until s.length) {
                curr = curr?.children?.get(s[pos].code - 'a'.code)
                if (found || curr == null) break
                if (curr.endOfWord) {
                    if (searched[pos]) continue
                    if (pos == s.length - 1) {
                        return true
                    }
                    searched[pos] = true
                    found = searchFrom(s, pos + 1, root, searched)
                }
            }
            return found
        }

        internal inner class Trie {
            var children: Array<Trie?> = arrayOfNulls<Trie>(26)
            var endOfWord = false

            constructor() {}
            constructor(wordList: List<String>) {
                for (word in wordList) {
                    add(word)
                }
            }

            fun add(word: String) {
                var curr: Trie = this
                for (c in word.toCharArray()) {
                    if (curr.children[c.code - 'a'.code] == null) {
                        curr.children[c.code - 'a'.code] = Trie()
                    }
                    curr = curr.children[c.code - 'a'.code]!!
                }
                curr.endOfWord = true
            }
        }
    }

    internal class WordBreak2 {
        var root: Trie? = null
        var result: MutableList<String>? = null
        fun wordBreak(s: String, wordDict: List<String>): List<String> {
            root = Trie()
            result = ArrayList()
            buildTrie(wordDict)
            searchWord(StringBuilder(), s)
            return result as ArrayList<String>
        }

        fun searchWord(sb: StringBuilder, s: String) {
            var curr = root
            var sbIndex = sb.length
            for (i in 0 until s.length) {
                val c = s[i]
                if (curr!!.child[c.code - 'a'.code] == null) return
                curr = curr.child[c.code - 'a'.code]
                if (curr!!.isWord == true) {
                    if (sb.isEmpty()) {
                        sb.append(curr.word)
                    } else {
                        sbIndex = sb.length
                        sb.append(" " + curr.word)
                    }
                    if (i == s.length - 1) {
                        result!!.add(sb.toString())
                        return
                    }
                    searchWord(sb, s.substring(i + 1))
                    sb.delete(sbIndex, sb.length)
                }
            }
        }

        fun buildTrie(wordDict: List<String>) {
            for (word in wordDict) {
                var curr = root
                for (c in word.toCharArray()) {
                    if (curr!!.child[c.code - 'a'.code] == null) {
                        curr.child[c.code - 'a'.code] = Trie()
                    }
                    curr = curr.child[c.code - 'a'.code]
                }
                curr!!.isWord = true
                curr.word = word
            }
        }

        internal inner class Trie {
            var child = arrayOfNulls<Trie>(26)
            var isWord = false
            var word: String? = null
        }
    }


    internal class DiffWaysToCompute {
        fun diffWaysToCompute(input: String): List<Int> {
            val ret: MutableList<Int> = LinkedList()
            for (i in input.indices) {
                if (input[i] == '-' || input[i] == '*' || input[i] == '+') {
                    val part1 = input.substring(0, i)
                    val part2 = input.substring(i + 1)
                    val part1Ret = diffWaysToCompute(part1)
                    val part2Ret = diffWaysToCompute(part2)
                    part1Ret.forEach { p1 ->
                        part2Ret.forEach { p2 ->
                            var c = 0
                            when (input[i]) {
                                '+' -> c = p1 + p2
                                '-' -> c = p1 - p2
                                '*' -> c = p1 * p2
                            }
                            ret.add(c)
                        }
                    }
                }
            }
            if (ret.size == 0) {
                ret.add(Integer.valueOf(input))
            }
            return ret
        }
    }

    internal class PerfectSquare {
        fun numSquares(n: Int): Int {
            val queue: Queue<Int> = LinkedList()
            val reviewedSet: MutableSet<Int> = HashSet()
            if (n > 0) queue.offer(n)
            var level = 0
            while (!queue.isEmpty()) {
                level++
                val size = queue.size
                for (i in 0 until size) {
                    val `val` = queue.poll()
                    // System.out.println(for)
                    // if(!reviewedSet.add(val)) continue;
                    if (reviewedSet.contains(`val`)) {
                        continue
                    } else {
                        reviewedSet.add(`val`)
                    }
                    var j = 1
                    while (j <= Math.sqrt(`val`.toDouble())) {
                        if (`val` - j * j == 0) return level
                        queue.offer(`val` - j * j)
                        j++
                    }
                }
            }
            return level
        }
    }

    internal class LongestIncreasingSubsequence {
        fun lengthOfLIS(nums: IntArray): Int {
            if (nums.size == 0) {
                return 0
            }
            val dp = IntArray(nums.size)
            dp[0] = 1
            var maxans = 1
            for (i in dp.indices) {
                var maxval = 0
                for (j in 0 until i) {
                    if (nums[i] > nums[j]) {
                        maxval = Math.max(maxval, dp[j])
                    }
                }
                dp[i] = maxval + 1
                maxans = Math.max(maxans, dp[i])
            }
            return maxans
        }
    }

    fun getMax(num: Array<Int>, index: Int, prevIndex: Int, dp: IntArray): Int {
        if (index < 0) return dp[prevIndex]
        if(dp[index]!=0) return dp[index]
        var max = 0
        if (num[index] < num[prevIndex] && dp[index] < dp[prevIndex] + 1) {
            dp[index] = dp[prevIndex] + 1
        } else {
            dp[index] = 1
        }
        max = max(dp[index], max(
            getMax(num, index - 1, prevIndex, dp),
            getMax(num, index - 1, index, dp)
        )
        )
        return max
    }

    fun getmax2(nums: Array<Int>, i: Int, prev: Int ): Int {
        if(i >= nums.size) return 0
        var take = 0
        var dontTake = getmax2(nums, i+1, prev)
        if(nums[i]>prev) {
            take = getmax2(nums, i+1, nums[i]) + 1
        }

        return max(take, dontTake)
    }

    fun getMax3(nums: Array<Int>,dp: Array<IntArray>,i: Int, prev_i: Int ): Int{
        if(i >= nums.size) return 0
        if(dp[i][prev_i+1]!=-1) return dp[i][prev_i+1]
        var take = 0
        var dontTake = getMax3(nums, dp, i+1, prev_i)
        if(prev_i == -1 || nums[i]>nums[prev_i]) {
            take = getMax3(nums, dp, i+1, i) + 1
        }

        dp[i][prev_i+1]=max(take, dontTake)

        return dp[i][prev_i+1]
    }


    fun coinChangeIterate(coins: Array<Int>, amount: Int): Int {
        if (amount == 0) return 0
        var minCoin = Int.MAX_VALUE
        for (i in coins.indices) {
            if (coins[i] <= amount) {
                var ans = coinChangeIterate(coins, amount - coins[i])

                if(ans != Int.MAX_VALUE) minCoin = Math.min(minCoin, 1+ ans)
            }
        }
        return minCoin
    }

    internal class LongestIncreasingPath {
        private var m = 0
        private var n = 0
        fun longestIncreasingPath(matrix: Array<IntArray>): Int {
            if(matrix.isEmpty()) return 0

            m = matrix.size
            n = matrix[0].size

            val memo = Array(m) {IntArray(n){0}}

            var ans = 0

            for (i in 0 until m) for(j in 0 until n) ans = Math.max(ans, dfs(matrix, memo, i, j))

            return ans
        }

        private fun dfs(matrix: Array<IntArray>, memo: Array<IntArray>, i:Int, j:Int): Int {
            if(memo[i][j] != 0) return memo[i][j]

            for(d in dirs) {
                val x = i+d[0]
                val y = j+d[1]

                if(0 <= x && x < m && 0 <= y && y<n && matrix[x][y] > matrix[i][j])
                    memo[i][j] = Math.max(memo[i][j], dfs(matrix, memo, x, y))
            }

            return ++memo[i][j]

        }

        companion object {
            private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
        }
    }
    fun longestIncreasingPathRun() {
        val soln = LongestIncreasingPath()

        println(soln.longestIncreasingPath(arrayOf(intArrayOf(9,9,4),intArrayOf(6,6,8),intArrayOf(2,1,1))))
    }


    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        var len = 0
        for (num in nums) {
            var i = Arrays.binarySearch(dp, 0, len, num)
            if (i < 0) {
                i = -(i + 1)
            }
            dp[i] = num
            if (i == len) {
                len++
            }
        }
        return len
    }

    fun maxEnvelopes(envelopes: Array<IntArray>): Int {
        // sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes) { arr1, arr2 ->
            if (arr1[0] == arr2[0]) {
                arr2[1] - arr1[1]
            } else {
                arr1[0] - arr2[0]
            }
        }
        // extract the second dimension and run LIS
        val secondDim = IntArray(envelopes.size)
        for (i in envelopes.indices) secondDim[i] = envelopes[i][1]
        return lengthOfLIS(secondDim)
    }

    fun getMaxEnvelope() {
        print(maxEnvelopes(arrayOf(intArrayOf(5,4), intArrayOf(6,4), intArrayOf(6,7), intArrayOf(2,3))))
    }

    fun canPartition(nums: IntArray): Boolean {
        var totalSum = 0
        for (num in nums) {
            totalSum += num
        }
        if (totalSum % 2 != 0) return false
        val halfSum = totalSum / 2
        val n = nums.size
        val memo = Array(n + 1) {
            arrayOfNulls<Boolean>(
                halfSum + 1
            )
        }
        return dfs(nums, n - 1, halfSum, memo)
    }

    private fun dfs(nums: IntArray, n: Int, halfSum: Int, memo: Array<Array<Boolean?>>): Boolean {
        if (halfSum == 0) return true
        if (halfSum < 0 || n == 0) return false
        memo[n][halfSum]?.let {
            return it
        }
        val result = dfs(nums, n - 1, halfSum - nums[n - 1], memo) || dfs(nums, n - 1, halfSum, memo)
        memo[n][halfSum] = result
        return result
    }

    fun canPartition() {
        println( canPartition(intArrayOf(1,5,11,5)));
    }

    internal class ConcatenatedWords {
        internal inner class TrieNode {
            val sons: Array<TrieNode?> = arrayOfNulls(26)
            var isEnd = false
        }

        fun findAllConcatenatedWordsInADict(words: Array<String>?): List<String> {
            val res: MutableList<String> = ArrayList()
            if (words.isNullOrEmpty()) {
                return res
            }
            val root = TrieNode()
            for (word in words) {
                if (word.isEmpty()) {
                    continue
                }
//                addWord(word, root)
                addWord(root, word)
            }
            for (word in words) {
                if (word.isEmpty()) {
                    continue
                }
//                if (testWord(word.toCharArray(), 0, root, 0)) {
//                    res.add(word)
//                }

                if(testWord(root, word, 0, 0)) {
                    res.add(word)
                }
            }
            return res
        }

//        private fun addWord(word: String, root: TrieNode?) {
//            var root = root ?: return
//
//            for (c in word.toCharArray()) {
//                val i = c.code - 'a'.code
//                if (root.sons[i] == null) {
//                    root.sons[i] = TrieNode()
//                }
//                root = root.sons[i]!!
//            }
//            root.isEnd = true
//        }
//
//        fun testWord(ch: CharArray, index: Int, root: TrieNode?, count: Int): Boolean {
//            var curr = root
//            val n = ch.size
//            for (i in ch.indices) {
//                if (curr!!.sons[ch[i].code - 'a'.code] == null) {
//                    return false
//                }
//                if (curr.sons[ch[i].code - 'a'.code]!!.isEnd) {
//                    if (i == n - 1) {
//                        return count >= 1
//                    }
//                    if (testWord(ch, i + 1, root, count + 1)) {
//                        return true
//                    }
//                }
//                curr = curr.sons[ch[i].code - 'a'.code]
//            }
//            return false
//        }


        private fun addWord(root: TrieNode?, word: String) {
            if(word.isNullOrEmpty()) return

            var curr = root ?: return

            word.toCharArray().forEach {
                val i = it.code - 'a'.code
                if(curr.sons[i]==null) {
                    curr.sons[i] = TrieNode()
                }


                curr = curr.sons[i]!!
            }

            curr.isEnd = true
        }

        private fun testWord(root: TrieNode?, word:String, index: Int, count: Int): Boolean{
            if(word.isNullOrEmpty()) return false
            var curr = root ?: return false

            for (i in index until word.toCharArray().size) {
                if(curr.sons[word[i].code - 'a'.code] == null) {
                    return false
                }

                if(curr.sons[word[i].code - 'a'.code]!!.isEnd) {
                    if(i == word.length-1) {
                        return count>= 1
                    }

                    if(testWord(root, word, i+1, count+1)) {
                        return true;
                    }
                }

                curr = curr.sons[word[i].code - 'a'.code]!!
            }

            return false
        }
    }

    internal class ConcatenatedWords2 {
        internal inner class Trie {
            var letterTrie: Array<Trie?> = arrayOfNulls(26)
            var isWord: Boolean = false

        }

        fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
            val result = ArrayList<String>()

            // initialize the trie
            val trie: Trie = Trie()
            for (word in words) {
                insert(word, trie)
            }
            for (word in words) {
                if (isConcatenatedWord(word, trie, 0, arrayOfNulls(word.length), 0)) {
                    result.add(word)
                }
            }
            return result
        }

        private fun isConcatenatedWord(
            word: String,
            trie: Trie,
            i: Int,
            memo: Array<Boolean?>,
            subWordCount: Int
        ): Boolean {
            if (i == word.length) {
                return subWordCount > 1 // more than 1 sub words
            }
            if (memo[i] != null) {
                return memo[i]!!
            }
            var dict: Trie? = trie
            for (k in i until word.length) {
                dict = dict!!.letterTrie[word[k].code - 'a'.code]
                if (dict == null) {
                    memo[i] = false
                    return memo[i]!!
                }
                if (dict.isWord) {
                    if (isConcatenatedWord(word, trie, k + 1, memo, subWordCount + 1)) {
                        return true
                    }
                }
            }
            return false.also { memo[i] = it }
        }

        private fun insert(word: String, trie: Trie) {
            var trie: Trie? = trie
            for (c in word.toCharArray()) {
                if (trie!!.letterTrie[c.code - 'a'.code] == null) {
                    trie.letterTrie[c.code - 'a'.code] = Trie()
                }
                trie = trie.letterTrie[c.code - 'a'.code]
            }
            trie!!.isWord = true
        }
    }


    fun getConcatnatedWords() {
        val c = ConcatenatedWords2()
        println(
            c.findAllConcatenatedWordsInADict(
                arrayOf(
//                    "cat",
//                    "cats",
//                    "catsdogcats",
//                    "dog",
//                    "dogcatsdog",
//                    "hippopotamuses",
//                    "rat",
//                    "ratcatdogcat"
                    "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","aaaaaaaaaaa","aaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaaaaa","aaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaz","b","bb","bbb","bbbb","bbbbb","bbbbbb","bbbbbbb","bbbbbbbb","bbbbbbbbb","bbbbbbbbbb","bbbbbbbbbbb","bbbbbbbbbbbb","bbbbbbbbbbbbb","bbbbbbbbbbbbbb","bbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbz","c","cc","ccc","cccc","ccccc","cccccc","ccccccc","cccccccc","ccccccccc","cccccccccc","ccccccccccc","cccccccccccc","ccccccccccccc","cccccccccccccc","ccccccccccccccc","cccccccccccccccc","ccccccccccccccccc","cccccccccccccccccc","ccccccccccccccccccc","cccccccccccccccccccc","ccccccccccccccccccccc","cccccccccccccccccccccc","ccccccccccccccccccccccc","cccccccccccccccccccccccc","ccccccccccccccccccccccccc","cccccccccccccccccccccccccc","ccccccccccccccccccccccccccc","cccccccccccccccccccccccccccc","ccccccccccccccccccccccccccccc","cccccccccccccccccccccccccccccc","cccccccccccccccccccccccccccccz","d","dd","ddd","dddd","ddddd","dddddd","ddddddd","dddddddd","ddddddddd","dddddddddd","ddddddddddd","dddddddddddd","ddddddddddddd","dddddddddddddd","ddddddddddddddd","dddddddddddddddd","ddddddddddddddddd","dddddddddddddddddd","ddddddddddddddddddd","dddddddddddddddddddd","ddddddddddddddddddddd","dddddddddddddddddddddd","ddddddddddddddddddddddd","dddddddddddddddddddddddd","ddddddddddddddddddddddddd","dddddddddddddddddddddddddd","ddddddddddddddddddddddddddd","dddddddddddddddddddddddddddd","ddddddddddddddddddddddddddddd","dddddddddddddddddddddddddddddd","dddddddddddddddddddddddddddddz","e","ee","eee","eeee","eeeee","eeeeee","eeeeeee","eeeeeeee","eeeeeeeee","eeeeeeeeee","eeeeeeeeeee","eeeeeeeeeeee","eeeeeeeeeeeee","eeeeeeeeeeeeee","eeeeeeeeeeeeeee","eeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeeeeee","eeeeeeeeeeeeeeeeeeeeeeeeeeeeez","f","ff","fff","ffff","fffff","ffffff","fffffff","ffffffff","fffffffff","ffffffffff","fffffffffff","ffffffffffff","fffffffffffff","ffffffffffffff","fffffffffffffff","ffffffffffffffff","fffffffffffffffff","ffffffffffffffffff","fffffffffffffffffff","ffffffffffffffffffff","fffffffffffffffffffff","ffffffffffffffffffffff","fffffffffffffffffffffff","ffffffffffffffffffffffff","fffffffffffffffffffffffff","ffffffffffffffffffffffffff","fffffffffffffffffffffffffff","ffffffffffffffffffffffffffff","fffffffffffffffffffffffffffff","ffffffffffffffffffffffffffffff","fffffffffffffffffffffffffffffz","g","gg","ggg","gggg","ggggg","gggggg","ggggggg","gggggggg","ggggggggg","gggggggggg","ggggggggggg","gggggggggggg","ggggggggggggg","gggggggggggggg","ggggggggggggggg","gggggggggggggggg","ggggggggggggggggg","gggggggggggggggggg","ggggggggggggggggggg","gggggggggggggggggggg","ggggggggggggggggggggg","gggggggggggggggggggggg","ggggggggggggggggggggggg","gggggggggggggggggggggggg","ggggggggggggggggggggggggg","gggggggggggggggggggggggggg","ggggggggggggggggggggggggggg","gggggggggggggggggggggggggggg","ggggggggggggggggggggggggggggg","gggggggggggggggggggggggggggggg","gggggggggggggggggggggggggggggz","h","hh","hhh","hhhh","hhhhh","hhhhhh","hhhhhhh","hhhhhhhh","hhhhhhhhh","hhhhhhhhhh","hhhhhhhhhhh","hhhhhhhhhhhh","hhhhhhhhhhhhh","hhhhhhhhhhhhhh","hhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","hhhhhhhhhhhhhhhhhhhhhhhhhhhhhz","i","ii","iii","iiii","iiiii","iiiiii","iiiiiii","iiiiiiii","iiiiiiiii","iiiiiiiiii","iiiiiiiiiii","iiiiiiiiiiii","iiiiiiiiiiiii","iiiiiiiiiiiiii","iiiiiiiiiiiiiii","iiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiiiiiii","iiiiiiiiiiiiiiiiiiiiiiiiiiiiiz","j","jj","jjj","jjjj","jjjjj","jjjjjj","jjjjjjj","jjjjjjjj","jjjjjjjjj","jjjjjjjjjj","jjjjjjjjjjj","jjjjjjjjjjjj","jjjjjjjjjjjjj","jjjjjjjjjjjjjj","jjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjz","k","kk","kkk","kkkk","kkkkk","kkkkkk","kkkkkkk","kkkkkkkk","kkkkkkkkk","kkkkkkkkkk","kkkkkkkkkkk","kkkkkkkkkkkk","kkkkkkkkkkkkk","kkkkkkkkkkkkkk","kkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkz","l","ll","lll","llll","lllll","llllll","lllllll","llllllll","lllllllll","llllllllll","lllllllllll","llllllllllll","lllllllllllll","llllllllllllll","lllllllllllllll","llllllllllllllll","lllllllllllllllll","llllllllllllllllll","lllllllllllllllllll","llllllllllllllllllll","lllllllllllllllllllll","llllllllllllllllllllll","lllllllllllllllllllllll","llllllllllllllllllllllll","lllllllllllllllllllllllll","llllllllllllllllllllllllll","lllllllllllllllllllllllllll","llllllllllllllllllllllllllll","lllllllllllllllllllllllllllll","llllllllllllllllllllllllllllll","lllllllllllllllllllllllllllllz","m","mm","mmm","mmmm","mmmmm","mmmmmm","mmmmmmm","mmmmmmmm","mmmmmmmmm","mmmmmmmmmm","mmmmmmmmmmm","mmmmmmmmmmmm","mmmmmmmmmmmmm","mmmmmmmmmmmmmm","mmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm","mmmmmmmmmmmmmmmmmmmmmmmmmmmmmz","n","nn","nnn","nnnn","nnnnn","nnnnnn","nnnnnnn","nnnnnnnn","nnnnnnnnn","nnnnnnnnnn","nnnnnnnnnnn","nnnnnnnnnnnn","nnnnnnnnnnnnn","nnnnnnnnnnnnnn","nnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn","nnnnnnnnnnnnnnnnnnnnnnnnnnnnnz","o","oo","ooo","oooo","ooooo","oooooo","ooooooo","oooooooo","ooooooooo","oooooooooo","ooooooooooo","oooooooooooo","ooooooooooooo","oooooooooooooo","ooooooooooooooo","oooooooooooooooo","ooooooooooooooooo","oooooooooooooooooo","ooooooooooooooooooo","oooooooooooooooooooo","ooooooooooooooooooooo","oooooooooooooooooooooo","ooooooooooooooooooooooo","oooooooooooooooooooooooo","ooooooooooooooooooooooooo","oooooooooooooooooooooooooo","ooooooooooooooooooooooooooo","oooooooooooooooooooooooooooo","ooooooooooooooooooooooooooooo","oooooooooooooooooooooooooooooo","oooooooooooooooooooooooooooooz","p","pp","ppp","pppp","ppppp","pppppp","ppppppp","pppppppp","ppppppppp","pppppppppp","ppppppppppp","pppppppppppp","ppppppppppppp","pppppppppppppp","ppppppppppppppp","pppppppppppppppp","ppppppppppppppppp","pppppppppppppppppp","ppppppppppppppppppp","pppppppppppppppppppp","ppppppppppppppppppppp","pppppppppppppppppppppp","ppppppppppppppppppppppp","pppppppppppppppppppppppp","ppppppppppppppppppppppppp","pppppppppppppppppppppppppp","ppppppppppppppppppppppppppp","pppppppppppppppppppppppppppp","ppppppppppppppppppppppppppppp","pppppppppppppppppppppppppppppp","pppppppppppppppppppppppppppppz","q","qq","qqq","qqqq","qqqqq","qqqqqq","qqqqqqq","qqqqqqqq","qqqqqqqqq","qqqqqqqqqq","qqqqqqqqqqq","qqqqqqqqqqqq","qqqqqqqqqqqqq","qqqqqqqqqqqqqq","qqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq","qqqqqqqqqqqqqqqqqqqqqqqqqqqqqz","r","rr","rrr","rrrr","rrrrr","rrrrrr","rrrrrrr","rrrrrrrr","rrrrrrrrr","rrrrrrrrrr","rrrrrrrrrrr","rrrrrrrrrrrr","rrrrrrrrrrrrr","rrrrrrrrrrrrrr","rrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrrrrrr","rrrrrrrrrrrrrrrrrrrrrrrrrrrrrz","s","ss","sss","ssss","sssss","ssssss","sssssss","ssssssss","sssssssss","ssssssssss","sssssssssss","ssssssssssss","sssssssssssss","ssssssssssssss","sssssssssssssss","ssssssssssssssss","sssssssssssssssss","ssssssssssssssssss","sssssssssssssssssss","ssssssssssssssssssss","sssssssssssssssssssss","ssssssssssssssssssssss","sssssssssssssssssssssss","ssssssssssssssssssssssss","sssssssssssssssssssssssss","ssssssssssssssssssssssssss","sssssssssssssssssssssssssss","ssssssssssssssssssssssssssss","sssssssssssssssssssssssssssss","ssssssssssssssssssssssssssssss","sssssssssssssssssssssssssssssz","t","tt","ttt","tttt","ttttt","tttttt","ttttttt","tttttttt","ttttttttt","tttttttttt","ttttttttttt","tttttttttttt","ttttttttttttt","tttttttttttttt","ttttttttttttttt","tttttttttttttttt","ttttttttttttttttt","tttttttttttttttttt","ttttttttttttttttttt","tttttttttttttttttttt","ttttttttttttttttttttt","tttttttttttttttttttttt","ttttttttttttttttttttttt","tttttttttttttttttttttttt","ttttttttttttttttttttttttt","tttttttttttttttttttttttttt","ttttttttttttttttttttttttttt","tttttttttttttttttttttttttttt","ttttttttttttttttttttttttttttt","tttttttttttttttttttttttttttttt","tttttttttttttttttttttttttttttz","u","uu","uuu","uuuu","uuuuu","uuuuuu","uuuuuuu","uuuuuuuu","uuuuuuuuu","uuuuuuuuuu","uuuuuuuuuuu","uuuuuuuuuuuu","uuuuuuuuuuuuu","uuuuuuuuuuuuuu","uuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuuuuuu","uuuuuuuuuuuuuuuuuuuuuuuuuuuuuz","v","vv","vvv","vvvv","vvvvv","vvvvvv","vvvvvvv","vvvvvvvv","vvvvvvvvv","vvvvvvvvvv","vvvvvvvvvvv","vvvvvvvvvvvv","vvvvvvvvvvvvv","vvvvvvvvvvvvvv","vvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv","vvvvvvvvvvvvvvvvvvvvvvvvvvvvvz","w","ww","www","wwww","wwwww","wwwwww","wwwwwww","wwwwwwww","wwwwwwwww","wwwwwwwwww","wwwwwwwwwww","wwwwwwwwwwww","wwwwwwwwwwwww","wwwwwwwwwwwwww","wwwwwwwwwwwwwww","wwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwwwwwww","wwwwwwwwwwwwwwwwwwwwwwwwwwwwwz","x","xx","xxx","xxxx","xxxxx","xxxxxx","xxxxxxx","xxxxxxxx","xxxxxxxxx","xxxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxxx","xxxxxxxxxxxxx","xxxxxxxxxxxxxx","xxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxz","y","yy","yyy","yyyy","yyyyy","yyyyyy","yyyyyyy","yyyyyyyy","yyyyyyyyy","yyyyyyyyyy","yyyyyyyyyyy","yyyyyyyyyyyy","yyyyyyyyyyyyy","yyyyyyyyyyyyyy","yyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy","yyyyyyyyyyyyyyyyyyyyyyyyyyyyyz"
                )
            )
        )
    }

    internal class TargetSUm {
        private val count = 0
        fun findTargetSumWays(nums: IntArray, S: Int): Int {
            val memo = Array(nums.size) { IntArray(2001) }
            for (row in memo) Arrays.fill(row, Int.MIN_VALUE)
            return calculate(nums, 0, 0, S, memo)
        }

        private fun calculate(nums: IntArray, index: Int, sum: Int, target: Int, memo: Array<IntArray>): Int {
            return if (index == nums.size) {
                if (sum == target) {
                    1
                } else {
                    0
                }
            } else {
                if (memo[index][sum + 1000] != Int.MIN_VALUE) {
                    return memo[index][sum + 1000]
                }
                memo[index][sum + 1000] = calculate(nums, index + 1, sum + nums[index], target, memo) +
                        calculate(nums, index + 1, sum - nums[index], target, memo)
                memo[index][sum + 1000]
            }
        }
    }

    fun concurrentNonnegativeIntegerWithoutConsecutiveOne (num: Int): Int {
        val sb = StringBuilder(Integer.toBinaryString(num)).reverse()
        val n = sb.length
        val a = IntArray(n)
        val b = IntArray(n)
        b[0] = 1
        a[0] = b[0]
        for (i in 1 until n) {
            a[i] = a[i - 1] + b[i - 1]
            b[i] = a[i - 1]
        }
        var result = a[n - 1] + b[n - 1]
        for (i in n - 2 downTo 0) {
            if (sb[i] == '1' && sb[i + 1] == '1') break
            if (sb[i] == '0' && sb[i + 1] == '0') result -= b[i]
        }
        return result
    }

    fun racecar(target: Int): Int {
        val queue: Deque<IntArray> = LinkedList()
        queue.offerLast(intArrayOf(0, 1)) // starts from position 0 with speed 1
        val visited: MutableSet<String> = HashSet()
        visited.add(0.toString() + " " + 1)
        var level = 0
        while (!queue.isEmpty()) {
            for (k in queue.size downTo 1) {
                val cur = queue.pollFirst() // cur[0] is position; cur[1] is speed
                if (cur[0] == target) {
                    return level
                }
                var nxt = intArrayOf(cur[0] + cur[1], cur[1] shl 1) // accelerate instruction
                var key = nxt[0].toString() + " " + nxt[1]
                if ((!visited.contains(key) && 0 < nxt[0]) && nxt[0] < (target shl 1)) {
                    queue.offerLast(nxt)
                    visited.add(key)
                }
                nxt = intArrayOf(cur[0], if (cur[1] > 0) -1 else 1) // reverse instruction
                key = nxt[0].toString() + " " + nxt[1]
                if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < target shl 1) {
                    queue.offerLast(nxt)
                    visited.add(key)
                }
            }
            level++
        }
        return -1
    }

    fun pushDominoes(S: String): String? {
        val A = S.toCharArray()
        val N = A.size
        val forces = IntArray(N)

        // Populate forces going from left to right
        var force = 0
        for (i in 0 until N) {
            force = if (A[i] == 'R') N else if (A[i] == 'L') 0 else Math.max(force - 1, 0)
            forces[i] += force
        }

        // Populate forces going from right to left
        force = 0
        for (i in N - 1 downTo 0) {
            force = if (A[i] == 'L') N else if (A[i] == 'R') 0 else Math.max(force - 1, 0)
            forces[i] -= force
        }
        val ans = StringBuilder()
        for (f in forces) ans.append(if (f > 0) 'R' else if (f < 0) 'L' else '.')
        return ans.toString()
    }

    fun minRefuelStops(target: Int, tank: Int, stations: Array<IntArray>): Int {
        // pq is a maxheap of gas station capacities
        var tank = tank
        val pq: PriorityQueue<Int?> = PriorityQueue<Int?>(Collections.reverseOrder())
        var ans = 0
        var prev = 0
        for (station in stations) {
            val location = station[0]
            val capacity = station[1]
            tank -= location - prev
            while (!pq.isEmpty() && tank < 0) {  // must refuel in past
                tank += pq.poll()!!
                ans++
            }
            if (tank < 0) return -1
            pq.offer(capacity)
            prev = location
        }

        // Repeat body for station = (target, inf)
        run {
            tank -= target - prev
            while (!pq.isEmpty() && tank < 0) {
                tank += pq.poll()!!
                ans++
            }
            if (tank < 0) return -1
        }
        return ans
    }

    fun countStairs(current: Int, dp: IntArray): Int {
        if (current == 0) {
            return 1
        }

        if(current<0) return 0

        if(dp[current] != -1) return dp[current]

        dp[current] = countStairs(current - 1, dp) + countStairs(current - 2, dp)
        return dp[current]
    }

}

fun main() {
    val soln = Solution()

//    val nums = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,739,740,741,742,743,744,745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999,1000,1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068,1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118,1119,1120,1121,1122,1123,1124,1125,1126,1127,1128,1129,1130,1131,1132,1133,1134,1135,1136,1137,1138,1139,1140,1141,1142,1143,1144,1145,1146,1147,1148,1149,1150,1151,1152,1153,1154,1155,1156,1157,1158,1159,1160,1161,1162,1163,1164,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182,1183,1184,1185,1186,1187,1188,1189,1190,1191,1192,1193,1194,1195,1196,1197,1198,1199,1200,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1211,1212,1213,1214,1215,1216,1217,1218,1219,1220,1221,1222,1223,1224,1225,1226,1227,1228,1229,1230,1231,1232,1233,1234,1235,1236,1237,1238,1239,1240,1241,1242,1243,1244,1245,1246,1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258,1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1284,1285,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1299,1300,1301,1302,1303,1304,1305,1306,1307,1308,1309,1310,1311,1312,1313,1314,1315,1316,1317,1318,1319,1320,1321,1322,1323,1324,1325,1326,1327,1328,1329,1330,1331,1332,1333,1334,1335,1336,1337,1338,1339,1340,1341,1342,1343,1344,1345,1346,1347,1348,1349,1350,1351,1352,1353,1354,1355,1356,1357,1358,1359,1360,1361,1362,1363,1364,1365,1366,1367,1368,1369,1370,1371,1372,1373,1374,1375,1376,1377,1378,1379,1380,1381,1382,1383,1384,1385,1386,1387,1388,1389,1390,1391,1392,1393,1394,1395,1396,1397,1398,1399,1400,1401,1402,1403,1404,1405,1406,1407,1408,1409,1410,1411,1412,1413,1414,1415,1416,1417,1418,1419,1420,1421,1422,1423,1424,1425,1426,1427,1428,1429,1430,1431,1432,1433,1434,1435,1436,1437,1438,1439,1440,1441,1442,1443,1444,1445,1446,1447,1448,1449,1450,1451,1452,1453,1454,1455,1456,1457,1458,1459,1460,1461,1462,1463,1464,1465,1466,1467,1468,1469,1470,1471,1472,1473,1474,1475,1476,1477,1478,1479,1480,1481,1482,1483,1484,1485,1486,1487,1488,1489,1490,1491,1492,1493,1494,1495,1496,1497,1498,1499,1500,1501,1502,1503,1504,1505,1506,1507,1508,1509,1510,1511,1512,1513,1514,1515,1516,1517,1518,1519,1520,1521,1522,1523,1524,1525,1526,1527,1528,1529,1530,1531,1532,1533,1534,1535,1536,1537,1538,1539,1540,1541,1542,1543,1544,1545,1546,1547,1548,1549,1550,1551,1552,1553,1554,1555,1556,1557,1558,1559,1560,1561,1562,1563,1564,1565,1566,1567,1568,1569,1570,1571,1572,1573,1574,1575,1576,1577,1578,1579,1580,1581,1582,1583,1584,1585,1586,1587,1588,1589,1590,1591,1592,1593,1594,1595,1596,1597,1598,1599,1600,1601,1602,1603,1604,1605,1606,1607,1608,1609,1610,1611,1612,1613,1614,1615,1616,1617,1618,1619,1620,1621,1622,1623,1624,1625,1626,1627,1628,1629,1630,1631,1632,1633,1634,1635,1636,1637,1638,1639,1640,1641,1642,1643,1644,1645,1646,1647,1648,1649,1650,1651,1652,1653,1654,1655,1656,1657,1658,1659,1660,1661,1662,1663,1664,1665,1666,1667,1668,1669,1670,1671,1672,1673,1674,1675,1676,1677,1678,1679,1680,1681,1682,1683,1684,1685,1686,1687,1688,1689,1690,1691,1692,1693,1694,1695,1696,1697,1698,1699,1700,1701,1702,1703,1704,1705,1706,1707,1708,1709,1710,1711,1712,1713,1714,1715,1716,1717,1718,1719,1720,1721,1722,1723,1724,1725,1726,1727,1728,1729,1730,1731,1732,1733,1734,1735,1736,1737,1738,1739,1740,1741,1742,1743,1744,1745,1746,1747,1748,1749,1750,1751,1752,1753,1754,1755,1756,1757,1758,1759,1760,1761,1762,1763,1764,1765,1766,1767,1768,1769,1770,1771,1772,1773,1774,1775,1776,1777,1778,1779,1780,1781,1782,1783,1784,1785,1786,1787,1788,1789,1790,1791,1792,1793,1794,1795,1796,1797,1798,1799,1800,1801,1802,1803,1804,1805,1806,1807,1808,1809,1810,1811,1812,1813,1814,1815,1816,1817,1818,1819,1820,1821,1822,1823,1824,1825,1826,1827,1828,1829,1830,1831,1832,1833,1834,1835,1836,1837,1838,1839,1840,1841,1842,1843,1844,1845,1846,1847,1848,1849,1850,1851,1852,1853,1854,1855,1856,1857,1858,1859,1860,1861,1862,1863,1864,1865,1866,1867,1868,1869,1870,1871,1872,1873,1874,1875,1876,1877,1878,1879,1880,1881,1882,1883,1884,1885,1886,1887,1888,1889,1890,1891,1892,1893,1894,1895,1896,1897,1898,1899,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,2040,2041,2042,2043,2044,2045,2046,2047,2048,2049,2050,2051,2052,2053,2054,2055,2056,2057,2058,2059,2060,2061,2062,2063,2064,2065,2066,2067,2068,2069,2070,2071,2072,2073,2074,2075,2076,2077,2078,2079,2080,2081,2082,2083,2084,2085,2086,2087,2088,2089,2090,2091,2092,2093,2094,2095,2096,2097,2098,2099,2100,2101,2102,2103,2104,2105,2106,2107,2108,2109,2110,2111,2112,2113,2114,2115,2116,2117,2118,2119,2120,2121,2122,2123,2124,2125,2126,2127,2128,2129,2130,2131,2132,2133,2134,2135,2136,2137,2138,2139,2140,2141,2142,2143,2144,2145,2146,2147,2148,2149,2150,2151,2152,2153,2154,2155,2156,2157,2158,2159,2160,2161,2162,2163,2164,2165,2166,2167,2168,2169,2170,2171,2172,2173,2174,2175,2176,2177,2178,2179,2180,2181,2182,2183,2184,2185,2186,2187,2188,2189,2190,2191,2192,2193,2194,2195,2196,2197,2198,2199,2200,2201,2202,2203,2204,2205,2206,2207,2208,2209,2210,2211,2212,2213,2214,2215,2216,2217,2218,2219,2220,2221,2222,2223,2224,2225,2226,2227,2228,2229,2230,2231,2232,2233,2234,2235,2236,2237,2238,2239,2240,2241,2242,2243,2244,2245,2246,2247,2248,2249,2250,2251,2252,2253,2254,2255,2256,2257,2258,2259,2260,2261,2262,2263,2264,2265,2266,2267,2268,2269,2270,2271,2272,2273,2274,2275,2276,2277,2278,2279,2280,2281,2282,2283,2284,2285,2286,2287,2288,2289,2290,2291,2292,2293,2294,2295,2296,2297,2298,2299,2300,2301,2302,2303,2304,2305,2306,2307,2308,2309,2310,2311,2312,2313,2314,2315,2316,2317,2318,2319,2320,2321,2322,2323,2324,2325,2326,2327,2328,2329,2330,2331,2332,2333,2334,2335,2336,2337,2338,2339,2340,2341,2342,2343,2344,2345,2346,2347,2348,2349,2350,2351,2352,2353,2354,2355,2356,2357,2358,2359,2360,2361,2362,2363,2364,2365,2366,2367,2368,2369,2370,2371,2372,2373,2374,2375,2376,2377,2378,2379,2380,2381,2382,2383,2384,2385,2386,2387,2388,2389,2390,2391,2392,2393,2394,2395,2396,2397,2398,2399,2400,2401,2402,2403,2404,2405,2406,2407,2408,2409,2410,2411,2412,2413,2414,2415,2416,2417,2418,2419,2420,2421,2422,2423,2424,2425,2426,2427,2428,2429,2430,2431,2432,2433,2434,2435,2436,2437,2438,2439,2440,2441,2442,2443,2444,2445,2446,2447,2448,2449,2450,2451,2452,2453,2454,2455,2456,2457,2458,2459,2460,2461,2462,2463,2464,2465,2466,2467,2468,2469,2470,2471,2472,2473,2474,2475,2476,2477,2478,2479,2480,2481,2482,2483,2484,2485,2486,2487,2488,2489,2490,2491,2492,2493,2494,2495,2496,2497,2498,2499,2500);
    val nums = arrayOf(0,1,0,3,2,3)
    val dp = IntArray(nums.size)
    dp[nums.size - 1] = 1


//    print( soln.getMax(nums, nums.size - 2, nums.size - 1, dp))
//    print(soln.getMax3(nums,Array(nums.size){IntArray(nums.size){-1}},0, -1))

//    println(soln.coinChangeIterate(arrayOf(2,5),11))

//    soln.longestIncreasingPathRun()
//    soln.getMaxEnvelope()
//        soln.canPartition()
//    soln.getConcatnatedWords()

//    println(soln.concurrentNonnegativeIntegerWithoutConsecutiveOne(5))

    val coin = 3
    val arr = IntArray(coin+1) {-1}
    arr[0] = 1
//    println(soln.countStairs(0,coin, arr))

    for(i in arr.indices) {
        arr[i] = soln.countStairs(i, arr)
    }

    println(arr.last())
}
