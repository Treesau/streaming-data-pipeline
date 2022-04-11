package com.labs1904.hwe.exercises

object StretchProblems {

  /*
  Checks if a string is palindrome.
 */
  def isPalindrome(s: String): Boolean = {
    s == s.reverse
  }

  /*
For a given number, return the next largest number that can be created by rearranging that number's digits.
If no larger number can be created, return -1
 */
  def getNextBiggestNumber(i: Integer): Int = {
    //TODO: Implement me!
    val digits = i.toString
    val last = digits.charAt(digits.size - 1).toString
    if (digits.sorted.reverse == digits) {
      return -1
    } else if (digits.sorted == digits) {
      val secondToLast = digits.charAt(digits.size - 2).toString
      return (digits.substring(0, (digits.size - 3)) + last + secondToLast).toInt
    } else {
      var i = digits.size - 1
      digits.reverse.foreach{ c =>
        i = i - 1
        if (c.toInt > digits.charAt(i).toInt) {
          return (digits.substring(0,i-1) + c + digits.charAt(i)).toInt
        }
      }
  }

}
