import java.util.*

fun mergeSort(nums1:Array<Int>,m:Int, nums2: Array<Int>, n:Int): Array<Int> {
    var i = m-1;
    var j = n-1;
    var k = m+n-1;
    while (j >= 0) {
        if (i >= 0 && nums1[i] > nums2[j]) {
            nums1[k--] = nums1[i--]
        } else {
            nums1[k--] = nums2[j--]
        }
    }

    return nums1;
}

fun main() {
    println(mergeSort(arrayOf(1, 2, 3, 0, 0, 0), 3, arrayOf(2, 5, 6), 3).contentToString())
}