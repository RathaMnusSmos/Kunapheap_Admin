package com.rathalove.kunapheapadmin.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.rathalove.kunapheapadmin.DataModel.MonthSaleData
import com.rathalove.kunapheapadmin.R
import com.rathalove.kunapheapadmin.databinding.DashboardFragmentBinding

class DashboardFragment: Fragment() {

   private lateinit var barChart: BarChart
   private lateinit var pieChart:PieChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: ViewGroup = inflater.inflate(R.layout.dashboard_fragment, container, false) as ViewGroup
//        return super.onCreateView(inflater, container, savedInstanceState)
        return root
    }

    private var month:ArrayList<String> = arrayListOf()
    private var monthSaleDataList:ArrayList<MonthSaleData> = arrayListOf()
    private lateinit var fragmentBinding :DashboardFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = DashboardFragmentBinding.bind(view)
        fragmentBinding = binding

        var barEntries: ArrayList<BarEntry> = arrayListOf()
        var pieEntries: ArrayList<PieEntry> = arrayListOf()

        var pieEntry1 = PieEntry(305f, "Jacket")
        var pieEntry2 = PieEntry(454f, "Jeans")
        var pieEntry3 = PieEntry(795f, "T-Shirt")
        var pieEntry4 = PieEntry(527f, "Trousers")
        var pieEntry5 = PieEntry(821f, "Hoodie")
        pieEntries.add(pieEntry1)
        pieEntries.add(pieEntry2)
        pieEntries.add(pieEntry3)
        pieEntries.add(pieEntry4)
        pieEntries.add(pieEntry5)
        //static data for barchart
        var barEntry1 = BarEntry(1f,4050f,49)
        barEntries.add(barEntry1)
        var barEntry2 = BarEntry(2f,3740f,65)
        barEntries.add(barEntry2)
        var barEntry3 = BarEntry(3f,4645f,29)
        barEntries.add(barEntry3)
        var barEntry4 = BarEntry(4f,7905f,15)
        barEntries.add(barEntry4)
//        var barEntry5 = BarEntry(5f,5050f,49)
//        barEntries.add(barEntry5)
//        var barEntry6 = BarEntry(6f,4740f,05f)
//        barEntries.add(barEntry6)
//        var barEntry7 = BarEntry(7f,4045f,29)
//        barEntries.add(barEntry7)
//        var barEntry8 = BarEntry(8f,5905f,15)
//        barEntries.add(barEntry8)
//        var barEntry9 = BarEntry(9f,6550f,49)
//        barEntries.add(barEntry9)
//        var barEntry10 = BarEntry(10f,4740f,65)
//        barEntries.add(barEntry10)
//        var barEntry11 = BarEntry(11f,5645f,29)
//        barEntries.add(barEntry11)
//        var barEntry12 = BarEntry(12f,6905f,15)
//        barEntries.add(barEntry12)

        month.add("Jan")
        month.add("Feb")
        month.add("Mar")
        month.add("Apr")
        month.add("May")
        month.add("Jun")
        month.add("Jul")
        month.add("Aug")
        month.add("Sep")
        month.add("Oct")
        month.add("Nov")
        month.add("Dec")

        val labels = arrayOf(
            "Dummy", "Jan", "Feb", "March", "April"
        )

        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c)
        }

        for (c in ColorTemplate.LIBERTY_COLORS) {
            colors.add(c)
        }

        for (c in ColorTemplate.JOYFUL_COLORS) {
            colors.add(c)
        }

        for (c in ColorTemplate.COLORFUL_COLORS) {
            colors.add(c)
        }

        for (c in ColorTemplate.PASTEL_COLORS) {
            colors.add(c)
        }

        colors.add(ColorTemplate.getHoloBlue())


        var barDataSet = BarDataSet(barEntries, "Earn Money for 2023")
        barChart = view.findViewById(R.id.bar_chart)
        barChart.data = BarData(barDataSet)
        barDataSet.setColors(colors)
        barDataSet.setDrawValues(false)
        barChart.animateY(4000)
        barChart.description.text = "Earn Money"
        barChart.description.textColor = Color.BLUE
        //set month
        var xAxis:XAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setLabelCount(month.size)
        xAxis.labelRotationAngle = 90f
        barChart.invalidate()

        //for pie chart =====================
        val colorsPie = ArrayList<Int>()
//        for (c in ColorTemplate.VORDIPLOM_COLORS) {
//            colorsPie.add(c)
//        }
//
//        for (c in ColorTemplate.LIBERTY_COLORS) {
//            colorsPie.add(c)
//        }

//        for (c in ColorTemplate.JOYFUL_COLORS) {
//            colorsPie.add(c)
//        }

        for (c in ColorTemplate.COLORFUL_COLORS) {
            colorsPie.add(c)
        }

        for (c in ColorTemplate.PASTEL_COLORS) {
            colorsPie.add(c)
        }

        var pieChartDataSet = PieDataSet(pieEntries, "Popular Category")
        pieChart = view.findViewById(R.id.pieChart)
        pieChart.data = PieData(pieChartDataSet)
        pieChartDataSet.setColors(colorsPie)
        pieChart.animateY(4000)
        pieChart.description.isEnabled = false
        pieChart.centerText = "Popular Categories"
        pieChartDataSet.setDrawValues(true)
        pieChartDataSet.setValueTextColor(Color.BLACK)
        pieChartDataSet.valueTextSize = 15f
        pieChart.invalidate()



    }

}