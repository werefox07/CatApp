package com.werefox.app_catlist.internal.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.werefox.app_catlist.R
import com.werefox.app_catlist.di.CatListComponentProvider
import com.werefox.app_catlist.internal.presentation.presenter.CatListPresenter
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_presentation.fragment.MvpBaseFragment
import kotlinx.android.synthetic.main.fragment_catlist.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class CatListFragment : MvpBaseFragment(), CatListView {

    @Inject
    internal lateinit var presenterProvider: Provider<CatListPresenter>

    @InjectPresenter
    internal lateinit var presenter: CatListPresenter

    @ProvidePresenter
    internal fun providePresenter() = presenterProvider.get()

/*    @Inject
    internal lateinit var resourceManager: ResourceManager*/

    private val catItemAdapter = CatItemAdapter(/*resourceManager*/)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as CatListComponentProvider)
            .provideCatListComponent()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    override fun hideLoader() {
        progress.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    private fun initUI(itemView: View) {
        val recyclerCats = itemView.findViewById<RecyclerView>(R.id.recycler_cats)
        recyclerCats.adapter = catItemAdapter
        recyclerCats.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        internal fun newInstance(): CatListFragment {
            val fragment = CatListFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }

    override fun showImages(catEntities: List<CatEntity>) {
        catItemAdapter.update(catEntities)
    }
}