package pl.arsonproject.kalks.ui.advenced

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_advenced.*
import kotlinx.android.synthetic.main.fragment_advenced.input
import kotlinx.android.synthetic.main.fragment_advenced.textresult
import pl.arsonproject.kalks.R
import pl.arsonproject.kalks.databinding.FragmentAdvencedBinding

class AdvencedFragment : Fragment() {

    private lateinit var advencedViewModel : AdvencedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAdvencedBinding>(
            inflater, R.layout.fragment_advenced, container, false
        )
        advencedViewModel =
            ViewModelProviders.of(this).get(AdvencedViewModel::class.java)
        binding.vm = advencedViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearButton.setOnLongClickListener {
            advencedViewModel.clearAll()
            true
        }
        buttonEqual?.setOnClickListener {
            equal(it)
        }
        advencedViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun equal(view: View) {
        textSizeDown(input)
        textSizeUp(textresult)
    }

    private fun textSizeDown(view: TextView) {
        val animator = ValueAnimator.ofFloat(22f, 18F)
        animator.duration = 400
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            view.setTextSize(animatedValue)
        }
        view.textSize = 18F
        animator.start()
    }

    private fun textSizeUp(view: TextView) {
        val animator = ValueAnimator.ofFloat(22f, 32F)
        animator.duration = 400
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            view.setTextSize(animatedValue)
        }
        view.textSize = 32F
        animator.start()
    }
}