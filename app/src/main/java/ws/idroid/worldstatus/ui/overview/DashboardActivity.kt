package ws.idroid.worldstatus.ui.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import org.koin.android.viewmodel.ext.android.viewModel
import ws.idroid.worldstatus.R
import ws.idroid.worldstatus.databinding.ActivityDashboardBinding
import ws.idroid.worldstatus.ui.adapter.ItemTypeFactoryImpl
import ws.idroid.worldstatus.ui.adapter.VisitableRecyclerAdapter
import ws.idroid.worldstatus.ui.adapter.viewholders.DailyItem
import ws.idroid.worldstatus.ui.adapter.viewholders.ErrorStateItem
import ws.idroid.worldstatus.ui.adapter.viewholders.OverviewItem
import ws.idroid.worldstatus.ui.adapter.viewholders.TextItem
import ws.idroid.worldstatus.ui.base.BaseActivity
import ws.idroid.worldstatus.ui.base.BaseViewItem
import ws.idroid.worldstatus.ui.dailygraph.DailyGraphActivity
import ws.idroid.worldstatus.ui.detail.DetailActivity
import ws.idroid.worldstatus.util.CaseType
import ws.idroid.worldstatus.util.ext.observe

class DashboardActivity : BaseActivity() {

    private val viewModel by viewModel<DashboardViewModel>()
    private val dailyAdapter by lazy {
        VisitableRecyclerAdapter(
            ItemTypeFactoryImpl(),
            ::onItemClicked
        )
    }

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadDashboard()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        with(binding) {
            recyclerView.adapter = dailyAdapter
            recyclerView.setHasFixedSize(true)

            swipeRefresh.setOnRefreshListener { viewModel.loadDashboard() }
            fab.setOnClickListener { permission(CaseType.FULL) }
        }
    }

    private fun permission(state: Int) {
        permission { DetailActivity.startActivity(this, state) }
    }

    override fun observeChange() {
        observe(viewModel.loading, ::handleLoading)
        observe(viewModel.items, ::onDataLoaded)
        observe(viewModel.toastMessage, ::showSnackbarMessage)
    }

    private fun handleLoading(status: Boolean) {
        binding.swipeRefresh.isRefreshing = status
    }

    private fun onDataLoaded(items: List<BaseViewItem>) {
        dailyAdapter.submitList(items)
    }

    private fun onItemClicked(viewItem: BaseViewItem, view: View) {
        when (viewItem) {
            is OverviewItem -> {
                when (view.id) {
                    R.id.layout_confirmed -> permission(CaseType.CONFIRMED)
                    R.id.layout_recovered -> permission(CaseType.RECOVERED)
                    R.id.layout_death -> permission(CaseType.DEATHS)
                }
            }
            is DailyItem -> {
                Log.e("DailyItem", "DailyItem Click: ${viewItem.deltaConfirmed}")
            }
            is TextItem -> {
                DailyGraphActivity.startActivity(this)
            }
            is ErrorStateItem -> {
                viewModel.loadDashboard()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.feedback_url)))
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}
