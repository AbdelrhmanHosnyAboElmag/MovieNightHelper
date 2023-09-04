package com.example.movienighthelper.ui.state

import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.CastMovie
import com.example.movienighthelper.data.api.response.Crew

data class ActorAndDircetorState(
    val isLoading: Boolean = false,
    val castActorAndDirector: Pair<List<Cast>, List<Crew>> = Pair(
        listOf(),
        listOf()
    ),
    val error: String = ""
)