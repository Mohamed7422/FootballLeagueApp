package com.example.footballleagueapp

import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competition_info_model.Season
import com.example.footballleagueapp.data.model.competitions_list_model.Area
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CurrentSeason
import com.example.footballleagueapp.data.model.competitions_list_model.Winner

object FakeData {

    val fakeWinner = Winner(
        address = "123 Fake Street, Faketown",
        clubColors = "Blue / White",
        crest = "https://fakeimageurl.com/crest",
        founded = 1901,
        id = 101,
        lastUpdated = "2023-01-01",
        name = "Fake FC",
        shortName = "FFC",
        tla = "FAK",
        venue = "Fake Stadium",
        website = "https://fakefc.com"
    )

    val fakeCurrentSeason = CurrentSeason(
        currentMatchday = 10,
        endDate = "2024-05-25",
        id = 2023,
        startDate = "2023-08-12",
        winner = fakeWinner
    )

    val fakeArea = Area(
        code = "FAKE",
        flag = "https://fakeimageurl.com/flag",
        id = 1001,
        name = "Fakeland"
    )

    val fakeCompetition = Competition(
        id = 5001,
        area = fakeArea,
        code = "FKL",
        currentSeason = fakeCurrentSeason,
        emblem = "https://fakeimageurl.com/emblem",
        lastUpdated = "2023-01-01",
        name = "Fake League",
        numberOfAvailableSeasons = 5,
        plan = "TIER_ONE",
        type = "LEAGUE"
    )



    // Fake data for Winner
    val fakeWinnerDetails  = com.example.footballleagueapp.data.model.competition_info_model.Winner(
        address = "123 Fake Street, Faketown",
        clubColors = "Blue / White",
        crest = "https://fakeimageurl.com/crest",
        founded = 1901,
        id = 101,
        lastUpdated = "2023-01-01",
        name = "Fake FC",
        shortName = "FFC",
        tla = "FAK",
        venue = "Fake Stadium",
        website = "https://fakefc.com"
    )



    // Fake data for CurrentSeason
    val fakeCurrentSeasonDetails = CurrentSeason(
        currentMatchday = 15,
        endDate = "2024-05-30",
        id = 123,
        startDate = "2023-08-01",
        winner = fakeWinner
    )

    // Fake data for Season
    val fakeSeasons = listOf(
        Season(
            currentMatchday = 15,
            endDate = "2023-05-30",
            id = 123,
            stages = listOf("Group Stage", "Knockout Phase"),
            startDate = "2022-08-01",
            winner = fakeWinnerDetails
        ),
        // You can add more seasons if needed
    )

    // Fake data for Area
    val fakeAreaDetails = Area(
        code = "AREA001",
        flag = "https://example.com/flag.png",
        id = 10,
        name = "Fakeland"
    )

    // Fake data for CompetitionDetailsResponse
    val fakeCompetitionDetailsResponse = CompetitionDetailsResponse(
        id = 10,
        area = fakeAreaDetails,
        code = "FLC",
        currentSeason = fakeCurrentSeasonDetails,
        emblem = "https://example.com/emblem.png",
        lastUpdated = "2024-01-01",
        name = "Fake League Championship",
        seasons = fakeSeasons,
        type = "League"
    )





}