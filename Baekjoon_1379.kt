package `2`

import java.util.PriorityQueue

private var classRoomCnt = 0
private val pq = PriorityQueue(comparator())
private lateinit var classroomTime: IntArray
private lateinit var assignedNum: IntArray

private data class ClassInfo(
    val classNum: Int,
    val startTime: Int,
    val endTime: Int
)

private fun comparator(): Comparator<ClassInfo> = Comparator<ClassInfo> { o1, o2 -> o1.startTime - o2.startTime }

fun main() {
    input()
    assign()
    output()
}

private fun input() {
    classRoomCnt = readLine()!!.toInt()

    classroomTime = IntArray(classRoomCnt + 1)
    assignedNum = IntArray(classRoomCnt + 1)

    repeat(classRoomCnt) {
        val (classNum, startTime, endTime) = readLine()!!.split(" ").map { it.toInt() }
        pq.add(ClassInfo(classNum, startTime, endTime))
    }
}

private fun assign() {
    while(pq.isNotEmpty()) {
        val curClass = pq.poll()

        val curClassNum = curClass.classNum
        val curClassStartTime = curClass.startTime
        val curClassEndTime = curClass.endTime

        for(i in 1 .. classRoomCnt) {
            if(classroomTime[i] <= curClassStartTime) {
                classroomTime[i] = curClassEndTime
                assignedNum[curClassNum] = i
                break
            }
        }
    }
}

private fun output() {
    println(classroomTime.count { it != 0 })

    for(i in 1 .. classRoomCnt) {
        println(assignedNum[i])
    }
}