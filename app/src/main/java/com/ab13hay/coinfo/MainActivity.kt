package com.ab13hay.coinfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ab13hay.coinfo.api.repository.RepositoryMainActivity
import ir.mahozad.android.PieChart
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val r = RepositoryMainActivity().userLogin()
        r.observe(this, Observer {
            val response = JSONObject(it)
            val r = JSONObject(response.optString("data"))
            val s = JSONArray(r.optString("unofficial-summary"))
            val t  = s.getJSONObject(0)
            val recovered = t.optString("recovered")
            val total = t.optString("total")
            val death = t.optString("deaths")
            val active = t.optString("active")
            val sum = recovered + total + death + active
            val pieChart = findViewById<PieChart>(R.id.pieChart)
//            pieChart.slices = listOf(
//                PieChart.Slice(percent(total.toInt(),sum.toInt()), Color.BLUE),
//                PieChart.Slice(percent(recovered.toInt(),sum.toInt()), Color.MAGENTA),
//                PieChart.Slice(percent(death.toInt(),sum.toInt()), Color.YELLOW),
//                PieChart.Slice(percent(active.toInt(),sum.toInt()), Color.CYAN)
//            )
            pieChart.apply {
                slices = listOf(
                    PieChart.Slice(
                       0.2f,
                        Color.rgb(120, 181, 0),
                        Color.rgb(149, 224, 0),
                        legend = "Legend A",
                        label = "Label A",
                        labelColor = Color.BLACK
                    ),
                    PieChart.Slice(
                        0.2f,
                        Color.rgb(204, 168, 0),
                        Color.rgb(249, 228, 0),
                        legend = "Legend B",
                        label = "Label B",
                        labelColor = Color.BLACK
                    ),
                    PieChart.Slice(
                        0.2f,
                        Color.rgb(0, 162, 216),
                        Color.rgb(31, 199, 255),
                        legend = "Legend C",
                        label = "Label C",
                        labelColor = Color.BLACK
                    ),
                    PieChart.Slice(
                        0.1f,
                        Color.rgb(255, 4, 4),
                        Color.rgb(255, 72, 86),
                        legend = "Legend D",
                        label = "Label D",
                        labelColor = Color.BLACK
                    ),
                    PieChart.Slice(
                        0.3f,
                        Color.rgb(160, 165, 170),
                        Color.rgb(175, 180, 185),
                        legend = "Legend E",
                        label = "Last Label",
                        labelColor = Color.BLACK
                    )
                )
                labelIconsTint = Color.rgb(136, 101, 206)
                startAngle = -90
                isLegendEnabled = false
                labelType = PieChart.LabelType.OUTSIDE
                labelIconsPlacement = PieChart.IconPlacement.TOP
                gradientType = PieChart.GradientType.SWEEP
                holeRatio = 0f
                overlayRatio = 0.75f
            }
        })
    }
    fun percent(number:Int,sum:Int):Float{
        val n = ((number.toFloat()/sum.toFloat())*100).toFloat()
        return n
    }
}