package com.example.tasktracker

//Transforms time from m to mm representation

fun transformTime(time:String):String{
    val regex = Regex("""(\d+)""")
    val matchResult = regex.find(time)
    var minute = ""
    if (matchResult != null) {
        minute = matchResult.groupValues[1].padStart(2, '0')
    }
    return minute
}
