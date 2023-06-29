package com.rathalove.kunapheapadmin.Fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rathalove.kunapheapadmin.R
import com.rathalove.kunapheapadmin.databinding.AddproductFragmentBinding
import top.defaults.colorpicker.ColorPickerPopup
import java.io.IOException

class AddProductFragment : Fragment() {

    private lateinit var bitmap: Bitmap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root: ViewGroup = inflater.inflate(R.layout.addproduct_fragment, container, false) as ViewGroup
//        return super.onCreateView(inflater, container, savedInstanceState)
        return root
    }
    private lateinit var fragmentBinding : AddproductFragmentBinding
    private var mColorPreview: View? = null
    private var upload : Boolean = false
    private var hide : Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = AddproductFragmentBinding.bind(view)
        fragmentBinding = binding

        var btn = binding.btnDate
        btn.setOnClickListener{
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our text view.
                    var text = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year).toString()
                    binding.dateEdt.setText(text)

                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }


        //color picker
        var mDefaultColor = 0

        binding.btnColor.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    ColorPickerPopup.Builder(context).initialColor(
                        Color.RED
                    ) // set initial color
                        // of the color
                        // picker dialog
                        .enableBrightness(
                            true
                        ) // enable color brightness
                        // slider or not
                        .enableAlpha(
                            true
                        ) // enable color alpha
                        // changer on slider or
                        // not
                        .okTitle(
                            "OK"
                        ) // this is top right
                        // Choose button
                        .cancelTitle(
                            "Cancel"
                        ) // this is top left
                        // Cancel button which
                        // closes the
                        .showIndicator(
                            true
                        ) // this is the small box
                        // which shows the chosen
                        // color by user at the
                        // bottom of the cancel
                        // button
                        .showValue(
                            true
                        ) // this is the value which
                        // shows the selected
                        // color hex code
                        // the above all values can be made
                        // false to disable them on the
                        // color picker dialog.
                        .build()
                        .show(
                            v,
                            object : ColorPickerPopup.ColorPickerObserver() {
                                override fun onColorPicked(color: Int) {
                                    // set the color
                                    // which is returned
                                    // by the color
                                    // picker
                                    mDefaultColor = color

                                    var textClr = String.format("#%06X", 0xFFFFFF and color)

                                    binding.colorEdt.setText(textClr.toString())

                                    // now as soon as
                                    // the dialog closes
                                    // set the preview
                                    // box to returned
                                    // color
                                    binding.previewSelectedColor.setBackgroundColor(mDefaultColor)
                                }
                            })
                }
            })



        var imgup = binding.imageUpload

        Log.d("imgup", "imgup = $imgup")

        val activityResultLauncher =
            registerForActivityResult<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val data: Intent = result.data!!
                    var uri: Uri? = data!!.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
//                        imgup.setImageBitmap(Bitmap.createScaledBitmap(bitmap,300,400,false))
                        imgup.setImageBitmap(bitmap)
                        hide = true
                        Log.d("hide", "hide = $hide")
                        if (hide == true){
                            binding.uploadBtn.setOnClickListener{
                                Toast.makeText(context, "Upload Success", Toast.LENGTH_SHORT).show()
                                /*

                                   do something

                                *

                                *?
                                 */
                                var tr : FragmentTransaction = requireFragmentManager().beginTransaction()
                                var addproduct = AddProductFragment()
                                tr.replace(R.id.container, addproduct);
                                tr.commit()
                            }
                        }
                    }catch (ex: IOException){
                        ex.printStackTrace()
                    }
                }

            }

        Log.d("hide1", "hide = $hide")

        imgup.setOnClickListener{
            var intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intent)
            Log.d("imgup", "imgup = $intent")

        }
        if (hide == false){
            binding.uploadBtn.setOnClickListener{
                Toast.makeText(context, "Please Upload Image", Toast.LENGTH_SHORT).show()
            }
        }





    }


}