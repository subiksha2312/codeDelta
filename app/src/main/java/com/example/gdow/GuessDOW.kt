package com.example.gdow

import android.util.Log
import kotlin.random.Random


class GuessDOW {

    private var yr: Int = 0
    private var mth: Int = 0
    private var dt: Int = 0
    private var storeRDOW: Int = -1
    private var nCurrentScore = 0


    fun checkLeapYear(yr: Int): Boolean {
        if (yr % 4 == 0) {
            if (yr % 100 == 0) {
                return yr % 400 == 0
            } else {
                return true
            }
        } else {
            return false
        }

    }

    fun ConvertDatetoDOW(): Int {

        val nMonthIndex = arrayOf<Int>(0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4)
        var nDOW: Int

        if (mth < 3) {
            nDOW =
                (yr - 1 + (yr - 1) / 4 - (yr - 1) / 100 + (yr - 1) / 400 + nMonthIndex[mth - 1] + dt) % 7
        } else {
            nDOW = (yr + yr / 4 - yr / 100 + yr / 400 + nMonthIndex[mth - 1] + dt) % 7
        }
        storeRDOW = nDOW
        return nDOW
    }

    fun getRightDOW(): String {
        val WeekArray = arrayOf<String>(
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        )
        return WeekArray[storeRDOW]


    }


    fun checkanswer(UserInput: String): Boolean {
        Log.d("UserInput:", UserInput)
        Log.d("CorrectDay:", getRightDOW())
        if (UserInput == getRightDOW()) {
            nCurrentScore++
            Log.d("checkanswerscore:",nCurrentScore.toString())

            return true
        } else {
            return false
        }
    }

    fun getscore(): Int {
        Log.d("current score:",nCurrentScore.toString())
        return nCurrentScore
    }


    fun RandomDows(): IntArray {
        val weekarray: IntArray = intArrayOf(0, 1, 2, 3, 4, 5, 6)
        var nOptions = IntArray(4) { 0 }
        var newArray = IntArray(6){0}
        var i: Int = 0
        storeRDOW = ConvertDatetoDOW()
        for(i in 0 until storeRDOW) {
             newArray[i]=weekarray[i]
        }
        for(i in storeRDOW+1..6){
            newArray[i-1]=weekarray[i]
        }
        newArray.shuffle()
        newArray.shuffle()

        nOptions.set(0,storeRDOW)
        nOptions.set(1,newArray[1])
        nOptions.set(2,newArray[2])
        nOptions.set(3,newArray[3])
        /*var j: Int = 0
        Log.d("StoredRDOW", storeRDOW.toString())
        storeRDOW = ConvertDatetoDOW()
        nOptions.set(0, storeRDOW)
        for (i in 1..3) {
            nOptions.set(i, weekarray.random())
//            println(nOptions[i]);
            for (j in 0 until i) {
                while (nOptions[i] == nOptions[j]) {
                    nOptions.set(i, weekarray.random())
                }

            }
        }
        Log.d("BeforeShuffle1", nOptions[0].toString())
        Log.d("BeforeShuffle2", nOptions[1].toString())
        Log.d("BeforeShuffle3", nOptions[2].toString())
        Log.d("BeforeShuffle4", nOptions[3].toString())

         */

        nOptions.shuffle()

        Log.d("afterShuffle1", nOptions[0].toString())
        Log.d("afterShuffle2", nOptions[1].toString())
        Log.d("afterShuffle3", nOptions[2].toString())
        Log.d("afterShuffle4", nOptions[3].toString())

        return nOptions
    }

    fun getDate(): Int {
        return this.dt
    }

    fun getMonth(): Int {
        return this.mth
    }

    fun getYear(): Int {
        return this.yr
    }

    fun DecoratedDate(): String {
        val MonthArray = arrayOf<String>(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        var DecorDate: String = ""
        var DecorMonth: String = ""

        DecorMonth = MonthArray[mth - 1]
        DecorDate = "$DecorMonth $dt, $yr"
        return DecorDate
    }


    fun generateDt() {
        // this function generates a random date between the years 1900 and 2100
        //
        yr = Random.nextInt(1900, 2100)
        mth = Random.nextInt(1, 13)
        if (mth == 1 || mth == 3 || mth == 5 || mth == 7 || mth == 8 || mth == 10 || mth == 12) {
            dt = Random.nextInt(1, 31)
        } else if (mth == 4 || mth == 6 || mth == 9 || mth == 11) {
            dt = Random.nextInt(1, 30)
        } else if (checkLeapYear(yr) == true) {
            dt = Random.nextInt(1, 29)
        } else {
            dt = Random.nextInt(1, 28)
        }

    }

}



