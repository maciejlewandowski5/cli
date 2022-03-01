package com.example.bbbbbb.loginactivity

import com.example.bbbbbb.MainComponent
import dagger.Module

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [LoginComponent::class])
class SubcomponentsModule {}

@Module(subcomponents = [MainComponent::class])
class MainModule {}