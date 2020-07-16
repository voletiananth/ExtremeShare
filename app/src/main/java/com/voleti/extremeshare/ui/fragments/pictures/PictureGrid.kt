package com.voleti.extremeshare.ui.fragments.pictures

    import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.BR
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.base.DynamicFragment
import com.voleti.extremeshare.ui.model.Empty
import com.voleti.extremeshare.ui.model.PictureGridHeader
import com.voleti.extremeshare.ui.utils.isLandscape
import com.voleti.extremeshare.ui.viewmodel.MainViewModel

class PictureGrid:DynamicFragment() {
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.mediaRepository.pictureGrid.dynamicData.observe(viewLifecycleOwner){
            dynamicData.apply {
                clear()
                addAll(it)
                add(Empty())
            }

        }
        viewModel.mediaRepository.pictureGrid.baseData.observe(viewLifecycleOwner){
            baseData.apply {
                clear()
                addAll(it)
                add(Empty())

            }
        }
    }


    private fun headerOnClick(item: PictureGridHeader) = View.OnClickListener {
        changeCurrentView(item.position)
    }



    override fun baseBind(viewBinding: ViewDataBinding, position: Int) {
        when(baseData[position].type){
            contentType->{
               viewBinding.setVariable(BR.imageData,baseData[position])

            }
            headerType->{
                viewBinding.setVariable(BR.header,baseData[position])
                viewBinding.setVariable(BR.handler,headerOnClick(baseData[position] as PictureGridHeader))
            }


        }
    }

    override fun dynamicBind(viewBinding: ViewDataBinding, position: Int) {
            when(dynamicData[position].type){
                contentType -> {
                    viewBinding.setVariable(BR.header,dynamicData[position])
                    viewBinding.setVariable(BR.handler,headerOnClick(dynamicData[position] as PictureGridHeader))
                }
            }
    }

    override fun baseViewType(position: Int): Int = when(baseData[position].type){
        contentType->R.layout.item_view_image_grid_content
        headerType->R.layout.item_view_header
        else->R.layout.item_view_empty

    }



    override fun dynamicViewType(position: Int): Int = when(dynamicData[position].type){
        contentType-> R.layout.item_view_header
        else->R.layout.item_view_empty
    }


    private fun getOptimizedGrid():Int{
        return if (isLandscape()) 5 else 3
    }



    override val baseLayoutManager: RecyclerView.LayoutManager
        get() = GridLayoutManager(requireContext(),getOptimizedGrid()).apply {
            spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int = if (baseData[position].type == headerType || baseData[position].type == emptyType) spanCount else 1

            }
        }


    override val dynamicLayoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(requireContext())

}