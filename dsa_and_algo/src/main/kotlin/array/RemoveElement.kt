package array

class RemoveElement {
    companion object {
        fun at(nums: Array<Int>, target: Int): Int {
            var i = 0;
            var n = nums.size
            while (i<n) {
                if (nums[i]==target) {
                    nums[i] = nums[n-1]
                    nums[n-1] = -1 // this line is of no need just to show
                    n--
                } else {
                    i++
                }
            }

            println(nums.contentToString())

            return n;
        }
    }
}

fun main() {
    println(RemoveElement.at(arrayOf(0,1,3,2,2,4,5,2), 2))
}