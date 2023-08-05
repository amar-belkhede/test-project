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

        fun duplicateFromSortedArray(nums: Array<Int>): Int {
            if (nums.isEmpty()) return 0;
            var i = 0
            var j=1
            for (j in j until nums.size) {
                if (nums[i] != nums[j]) {
                    i++
                    nums[i] = nums[j]
                }
            }

            println(nums.contentToString())

            return i+1;
        }
    }
}

fun main() {
//    println(RemoveElement.at(arrayOf(0,1,3,2,2,4,5,2), 2))
    println(RemoveElement.duplicateFromSortedArray(arrayOf(1,2,2,3,3,3,4,5,6,77)))
}