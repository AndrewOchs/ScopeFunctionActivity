package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

        Log.d("Function1", "========== Testing getTestDataArray() ==========")
        val testArray1 = getTestDataArray()
        Log.d("Function1", "Generated array: $testArray1")
        Log.d("Function1", "Array size: ${testArray1.size}")
        Log.d("Function1", "Is sorted? ${testArray1 == testArray1.sorted()}")

        val testArray2 = getTestDataArray()
        Log.d("Function1", "Second call: $testArray2")
        Log.d("Function1", "Arrays are different? ${testArray1 != testArray2}")
        Log.d("Function1", "")

        Log.d("Function2", "========== Testing averageLessThanMedian() ==========")

        val testList1 = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val avg1 = testList1.average()
        val median1 = 3.0
        Log.d("Function2", "Test 1: $testList1")
        Log.d("Function2", "Average: $avg1, Median: $median1")
        Log.d("Function2", "Result: ${averageLessThanMedian(testList1)} (Expected: false)")
        Log.d("Function2", "")


        val testList2 = listOf(1.0, 2.0, 100.0)
        val avg2 = testList2.average()
        val median2 = 2.0
        Log.d("Function2", "Test 2: $testList2")
        Log.d("Function2", "Average: $avg2, Median: $median2")
        Log.d("Function2", "Result: ${averageLessThanMedian(testList2)} (Expected: false)")
        Log.d("Function2", "")

        val testList3 = listOf(1.0, 50.0, 51.0)
        val avg3 = testList3.average()
        val median3 = 50.0
        Log.d("Function2", "Test 3: $testList3")
        Log.d("Function2", "Average: $avg3, Median: $median3")
        Log.d("Function2", "Result: ${averageLessThanMedian(testList3)} (Expected: true)")
        Log.d("Function2", "")

        val testList4 = listOf(1.0, 2.0, 3.0, 4.0)
        val avg4 = testList4.average()
        val median4 = 2.5
        Log.d("Function2", "Test 4 (even size): $testList4")
        Log.d("Function2", "Average: $avg4, Median: $median4")
        Log.d("Function2", "Result: ${averageLessThanMedian(testList4)} (Expected: false)")
        Log.d("Function2", "")


        Log.d("Function3", "========== Testing getView() ==========")
        val testCollection = listOf(10, 20, 30, 40, 50)

        val view1 = getView(0, null, testCollection, this)
        Log.d("Function3", "Test 1: Created new view (recycledView = null)")
        Log.d("Function3", "Text: ${(view1 as TextView).text}")
        Log.d("Function3", "Text size: ${view1.textSize}")
        Log.d("Function3", "Padding left: ${view1.paddingLeft}")
        Log.d("Function3", "")

        val view2 = getView(1, view1, testCollection, this)
        Log.d("Function3", "Test 2: Recycled view")
        Log.d("Function3", "Text updated to: ${(view2 as TextView).text}")
        Log.d("Function3", "Same view object? ${view1 === view2}")
        Log.d("Function3", "")

        val view3 = getView(4, view2, testCollection, this)
        Log.d("Function3", "Test 3: Recycled again with position 4")
        Log.d("Function3", "Text: ${(view3 as TextView).text} (Expected: 50)")
        Log.d("Function3", "")

    }




    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        listOfNumbers.average().let { avg ->
            listOfNumbers.sorted().let { sortedList ->
                if (sortedList.size % 2 == 0)
                    (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
                else
                    sortedList[sortedList.size / 2]
            }.let { median ->
                avg < median
            }
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }

}