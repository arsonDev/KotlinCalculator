package pl.arsonproject.kalks.ui.simple

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
import kotlinx.android.synthetic.main.fragment_simple.*
import kotlinx.android.synthetic.main.fragment_simple.input
import kotlinx.android.synthetic.main.fragment_simple.textresult
import pl.arsonproject.kalks.R
import pl.arsonproject.kalks.databinding.FragmentSimpleBinding

class SimpleFragment : Fragment() {

    private lateinit var simpleViewModel: SimpleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSimpleBinding>(
                inflater, R.layout.fragment_simple, container, false
            )
        simpleViewModel =
            ViewModelProviders.of(this).get(SimpleViewModel::class.java)
        binding.vm = simpleViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    private fun setUi() {
        backButton?.setOnLongClickListener {
            simpleViewModel.clearAll()
            true
        }
        buttonEqual?.setOnClickListener {
            equal(it)
        }
        simpleViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
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