package com.example.bbbbbb.main

import com.example.bbbbbb.stats.ActivityScope
import dagger.Subcomponent


@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}
